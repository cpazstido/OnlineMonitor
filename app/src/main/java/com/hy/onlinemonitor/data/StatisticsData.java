package com.hy.onlinemonitor.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StatisticsData {

    private static Map<String, String> selectStatisticsValue;
    private static Map<String, String> statisticalParametersValue;
    private static Map<String, String> specificAttributesValue;

    private static Map<String, String[]> statisticalParametersKey;

    //设备状态信息统计-选择统计
    public static String[] selectStatistics = new String[]{"工作温度", "工作电压", "工作电流", "在线取电功", "设备开关机", "未上传气象数据", "未上传杆塔数据", "未上传风偏数据", "电池电压", "电池充电电流", "电源剩余电量", "电源输出电流"};
    //监测状态统计-统计参数
    public static String[] statisticalParameters = new String[]{"微气象", "微风振动", "风偏", "杆塔倾斜", "导线弧垂", "告警统计"};
    //监测状态统计-具体属性
    private static String[][] specificAttributes = new String[][]{{"气温", "湿度", "风速", "风向", "气压", "雨量", "光辐射"}, {"振动浮值", "振动频率"}, {"风偏角", "偏斜角", "最小电气间隙"}, {"顺线倾斜角", "横向倾斜角"}, {"导线弧垂", "水平线夹角"}, {"山火告警", "外破告警"}};
    private static List<String> statisticalParametersKeyList = new ArrayList<>();
    private static List<String> statisticsKeyList = new ArrayList<>();
    private static List<String> specificAttributesKeyList = new ArrayList<>();

    static {
        selectStatisticsValue = new HashMap<>();
        statisticalParametersValue = new HashMap<>();
        specificAttributesValue = new HashMap<>();

        statisticalParametersKey = new HashMap<>();

        selectStatisticsValue.put("工作温度", "Operation_Temperature");
        selectStatisticsValue.put("工作电压", "Bat_Capacity");
        selectStatisticsValue.put("工作电流", "Work_Current");
        selectStatisticsValue.put("在线取电功", "Bat_GetChargeWork");
        selectStatisticsValue.put("设备开关机", "openCount");
        selectStatisticsValue.put("未上传气象数据", "Failed_send_weather_num");
        selectStatisticsValue.put("未上传杆塔数据", "Failed_send_tower_slop_num");
        selectStatisticsValue.put("未上传风偏数据", "Failed_send_angle_num");
        selectStatisticsValue.put("电池电压", "Bat1_Voltage");
        selectStatisticsValue.put("电池充电电流", "Charge_Currrent_1");
        selectStatisticsValue.put("电源剩余电量", "Bat1_RemainCapacity");
        selectStatisticsValue.put("电源输出电流", "Bat1_OutputCurrent");

        statisticalParametersValue.put("微气象", "meteorology");
        statisticalParametersValue.put("微风振动", "aeolianVibration");
        statisticalParametersValue.put("风偏", "windageYaw");
        statisticalParametersValue.put("杆塔倾斜", "inclination");
        statisticalParametersValue.put("导线弧垂", "conductorSag");
        statisticalParametersValue.put("告警统计", "alarm");

        specificAttributesValue.put("气温", "airTemperature");
        specificAttributesValue.put("湿度", "humidity");
        specificAttributesValue.put("风速", "averageWindSpeed10Min");
        specificAttributesValue.put("风向", "averageWindDirection10Min");
        specificAttributesValue.put("气压", "airPressure");
        specificAttributesValue.put("雨量", "precipitation");
        specificAttributesValue.put("光辐射", "radiationIntensity");
        specificAttributesValue.put("振动浮值", "vibrationAmplitude");
        specificAttributesValue.put("振动频率", "vibrationFrequency");
        specificAttributesValue.put("风偏角", "windageYawAngle");
        specificAttributesValue.put("偏斜角", "deflectionAngle");
        specificAttributesValue.put("最小电气间隙", "leastClearance");
        specificAttributesValue.put("顺线倾斜角", "angleX");
        specificAttributesValue.put("横向倾斜角", "angleY");
        specificAttributesValue.put("导线弧垂", "sag");
        specificAttributesValue.put("水平线夹角", "angle");
        specificAttributesValue.put("山火告警", "alarmTime");
        specificAttributesValue.put("外破告警", "outsidebreakAlarm");

        statisticalParametersKey.put("微气象", specificAttributes[0]);
        statisticalParametersKey.put("微风振动", specificAttributes[1]);
        statisticalParametersKey.put("风偏", specificAttributes[2]);
        statisticalParametersKey.put("杆塔倾斜", specificAttributes[3]);
        statisticalParametersKey.put("导线弧垂", specificAttributes[4]);
        statisticalParametersKey.put("告警统计", specificAttributes[5]);
    }

    public static String getValueByStatisticsKey(String statisticsKey) {
        return selectStatisticsValue.get(statisticsKey);
    }

    public static String getValueBySpecificAttributesKey(String specificAttributesKey) {
        return specificAttributesValue.get(specificAttributesKey);
    }

    public static String getValueByStatisticalParametersKey(String statisticalParametersKey) {
        return statisticalParametersValue.get(statisticalParametersKey);
    }

    //根据统计参数获得具体属性的值
    public static List<String> getStatisticalParametersKey(String statisticalParameter) {
        statisticalParametersKeyList.clear();
        Collections.addAll(statisticalParametersKeyList, statisticalParametersKey.get(statisticalParameter));
        return statisticalParametersKeyList;
    }

    //得到选择统计的值
    public static List<String> getStatisticsKey() {
        Collections.addAll(statisticsKeyList, selectStatistics);
        return statisticsKeyList;
    }

    //得到统计参数的值
    public static List<String> getSpecificAttributesKey() {
        Collections.addAll(specificAttributesKeyList, statisticalParameters);
        return specificAttributesKeyList;
    }

}
