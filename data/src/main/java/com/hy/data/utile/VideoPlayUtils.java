package com.hy.data.utile;

import android.util.Log;

import org.apache.http.entity.StringEntity;

import java.io.UnsupportedEncodingException;

/**
 * Created by 24363 on 2015/8/27.
 */
public class VideoPlayUtils {

    public static StringEntity getRecordPlayXml(String fileNames) throws UnsupportedEncodingException {
        //通过StringBuffer对象拼接
        StringBuffer sb = new StringBuffer();
        //添加xml声明
        sb.append("<?xml version='1.0' encoding='UTF-8'?>");
        //添加根节点
        sb.append("<RecordPlay>");
        //添加子节点
        sb.append("<FileName>");
        //添加内容节点
        sb.append(fileNames);
        sb.append("</FileName>");
        sb.append("</RecordPlay>");
        Log.e("RecordPlayXml",sb.toString());
        return new StringEntity(sb.toString());
    }

    public static StringEntity getVideoControlXml(String type) throws UnsupportedEncodingException {
        //通过StringBuffer对象拼接

        int ptzSpeedValue = 60;
        String PTZSpeed="0x1F";
        StringBuffer sb = new StringBuffer();
        sb.append("<?xml version='1.0' encoding='UTF-8'?>");
        sb.append("<PTZData>");
        sb.append("<pan>");
        switch (type){
            case "left":
                sb.append(-ptzSpeedValue);
                break;
            case "right":
                sb.append(ptzSpeedValue);
                break;
            case "up":
                sb.append(0);
                break;
            case "down":
                sb.append(0);
                break;
            case "stop":
                sb.append(0);
                break;
        }
        sb.append("</pan>");
        sb.append("<tilt>");
        switch (type){
            case "left":
                sb.append(0);
                break;
            case "right":
                sb.append(0);
                break;
            case "up":
                sb.append(ptzSpeedValue);
                break;
            case "down":
                sb.append(-ptzSpeedValue);
                break;
            case "stop":
                sb.append(0);
                break;
        }
        sb.append("</tilt>");
        sb.append("<PTZSpeed>");
        sb.append(PTZSpeed);
        sb.append("</PTZSpeed>");
        sb.append("</PTZData>");
        Log.e("StringEntity",sb.toString());
        return new StringEntity(sb.toString());
    }

    public static StringEntity getPTZControlXml(Boolean type) throws UnsupportedEncodingException {
        //通过StringBuffer对象拼接
        StringBuffer sb = new StringBuffer();
        //添加xml声明
        sb.append("<?xml version='1.0' encoding='UTF-8'?>");
        //添加根节点
        sb.append("<PTZMode>");
        //添加子节点
        sb.append("<mode>");
        //添加内容节点
        if(type){
            sb.append("ptzmanu");
        }else{
            sb.append("ptzauto");
        }
        sb.append("</mode>");
        sb.append("</PTZMode>");
        return new StringEntity(sb.toString());

    }
}
