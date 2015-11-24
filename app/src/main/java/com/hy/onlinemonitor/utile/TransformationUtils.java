package com.hy.onlinemonitor.utile;

import com.google.gson.Gson;
import com.hy.onlinemonitor.bean.RecordUrl;
import com.hy.onlinemonitor.bean.VideoUrl;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;


public class TransformationUtils {
    public static List<String> getListFromString(String str, String splitToWhat) {
        List<String> list = Arrays.asList(str.split(splitToWhat));
        return list;
    }

    public static String getNumberButton(String str, int num) {
        String newStr = null;
        switch (str) {
            case "传感器":
                newStr = "传感器" + "(" + num + ")";
                break;
            case "山火":
                newStr = "山火" + "(" + num + ")";
                break;
            case "外破":
                newStr = "外破" + "(" + num + ")";
                break;
        }
        return newStr;
    }

    /**
     * 转换float为String
     *
     * @param number float参数
     * @return 字符串
     */
    public static String getStringFromFloat(float number) {
        DecimalFormat df = new DecimalFormat("0.00");
        return df.format(number);

    }

    /**
     * 转换float为String
     *
     * @param number float参数
     * @param type   根据type来决定精度
     * @return 字符串
     */
    public static String getStringFromFloat(float number, int type) {
        DecimalFormat df;
        switch (type) {
            case 1:
                df = new DecimalFormat("0.0");
                break;
            case 2:
                df = new DecimalFormat("0");
                break;
            default:
                df = new DecimalFormat("0.00");
                break;
        }
        return df.format(number);

    }

    /**
     * 转换float为String
     *
     * @param number long参数
     * @param type   根据type来决定精度
     * @return 字符串
     */
    public static String getStringFromLong(long number, int type) {
        DecimalFormat df;
        switch (type) {
            case 1:
                df = new DecimalFormat("0.0");
                break;
            case 2:
                df = new DecimalFormat("0");
                break;
            default:
                df = new DecimalFormat("0.00");
                break;
        }

        return df.format(number);

    }

    /**
     * 根据byte来显示开启与关闭状态
     *
     * @param number byte
     * @return 字符串开启或关闭
     */
    public static String getStringBySwitch(byte number) {
        if (number == 1) {
            return "开启";
        } else {
            return "关闭";
        }
    }

    /**
     * 根据byte来显示开启与关闭状态
     *
     * @param number byte
     * @return 字符串
     */
    public static String getStringByByte(byte number) {
        String newStr = null;
        if (number == 0) {
            newStr = "关电";
        } else if (number == 1) {
            newStr = "正在启动";
        } else if (number == 2) {
            newStr = "正常工作";
        }
        return newStr;
    }

    /**
     * 根据byte来显示密匙的状态
     *
     * @param number byte
     * @return 字符串
     */
    public static String getStringByKey(byte number) {
        String newStr = null;
        if (number == 0) {
            newStr = "未发起协商";
        } else if (number == 1) {
            newStr = "密钥协商成功";
        } else if (number == 2) {
            newStr = "密钥协商失败";
        } else if (number == 3) {
            newStr = "明文协商成功";
        } else if (number == 4) {
            newStr = "明文协商失败";
        }
        return newStr;
    }

    /**
     * 根据byte来显示密匙的状态
     *
     * @param number number
     * @return 字符串
     */
    public static String getStringByDVR(byte number) {
        String newStr = null;
        switch (number) {
            case 0:
                newStr = "关电";
                break;
            case 1:
                newStr = "开电";
                break;
            case 2:
                newStr = "正在关电";
                break;
        }
        return newStr;
    }

    /**
     * 根据byte来显示可见光的状态
     *
     * @param number number
     * @return 字符串
     */
    public static String getStringByVisible(byte number) {
        String newStr = null;
        switch (number) {
            case 0:
                newStr = "未上传";
                break;
            case 1:
                newStr = "开机";
                break;
            case 2:
                newStr = "关机";
                break;
            case 3:
                newStr = "故障";
                break;
            case 4:
                newStr = "正在关闭";
                break;
            case 5:
                newStr = "正在打开";
                break;
        }
        return newStr;
    }

