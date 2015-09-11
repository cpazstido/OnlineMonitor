package com.hy.data.repository;

import android.content.Context;

import com.example.bean.DomainEquipmentPage;
import com.example.bean.DomainLine;
import com.example.bean.DomainSensor;
import com.example.repository.SMEquipmentRepository;
import com.hy.data.entity.mapper.LineEntityDataMapper;
import com.hy.data.entity.mapper.LineEntityJsonMapper;
import com.hy.data.entity.mapper.PageEntityDataMapper;
import com.hy.data.entity.mapper.PageEntityJsonMapper;
import com.hy.data.net.RestApiImpl;

import java.util.List;

import rx.Observable;

/**
 * Created by 24363 on 2015/9/10.
 */
public class EquipmentDataRepository implements SMEquipmentRepository {
    private int userId;
    private int sn;
    private int equipmentSn;
    private final Context mContext;
    private int poleSn;
    private String deviceID; //监测设备编码
    private String dvrID;
    private String deviceType;//设备类型
    private String dvrType;//用与接入服务器通信时表示设备类型，1为山火，2为外破，3为无人机，4为普通视频
    private int sensorType; //被监测设备类型
    private Double angleRelativeToNorthPole;//预置位0度相对于北极夹角
    private Integer sendMmsState; // 该设备是否发送彩信，0不发送，1发送
    private String cma_ID;
    private String sensor_ID;
    private String equipment_ID;

    public EquipmentDataRepository(int userId, int sn, Context mContext) {
        this.userId = userId;
        this.sn = sn;
        this.mContext = mContext;
    }

    public EquipmentDataRepository(Context mContext, int userId, int poleSn) {
        this.userId = userId;
        this.poleSn = poleSn;
        this.mContext = mContext;
    }

    public EquipmentDataRepository(Context mContext, int userId, int poleSn, String deviceID, String dvrId, String angleRelativeToNorthPole, String deviceType, int isSendMessage, String cma_id, String sensor_id, String equipment_id) {
        this.userId = userId;
        this.deviceID = deviceID;
        this.mContext = mContext;
        this.poleSn = poleSn;
        this.dvrID = dvrId;
        this.angleRelativeToNorthPole = Double.valueOf(angleRelativeToNorthPole);
        this.deviceType = deviceType;
        this.sendMmsState = isSendMessage;
        this.cma_ID = cma_id;
        this.sensor_ID = sensor_id;
        this.equipment_ID = equipment_id;
    }

    public EquipmentDataRepository(Context mContext, int userId, String deviceID, String dvrId, String angleRelativeToNorthPole, String deviceType, int isSendMessage, String cma_id, String sensor_id, String equipment_id, int equipmentSn) {
        this.userId = userId;
        this.deviceID = deviceID;
        this.mContext = mContext;
        this.equipmentSn = equipmentSn;
        this.dvrID = dvrId;
        this.angleRelativeToNorthPole = Double.valueOf(angleRelativeToNorthPole);
        this.deviceType = deviceType;
        this.sendMmsState = isSendMessage;
        this.cma_ID = cma_id;
        this.sensor_ID = sensor_id;
        this.equipment_ID = equipment_id;

    }

    public EquipmentDataRepository(int sn, Context mContext, int userId) {
        this.equipmentSn = sn;
        this.mContext = mContext;
        this.userId = userId;
    }

    @Override
    public Observable<List<DomainLine>> getAllTower() {
        RestApiImpl restApi = new RestApiImpl(mContext, new LineEntityJsonMapper());
        LineEntityDataMapper lineEntityDataMapper = new LineEntityDataMapper();
        return restApi.getAllTower(userId, sn).map(lineEntityDataMapper::transform);
    }

    @Override
    public Observable<DomainEquipmentPage> getEquipmentPage() {
        RestApiImpl restApi = new RestApiImpl(mContext, new PageEntityJsonMapper());
        PageEntityDataMapper pageEntityDataMapper = new PageEntityDataMapper();
        return restApi.getEquipmentPage(userId, poleSn).map(pageEntityDataMapper::transform);
    }

    @Override
    public Observable<DomainEquipmentPage> addEquipment() {
        RestApiImpl restApi = new RestApiImpl(mContext, new PageEntityJsonMapper());
        PageEntityDataMapper pageEntityDataMapper = new PageEntityDataMapper();
        return restApi.addEquipment(userId, poleSn,deviceID, dvrID, angleRelativeToNorthPole, deviceType, sendMmsState, cma_ID, sensor_ID, equipment_ID).map(pageEntityDataMapper::transform);
    }

    @Override
    public Observable<DomainEquipmentPage> deleteEquipment() {
        RestApiImpl restApi = new RestApiImpl(mContext, new PageEntityJsonMapper());
        PageEntityDataMapper pageEntityDataMapper = new PageEntityDataMapper();
        return restApi.deleteEquipment(userId, equipmentSn).map(pageEntityDataMapper::transform);
    }

    @Override
    public Observable<DomainEquipmentPage> changeEquipment() {
        RestApiImpl restApi = new RestApiImpl(mContext, new PageEntityJsonMapper());
        PageEntityDataMapper pageEntityDataMapper = new PageEntityDataMapper();
        return restApi.changeEquipment(userId,equipmentSn, deviceID, dvrID, angleRelativeToNorthPole, deviceType, sendMmsState, cma_ID, sensor_ID, equipment_ID).map(pageEntityDataMapper::transform);
    }

    @Override
    public Observable<String> restartEquipment() {
        return null;

    }

    @Override
    public Observable<List<DomainSensor>> getAllSensor() {
        return null;

    }

    @Override
    public Observable<List<DomainSensor>> addSensor() {
        return null;

    }

    @Override
    public Observable<List<DomainSensor>> deleteSensor() {
        return null;

    }

    @Override
    public Observable<List<DomainSensor>> changeSensor() {
        return null;
    }
}
