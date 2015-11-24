package com.hy.onlinemonitor.view.Activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.Toast;

import com.hy.onlinemonitor.R;
import com.hy.onlinemonitor.presenter.UserPresenter;
import com.hy.onlinemonitor.utile.ActivityCollector;
import com.hy.onlinemonitor.utile.GetLoading;
import com.hy.onlinemonitor.view.Adapter.TypeSelectAdapter;
import com.hy.onlinemonitor.view.InitView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by wsw on 2015/7/11.
 */
public class TypeSelectionActivity extends AppCompatActivity implements InitView {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.type_select)
    RecyclerView typeSelect;
    private List<String> ownedEquipmentList;
    private TypeSelectAdapter typeSelectAdapter;
    private UserPresenter userPresenter;
    private AlertDialog alertDialog;

    public void setOwnedEquipmentList(List<String> ownedEquipmentList) {
        this.ownedEquipmentList = ownedEquipmentList;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ActivityCollector.addActivity(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type_selection);
        ButterKnife.bind(this);
        setupUI();
    }

    public void GotoActivity() {
        Log.e("TypeSelectionActivity", "GotoActivity");
        Intent intent = new Intent(TypeSelectionActivity.this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void onResume() {
        super.onResume();
        this.userPresenter.resume();
    }

    @Override
    public void onPause() {
        super.onPause();
        this.userPresenter.pause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        this.userPresenter.stop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivity(this);
        this.userPresenter.destroy();
    }

    @Override
    public void setupUI() {
        alertDialog = GetLoading.getDialog(TypeSelectionActivity.this, "加载中..");
        userPresenter = new UserPresenter();
        userPresenter.setTypeSelectionActivity(this);
        userPresenter.getUserEquipmentList(this);
    }

    @Override
    public void initialize() {
        typeSelect.setLayoutManager(new LinearLayoutManager(this));
        typeSelect.setHasFixedSize(true);
        typeSelectAdapter = new TypeSelectAdapter(TypeSelectionActivity.this,ownedEquipmentList,userPresenter);

        typeSelect.setAdapter(typeSelectAdapter);

        toolbar.setTitle(R.string.equipment_type);
        toolbar.setSubtitle(R.string.please_choice);
        setSupportActionBar(toolbar);
    }

    long mExitTime = 0;
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if ((System.currentTimeMillis() - mExitTime) > 2000) {
                Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                mExitTime = System.currentTimeMillis();
            } else {
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    public void showLoading() {
        alertDialog.show();
    }

    public void hideLoading() {
        alertDialog.hide();
    }
}
