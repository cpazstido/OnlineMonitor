package com.hy.onlinemonitor.data;


import com.hy.onlinemonitor.R;

/**
 * 类型分类汇总
 * Created by wsw on 2015/7/13.
 */

public class TypeDef {

    /**
     * 全部功能的总分类
     */

    /**
     *
    //父分类
    public static final String[] typeFireDadList = { "功能", "参数配置", "系统管理", "状态监测" };
    public static final String[] typeBreakDadList = { "功能", "参数配置", "系统管理", "状态监测" };
    public static final String[] typeNormalDadList = { "功能", "参数配置", "系统管理", "状态监测" };
    public static final String[] typeAutoPlaneDadList = { "功能", "参数配置", "系统管理"};

    //子分类--功能
    public static final String[] typeFireFunctionText = { "设备列表", "报警信息", "录像回放", "电子地图"};
    public static final int[] typeFireFunctionImages = {R.drawable.gv_tower, R.drawable.gv_fire, R.drawable.gv_tower, R.drawable.gv_tower};

    public static final String[] typeBreakFunction = { "设备列表", "报警信息", "电子地图","计划任务" };
    public static final int[] typeBreakFunctionImages = {R.drawable.gv_tower, R.drawable.gv_fire, R.drawable.gv_tower, R.drawable.gv_tower};

    public static final String[] typeNormalFunction = { "设备列表", "报警信息", "录像回放", "电子地图","图片查询","计划任务" };
    public static final int[] typeNormalFunctionImages = {R.drawable.gv_tower, R.drawable.gv_fire, R.drawable.gv_tower, R.drawable.gv_tower,R.drawable.gv_tower,R.drawable.gv_tower};

    public static final String[] typeAutoPlaneFunction = { "设备列表","录像回放", "电子地图"};
    public static final int[] typeAutoPlaneFunctionImages = {R.drawable.gv_tower, R.drawable.gv_fire, R.drawable.gv_tower};

    //子分类--参数配置
    public static final String[] typeFireConfig = { "设备参数", "阈值参数"};
    public static final int[] typeFireConfigImages = {R.drawable.gv_tower, R.drawable.gv_tower};

    public static final String[] typeBreakConfig = { "设备参数"};
    public static final int[] typeBreakConfigImages = {R.drawable.gv_tower};

    public static final String[] typeNormalConfig = {"阈值参数"};
    public static final int[] typeNormalConfigImages = {R.drawable.gv_tower};

    public static final String[] typeAutoPlaneConfig = { "设备参数", "阈值参数"};
    public static final int[] typeAutoPlaneConfigImages = {R.drawable.gv_tower, R.drawable.gv_tower};

    //子分类--系统管理
    public static final String[] typeFireManage = {"公司", "管理员", "权限", "线路", "杆塔", "设备"};
    public static final int[] typeFireManageImages = {R.drawable.gv_tower, R.drawable.gv_tower, R.drawable.gv_tower, R.drawable.gv_tower, R.drawable.gv_tower, R.drawable.gv_tower};

    public static final String[] typeBreakManage = {"公司", "管理员", "权限", "线路", "杆塔", "设备"};
    public static final int[] typeBreakManageImages = {R.drawable.gv_tower, R.drawable.gv_tower, R.drawable.gv_tower, R.drawable.gv_tower, R.drawable.gv_tower, R.drawable.gv_tower};

    public static final String[] typeNormalManage = {"公司", "管理员", "权限", "线路", "杆塔", "设备"};
    public static final int[] typeNormalManageImages = {R.drawable.gv_tower, R.drawable.gv_tower, R.drawable.gv_tower, R.drawable.gv_tower, R.drawable.gv_tower, R.drawable.gv_tower};

    public static final String[] typeAutoPlaneManage = {"公司", "管理员", "权限", "线路", "杆塔", "设备"};
    public static final int[] typeAutoPlaneManageImages = {R.drawable.gv_tower, R.drawable.gv_tower, R.drawable.gv_tower, R.drawable.gv_tower, R.drawable.gv_tower, R.drawable.gv_tower};

    //子分类--状态监测
    public static final String[] typeFireMonitor = { "状态统计", "状态"};
    public static final int[] typeFireMonitorImages =  {R.drawable.gv_tower, R.drawable.gv_tower};


    public static final String[] typeBreakMonitor = { "状态统计", "状态"};
    public static final int[] typeBreakMonitorImages =  {R.drawable.gv_tower, R.drawable.gv_tower};

    public static final String[] typeNormalMonitor = { "状态统计", "状态"};
    public static final int[] typeNormalMonitorImages =  {R.drawable.gv_tower, R.drawable.gv_tower};
     */

