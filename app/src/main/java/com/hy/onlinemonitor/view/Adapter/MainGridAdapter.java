package com.hy.onlinemonitor.view.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.hy.onlinemonitor.R;
import com.hy.onlinemonitor.data.TypeDef;


/**
 * Created by wsw on 2015/7/13.
 */

public class MainGridAdapter extends BaseAdapter {
    private Context mContext;
    private int mSelectedType;   //代表选中的是山火,外破,无人机或者普通视频
    private int mIndex; // 代表当前需要适配页面中第几个GridView

    //山火功能
    public static String[] mFireFunctionText = TypeDef.typeFireFunctionText;
    public int[] mFireFunctionImages = TypeDef.typeFireFunctionImages;


    //山火参数配置
    public static String[] mFireConfigText = TypeDef.typeFireConfig;
    public int[] mFireConfigImages = TypeDef.typeFireConfigImages;

    //gvManage  gvMonitor

    //山火系统管理
    public static String[] mFireManageText = TypeDef.typeFireManage;
    public int[] mFireManageImages =TypeDef.typeFireManageImages;

    //山火状态监测
    public static String[] mFireMonitorText = TypeDef.typeFireMonitor;
    public int[] mFireMonitorImages = TypeDef.typeFireMonitorImages;

    //外破功能
    public static String[] mBreakFunctionText = TypeDef.typeBreakFunction;
    public int[] mBreakFunctionImages = TypeDef.typeBreakFunctionImages;


    //外破参数配置
    public static String[] mBreakConfigText = TypeDef.typeBreakConfig;
    public int[] mBreakConfigImages = TypeDef.typeBreakConfigImages;

    //外破系统管理
    public static String[] mBreakManageText = TypeDef.typeBreakManage;
    public int[] mBreakManageImages = TypeDef.typeBreakManageImages;

    //外破状态监测
    public static String[] mBreakMonitorText = TypeDef.typeBreakMonitor;
    public int[] mBreakMonitorImages =TypeDef.typeBreakMonitorImages;

    //普通视频功能
    public static String[] mNormalFunctionText = TypeDef.typeNormalFunction;
    public int[] mNormalFunctionImages = TypeDef.typeNormalFunctionImages;

    //普通视频参数配置
    public static String[] mNormalConfigText = TypeDef.typeNormalConfig;
    public int[] mNormalConfigImages = TypeDef.typeNormalConfigImages;

    //gvManage  gvMonitor

    //普通视频系统管理
    public static String[] mNormalManageText = TypeDef.typeNormalManage;
    public int[] mNormalManageImages =TypeDef.typeNormalManageImages;

    //普通视频状态监测
    public static String[] mNormalMonitorText = TypeDef.typeNormalMonitor;
    public int[] mNormalMonitorImages = TypeDef.typeNormalMonitorImages;

    //无人机功能
    public static String[] mAutoPlaneFunctionText = TypeDef.typeAutoPlaneFunction;
    public int[] mAutoPlaneFunctionImages = TypeDef.typeAutoPlaneFunctionImages;


    //无人机参数配置
    public static String[] mAutoPlaneConfigText = TypeDef.typeAutoPlaneConfig;
    public int[] mAutoPlaneConfigImages = TypeDef.typeAutoPlaneConfigImages;

    //gvManage  gvMonitor

    //无人机系统管理
    public static String[] mAutoPlaneManageText = TypeDef.typeAutoPlaneManage;
    public int[] mAutoPlaneManageImages = TypeDef.typeAutoPlaneManageImages;

    public MainGridAdapter(Context context, int index, int selectedType) {
        mContext = context;
        mIndex = index;
        mSelectedType = selectedType;
    }

