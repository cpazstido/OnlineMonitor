package com.hy.onlinemonitor.view.Activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.GravityEnum;
import com.afollestad.materialdialogs.MaterialDialog;
import com.google.gson.Gson;
import com.hy.data.utile.SystemRestClient;
import com.hy.onlinemonitor.MyApplication;
import com.hy.onlinemonitor.R;
import com.hy.onlinemonitor.bean.AppInfo;
import com.hy.onlinemonitor.presenter.LoginPresenter;
import com.hy.onlinemonitor.utile.ActivityCollector;
import com.hy.onlinemonitor.utile.GetLoading;
import com.hy.onlinemonitor.utile.ShowUtile;
import com.hy.onlinemonitor.view.JumpView;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.FileAsyncHttpResponseHandler;
import com.rey.material.widget.Button;
import com.rey.material.widget.CheckBox;
import com.rey.material.widget.SnackBar;

import org.apache.http.Header;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import butterknife.Bind;
import butterknife.ButterKnife;


public class LoginActivity extends AppCompatActivity implements JumpView {

    private static final String TAG = "LOGIN";
    private SharedPreferences.Editor editor;
    private AsyncHttpClient client;
    private Handler handler = null;
    private MaterialDialog downDialog = null;
    private AlertDialog upDataDialog;
    private MaterialDialog dialogPro;
    private File apk = null;
    private View positiveAction;
    @Bind(R.id.remember_password_check)
    CheckBox rememberPasswordCheck;
    @Bind(R.id.auto_login_check)
    CheckBox autoLoginCheck;
    private LoginPresenter loginPresenter;
    private AlertDialog LoginAlert;
    @Bind(R.id.login_account)
    EditText loginAccount;
    @Bind(R.id.login_pwd)
    EditText loginPwd;
    @Bind(R.id.login_btn)
    Button loginBtn;
    @Bind(R.id.titles)
    TextView titles;

    private int serverVersion;
    private int localVersion;
    private long appSize;

