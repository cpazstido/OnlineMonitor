package com.hy.data.net;


import com.hy.data.entity.AdministratorPageEntity;
import com.hy.data.entity.AeolianVibrationPageEntity;
import com.hy.data.entity.AlarmPageEntity;
import com.hy.data.entity.CompanyEntity;
import com.hy.data.entity.ConductorSagPageEntity;
import com.hy.data.entity.ConductorSwingWithWindPageEntity;
import com.hy.data.entity.EquipmentInforPageEntity;
import com.hy.data.entity.EquipmentPageEntity;
import com.hy.data.entity.IceCoatingPageEntity;
import com.hy.data.entity.LineEntity;
import com.hy.data.entity.LinePageEntity;
import com.hy.data.entity.MapEntity;
import com.hy.data.entity.MicroclimatePageEntity;
import com.hy.data.entity.OnlineDeviceStatePageEntity;
import com.hy.data.entity.PolePageEntity;
import com.hy.data.entity.PoleStatusPageEntity;
import com.hy.data.entity.PrivilegeEntity;
import com.hy.data.entity.RoleEntity;
import com.hy.data.entity.RolePageEntity;
import com.hy.data.entity.SensorTypeEntity;
import com.hy.data.entity.UserEntity;

import java.util.List;
import java.util.TreeMap;

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
     * 设置选择的类型
     *
     * @param curProject 当前的项目
     * @return 返回字符串
     */
    Observable<String> setCurrentPorject(String curProject);

    /**
     * 取得设备列表
     *
     * @param userName   唯一标示某一个用户
     * @param choiceType 标示查看的设备类型
     * @param pageNumber 页数
     * @return 设备列表
     */
    Observable<EquipmentInforPageEntity> equipmentEntity(int userName, int choiceType,
                                                         int pageNumber);


    /**
     * 查看所有的报警
     *
     * @param queryAlarmType 查看的报警类型
     * @param status         查看的报警类型的状态(历史,或者新报警)
     * @param userId         唯一标示用户
     * @param pageNumber     第几页的数据
     */
    Observable<AlarmPageEntity> alarmEntity(int userId, String curProject,
                                            String queryAlarmType,
                                            int status, int pageNumber);

    /**
     * 处理报警
     *
     * @param AlarmSn        报警sn
     * @param queryAlarmType 处理的哪种报警
     * @return 成功或失败的String
     */
    Observable<String> handleAlarm(int AlarmSn, String queryAlarmType);

    /**
     * 查看特定 equipmentName的报警信息
     *
     * @param userId         唯一标示用户
     * @param equipmentName  设备名(唯一标示)
     * @param queryAlarmType 查看的报警类型
     * @param status         查看的报警类型的状态(历史,或者新报警)
     * @param pageNumber     第几页的数据
     */
    Observable<AlarmPageEntity> alarmEntity(int userId, String equipmentName,
                                            String curProject,
                                            String queryAlarmType, int status,
                                            int pageNumber);

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
    Observable<String> videoUrl(String fileName, int dvrId, int dvrType);

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
     * 控制云台转动
     *
     * @param type 选择云台转动方向
     * @return 字符串代表指令发送是否成功
     */
    Observable<String> videoControl(String type, int dvrID, int channelID, String dvrType);

    /**
     * 得到公司列表
     *
     * @param userId 用户唯一标示
     * @return 公司列表
     */
    Observable<List<CompanyEntity>> companyList(int userId);

    /**
     * 添加公司
     *
     * @param userId 用户唯一标示
     * @return 公司列表
     */
    Observable<List<CompanyEntity>> addCompany(int userId, int sn, String companyName,
                                               String companyAddress);

    /**
     * 查询添加公司时可以选择的父公司
     *
     * @param userId 用户唯一标示
     * @return 公司列表
     */
    Observable<List<CompanyEntity>> queryParetSelectCompany(int userId);

    /**
     * 修改公司
     *
     * @param userId         用户唯一标示
     * @param sn             sn
     * @param companyName    公司名
     * @param companyAddress 公司地址
     * @return 返回公司列表
     */
    Observable<List<CompanyEntity>> changeCompany(int userId, int sn, String companyName,
                                                  String companyAddress);

    /**
     * 删除公司
     *
     * @param userId 用户唯一表示
     * @param sn     sn
     * @return 公司列表
     */
    Observable<List<CompanyEntity>> deleteCompany(int userId, int sn);

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
    Observable<AdministratorPageEntity> addAdministrator(int roleSn, int companySn,
                                                         String loginName, String realName,
                                                         String password, String mobilePhone,
                                                         String isMessage);

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
    Observable<AdministratorPageEntity> changeAdministrator(int sn, int roleSn,
                                                            int companySn, String loginName,
                                                            String realName, String password,
                                                            String mobilePhone, String isMessage);

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
    Observable<String> changeManageTower(int userId, int adminSn, List<Integer> snList,
                                         int allPoleSelected);


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
     * @param userId     唯一标示
     * @param pageNumber 页数
     * @param companySn  公司sn
     * @return 线路列表
     */
    Observable<LinePageEntity> getLinePage(int userId, int companySn, int pageNumber);

    /**
     * 得到所有线路列表
     *
     * @param userId     唯一标示
     * @param pageNumber 页数
     * @return 线路列表
     */
    Observable<LinePageEntity> getAllLinePage(int userId, int pageNumber);

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
    Observable<LinePageEntity> addLine(int userId, int companySn,
                                       String lineName, String lineStart, String lineFinish,
                                       String lineTrend, String voltageLevel);

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
    Observable<LinePageEntity> changeLine(int userId, int companySn, int lineSn,
                                          String lineName, String lineStart, String lineFinish,
                                          String lineTrend, String voltageLevel);

    /**
     * 得到杆塔列表
     *
     * @param pageNumber 页数
     * @param userId     唯一标示
     * @param lineSn     线路sn
     * @return 杆塔列表
     */
    Observable<PolePageEntity> getPolePage(int userId, int lineSn, int pageNumber);

    /**
     * 得到全部的杆塔列表
     *
     * @param userId     唯一标示
     * @param pageNumber 页数
     * @return 杆塔列表
     */
    Observable<PolePageEntity> getPolePage(int userId, int pageNumber);

    /**
     * 添加杆塔
     *
     * @param userId    唯一标示
     * @param lineSn    线路sn
     * @param poleName  杆塔名
     * @param longitude 经度
     * @param latitude  纬度
     * @param altitude  海拔高度
     * @return 返回杆塔列表
     */
    Observable<PolePageEntity> addPole(int userId, int lineSn, String poleName,
                                       String longitude, String latitude, String altitude);

    /**
     * 删除杆塔
     *
     * @param userId 唯一标示
     * @param poleSn 杆塔sn
     * @return 杆塔列表
     */
    Observable<PolePageEntity> deletePole(int userId, int poleSn);

    /**
     * 修改杆塔
     *
     * @param userId    唯一标示
     * @param poleName  杆塔名
     * @param longitude 经度
     * @param latitude  纬度
     * @param altitude  海拔高度
     * @param poleSn    杆塔sn
     * @return 返回杆塔列表
     */
    Observable<PolePageEntity> changePole(int userId, int poleSn, String poleName,
                                          String longitude, String latitude, String altitude);

    /**
     * 得到线路列表
     *
     * @param userId 唯一标示
     * @return 公司列表, 包含了线路
     */
    Observable<List<CompanyEntity>> getAllLine(int userId);

    /**
     * 得到 设备page
     *
     * @param userId  唯一标示
     * @param poleSn  选中的杆塔sn
     * @param pageNum 页数
     * @return 设备page
     */
    Observable<EquipmentPageEntity> getEquipmentPage(int userId, int poleSn, int pageNum);

    /**
     * 添加设备
     *
     * @param userId                   唯一标示
     * @param deviceID                 监测设备编码
     * @param dvrID                    dvrID
     * @param angleRelativeToNorthPole 预置位0度相对于北极夹角
     * @param deviceType               被监测设备类型
     * @param sendMmsState             该设备是否发送彩信，0不发送，1发送
     * @param cma_ID                   cma_ID
     * @param sensor_ID                sensor_ID
     * @param equipment_ID             equipment_ID
     * @return 设备page
     */
    Observable<EquipmentPageEntity> addEquipment(int userId, int poleSn, String deviceID,
                                                 String dvrID,
                                                 Double angleRelativeToNorthPole,
                                                 String deviceType, int sendMmsState,
                                                 String cma_ID, String sensor_ID,
                                                 String equipment_ID);

    /**
     * 删除设备
     *
     * @param userId      唯一标示
     * @param equipmentSn 设备sn
     * @return 设备page
     */
    Observable<EquipmentPageEntity> deleteEquipment(int userId, int equipmentSn);

    /**
     * 修改设备
     *
     * @param userId                   唯一标示
     * @param equipmentSn              设备sn
     * @param deviceID                 监测设备编码
     * @param dvrID                    dvrID
     * @param angleRelativeToNorthPole 预置位0度相对于北极夹角
     * @param deviceType               被监测设备类型
     * @param sendMmsState             该设备是否发送彩信，0不发送，1发送
     * @param cma_ID                   cma_ID
     * @param sensor_ID                sensor_ID
     * @param equipment_ID             equipment_ID
     * @return 设备page
     */
    Observable<EquipmentPageEntity> changeEquipment(int userId, int equipmentSn, String deviceID,
                                                    String dvrID,
                                                    Double angleRelativeToNorthPole,
                                                    String deviceType, int sendMmsState,
                                                    String cma_ID, String sensor_ID,
                                                    String equipment_ID);

    /**
     * 重置设备
     *
     * @param userId      唯一标示
     * @param equipmentSn 设备sn
     * @return 成功或失败的string
     */
    Observable<String> restartEquipment(int userId, int equipmentSn);

    /**
     * 得到所有的传感器,用于spinner的显示
     *
     * @param userId 唯一标示
     * @return List的传感器对象
     */
    Observable<List<SensorTypeEntity>> getAllSensor(int userId);

    /**
     * 修改传感器
     *
     * @return List的传感器对象
     */
    Observable<String> changeSensor(int userId, int equipmentSn, String sensorJson);

    /**
     * 得到设备状态
     *
     * @param equipmentSn 设备sn
     * @return 返回设备状态字符串
     */
    Observable<String> getEquipmentStatus(int equipmentSn);

    /**
     * 打开设备电源
     *
     * @param deviceId 设备sn
     * @return 返回指令是否成功
     */
    Observable<String> openPower(String deviceId, int operationType);

    /**
     * 山火特有的打开电源
     *
     * @param dvrId     dvrId
     * @param channelID channelID
     * @param dvrType   dvrType
     * @return 字符串
     */
    Observable<String> openFirePower(int dvrId, int channelID, String dvrType);

    /**
     * 切换手自动
     *
     * @param dvrId     dvrId
     * @param channelID channelID
     * @param dvrType   dvrType
     * @param type      true:手动切换自动 false 自动切换手动
     * @return 字符串
     */
    Observable<String> changePtz(int dvrId, int channelID, String dvrType, Boolean type);

    /**
     * 停止播放
     *
     * @param dvrId      dvrId
     * @param channelID  channelID
     * @param dvrType    dvrType
     * @param streamType streamType
     * @return 是否
     */
    Observable<String> stopPlay(int dvrId, int channelID, String dvrType, int streamType);

    /**
     * 获取设备状态监测数据
     *
     * @param userId          唯一标示
     * @param fieldName       选择统计类型
     * @param startTime       开始时间
     * @param endTime         结束时间
     * @param deviceSn        设备sn
     * @param statisticByTime 统计方式
     * @param deviceID        设备编号
     * @return 返回一个Map 横坐标(时间),纵坐标(值)
     */
    Observable<TreeMap<String, Float>> queryConditionMonitorData(int userId, String fieldName,
                                                                 String startTime, String endTime,
                                                                 String deviceSn, String statisticByTime,
                                                                 String deviceID);

    /**
     * 获取监测状态监测数据
     *
     * @param type            统计参数
     * @param userId          唯一标示
     * @param fieldName       具体属性
     * @param startTime       开始时间
     * @param endTime         结束时间
     * @param deviceSn        设备sn
     * @param statisticByTime 统计方式
     * @return 返回一个Map 横坐标(时间),纵坐标(值)
     */
    Observable<TreeMap<String, Float>> queryMonitoringStateData(String type, int userId, String fieldName,
                                                                String startTime, String endTime,
                                                                String deviceSn, String statisticByTime);

    /**
     * 获取在线设备状态
     *
     * @param userId  唯一标示
     * @param lineSn  线路sn
     * @param pageNum 页数
     * @return 返回状态page
     */
    Observable<OnlineDeviceStatePageEntity> loadOnlineDeviceState(int userId, int lineSn,
                                                                  int pageNum);

    /**
     * 得到微风振动数据
     *
     * @param deviceSn 设备sn
     * @param pageNum  页数
     * @return Page对象
     */
    Observable<AeolianVibrationPageEntity> getAeolianVibration(String deviceSn, int pageNum);

    /**
     * 得到覆冰数据
     *
     * @param deviceSn 设备sn
     * @param pageNum  页数
     * @return Page对象
     */
    Observable<IceCoatingPageEntity> getIceCoating(String deviceSn, int pageNum);

    /**
     * 得到导线弧垂数据
     *
     * @param deviceSn 设备sn
     * @param pageNum  页数
     * @return Page对象
     */
    Observable<ConductorSagPageEntity> getConductorSag(String deviceSn, int pageNum);

    /**
     * 得到导线风偏数据
     *
     * @param deviceSn 设备sn
     * @param pageNum  页数
     * @return Page对象
     */
    Observable<ConductorSwingWithWindPageEntity> getConductorSwingWithWind(String deviceSn, int pageNum);

    /**
     * 得到杆塔状态数据
     *
     * @param deviceSn 设备sn
     * @param pageNum  页数
     * @return Page对象
     */
    Observable<PoleStatusPageEntity> getPoleStatus(String deviceSn, int pageNum);

    /**
     * 得到微气象数据
     *
     * @param deviceSn 设备sn
     * @param pageNum  页数
     * @return Page对象
     */
    Observable<MicroclimatePageEntity> getMicroclimate(String deviceSn, int pageNum);

    /**
     * 获取传感器类型,用来显示page
     *
     * @param devicesn 设备sn
     * @return List
     */
    Observable<List<SensorTypeEntity>> getConditionMonitoringType(String devicesn);
}
