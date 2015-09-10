package com.hy.data.net;


import com.hy.data.entity.AdministratorPageEntity;
import com.hy.data.entity.AlarmPageEntity;
import com.hy.data.entity.CompanyEntity;
import com.hy.data.entity.EquipmentPageEntity;
import com.hy.data.entity.LineEntity;
import com.hy.data.entity.LinePageEntity;
import com.hy.data.entity.MapEntity;
import com.hy.data.entity.PolePageEntity;
import com.hy.data.entity.PrivilegeEntity;
import com.hy.data.entity.RoleEntity;
import com.hy.data.entity.RolePageEntity;
import com.hy.data.entity.UserEntity;

import java.util.List;

import rx.Observable;

/**
 * RestApi for retrieving data from the network.
 */
public interface RestApi {
    /**
     * 取得用户对象
     *
     * @param loginAccount 登录账号
     * @param loginPwd     登录密码
     * @return 用户对象
     */
    Observable<UserEntity> userEntity(String loginAccount, String loginPwd);

    /**
     * 取得设备列表
     *
     * @param userName   唯一标示某一个用户
     * @param choiceType 标示查看的设备类型
     * @param pageNumber 页数
     * @return 设备列表
     */
    Observable<EquipmentPageEntity> equipmentEntity(int userName, int choiceType, int pageNumber);


    /**
     * 查看所有的报警
     *
     * @param queryAlarmType 查看的报警类型
     * @param status         查看的报警类型的状态(历史,或者新报警)
     * @param userId         唯一标示用户
     * @param pageNumber     第几页的数据
     */
    Observable<AlarmPageEntity> alarmEntity(int userId, String queryAlarmType, int status, int pageNumber);

    /**
     * 查看特定 equipmentName的报警信息
     *
     * @param userId         唯一标示用户
     * @param equipmentName  设备名(唯一标示)
     * @param queryAlarmType 查看的报警类型
     * @param status         查看的报警类型的状态(历史,或者新报警)
     * @param pageNumber     第几页的数据
     */
    Observable<AlarmPageEntity> alarmEntity(int userId, String equipmentName, String queryAlarmType, int status, int pageNumber);

    /**
     * 取得地图列表
     *
     * @param userId     唯一标示用户
     * @param choiceType 标示查看的设备类型
     * @return 地图列表
     */
    Observable<List<MapEntity>> mapEntity(int userId, int choiceType);


    /**
     * 取得视频播放地址
     *
     * @param fileName 根据文件名获得播放地址
     * @return 返回Url
     */
    Observable<String> videoUrl(String fileName);

    /**
     * 取得实时视频播放地址
     *
     * @param channelID  通道号
     * @param dvrId      dvrId
     * @param dvrType    dvr类型
     * @param streamType 码流类型
     * @return 返回Url
     */
    Observable<String> videoUrl(String dvrType, int dvrId, int channelID, int streamType);

    /**
     * @param userId 用户唯一标示
     * @return 公司列表
     */
    Observable<List<CompanyEntity>> companyList(int userId);

    /**
     * @param userId 用户唯一标示
     * @return 角色列表
     */
    Observable<List<RoleEntity>> roleList(int userId);

    /**
     * 根据userId获取所有的用户信息
     *
     * @param userId userId userId
     * @return 管理员列表
     */
    Observable<AdministratorPageEntity> administratorEntity(int userId);

    /**
     * 添加管理员
     *
     * @param roleSn      角色sn
     * @param companySn   公司sn
     * @param loginName   登录名
     * @param realName    真实姓名
     * @param password    密码
     * @param mobilePhone 电话号码
     * @param isMessage   是否接受短信
     * @return 管理员的Page对象
     */
    Observable<AdministratorPageEntity> addAdministrator(int roleSn, int companySn, String loginName, String realName, String password, String mobilePhone, String isMessage);

    /**
     * 修改管理员
     *
     * @param sn          管理员的sn
     * @param roleSn      角色sn
     * @param companySn   公司sn
     * @param loginName   登录名
     * @param realName    真实姓名
     * @param password    密码
     * @param mobilePhone 电话号码
     * @param isMessage   是否接受短信
     * @return 管理员的Page对象
     */
    Observable<AdministratorPageEntity> changeAdministrator(int sn, int roleSn, int companySn, String loginName, String realName, String password, String mobilePhone, String isMessage);

    /**
     * 删除管理员
     *
     * @param sn 管理员sn
     * @return 管理员的Page对象
     */
    Observable<AdministratorPageEntity> deleteAdministrator(int sn);

    /**
     * 获得所有的杆塔列表
     *
     * @param userId 唯一标示
     * @return 线路列表, 包含了所有的杆塔
     */
    Observable<List<LineEntity>> getAllTower(int userId, int sn);

    /**
     * 获得自己拥有的的杆塔sn
     *
     * @param userId 唯一标示
     * @param sn     哪一个sn的杆塔
     * @return 杆塔sn列表
     */
    Observable<List<Integer>> getOwnTower(int userId, int sn);