    @Override
    public int getCount() {
        int count = 0;
        switch (mSelectedType) {
            case 0:   //山火
            {
                switch (mIndex) {
                    case 0: //功能
                        count = mFireFunctionImages.length;
                        break;
                    case 1: //参数配置
                        count = mFireConfigImages.length;
                        break;
                    case 2: //系统管理
                        count = mFireManageImages.length;
                        break;
                    case 3://状态监测
                        count = mFireMonitorImages.length;
                        break;
                }
            }
            break;
            case 1: //外破
            {
                switch (mIndex) {
                    case 0: //功能
                        count = mBreakFunctionImages.length;
                        break;
                    case 1: //参数配置
                        count = mBreakConfigImages.length;
                        break;
                    case 2: //系统管理
                        count = mBreakManageImages.length;
                        break;
                    case 3://状态监测
                        count = mBreakMonitorImages.length;
                        break;
                }
            }
            break;
            case 2: //普通视频
                switch (mIndex) {
                    case 0: //功能
                        count = mNormalFunctionImages.length;
                        break;
                    case 1: //参数配置
                        count = mNormalConfigImages.length;
                        break;
                    case 2: //系统管理
                        count = mNormalManageImages.length;
                        break;
                    case 3://状态监测
                        count = mNormalMonitorImages.length;
                        break;
                }
                break;
            case 3: //无人机
                switch (mIndex) {
                    case 0: //功能
                        count = mAutoPlaneFunctionImages.length;
                        break;
                    case 1: //参数配置
                        count = mAutoPlaneConfigImages.length;
                        break;
                    case 2: //系统管理
                        count = mAutoPlaneManageImages.length;
                        break;
                    case 4: //状态监测
                        //不存在,直接返回0
                        count = 0;
                        break;
                }
                break;
        }
        return count;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = View.inflate(mContext, R.layout.item_main_grid, null);

        ImageView image = (ImageView) view.findViewById(R.id.img_chooseImage);
        TextView text = (TextView) view.findViewById(R.id.tv_chooseText);

        switch (mSelectedType) { //0:山火,1:外破,2:普通视频,3:无人机
            case 0:   //山火
                switch (mIndex) {
                    case 0: //功能
                        image.setImageResource(mFireFunctionImages[position]);
                        text.setText(mFireFunctionText[position]);
                        break;
                    case 1: //参数配置
                        image.setImageResource(mFireConfigImages[position]);
                        text.setText(mFireConfigText[position]);
                        break;
                    case 2: //系统管理
                        image.setImageResource(mFireManageImages[position]);
                        text.setText(mFireManageText[position]);
                        break;
                    case 3://状态监测
                        image.setImageResource(mFireMonitorImages[position]);
                        text.setText(mFireMonitorText[position]);
                        break;
                }
                break;
            case 1: //外破
                switch (mIndex) {
                    case 0: //功能
                        image.setImageResource(mBreakFunctionImages[position]);
                        text.setText(mBreakFunctionText[position]);
                        break;
                    case 1: //参数配置
                        image.setImageResource(mBreakConfigImages[position]);
                        text.setText(mBreakConfigText[position]);
                        break;
                    case 2: //系统管理
                        image.setImageResource(mBreakManageImages[position]);
                        text.setText(mBreakManageText[position]);
                        break;
                    case 3://状态监测
                        image.setImageResource(mBreakMonitorImages[position]);
                        text.setText(mBreakMonitorText[position]);
                        break;
                }
                break;
            case 2: //普通视频
                switch (mIndex) {
                    case 0: //功能
                        image.setImageResource(mNormalFunctionImages[position]);
                        text.setText(mNormalFunctionText[position]);
                        break;
                    case 1: //参数配置
                        image.setImageResource(mNormalConfigImages[position]);
                        text.setText(mNormalConfigText[position]);
                        break;
                    case 2: //系统管理
                        image.setImageResource(mNormalManageImages[position]);
                        text.setText(mNormalManageText[position]);
                        break;
                    case 3://状态监测
                        image.setImageResource(mNormalMonitorImages[position]);
                        text.setText(mNormalMonitorText[position]);
                        break;
                }

                break;
            case 3: //无人机
                switch (mIndex) {
                    case 0: //功能
                        image.setImageResource(mAutoPlaneFunctionImages[position]);
                        text.setText(mAutoPlaneFunctionText[position]);
                        break;
                    case 1: //参数配置
                        image.setImageResource(mAutoPlaneConfigImages[position]);
                        text.setText(mAutoPlaneConfigText[position]);
                        break;
                    case 2: //系统管理
                        image.setImageResource(mAutoPlaneManageImages[position]);
                        text.setText(mAutoPlaneManageText[position]);
                        break;
                    case 4://状态监测
                        break;
                }
                break;
            default:
                break;
        }

        return view;
    }
}
