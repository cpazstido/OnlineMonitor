package com.hy.data.net;


import com.hy.data.entity.AlarmPageEntity;
import com.hy.data.entity.MapEntity;
import com.hy.data.entity.EquipmentPageEntity;
import com.hy.data.entity.UserEntity;

import java.util.List;

import rx.Observable;

/**
 * RestApi for retrieving data from the network.
 */
public interface RestApi {
  /**
   * 取得用户对象
   */

  Observable<UserEntity> userEntity(String loginAccount, String loginPwd);

  /**
   * 取得设备列表
   */
  Observable<EquipmentPageEntity> equipmentEntity(int userName, int choiceType,int pageNumber);


  /**
   * 查看所有的报警
   * @param queryAlarmType 查看的报警类型
   * @param status 查看的报警类型的状态(历史,或者新报警)
   * @param userId 唯一标示用户
   * @param pageNumber 第几页的数据
   */
  Observable<AlarmPageEntity> alarmEntity(int userId,String queryAlarmType,int status,int pageNumber);

  /**
   * 查看特定 equipmentName的报警信息
   * @param userId 唯一标示用户
   * @param equipmentName 设备名(唯一标示)
   * @param queryAlarmType 查看的报警类型
   * @param status 查看的报警类型的状态(历史,或者新报警)
   * @param pageNumber 第几页的数据
   */
  Observable<AlarmPageEntity> alarmEntity(int userId,String equipmentName,String queryAlarmType,int status,int pageNumber);

  /**
   * 取得地图列表
   */

  Observable<List<MapEntity>> mapEntity(int userId,int choiceType);

  /**
   * 取得视频播放地址
   * @param fileName 根据文件名获得播放地址
   * @return 返回Url
   */
  Observable<String> videoUrl(String fileName);

  /**
   * 取得实时视频播放地址
   * @param channelID 通道号
   * @param dvrId dvrId
   * @param dvrType dvr类型
   * @param streamType 码流类型
   * @return 返回Url
   */
  Observable<String> videoUrl(String dvrType,int dvrId,int channelID, int streamType);
}