    /**
     * 修改管理的杆塔
     *
     * @param userId          唯一标示
     * @param snList          杆塔的sn
     * @param adminSn         某一个管理员的sn
     * @param allPoleSelected 是否全选
     * @return 返回是否处理成功的字符串
     */
    Observable<String> changeManageTower(int userId, int adminSn, List<Integer> snList, int allPoleSelected);


    /**
     * 得到角色列表
     *
     * @param userId 唯一标示
     * @return 角色列表
     */
    Observable<RolePageEntity> getRolePage(int userId);

    /**
     * 添加角色
     *
     * @param userId 唯一标示
     * @param name   新角色名
     * @return 角色列表
     */
    Observable<RolePageEntity> addRole(int userId, String name);

    /**
     * 修改角色
     *
     * @param userId 唯一标示
     * @param name   角色新名
     * @param roleSn 角色sn
     * @return 角色列表
     */
    Observable<RolePageEntity> changeRole(int userId, int roleSn, String name);

    /**
     * 删除角色
     *
     * @param userId 唯一标示
     * @param roleSn 角色sn
     * @return 角色列表
     */
    Observable<RolePageEntity> deleteRole(int userId, int roleSn);

    /**
     * 得到拥有的权限
     *
     * @param userId 唯一标示
     * @param roleSn 角色sn
     * @return 拥有的权限列表
     */
    Observable<List<PrivilegeEntity>> getOwnPrivilege(int userId, int roleSn);

    /**
     * 得到权限列表
     *
     * @param userId 唯一标示
     * @return 返回所有的权限列表
     */
    Observable<List<PrivilegeEntity>> getAllPrivilege(int userId);

    /**
     * 权限修改
     *
     * @param userId         唯一标示
     * @param roleSn         角色sn
     * @param jurisdictionSN 权限sn列表
     * @return 表示成功或失败字符串
     */
    Observable<String> jurisdictionChange(int userId, int roleSn, List<Integer> jurisdictionSN);

    /**
     * 得到线路列表
     *
     * @param userId    唯一标示
     * @param companySn 公司sn
     * @return 线路列表
     */
    Observable<LinePageEntity> getLinePage(int userId, int companySn);

    /**
     * 得到所有线路列表
     *
     * @param userId 唯一标示
     * @return 线路列表
     */
    Observable<LinePageEntity> getAllLinePage(int userId);

    /**
     * 添加线路
     *
     * @param userId       唯一标示
     * @param lineName     线路名
     * @param companySn    公司sn
     * @param lineStart    线路起点
     * @param lineFinish   线路终点
     * @param lineTrend    线路走向
     * @param voltageLevel 电压等级
     * @return 线路列表对象
     */
    Observable<LinePageEntity> addLine(int userId, int companySn, String lineName, String lineStart, String lineFinish, String lineTrend, String voltageLevel);

    /**
     * 删除线路
     *
     * @param userId 唯一标示
     * @param lineSn 线路sn
     * @return 线路列表
     */
    Observable<LinePageEntity> deleteLine(int userId, int lineSn);

    /**
     * 修改线路
     *
     * @param userId       唯一标示
     * @param lineSn       线路sn
     * @param lineName     线路名
     * @param lineStart    线路起点
     * @param lineFinish   线路终点
     * @param lineTrend    线路走向
     * @param voltageLevel 电压等级
     * @return 线路列表
     */
    Observable<LinePageEntity> changeLine(int userId, int companySn,int lineSn, String lineName, String lineStart, String lineFinish, String lineTrend, String voltageLevel);

    /**
     *  得到杆塔列表
     * @param userId 唯一标示
     * @param lineSn 线路sn
     * @return 杆塔列表
     */
    Observable<PolePageEntity> getPolePage(int userId, int lineSn);

    /**
     *  添加杆塔
     * @param userId 唯一标示
     * @param lineSn 线路sn
     * @param poleName 杆塔名
     * @param longitude 经度
     * @param latitude 纬度
     * @param altitude 海拔高度
     * @return 返回杆塔列表
     */
    Observable<PolePageEntity> addPole(int userId, int lineSn,String poleName,String longitude,String latitude,String altitude);

    /**
     * 删除杆塔
     * @param userId 唯一标示
     * @param poleSn 杆塔sn
     * @return 杆塔列表
     */
    Observable<PolePageEntity> deletePole(int userId, int poleSn);

    /**
     *  修改杆塔
     * @param userId 唯一标示
     * @param poleName 杆塔名
     * @param longitude 经度
     * @param latitude 纬度
     * @param altitude 海拔高度
     * @param poleSn 杆塔sn
     * @return 返回杆塔列表
     */
    Observable<PolePageEntity> changePole(int userId, int poleSn, String poleName,String longitude,String latitude,String altitude);

    /**
     * 得到线路列表
     * @param userId 唯一标示
     * @return 公司列表,包含了线路
     */
    Observable<List<CompanyEntity>> getAllLine(int userId);

}
