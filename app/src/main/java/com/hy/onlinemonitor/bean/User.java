package com.hy.onlinemonitor.bean;

import java.io.Serializable;

public class User implements Serializable {
    private int selectionType;  //代表选择的类型 0:fire 1:break 2:video 3:auv
    private String companyName; //公司名
    private int userId;//唯一标示一个用户的属性
    private int id; //使用xUtile必须拥有的参数
    private String OwnedEquipment;  //原本应该是一个String[]或者List<String>,但是不能保存在数据库中,所以选用"fire&uav&video&break"这种方式,在代码中进行分离
                                    //fire-->山火 uav-->无人机 video-->普通视频 break-->外破
    public User() {//使用xUtile必须的构造函数
    }

    public User(String companyName, String ownedEquipment) {
        this.companyName = companyName;
        OwnedEquipment = ownedEquipment;
    }

    public int getSelectionType() {
        return selectionType;
    }

    public void setSelectionType(int selectionType) {
        this.selectionType = selectionType;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOwnedEquipment() {
        return OwnedEquipment;
    }

    public void setOwnedEquipment(String ownedEquipment) {
        OwnedEquipment = ownedEquipment;
    }
}
