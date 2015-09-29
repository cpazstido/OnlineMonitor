package com.hy.onlinemonitor.view.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;

import com.afollestad.materialdialogs.MaterialDialog;
import com.hy.onlinemonitor.MyApplication;
import com.hy.onlinemonitor.R;
import com.hy.onlinemonitor.presenter.LoginPresenter;
import com.hy.onlinemonitor.utile.ActivityCollector;
import com.hy.onlinemonitor.utile.GetLoading;
import com.hy.onlinemonitor.view.JumpView;
import com.rey.material.widget.Button;
import com.rey.material.widget.CheckBox;
import com.rey.material.widget.SnackBar;

import butterknife.Bind;
import butterknife.ButterKnife;


public class LoginActivity extends AppCompatActivity implements JumpView {

    private static final String TAG = "LOGIN";
    private SharedPreferences.Editor editor;
    private MyApplication myApplication;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ActivityCollector.addActivity(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        initPresenter();
        LoginAlert = GetLoading.getDialog(LoginActivity.this, "录中");
        initSP();

        checkVersion();
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
                loginPresenter.initialize(loginAccount.getText().toString(), loginPwd.getText().toString());
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

    private void checkVersion() {
        if (MyApplication.localVersion < MyApplication.serverVersion) {
            String content = "发现新版本,建议在wifi环境下更新/n";
            content += "安装包大小:"+MyApplication.appSize;
            MaterialDialog dialog = new MaterialDialog.Builder(LoginActivity.this)
                    .title(R.string.up_data) //对话框标题
                    .content(content)
                    .positiveText(R.string.downloads) //positive按钮文字
                    .negativeText(R.string.cancels)//negative按钮文字
                    .callback(new MaterialDialog.ButtonCallback() { //回调函数
                        @Override
                        public void onPositive(MaterialDialog dialog) {
                            super.onPositive(dialog);//显示下载对话框
                        }

                    })
                    .show();
        }

    }

    private void initSP() {
        SharedPreferences sharedPreferences = getSharedPreferences("user", MODE_PRIVATE);
        editor = sharedPreferences.edit();

        String rememberPassword = sharedPreferences.getString("rememberPassword", "");
        String autoLogin = sharedPreferences.getString("autoLogin", "");
        String userName;
        String userPassword;

        if (autoLogin.equals("true") && rememberPassword.equals("true")) {
            userName = sharedPreferences.getString("userName", "");
            userPassword = sharedPreferences.getString("userPassword", "");
            loginAccount.setText(userName);
            loginPwd.setText(userPassword);
            loginPresenter.initialize(userName, userPassword);
            autoLoginCheck.setChecked(true);
            rememberPasswordCheck.setChecked(true);
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
    public void GoToView() {
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
        if (loginPresenter != null)
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

        Intent intent = new Intent(mContext, LoginActivity.class);
        mContext.startActivity(intent);

    }


}
