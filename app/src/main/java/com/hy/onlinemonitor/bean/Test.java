package com.hy.onlinemonitor.bean;

import java.util.List;

/**
 * Created by 24363 on 2015/10/23.
 */
public class Test {

    /**
     * allNum : 21
     * firstNum : 1
     * lastPageNum : 21
     * list : [{"alarmFlag":0,"angleX":0.6,"angleY":0.6,"cag_cmaid":null,"cag_equipmentid":null,"cag_sensorid":null,"collectDataTime":"2015-10-28T03:11:58","collectDataTimeStr":"2015-10-28 03:11:58","deviceID":"","deviceSn":122,"deviceType":"fire","inclination":0,"inclinationX":0,"inclinationY":0,"sensorID":0,"sensorName":"1","sn":101,"type":"tower_inclination"},{"alarmFlag":0,"angleX":0.6,"angleY":0.6,"cag_cmaid":null,"cag_equipmentid":null,"cag_sensorid":null,"collectDataTime":"2015-10-28T03:11:28","collectDataTimeStr":"2015-10-28 03:11:28","deviceID":"","deviceSn":122,"deviceType":"fire","inclination":0,"inclinationX":0,"inclinationY":0,"sensorID":0,"sensorName":"1","sn":100,"type":"tower_inclination"},{"alarmFlag":0,"angleX":0.6,"angleY":0.6,"cag_cmaid":null,"cag_equipmentid":null,"cag_sensorid":null,"collectDataTime":"2015-10-28T03:10:58","collectDataTimeStr":"2015-10-28 03:10:58","deviceID":"","deviceSn":122,"deviceType":"fire","inclination":0,"inclinationX":0,"inclinationY":0,"sensorID":0,"sensorName":"1","sn":99,"type":"tower_inclination"},{"alarmFlag":0,"angleX":0.6,"angleY":0.6,"cag_cmaid":null,"cag_equipmentid":null,"cag_sensorid":null,"collectDataTime":"2015-10-28T03:10:28","collectDataTimeStr":"2015-10-28 03:10:28","deviceID":"","deviceSn":122,"deviceType":"fire","inclination":0,"inclinationX":0,"inclinationY":0,"sensorID":0,"sensorName":"1","sn":98,"type":"tower_inclination"},{"alarmFlag":0,"angleX":0.6,"angleY":0.6,"cag_cmaid":null,"cag_equipmentid":null,"cag_sensorid":null,"collectDataTime":"2015-10-28T03:09:58","collectDataTimeStr":"2015-10-28 03:09:58","deviceID":"","deviceSn":122,"deviceType":"fire","inclination":0,"inclinationX":0,"inclinationY":0,"sensorID":0,"sensorName":"1","sn":97,"type":"tower_inclination"}]
     * nextNum : 2
     * pageNum : 1
     * pageSize : 5
     * pageindex : {"endindex":5,"startindex":1}
     * perNum : 1
     * rowCount : 101
     * totalPage : 21
     */

    private int allNum;
    private int firstNum;
    private int lastPageNum;
    private int nextNum;
    private int pageNum;
    private int pageSize;
    private PageindexEntity pageindex;
    private int perNum;
    private int rowCount;
    private int totalPage;
    private List list;

    public void setAllNum(int allNum) {
        this.allNum = allNum;
    }

    public void setFirstNum(int firstNum) {
        this.firstNum = firstNum;
    }

    public void setLastPageNum(int lastPageNum) {
        this.lastPageNum = lastPageNum;
    }

