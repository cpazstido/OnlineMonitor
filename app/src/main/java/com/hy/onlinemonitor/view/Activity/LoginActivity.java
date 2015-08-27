package com.hy.onlinemonitor.view.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.hy.onlinemonitor.R;
import com.hy.onlinemonitor.presenter.LoginPresenter;
import com.hy.onlinemonitor.utile.GetLoading;
import com.hy.onlinemonitor.view.JumpView;
import com.rey.material.widget.Button;
import com.rey.material.widget.SnackBar;

import butterknife.Bind;
import butterknife.ButterKnife;


public class LoginActivity extends AppCompatActivity implements JumpView {

    private static final String TAG = "LOGIN";
    private LoginPresenter loginPresenter;
    private AlertDialog LoginAlert;
    @Bind(R.id.login_account)
    EditText loginAccount;
    @Bind(R.id.login_pwd)
    EditText loginPwd;
    @Bind(R.id.login_btn)
    Button loginBtn;
    @Bind(R.id.rest_btn)
    Button restBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        initPresenter();
        LoginAlert = GetLoading.getDialog(LoginActivity.this, "登录中");
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginPresenter.initialize(loginAccount.getText().toString(), loginPwd.getText().toString());
            }
        });

        restBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginAccount.setText("");
                loginPwd.setText("");
                startActivity(new Intent(LoginActivity.this, TestActivity.class));
            }
        });
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
        Intent intent = new Intent(LoginActivity.this,TypeSelectionActivity.class);
        startActivity(intent);
    }

    @Override public void onResume() {
        super.onResume();
        this.loginPresenter.resume();
    }

    @Override public void onPause() {
        super.onPause();
        this.loginPresenter.pause();
    }

    @Override public void onDestroy() {
        super.onDestroy();
        this.loginPresenter.destroy();
        ButterKnife.unbind(this);
    }

}
