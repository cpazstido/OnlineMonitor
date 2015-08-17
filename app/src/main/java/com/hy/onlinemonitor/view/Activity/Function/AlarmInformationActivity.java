package com.hy.onlinemonitor.view.Activity.Function;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.widget.Toolbar;
import android.view.ViewGroup;

import com.github.florent37.materialviewpager.MaterialViewPager;
import com.github.florent37.materialviewpager.header.HeaderDesign;
import com.hy.onlinemonitor.R;
import com.hy.onlinemonitor.data.TypeDef;
import com.hy.onlinemonitor.view.Activity.BaseActivity;
import com.hy.onlinemonitor.view.Component.RecyclerViewFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wsw on 2015/7/13.
 */
public class AlarmInformationActivity extends BaseActivity {
    private MaterialViewPager mViewPager;
    private List<String> alarmTitles;

    @Override
    protected Toolbar getToolbar() {
        mViewPager = (MaterialViewPager) findViewById(R.id.materialViewPager);
        Toolbar toolbar =  mViewPager.getToolbar();
        toolbar.setTitle("");
        return toolbar;
    }

    @Override
    protected void setOwnContentView() {
        setContentView(R.layout.activity_alarm);
    }

    @Override
    public void setupUI() {
        int selectedType = this.getUser().getSelectionType();
        alarmTitles = new ArrayList<>();
        switch (selectedType) {
            case 0://山火
                for (String alarmTitle : TypeDef.typeFireAlarmTitle) {
                    alarmTitles.add(alarmTitle);
                }
                break;
            case 1://外破
                for (String alarmTitle : TypeDef.typeBreakAlarmTitle) {
                    alarmTitles.add(alarmTitle);
                }
                break;
            case 2://普通视频
                for (String alarmTitle : TypeDef.typeNormalAlarmTitle) {
                    alarmTitles.add(alarmTitle);
                }
                break;
        }

        mViewPager.getViewPager().setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return RecyclerViewFragment.newInstance(position, alarmTitles);
            }

            @Override
            public int getCount() {
                return alarmTitles.size();
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                return super.instantiateItem(container, position);
            }

            @Override
            public int getItemPosition(Object object) {
                return POSITION_NONE;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return alarmTitles.get(position);
            }
        });

        //在这里应该加载数据
        mViewPager.setMaterialViewPagerListener(new MaterialViewPager.Listener() {
            @Override
            public HeaderDesign getHeaderDesign(int page) {
                switch (page) {
                    case 0:
                        return HeaderDesign.fromColorResAndUrl(
                                R.color.green,
                                "https://fs01.androidpit.info/a/63/0e/android-l-wallpapers-630ea6-h900.jpg");
                    case 1:
                        return HeaderDesign.fromColorResAndUrl(
                                R.color.blue,
                                "http://cdn1.tnwcdn.com/wp-content/blogs.dir/1/files/2014/06/wallpaper_51.jpg");
                    case 2:
                        return HeaderDesign.fromColorResAndUrl(
                                R.color.cyan,
                                "http://www.droid-life.com/wp-content/uploads/2014/10/lollipop-wallpapers10.jpg");
                    case 3:
                        return HeaderDesign.fromColorResAndUrl(
                                R.color.red,
                                "http://www.tothemobile.com/wp-content/uploads/2014/07/original.jpg");
                    case 4:
                        return HeaderDesign.fromColorResAndUrl(
                                R.color.lime,
                                "http://www.tothemobile.com/wp-content/uploads/2014/07/original.jpg");
                    case 5:
                        return HeaderDesign.fromColorResAndUrl(
                                R.color.purple,
                                "http://www.tothemobile.com/wp-content/uploads/2014/07/original.jpg");

                }
                //execute others actions if needed (ex : modify your header logo)
                return null;
            }
        });

        mViewPager.getViewPager().setOffscreenPageLimit(mViewPager.getViewPager().getAdapter().getCount());
        mViewPager.getPagerTitleStrip().setViewPager(mViewPager.getViewPager());
        mViewPager.getViewPager().setCurrentItem(0);
    }

    @Override
    public void initialize() {

    }
}