    /**
     * 根据byte来显示3G路由器状态
     *
     * @param number number
     * @return 字符串
     */
    public static String getStringByRouter(byte number) {
        String newStr = null;
        switch (number) {
            case 0:
                newStr = "未上传";
                break;
            case 1:
                newStr = "正常";
                break;
            case 3:
                newStr = "故障";
                break;
        }
        return newStr;
    }

    /**
     * 得到电流A为结尾的字符串
     *
     * @param number 传入的float参数
     * @return 返回, 例如12.01A
     */
    public static String getAFromFloat(float number) {
        String newStr = getStringFromFloat(number);
        return newStr + "A";
    }

    /**
     * 得到焦耳J为结尾的字符串
     *
     * @param number 传入的float参数
     * @return 返回, 例如12.01j
     */

    public static String getJFromFloat(float number) {
        String newStr = getStringFromFloat(number);
        return newStr + "J";
    }

    /**
     * 得到福特V为结尾的字符串
     *
     * @param number 传入的float参数
     * @return 返回, 例如12.01V
     */

    public static String getVFromFloat(float number) {
        String newStr = getStringFromFloat(number);
        return newStr + "V";
    }

    /**
     * 得到百分号%为结尾的字符串
     *
     * @param number 传入的float参数
     * @return 返回, 例如12.0%
     */

    public static String getSignFromFloat(float number) {
        String newStr = getStringFromFloat(number, 1);
        return newStr + "%";
    }

    public static String getSignFromFloat(byte number) {
        String newStr = getStringFromFloat(number, 2);
        return newStr + "%";
    }

    /**
     * 得到兆M为结尾的字符串
     *
     * @param number 传入的long参数
     * @return 返回, 例如12M
     */

    public static String getMFromLong(long number) {
        String newStr = getStringFromLong(number, 1);
        return newStr + "M";
    }

    /**
     * 得到度℃为结尾的字符串
     *
     * @param number 传入的float参数
     * @return 返回, 例如25.24℃
     */

    public static String getCFromFloat(float number) {
        String newStr = getStringFromFloat(number);
        return newStr + "℃";
    }

    /**
     * 得到字符串的后六位
     *
     * @param str 传入字符串
     * @return 字符串的后六位
     */
    public static String getDeviceNameLastSix(String str) {
        int totalNum = str.length();
        if (totalNum > 6)
            return str.substring(totalNum - 6, totalNum);
        else
            return str;
    }

    /**
     * 得到Integer数组从int数组中
     *
     * @param nums int型数组
     * @return Integer数组
     */
    public static Integer[] getIntegerFromInt(int[] nums) {
        Integer[] in = new Integer[nums.length];
        for (int i = 0; i < nums.length; i++) {
            in[i] = Integer.valueOf(nums[i]);
        }
        return in;
    }

    public static void removeDuplicateWithOrder(List list) {
        Set set = new HashSet();
        List newList = new ArrayList();
        for (Iterator iter = list.iterator(); iter.hasNext(); ) {
            Object element = iter.next();
            if (set.add(element))
                newList.add(element);
        }
        list.clear();
        list.addAll(newList);
    }

    /**
     * 得到真实实时视频的Url
     *
     * @param url 传入json格式的url
     * @return 字符串, 真实的可播放地址
     */
    public static String getRealVideoUrl(String url) {
        Gson gson = new Gson();
        VideoUrl videoUrl = gson.fromJson(url, VideoUrl.class);
        return videoUrl.getRtspURL();
    }

    /**
     * 得到真实的录像Url
     *
     * @param url 传入json格式的url
     * @return 字符串, 真实的可播放地址
     */
    public static String getRecordVideoUrl(String url) {
        Gson gson = new Gson();
        RecordUrl recordUrl = gson.fromJson(url, RecordUrl.class);
        return recordUrl.getRtsp();
    }
}