    /**
     * 部分功能实现的分类
     */

    //报警标题
    public static final String[] typeFireAlarmTitle={"山火历史报警","山火新报警","传感器历史报警","传感器新报警"};
    public static final String[] typeBreakAlarmTitle={"外破历史报警","外破新报警","传感器历史报警","传感器新报警"};
    public static final String[] typeNormalAlarmTitle={"传感器历史报警","传感器新报警"};


    //子分类--功能
    public static final String[] typeFireFunctionText = { "设备列表", "报警信息", "电子地图"};
    public static final int[] typeFireFunctionImages = {R.drawable.ic_equipemnt_list, R.drawable.ic_arlarm, R.drawable.ic_map};

    public static final String[] typeBreakFunction = { "设备列表", "报警信息", "电子地图"};
    public static final int[] typeBreakFunctionImages = {R.drawable.ic_equipemnt_list, R.drawable.ic_arlarm,R.drawable.ic_map};

    public static final String[] typeNormalFunction = { "设备列表", "报警信息","电子地图"};
    public static final int[] typeNormalFunctionImages = {R.drawable.ic_equipemnt_list, R.drawable.ic_arlarm,R.drawable.ic_map};

    public static final String[] typeAutoPlaneFunction = { "设备列表","电子地图"};
    public static final int[] typeAutoPlaneFunctionImages = {R.drawable.ic_equipemnt_list,R.drawable.ic_map};

    //子分类--系统管理
    public static final String[] typeFireManage = {"公司", "管理员", "权限", "线路", "杆塔", "设备"};
    public static final int[] typeFireManageImages = {R.drawable.ic_companys, R.drawable.ic_admin, R.drawable.ic_jurisdiction, R.drawable.ic_line, R.drawable.ic_pole, R.drawable.ic_equipment};

    public static final String[] typeBreakManage = {"公司", "管理员", "权限", "线路", "杆塔", "设备"};
    public static final int[] typeBreakManageImages = {R.drawable.ic_companys, R.drawable.ic_admin, R.drawable.ic_jurisdiction, R.drawable.ic_line, R.drawable.ic_pole, R.drawable.ic_equipment};

    public static final String[] typeNormalManage = {"公司", "管理员", "权限", "线路", "杆塔", "设备"};
    public static final int[] typeNormalManageImages = {R.drawable.ic_companys, R.drawable.ic_admin, R.drawable.ic_jurisdiction, R.drawable.ic_line, R.drawable.ic_pole, R.drawable.ic_equipment};

    public static final String[] typeAutoPlaneManage = {"公司", "管理员", "权限", "线路", "杆塔", "设备"};
    public static final int[] typeAutoPlaneManageImages = {R.drawable.ic_companys, R.drawable.ic_admin, R.drawable.ic_jurisdiction, R.drawable.ic_line, R.drawable.ic_pole, R.drawable.ic_equipment};

    public static final String[] typeFireMonitor = { "状态统计", "状态"};
    public static final int[] typeFireMonitorImages =  {R.drawable.gv_tower, R.drawable.gv_tower};

    //子分类--状态监测
    public static final String[] typeBreakMonitor = { "状态统计", "状态"};
    public static final int[] typeBreakMonitorImages =  {R.drawable.gv_tower, R.drawable.gv_tower};

    public static final String[] typeNormalMonitor = { "状态统计", "状态"};
    public static final int[] typeNormalMonitorImages =  {R.drawable.gv_tower, R.drawable.gv_tower};


    //子分类--参数配置
    public static final String[] typeFireConfig = { "设备参数", "阈值参数"};
    public static final int[] typeFireConfigImages = {R.drawable.gv_tower, R.drawable.gv_tower};

    public static final String[] typeBreakConfig = { "设备参数"};
    public static final int[] typeBreakConfigImages = {R.drawable.gv_tower};

    public static final String[] typeNormalConfig = {"阈值参数"};
    public static final int[] typeNormalConfigImages = {R.drawable.gv_tower};

    public static final String[] typeAutoPlaneConfig = { "设备参数", "阈值参数"};
    public static final int[] typeAutoPlaneConfigImages = {R.drawable.gv_tower, R.drawable.gv_tower};
}
