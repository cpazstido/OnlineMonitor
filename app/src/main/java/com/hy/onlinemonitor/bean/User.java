package com.hy.onlinemonitor.bean;

import java.io.Serializable;

/**
 * Created by wsw on 2015/8/10.
 */
public class User implements Serializable {
    private int selectionType;  //代表选择的类型
    private String userName;
    private int id; //使用xUtile必须拥有的参数
    private String OwnedEquipment;  //原本应该是一个String[]或者List<String>,但是不能保存在数据库中,所以选用"山火,外破"这种方式,在代码中进行分离

    public User() {//使用xUtile必须的构造函数
    }

    public User(String userName, String ownedEquipment) {
        this.userName = userName;
        OwnedEquipment = ownedEquipment;
    }

    @Override
    public String toString() {
        return "User{" +
                "selectionType=" + selectionType +
                ", userName='" + userName + '\'' +
                ", id=" + id +
                ", OwnedEquipment='" + OwnedEquipment + '\'' +
                '}';
    }

    public String getOwnedEquipment() {
        return OwnedEquipment;
    }

    public void setOwnedEquipment(String ownedEquipment) {
        OwnedEquipment = ownedEquipment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setSelectionType(int selectionType) {
        this.selectionType = selectionType;
    }



    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getSelectionType() {
        return selectionType;
    }


    public String getUserName() {
        return userName;
    }
}
