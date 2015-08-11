package com.hy.data.entity;

/**
 * User Entity used in the data layer.
 */
public class UserEntity {

  private int selectionType;
  private String userName;
  private int id;
  private String OwnedEquipment;  //原本应该是一个String[]或者List<String>,但是不能保存在数据库中,所以选用"山火,外破"这种方式,在代码中进行分离

  public UserEntity() {
    //empty
  }

  public UserEntity(String userName, String ownedEquipment) {
    this.userName = userName;
    OwnedEquipment = ownedEquipment;
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