    public void setNextNum(int nextNum) {
        this.nextNum = nextNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public void setPageindex(PageindexEntity pageindex) {
        this.pageindex = pageindex;
    }

    public void setPerNum(int perNum) {
        this.perNum = perNum;
    }

    public void setRowCount(int rowCount) {
        this.rowCount = rowCount;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public void setList(List<ListEntity> list) {
        this.list = list;
    }

    public int getAllNum() {
        return allNum;
    }

    public int getFirstNum() {
        return firstNum;
    }

    public int getLastPageNum() {
        return lastPageNum;
    }

    public int getNextNum() {
        return nextNum;
    }

    public int getPageNum() {
        return pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public PageindexEntity getPageindex() {
        return pageindex;
    }

    public int getPerNum() {
        return perNum;
    }

    public int getRowCount() {
        return rowCount;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public List<ListEntity> getList() {
        return list;
    }

    public static class PageindexEntity {
        /**
         * endindex : 5
         * startindex : 1
         */

        private int endindex;
        private int startindex;

        public void setEndindex(int endindex) {
            this.endindex = endindex;
        }

        public void setStartindex(int startindex) {
            this.startindex = startindex;
        }

        public int getEndindex() {
            return endindex;
        }

        public int getStartindex() {
            return startindex;
        }
    }

    public static class ListEntity {
        /**
         * alarmFlag : 0
         * angleX : 0.6
         * angleY : 0.6
         * cag_cmaid : null
         * cag_equipmentid : null
         * cag_sensorid : null
         * collectDataTime : 2015-10-28T03:11:58
         * collectDataTimeStr : 2015-10-28 03:11:58
         * deviceID :
         * deviceSn : 122
         * deviceType : fire
         * inclination : 0.0
         * inclinationX : 0.0
         * inclinationY : 0.0
         * sensorID : 0
         * sensorName : 1
         * sn : 101
         * type : tower_inclination
         */

        private int alarmFlag;
        private double angleX;
        private double angleY;
        private Object cag_cmaid;
        private Object cag_equipmentid;
        private Object cag_sensorid;
        private String collectDataTime;
        private String collectDataTimeStr;
        private String deviceID;
        private int deviceSn;
        private String deviceType;
        private double inclination;
        private double inclinationX;
        private double inclinationY;
        private int sensorID;
        private String sensorName;
        private int sn;
        private String type;

        public void setAlarmFlag(int alarmFlag) {
            this.alarmFlag = alarmFlag;
        }

        public void setAngleX(double angleX) {
            this.angleX = angleX;
        }

        public void setAngleY(double angleY) {
            this.angleY = angleY;
        }

        public void setCag_cmaid(Object cag_cmaid) {
            this.cag_cmaid = cag_cmaid;
        }

        public void setCag_equipmentid(Object cag_equipmentid) {
            this.cag_equipmentid = cag_equipmentid;
        }

        public void setCag_sensorid(Object cag_sensorid) {
            this.cag_sensorid = cag_sensorid;
        }

        public void setCollectDataTime(String collectDataTime) {
            this.collectDataTime = collectDataTime;
        }

        public void setCollectDataTimeStr(String collectDataTimeStr) {
            this.collectDataTimeStr = collectDataTimeStr;
        }

        public void setDeviceID(String deviceID) {
            this.deviceID = deviceID;
        }

        public void setDeviceSn(int deviceSn) {
            this.deviceSn = deviceSn;
        }

        public void setDeviceType(String deviceType) {
            this.deviceType = deviceType;
        }

        public void setInclination(double inclination) {
            this.inclination = inclination;
        }

        public void setInclinationX(double inclinationX) {
            this.inclinationX = inclinationX;
        }

        public void setInclinationY(double inclinationY) {
            this.inclinationY = inclinationY;
        }

        public void setSensorID(int sensorID) {
            this.sensorID = sensorID;
        }

        public void setSensorName(String sensorName) {
            this.sensorName = sensorName;
        }

        public void setSn(int sn) {
            this.sn = sn;
        }

        public void setType(String type) {
            this.type = type;
        }

        public int getAlarmFlag() {
            return alarmFlag;
        }

        public double getAngleX() {
            return angleX;
        }

        public double getAngleY() {
            return angleY;
        }

        public Object getCag_cmaid() {
            return cag_cmaid;
        }

        public Object getCag_equipmentid() {
            return cag_equipmentid;
        }

        public Object getCag_sensorid() {
            return cag_sensorid;
        }

        public String getCollectDataTime() {
            return collectDataTime;
        }

        public String getCollectDataTimeStr() {
            return collectDataTimeStr;
        }

        public String getDeviceID() {
            return deviceID;
        }

        public int getDeviceSn() {
            return deviceSn;
        }

        public String getDeviceType() {
            return deviceType;
        }

        public double getInclination() {
            return inclination;
        }

        public double getInclinationX() {
            return inclinationX;
        }

        public double getInclinationY() {
            return inclinationY;
        }

        public int getSensorID() {
            return sensorID;
        }

        public String getSensorName() {
            return sensorName;
        }

        public int getSn() {
            return sn;
        }

        public String getType() {
            return type;
        }
    }
}