    private boolean goActivity = false;
    private boolean autoLoginFlag = false;
    private String userName;
    private String userPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ActivityCollector.addActivity(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        titles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TestActivity.StartTestView(LoginActivity.this);
            }
        });

        initPresenter();
        LoginAlert = GetLoading.getDialog(LoginActivity.this, "登录中");
        initSP();

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkVersion();
                autoLoginFlag = false;
                if (rememberPasswordCheck.isChecked() && !autoLoginCheck.isChecked()) {
                    editor.putString("rememberPassword", "true");
                    editor.putString("userName", loginAccount.getText().toString());
                    editor.putString("userPassword", loginPwd.getText().toString());
                } else if (autoLoginCheck.isChecked() && rememberPasswordCheck.isChecked()) {
                    editor.putString("rememberPassword", "true");
                    editor.putString("autoLogin", "true");
                    editor.putString("userName", loginAccount.getText().toString());
                    editor.putString("userPassword", loginPwd.getText().toString());
                }
                editor.apply();

            }
        });

        rememberPasswordCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!isChecked) {
                    editor.putString("rememberPassword", "false");
                    editor.putString("userName", "");
                    editor.putString("userPassword", "");
                }
                editor.apply();
            }
        });

        autoLoginCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    if (!rememberPasswordCheck.isChecked()) {
                        rememberPasswordCheck.setChecked(true);
                    }
                } else {
                    editor.putString("autoLogin", "false");
                }
                editor.apply();
            }
        });

    }

    private void goTypeView() {
        if (goActivity) {
            Log.e("go", "go");
            loginPresenter.initialize(loginAccount.getText().toString(), loginPwd.getText().toString());
            goActivity = false;
        }
    }

    private void checkVersion() {
        upDataDialog = GetLoading.getDialog(LoginActivity.this, "检查版本更新中");
        upDataDialog.show();
        Log.e("checkVersion", "localVersion:" + MyApplication.localVersion + "-serverVersion:" + MyApplication.serverVersion);
        SystemRestClient.get("/checkUpdate", null, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    String response = new String(responseBody, "UTF-8");
                    Log.d("MyApplication", "response:" + response);
                    if (response.contains("资源已经被移除或不存在")) {
                        Log.e("contains", "访问升级服务失败");
                        goActivity = true;
                        upDataDialog.cancel();
                        serverVersion = 0;
                        appSize = 0;
                        if (autoLoginFlag)
                            loginPresenter.initialize(loginAccount.getText().toString(), loginPwd.getText().toString());
                        else {
                            LoginActivity.this.goTypeView();
                        }
                    } else if (response.isEmpty()) {
                        Log.e("isEmpty", "访问升级服务失败");
                        goActivity = true;
                        upDataDialog.cancel();
                        serverVersion = 0;
                        appSize = 0;
                        if (autoLoginFlag)
                            loginPresenter.initialize(loginAccount.getText().toString(), loginPwd.getText().toString());
                        else {
                            LoginActivity.this.goTypeView();
                        }
                    } else if ("null".equals(response)) {
                        Log.e("null", "访问升级服务失败");
                        goActivity = true;
                        upDataDialog.cancel();
                        serverVersion = 0;
                        appSize = 0;
                        if (autoLoginFlag)
                            loginPresenter.initialize(loginAccount.getText().toString(), loginPwd.getText().toString());
                        else {
                            LoginActivity.this.goTypeView();
                        }
                    } else {
                        upDataDialog.cancel();
                        Gson gson = new Gson();
                        AppInfo appInfo = gson.fromJson(response, AppInfo.class);
                        String getServerVersion = appInfo.getServerVersion();
                        Log.e("getServerVersion", "---" + getServerVersion.replace(".apk", "").replace("OM_", "") + "---");
                        serverVersion = Integer.parseInt(getServerVersion.replace(".apk", "").replace("OM_", ""));
                        appSize = appInfo.getSize();
                        MyApplication.setAppSize(appSize);
                        MyApplication.setServerVersion(serverVersion);
                        try {
                            PackageInfo packageInfo = getApplicationContext()
                                    .getPackageManager().getPackageInfo(getPackageName(), 0);
                            localVersion = packageInfo.versionCode;
                            MyApplication.setLocalVersion(localVersion);
                        } catch (PackageManager.NameNotFoundException e) {
                            e.printStackTrace();
                        }

                        if (MyApplication.localVersion < MyApplication.serverVersion) {
                            String upDataInfo = "发现新版本,建议在wifi环境下更新\n";
                            upDataInfo += "安装包大小:" + MyApplication.appSize + "kb\n";
                            upDataInfo += "版本号:" + MyApplication.serverVersion;
                            new MaterialDialog.Builder(LoginActivity.this)
                                    .content(upDataInfo)
                                    .positiveText(R.string.downloads)
                                    .negativeText("下次升级")
                                    .callback(new MaterialDialog.ButtonCallback() {
                                        @Override
                                        public void onNegative(MaterialDialog dialog) {
                                            super.onNegative(dialog);
                                            goActivity = false;
                                            loginPresenter.initialize(loginAccount.getText().toString(), loginPwd.getText().toString());
                                        }

                                        @Override
                                        public void onPositive(final MaterialDialog dialog) {
                                            super.onPositive(dialog);
                                            //新建一个下载对话框
                                            downApk();
                                            downDialog = new MaterialDialog.Builder(LoginActivity.this)
                                                    .title(R.string.download_updata)
                                                    .content(R.string.downloading)
                                                    .contentGravity(GravityEnum.CENTER)
                                                    .positiveText(R.string.install)
                                                    .progress(false, 100, true)
                                                    .callback(new MaterialDialog.ButtonCallback() {
                                                        @Override
                                                        public void onPositive(MaterialDialog dialog) {
                                                            installApk(apk);
                                                        }
                                                    })
                                                    .showListener(new DialogInterface.OnShowListener() {
                                                        @Override
                                                        public void onShow(DialogInterface dialogInterface) {
                                                            if (dialogPro != null) {
                                                                dialogPro = null;
                                                            }
                                                            dialogPro = (MaterialDialog) dialogInterface;
                                                            handler = new Handler() {
                                                                @Override
                                                                public void handleMessage(Message msg) {
                                                                    super.handleMessage(msg);
                                                                    if (msg.what == 1) {
                                                                        float getDatas = msg.getData().getLong("progress");
                                                                        long totle = MyApplication.appSize;
                                                                        float count = ((getDatas / totle)) * 100;
                                                                        dialogPro.incrementProgress((int) (count - dialogPro.getCurrentProgress()));
                                                                        if (getDatas == totle) {
                                                                            downDialog.setContent("下载成功");
                                                                        }
                                                                    }
                                                                }
                                                            };
                                                        }
                                                    }).build();
                                            downDialog.setCancelable(false);
                                            downDialog.setCanceledOnTouchOutside(false);
                                            positiveAction = downDialog.getActionButton(DialogAction.POSITIVE);
                                            positiveAction.setEnabled(false);
                                            downDialog.show();
                                        }
                                    })
                                    .show();
                        } else {//版本符合要求
                            autoLoginFlag = true;
                            goActivity = true;
                            goTypeView();
                        }
                    }
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.e("application", "error");
                ShowUtile.toastShow(LoginActivity.this, "访问升级服务器失败");
                upDataDialog.cancel();
                serverVersion = 0;
                appSize = 0;
            }
        });

    }

    private void downApk() {
        File targFile;
        try {
            if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                File sdCard = Environment.getExternalStorageDirectory();
                targFile = new File(sdCard.getCanonicalPath(), "OnlineMonitor.apk");
                Log.e("haveSd", targFile.getPath());
            } else {
                targFile = new File(getFilesDir().getPath(), "OnlineMonitor.apk");
                Log.e("noSd", targFile.getPath());
            }
            client = SystemRestClient.getClient();
            SystemRestClient.get("/downLoadApp", new FileAsyncHttpResponseHandler(targFile) {
                @Override
                public void onFailure(int statusCode, Header[] headers, Throwable throwable, File file) {
                    Log.e("onFailure", "onFailure");
                    downDialog.cancel();
                }

                @Override
                public void onSuccess(int statusCode, Header[] headers, File response) {
                    Log.e("onSuccess", "onSuccess");
                    apk = response;
                    positiveAction.setEnabled(true);
                }

                @Override
                public void onProgress(long bytesWritten, long totalSize) {
                    super.onProgress(bytesWritten, totalSize);
                    Message message = new Message();
                    Bundle bundle = new Bundle();
                    bundle.putLong("progress", bytesWritten);
                    message.setData(bundle);
                    message.what = 1;
                    handler.sendMessage(message);

                }
            });

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void installApk(File file) {
        Intent intent = new Intent();
        //执行动作
        intent.setAction(Intent.ACTION_VIEW);
        //执行的数据类型
        intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
        startActivity(intent);
    }

    private void initSP() {
        SharedPreferences sharedPreferences = getSharedPreferences("user", MODE_PRIVATE);
        editor = sharedPreferences.edit();

        String rememberPassword = sharedPreferences.getString("rememberPassword", "");
        String autoLogin = sharedPreferences.getString("autoLogin", "");

        if (autoLogin.equals("true") && rememberPassword.equals("true")) {
            autoLoginFlag = true;
            userName = sharedPreferences.getString("userName", "");
            userPassword = sharedPreferences.getString("userPassword", "");
            loginAccount.setText(userName);
            loginPwd.setText(userPassword);
            autoLoginCheck.setChecked(true);
            rememberPasswordCheck.setChecked(true);
            checkVersion();
        } else if (rememberPassword.equals("true") && !autoLogin.equals("true")) {
            userName = sharedPreferences.getString("userName", "");
            userPassword = sharedPreferences.getString("userPassword", "");
            loginAccount.setText(userName);
            loginPwd.setText(userPassword);
            rememberPasswordCheck.setChecked(true);
            autoLoginCheck.setChecked(false);
        }

    }

    private void initPresenter() {
        loginPresenter = new LoginPresenter();
        loginPresenter.setView(this);
    }

    @Override
    public void showLoading() {
        LoginAlert.show();
        Log.e("Loading", "show Loading");
    }

    @Override
    public void hideLoading() {
        Log.e("Loading", "hide Loading");
        LoginAlert.cancel();
    }

    @Override
    public void showError(String message) {
        new SnackBar(LoginActivity.this).text("出现错误").show();
    }

    @Override
    public Context getContext() {
        return LoginActivity.this;
    }

    @Override
    public void goToView() {
        Intent intent = new Intent(LoginActivity.this, TypeSelectionActivity.class);
        startActivity(intent);
    }

    @Override
    public void onResume() {
        super.onResume();
        this.loginPresenter.resume();
    }

    @Override
    public void onPause() {
        super.onPause();
        this.loginPresenter.pause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivity(this);
        this.loginPresenter.destroy();
        ButterKnife.unbind(this);
    }

    public static void goLoginView(Context mContext) {
        ActivityCollector.finishAll();
        Intent intent = new Intent(mContext, LoginActivity.class);
        mContext.startActivity(intent);

    }


}
