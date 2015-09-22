package com.hy.data.entity;

/**
 * User Entity used in the data layer.
 */
public class UserEntity {
  /**
   * ownedEquipment : fire&break&video
   * companyName : 四川汇源光通信有限公司
   * userId : 27
   */
  private String ownedEquipment;
  private String companyName;
  private int userId;
  private int selectionType;
  private int id;
  private int roleSn; //角色sn,用于权限

  public void setOwnedEquipment(String ownedEquipment) {
    this.ownedEquipment = ownedEquipment;
  }

  public void setCompanyName(String companyName) {
    this.companyName = companyName;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getOwnedEquipment() {
    return ownedEquipment;
  }

  public String getCompanyName() {
    return companyName;
  }

  public int getUserId() {
    return userId;
  }

  public void setUserId(int userId) {
    this.userId = userId;
  }

  public int getSelectionType() {
    return selectionType;
  }

  public void setSelectionType(int selectionType) {
    this.selectionType = selectionType;
  }

  public int getRoleSn() {
    return roleSn;
  }

  public void setRoleSn(int roleSn) {
    this.roleSn = roleSn;
  }
}
