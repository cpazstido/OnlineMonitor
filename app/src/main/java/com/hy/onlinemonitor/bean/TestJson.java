package com.hy.onlinemonitor.bean;

import java.util.List;

/**
 * Created by 24363 on 2015/9/11.
 */
public class TestJson {

    /**
     * nextNum : 1
     * totalPage : 1
     * lastPageNum : 1
     * pageSize : 10
     * perNum : 1
     * rowCount : 1
     * list : [{"deviceType":"break","sensor_ID":"1","dvrID":"150","dvrType":"2","sensorInDeviceSet":[],"sensorType":0,"sn":123,"deviceID":"CD_CS_10000000002","cma_ID":"1","sendMmsState":1,"angleRelativeToNorthPole":1,"equipment_ID":"1"}]
     * pageNum : 1
     * allNum : 1
     * firstNum : 1
     */
    private int nextNum;
    private int totalPage;
    private int lastPageNum;
    private int pageSize;
    private int perNum;
    private int rowCount;
    private List<ListEntity> list;
    private int pageNum;
    private int allNum;
    private int firstNum;

    public void setNextNum(int nextNum) {
        this.nextNum = nextNum;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public void setLastPageNum(int lastPageNum) {
        this.lastPageNum = lastPageNum;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public void setPerNum(int perNum) {
        this.perNum = perNum;
    }

    public void setRowCount(int rowCount) {
        this.rowCount = rowCount;
    }

    public void setList(List<ListEntity> list) {
        this.list = list;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public void setAllNum(int allNum) {
        this.allNum = allNum;
    }

    public void setFirstNum(int firstNum) {
        this.firstNum = firstNum;
    }

    public int getNextNum() {
        return nextNum;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public int getLastPageNum() {
        return lastPageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public int getPerNum() {
        return perNum;
    }

    public int getRowCount() {
        return rowCount;
    }

    public List<ListEntity> getList() {
        return list;
    }

    public int getPageNum() {
        return pageNum;
    }

    public int getAllNum() {
        return allNum;
    }

    public int getFirstNum() {
        return firstNum;
    }

    public static class ListEntity {
        /**
         * deviceType : break
         * sensor_ID : 1
         * dvrID : 150
         * dvrType : 2
         * sensorInDeviceSet : []
         * sensorType : 0
         * sn : 123
         * deviceID : CD_CS_10000000002
         * cma_ID : 1
         * sendMmsState : 1
         * angleRelativeToNorthPole : 1.0
         * equipment_ID : 1
         */
        private String deviceType;
        private String sensor_ID;
        private String dvrID;
        private String dvrType;
        private List<?> sensorInDeviceSet;
        private int sensorType;
        private int sn;
        private String deviceID;
        private String cma_ID;
        private int sendMmsState;
        private double angleRelativeToNorthPole;
        private String equipment_ID;

        public void setDeviceType(String deviceType) {
            this.deviceType = deviceType;
        }

        public void setSensor_ID(String sensor_ID) {
            this.sensor_ID = sensor_ID;
        }

        public void setDvrID(String dvrID) {
            this.dvrID = dvrID;
        }

        public void setDvrType(String dvrType) {
            this.dvrType = dvrType;
        }

        public void setSensorInDeviceSet(List<?> sensorInDeviceSet) {
            this.sensorInDeviceSet = sensorInDeviceSet;
        }

        public void setSensorType(int sensorType) {
            this.sensorType = sensorType;
        }

        public void setSn(int sn) {
            this.sn = sn;
        }

        public void setDeviceID(String deviceID) {
            this.deviceID = deviceID;
        }

        public void setCma_ID(String cma_ID) {
            this.cma_ID = cma_ID;
        }

        public void setSendMmsState(int sendMmsState) {
            this.sendMmsState = sendMmsState;
        }

        public void setAngleRelativeToNorthPole(double angleRelativeToNorthPole) {
            this.angleRelativeToNorthPole = angleRelativeToNorthPole;
        }

        public void setEquipment_ID(String equipment_ID) {
            this.equipment_ID = equipment_ID;
        }

        public String getDeviceType() {
            return deviceType;
        }

        public String getSensor_ID() {
            return sensor_ID;
        }

        public String getDvrID() {
            return dvrID;
        }

        public String getDvrType() {
            return dvrType;
        }

        public List<?> getSensorInDeviceSet() {
            return sensorInDeviceSet;
        }

        public int getSensorType() {
            return sensorType;
        }

        public int getSn() {
            return sn;
        }

        public String getDeviceID() {
            return deviceID;
        }

        public String getCma_ID() {
            return cma_ID;
        }

        public int getSendMmsState() {
            return sendMmsState;
        }

        public double getAngleRelativeToNorthPole() {
            return angleRelativeToNorthPole;
        }

        public String getEquipment_ID() {
            return equipment_ID;
        }
    }
}
