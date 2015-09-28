package com.hy.onlinemonitor.view.Activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.hy.onlinemonitor.R;
import com.hy.onlinemonitor.presenter.UserPresenter;
import com.hy.onlinemonitor.utile.ActivityCollector;
import com.hy.onlinemonitor.view.InitView;

import java.util.List;

/**
 * Created by wsw on 2015/7/11.
 */
public class TypeSelectionActivity extends AppCompatActivity implements InitView {

    private CardView fire_card, break_card, auto_card, normal_card;
    private TextView fire_tv, break_tv, auto_tv, normal_tv;
    private ImageView fire_iv, break_iv, auto_iv, normal_iv;
    private Toolbar toolbar;
    private List<String> ownedEquipmentList;
    private UserPresenter userPresenter;

    public void setOwnedEquipmentList(List<String> ownedEquipmentList) {
        this.ownedEquipmentList = ownedEquipmentList;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ActivityCollector.addActivity(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type_selection);
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
    public void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivity(this);
        this.userPresenter.destroy();
    }

    @Override
    public void setupUI() {
        fire_card = (CardView) findViewById(R.id.fire_alarm_cardview);
        break_card = (CardView) findViewById(R.id.break_alarm_cardview);
        auto_card = (CardView) findViewById(R.id.autoplane_alarm_cardview);
        normal_card = (CardView) findViewById(R.id.normal_alarm_cardview);

        fire_tv = (TextView) fire_card.findViewById(R.id.list_item_title);
        fire_iv = (ImageView) fire_card.findViewById(R.id.list_item_image);
        break_tv = (TextView) break_card.findViewById(R.id.list_item_title);
        break_iv = (ImageView) break_card.findViewById(R.id.list_item_image);
        auto_tv = (TextView) auto_card.findViewById(R.id.list_item_title);
        auto_iv = (ImageView) auto_card.findViewById(R.id.list_item_image);
        normal_tv = (TextView) normal_card.findViewById(R.id.list_item_title);
        normal_iv = (ImageView) normal_card.findViewById(R.id.list_item_image);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        userPresenter = new UserPresenter();
        userPresenter.setTypeSelectionActivity(this);
        userPresenter.getUserEquipmentList(this);
    }

    @Override
    public void initialize() {
        fire_tv.setText(R.string.the_fire);
        break_tv.setText(R.string.the_break);
        auto_tv.setText(R.string.the_auto);
        normal_tv.setText(R.string.the_normal);

        fire_iv.setImageDrawable(getResources().getDrawable(R.drawable.fire));
        break_iv.setImageDrawable(getResources().getDrawable(R.drawable.break_picture));
        auto_iv.setImageDrawable(getResources().getDrawable(R.drawable.autoplane));
        normal_iv.setImageDrawable(getResources().getDrawable(R.drawable.normal));

//        Picasso.with(this).load(R.drawable.fire).into(fire_iv);
//        Picasso.with(this).load(R.drawable.break_picture).into(break_iv);
//        Picasso.with(this).load(R.drawable.autoplane).into(auto_iv);
//        Picasso.with(this).load(R.drawable.normal).into(normal_iv);
        toolbar.setTitle(R.string.equipment_type);
        toolbar.setSubtitle(R.string.please_choice);
        setSupportActionBar(toolbar);

        fire_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userPresenter.setCurrentPorject(0, TypeSelectionActivity.this, "fire");
            }
        });
        break_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userPresenter.setCurrentPorject(1, TypeSelectionActivity.this, "break");
            }
        });

        normal_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userPresenter.setCurrentPorject(2, TypeSelectionActivity.this, "video");
            }
        });
        auto_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userPresenter.setCurrentPorject(3, TypeSelectionActivity.this, "uav");
            }
        });

        for (String equipment : ownedEquipmentList) {
            switch (equipment) {
                case "fire":
                    fire_card.setVisibility(View.VISIBLE);
                    break;
                case "break":
                    break_card.setVisibility(View.VISIBLE);
                    break;
                case "video":
                    normal_card.setVisibility(View.VISIBLE);
                    break;
                case "uav":
                    auto_card.setVisibility(View.VISIBLE);
                    break;
            }
        }
//        if (ownedEquipmentList.size() == 1) {
//            String type = ownedEquipmentList.get(0);
//            switch (type) {
//                case "fire":
//                    userPresenter.setCurrentPorject(0, TypeSelectionActivity.this, type);
//                    break;
//                case "break":
//                    userPresenter.setCurrentPorject(1, TypeSelectionActivity.this, type);
//                    break;
//                case "video":
//                    userPresenter.setCurrentPorject(2, TypeSelectionActivity.this, type);
//                    break;
//                case "uav":
//                    userPresenter.setCurrentPorject(3, TypeSelectionActivity.this, type);
//                    break;
//            }
//        }
    }
}
