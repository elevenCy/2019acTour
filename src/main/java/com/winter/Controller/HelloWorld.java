package com.winter.Controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.winter.common.HttpRequest;
import com.winter.common.MD5;
import com.winter.common.Ping;
import com.winter.model.*;
import com.winter.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created by h on 2018/12/6.
 */
//@RestController//请求
@Component
public class HelloWorld {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping("/hello")
    public String index(){
        return "Hello World";
    }


    /**
     * 0 0 10,14,16 * * ? 每天上午10点，下午2点，4点
     0 0/30 9-17 * * ?   朝九晚五工作时间内每半小时
     0 0 12 ? * WED 表示每个星期三中午12点 
     "0 0 12 * * ?" 每天中午12点触发 
     "0 15 10 ? * *" 每天上午10:15触发 
     "0 15 10 * * ?" 每天上午10:15触发 
     "0 15 10 * * ? *" 每天上午10:15触发 
     "0 15 10 * * ? 2005" 2005年的每天上午10:15触发 
     "0 * 14 * * ?" 在每天下午2点到下午2:59期间的每1分钟触发 
     "0 0/5 14 * * ?" 在每天下午2点到下午2:55期间的每5分钟触发 
     "0 0/5 14,18 * * ?" 在每天下午2点到2:55期间和下午6点到6:55期间的每5分钟触发 
     "0 0-5 14 * * ?" 在每天下午2点到下午2:05期间的每1分钟触发 
     "0 10,44 14 ? 3 WED" 每年三月的星期三的下午2:10和2:44触发 
     "0 15 10 ? * MON-FRI" 周一至周五的上午10:15触发 
     "0 15 10 15 * ?" 每月15日上午10:15触发 
     "0 15 10 L * ?" 每月最后一日的上午10:15触发 
     "0 15 10 ? * 6L" 每月的最后一个星期五上午10:15触发 
     "0 15 10 ? * 6L 2002-2005" 2002年至2005年的每月的最后一个星期五上午10:15触发 
     "0 15 10 ? * 6#3" 每月的第三个星期五上午10:15触发
     */


    //    @Scheduled(fixedRate=20000)
//    public void testTasks(){
//        logger.info("每20秒执行一次。开始……");
//        List<DimTourDevcVideoSurveillance> list = dimTourDevcVideoSurveillanceService.findBySql("select  * from dim_tour_devc_video_surveillance");
//        logger.info(""+list.size());
//        logger.info("每20秒执行一次。结束。");
//    }
    private static final String IP= "http://172.16.10.10:86";
    private static final String URLgetEncoders = "/openapi/service/vss/res/getEncoders";
    private static final String URLgetCamerasEx = "/openapi/service/vss/res/getCamerasEx";

    private static final String APPKEYNAME = "appkey";
    private static final String APPKEY = "dd86e729";

    private static final String SECRETNAME= "screct";
    private static final String SECRET = "35d6f2fd551a418d83d8c1b1878d93b3";

    private static final String OPUSERUUIDNAME = "opUserUuid";
    private static final String OPUSERUUID = "5b2eb534696b11e89c2e438f92627767";
//    //视频编码
//    @Scheduled(fixedRate=20000)
//    public void requestDevcVideoSurveillanceInfo(){
//        HttpRequest httpRequest = new  HttpRequest();
//        MD5 md5 = new MD5();
//        Date now  = new Date();
//        String timeName = "time";
//        Long time = now.getTime();
//        String pageNoName = "pageNo";
//        int pageNo = 1;
//        String pageSizeName = "pageSize";
//        int pageSize = 99;
//
//        String jsonString = "{" +
//                "\""+APPKEYNAME+"\":\""+APPKEY+"\"," +
//                "\""+timeName+"\":\""+time+"\"," +
//                "\""+OPUSERUUIDNAME+"\":\""+OPUSERUUID+"\"," +
//                "\""+pageNoName+"\":\""+pageNo+"\"," +
//                "\""+pageSizeName+"\":\""+pageSize+"\""+
//        "}";
//        String token = md5.encryption(URL+jsonString+SECRET);
//        String ipAddress = IP+URL+"?token="+token;
//        String res = httpRequest.sendPostJsonStr(ipAddress,jsonString);
//        logger.info(res);
//        JSONObject jsonObject = JSON.parseObject(res);
//        if(jsonObject.getString("errorCode").equals("0")){
//            JSONObject dataJson = jsonObject.getJSONObject("data");//data 含 total pageNo pageSize list
//            JSONArray jsonArray = dataJson.getJSONArray("list");
//
//            String sql = "select * from dim_tour_devc_video_surveillance";
//            List<DimTourDevcVideoSurveillance> dimTourDevcVideoSurveillances = dimTourDevcVideoSurveillanceService.findBySql(sql);
//
//            //遍历方式2
//            for (Object obj : jsonArray) {
//                JSONObject jsonObj = (JSONObject) obj;
//                String encoderUuid = jsonObj.getString("encoderUuid");//编码设备UUID
//                String encoderName = jsonObj.getString("encoderName");//编码设备名称
//                String encoderModel = jsonObj.getString("encoderModel");//编码设备类型码
//                String encoderUserName = jsonObj.getString("encoderUserName");//设备用户名
//                String encoderPort = jsonObj.getString("encoderPort");//设备用户名
//                String encoderIp = jsonObj.getString("encoderIp");//设备IP
//                String smartType = jsonObj.getString("smartType");//专业智能类型码
//                String smartSupport = jsonObj.getString("smartSupport");//是否支持智能
//                String devType = jsonObj.getString("devType");//海康设备类型码
//                String orderNum = jsonObj.getString("orderNum");//排序字段
//                String unitUuid = jsonObj.getString("unitUuid");//中心UUID
//                String updateTime = jsonObj.getString("updateTime");//更新时间
//                String alarmIn = jsonObj.getString("alarmIn");//报警输入数
//                String alarmOut = jsonObj.getString("alarmOut");//报警输出数
//                String visIntercomChanNum = jsonObj.getString("visIntercomChanNum");//可视对讲通道数
//                String resAuths = jsonObj.getString("resAuths");//	资源权限集
//                DimTourDevcVideoSurveillance haveItme = null;
//                if(!dimTourDevcVideoSurveillances.isEmpty()){
//                    for(int i=0;i<dimTourDevcVideoSurveillances.size();i++){
//                        DimTourDevcVideoSurveillance item = dimTourDevcVideoSurveillances.get(i);
//                        if(item.getId().equals(encoderUuid)){//存在
//                            dimTourDevcVideoSurveillances.remove(i);
//                            haveItme = item;
//                            break;
//                        }
//                    }
//                    if(haveItme!=null){
//                        logger.info("数据库中含有视频设备>>>>>>更新视频设备信息");
//                        haveItme.setName(encoderName);
//
//                        haveItme.setIp(encoderIp);
//                        haveItme.setPort(encoderPort);
//
//                        haveItme.setUpdateTime(now);
//                        haveItme.setUpdateTime(now);
//                        if(haveItme.getStatus()!=1){
//                            haveItme.setStatus(1);
//                        }
//                        dimTourDevcVideoSurveillanceService.update(haveItme);
//                    }else{
//                        logger.info("数据库中没有视频设备>>>>>>添加视频设备信息");
//                        DimTourDevcVideoSurveillance dimTourDevcVideoSurveillance = new DimTourDevcVideoSurveillance();
//                        dimTourDevcVideoSurveillance.setId(encoderUuid);
//
//                        dimTourDevcVideoSurveillance.setName(encoderName);
//                        dimTourDevcVideoSurveillance.setIp(encoderIp);
//                        dimTourDevcVideoSurveillance.setPort(encoderPort);
//
//                        dimTourDevcVideoSurveillance.setCreateTime(now);
//                        dimTourDevcVideoSurveillance.setUpdateTime(now);
//                        dimTourDevcVideoSurveillance.setStatus(1);
//                        if(orderNum!=null&&!orderNum.equals("")){
//                            dimTourDevcVideoSurveillance.setSort(Integer.parseInt(orderNum));
//                        }else{
//                            dimTourDevcVideoSurveillance.setSort(0);
//                        }
//                        dimTourDevcVideoSurveillanceService.insert(dimTourDevcVideoSurveillance);
//                    }
//                }else{
//                    logger.info("数据库中没有视频设备>>>>>>添加视频设备信息");
//                    DimTourDevcVideoSurveillance dimTourDevcVideoSurveillance = new DimTourDevcVideoSurveillance();
//                    dimTourDevcVideoSurveillance.setId(encoderUuid);
//
//                    dimTourDevcVideoSurveillance.setName(encoderName);
//                    dimTourDevcVideoSurveillance.setIp(encoderIp);
//                    dimTourDevcVideoSurveillance.setPort(encoderPort);
//
//                    dimTourDevcVideoSurveillance.setCreateTime(now);
//                    dimTourDevcVideoSurveillance.setUpdateTime(now);
//                    dimTourDevcVideoSurveillance.setStatus(1);
//                    if(orderNum!=null&&!orderNum.equals("")){
//                        dimTourDevcVideoSurveillance.setSort(Integer.parseInt(orderNum));
//                    }else{
//                        dimTourDevcVideoSurveillance.setSort(0);
//                    }
//                    dimTourDevcVideoSurveillanceService.insert(dimTourDevcVideoSurveillance);
//                }
//            }
//        }else{
//            String errorCode = jsonObject.getString("errorCode");
//            String errorMessage = jsonObject.getString("errorMessage");
//            logger.info("errorCode:"+errorCode);
//            logger.info("errorMessage:"+errorMessage);
//        }
//    }

    //监控点位
    @Scheduled(fixedRate=3600000)
    public void requestDevcVideoSurveillanceInfo(){
        HttpRequest httpRequest = new  HttpRequest();
        MD5 md5 = new MD5();
        Date now  = new Date();
        String timeName = "time";
        Long time = now.getTime();
        String pageNoName = "pageNo";
        int pageNo = 1;
        String pageSizeName = "pageSize";
        int pageSize = 150;

        String jsonString = "{" +
                "\""+APPKEYNAME+"\":\""+APPKEY+"\"," +
                "\""+timeName+"\":\""+time+"\"," +
                "\""+OPUSERUUIDNAME+"\":\""+OPUSERUUID+"\"," +
                "\""+pageNoName+"\":\""+pageNo+"\"," +
                "\""+pageSizeName+"\":\""+pageSize+"\""+
                "}";
        String token = md5.encryption(URLgetCamerasEx+jsonString+SECRET);
        String ipAddress = IP+URLgetCamerasEx+"?token="+token;
        String res = httpRequest.sendPostJsonStr(ipAddress,jsonString);
        logger.info(res);
        JSONObject jsonObject = JSON.parseObject(res);
        if(jsonObject.getString("errorCode").equals("0")){
            JSONObject dataJson = jsonObject.getJSONObject("data");//data 含 total pageNo pageSize list
            JSONArray jsonArray = dataJson.getJSONArray("list");
            //遍历方式2
            for (Object obj : jsonArray) {
                JSONObject jsonObj = (JSONObject) obj;
                String cameraUuid = jsonObj.getString("cameraUuid");//监控点UUID
                String cameraName  = jsonObj.getString("cameraName");//监控点名称
                String cameraType = jsonObj.getString("cameraType");//监控点类型 0枪机1半球2快球 3带云镜枪机
                String cameraChannelNum = jsonObj.getString("cameraChannelNum");//通道号
                String smartType = jsonObj.getString("smartType");//专业智能类型码
                String smartSupport = jsonObj.getString("smartSupport");//是否支持智能
                String onLineStatus = jsonObj.getString("onLineStatus");//在线状态
                String keyBoardCode = jsonObj.getString("keyBoardCode");//键盘矩阵UUID
                String orderNum = jsonObj.getString("orderNum");//排序字段
                String updateTime = jsonObj.getString("updateTime");//更新时间
                String unitUuid = jsonObj.getString("unitUuid");//中心UUID
                String regionUuid = jsonObj.getString("regionUuid");//区域UUID
                String encoderUuid = jsonObj.getString("encoderUuid");//编码设备UUID
                String resAuths = jsonObj.getString("resAuths");//	资源权限集
                if(cameraName.indexOf("报警柱")!=-1) {//插入sos表
                    updateDevcSosAlarmInfo(now, cameraUuid, cameraName, cameraType, orderNum, onLineStatus, encoderUuid);
                }else{
                    updateDevcVideoSurveillanceInfo(now, cameraUuid, cameraName, cameraType, orderNum, onLineStatus, encoderUuid);
                }
            }
        }else{
            String errorCode = jsonObject.getString("errorCode");
            String errorMessage = jsonObject.getString("errorMessage");
            logger.info("errorCode:"+errorCode);
            logger.info("errorMessage:"+errorMessage);
        }
    }
    @Autowired
    private DimTourDevcVideoSurveillanceService dimTourDevcVideoSurveillanceService;
    public void updateDevcVideoSurveillanceInfo(Date now,String cameraUuid,String cameraName,String cameraType,String orderNum,String onLineStatus,String encoderUuid){
        String sql = "select * from dim_tour_devc_video_surveillance";
        List<DimTourDevcVideoSurveillance> dimTourDevcVideoSurveillances = dimTourDevcVideoSurveillanceService.findBySql(sql);
        DimTourDevcVideoSurveillance haveItme = null;
        if(!dimTourDevcVideoSurveillances.isEmpty()){
            for(int i=0;i<dimTourDevcVideoSurveillances.size();i++){
                DimTourDevcVideoSurveillance item = dimTourDevcVideoSurveillances.get(i);
                if(item.getId().equals(cameraUuid)){//存在
                    dimTourDevcVideoSurveillances.remove(i);
                    haveItme = item;
                    break;
                }
            }
            if(haveItme!=null){
                logger.info("数据库中含有视频设备>>>>>>更新视频设备信息:"+cameraName);
                haveItme.setName(cameraName);
                haveItme.setType(cameraType);
                if(orderNum!=null&&!orderNum.equals("")){
                    haveItme.setSort(Integer.parseInt(orderNum));
                }else{
                    haveItme.setSort(0);
                }
                if(onLineStatus!=null&&!onLineStatus.equals("")){
                    haveItme.setStatus(Integer.parseInt(onLineStatus));
                }else{
                    haveItme.setStatus(0);
                }
                haveItme.setUpdateTime(now);

                haveItme.setCode(encoderUuid);

                dimTourDevcVideoSurveillanceService.update(haveItme);
            }else{
                logger.info("数据库中没有视频设备>>>>>>添加视频设备信息:"+cameraName);
                DimTourDevcVideoSurveillance dimTourDevcVideoSurveillance = new DimTourDevcVideoSurveillance();
                dimTourDevcVideoSurveillance.setId(cameraUuid);
                dimTourDevcVideoSurveillance.setName(cameraName);
                dimTourDevcVideoSurveillance.setType(cameraType);
                if(orderNum!=null&&!orderNum.equals("")){
                    dimTourDevcVideoSurveillance.setSort(Integer.parseInt(orderNum));
                }else{
                    dimTourDevcVideoSurveillance.setSort(0);
                }
                if(onLineStatus!=null&&!onLineStatus.equals("")){
                    dimTourDevcVideoSurveillance.setStatus(Integer.parseInt(onLineStatus));
                }else{
                    dimTourDevcVideoSurveillance.setStatus(0);
                }
                dimTourDevcVideoSurveillance.setCreateTime(now);
                dimTourDevcVideoSurveillance.setUpdateTime(now);
                dimTourDevcVideoSurveillance.setCode(encoderUuid);
                dimTourDevcVideoSurveillanceService.insert(dimTourDevcVideoSurveillance);
            }
        }else{
            logger.info("数据库中没有视频设备>>>>>>添加视频设备信息:"+cameraName);
            DimTourDevcVideoSurveillance dimTourDevcVideoSurveillance = new DimTourDevcVideoSurveillance();
            dimTourDevcVideoSurveillance.setId(cameraUuid);
            dimTourDevcVideoSurveillance.setName(cameraName);
            dimTourDevcVideoSurveillance.setType(cameraType);
            if(orderNum!=null&&!orderNum.equals("")){
                dimTourDevcVideoSurveillance.setSort(Integer.parseInt(orderNum));
            }else{
                dimTourDevcVideoSurveillance.setSort(0);
            }
            if(onLineStatus!=null&&!onLineStatus.equals("")){
                dimTourDevcVideoSurveillance.setStatus(Integer.parseInt(onLineStatus));
            }else{
                dimTourDevcVideoSurveillance.setStatus(0);
            }
            dimTourDevcVideoSurveillance.setCreateTime(now);
            dimTourDevcVideoSurveillance.setUpdateTime(now);
            dimTourDevcVideoSurveillance.setCode(encoderUuid);
            dimTourDevcVideoSurveillanceService.insert(dimTourDevcVideoSurveillance);
        }
    }

    @Autowired
    private DimTourDevcSosAlarmService dimTourDevcSosAlarmService;
    public void updateDevcSosAlarmInfo(Date now,String cameraUuid,String cameraName,String cameraType,String orderNum,String onLineStatus,String encoderUuid){
        String sql = "select * from dim_tour_devc_sos_alarm";
        List<DimTourDevcSosAlarm> DimTourDevcSosAlars = dimTourDevcSosAlarmService.findBySql(sql);
        DimTourDevcSosAlarm haveItme = null;
        if(!DimTourDevcSosAlars.isEmpty()){
            for(int i=0;i<DimTourDevcSosAlars.size();i++){
                DimTourDevcSosAlarm item = DimTourDevcSosAlars.get(i);
                if(item.getId().equals(cameraUuid)){//存在
                    DimTourDevcSosAlars.remove(i);
                    haveItme = item;
                    break;
                }
            }
            if(haveItme!=null){
                logger.info("数据库中含有视频设备>>>>>>更新视频设备信息:"+cameraName);
                haveItme.setName(cameraName);
//                haveItme.setType(cameraType);
                if(orderNum!=null&&!orderNum.equals("")){
                    haveItme.setSort(Integer.parseInt(orderNum));
                }else{
                    haveItme.setSort(0);
                }
                if(onLineStatus!=null&&!onLineStatus.equals("")){
                    haveItme.setStatus(Integer.parseInt(onLineStatus));
                }else{
                    haveItme.setStatus(0);
                }
                haveItme.setUpdateTime(now);

                haveItme.setCode(encoderUuid);

                dimTourDevcSosAlarmService.update(haveItme);
            }else{
                logger.info("数据库中没有视频设备>>>>>>添加视频设备信息:"+cameraName);
                DimTourDevcSosAlarm dimTourDevcSosAlarm = new DimTourDevcSosAlarm();
                dimTourDevcSosAlarm.setId(cameraUuid);
                dimTourDevcSosAlarm.setName(cameraName);
//                dimTourDevcSosAlarm.setType(cameraType);
                if(orderNum!=null&&!orderNum.equals("")){
                    dimTourDevcSosAlarm.setSort(Integer.parseInt(orderNum));
                }else{
                    dimTourDevcSosAlarm.setSort(0);
                }
                if(onLineStatus!=null&&!onLineStatus.equals("")){
                    dimTourDevcSosAlarm.setStatus(Integer.parseInt(onLineStatus));
                }else{
                    dimTourDevcSosAlarm.setStatus(0);
                }
                dimTourDevcSosAlarm.setCreateTime(now);
                dimTourDevcSosAlarm.setUpdateTime(now);
                dimTourDevcSosAlarm.setCode(encoderUuid);
                dimTourDevcSosAlarmService.insert(dimTourDevcSosAlarm);
            }
        }else{
            logger.info("数据库中没有视频设备>>>>>>添加视频设备信息:"+cameraName);
            DimTourDevcSosAlarm dimTourDevcSosAlarm = new DimTourDevcSosAlarm();
            dimTourDevcSosAlarm.setId(cameraUuid);
            dimTourDevcSosAlarm.setName(cameraName);
//            DimTourDevcSosAlarm.setType(cameraType);
            if(orderNum!=null&&!orderNum.equals("")){
                dimTourDevcSosAlarm.setSort(Integer.parseInt(orderNum));
            }else{
                dimTourDevcSosAlarm.setSort(0);
            }
            if(onLineStatus!=null&&!onLineStatus.equals("")){
                dimTourDevcSosAlarm.setStatus(Integer.parseInt(onLineStatus));
            }else{
                dimTourDevcSosAlarm.setStatus(0);
            }
            dimTourDevcSosAlarm.setCreateTime(now);
            dimTourDevcSosAlarm.setUpdateTime(now);
            dimTourDevcSosAlarm.setCode(encoderUuid);
            dimTourDevcSosAlarmService.insert(dimTourDevcSosAlarm);
        }
    }





    /************************广播*************************/
    private static final String ITCIP = "http://172.16.20.2:80";

    /*登录*/
    private static final String ITC_URL_LOGIN = "/v1/session/";
    private static final String ITC_ID_LOGIN = "login";
    /*获取全部广播*/
    private static final String ITC_URL_GET_ALL = "/v1/terminal/currentStatus/";
    private static final String ITC_ID_GET_ALL = "json_endpoint_status";

    private static final String ITC_VERSION_NAME = "VERSION";
    private static final String ITC_VERSION = "1.0";

    private static final String ITC_ID_NAME = "ID";

    private static final String ITC_TOKEN_NAME = "TOKEN";

    private static final String ITC_PARA_NAME = "PARA";
    private static final String ITC_ADMIN_NAME = "js_username";
    private static final String ITC_ADMIN ="admin";
    private static final String ITCIP_POSSWORD_NAME = "js_password";
    private static final String ITCIP_POSSWORD = "123456";
    private static final String ITCIP_JSN_USER_INFO_NAME = "jsn_user_info";
    private static final String  ITCIP_JSN_USER_INFO = "[]";


    private static final String ITC_RESULT_NAME = "RESULT";
    private static final Integer ITC_RESULT = 0;

    private static final String ITC_BODY_NAME = "BODY";
    private static final String ITC_BODY = "";

    @Scheduled(fixedRate=3600000)
    public void requestDevcPublicBroadcast(){
        String TOKEN = loginIPCGetToken();
        if(TOKEN!=null&&!TOKEN.equals("")){
            String ipAddress = ITCIP+ITC_URL_GET_ALL;
            JSONObject jsonObject = new JSONObject();
            jsonObject.put(ITC_BODY_NAME,ITC_BODY);
            jsonObject.put(ITC_ID_NAME,ITC_ID_GET_ALL);
            jsonObject.put(ITC_PARA_NAME,"");
            jsonObject.put(ITC_RESULT_NAME,ITC_RESULT);
            jsonObject.put(ITC_TOKEN_NAME,TOKEN);
            jsonObject.put(ITC_VERSION_NAME,ITC_VERSION);
            String res = HttpRequest.sendPostJsonStr(ipAddress,jsonObject.toString());
            Date now = new Date();
            logger.info(res);
            JSONObject resJsonObject = JSON.parseObject(res);
            logger.info(resJsonObject.getString("PARA"));
            if(resJsonObject.getString("PARA")!=null&&!resJsonObject.getString("PARA").equals("")){
                JSONArray jsonArray = resJsonObject.getJSONArray("PARA");
                //遍历方式2
                for (Object obj : jsonArray) {
                    JSONObject jsonObj = (JSONObject) obj;
                    String js_endpoint_address = jsonObj.getString("js_endpoint_address");//终端 ip
                    String js_endpoint_id  = jsonObj.getString("js_endpoint_id");//终端 id
                    String js_endpoint_mac_addr = jsonObj.getString("js_endpoint_mac_addr");//终端 mac
                    String js_endpoint_name = jsonObj.getString("js_endpoint_name");//终端名称
                    String js_endpoint_netifs = jsonObj.getString("js_endpoint_netifs");///终端调试信息
                    String js_endpoint_online = jsonObj.getString("js_endpoint_online");///1-在线 0-离线
                    String js_endpoint_type = jsonObj.getString("js_endpoint_type");//终端类型
                    String js_endpoint_type1 = jsonObj.getString("js_endpoint_type1");//硬件版本
                    String js_endpoint_version = jsonObj.getString("js_endpoint_version");//
                    String js_endpoint_version1 = jsonObj.getString("js_endpoint_version1");//
                    String js_endpoint_volume = jsonObj.getString("js_endpoint_volume");//当前音量
                    String js_task_hashkey = jsonObj.getString("js_task_hashkey");//任务 hashkey
                    String js_task_name = jsonObj.getString("js_task_name");//当前任务名称
                    String js_task_priority = jsonObj.getString("js_task_priority");//当前任务优先级
                    String js_task_type = jsonObj.getString("js_task_type");//任务类型
                    String js_task_user = jsonObj.getString("js_task_user");//当前任务发起者
                    String endpoint_id = jsonObj.getString("endpoint_id");//终端 id
                    updateDevcPublicBroadcast(now,js_endpoint_id,js_endpoint_name,js_endpoint_online,js_endpoint_address);
                }
            }
        }else{
            logger.info("token获取失败");
        }
    }
    @Autowired
    private DimTourDevcPublicBroadcastService dimTourDevcPublicBroadcastService;
    public void updateDevcPublicBroadcast(Date now,String js_endpoint_id,String js_endpoint_name,String js_endpoint_online,String js_endpoint_address){
        String sql = "select * from dim_tour_devc_public_broadcast";
        List<DimTourDevcPublicBroadcast> dimTourDevcPublicBroadcasts = dimTourDevcPublicBroadcastService.findBySql(sql);
        DimTourDevcPublicBroadcast haveItme = null;
        if(!dimTourDevcPublicBroadcasts.isEmpty()){
            for(int i=0;i<dimTourDevcPublicBroadcasts.size();i++){
                DimTourDevcPublicBroadcast item = dimTourDevcPublicBroadcasts.get(i);
                if(item.getId().equals(js_endpoint_id)){//存在
                    dimTourDevcPublicBroadcasts.remove(i);
                    haveItme = item;
                    break;
                }
            }
            if(haveItme!=null){
                logger.info("数据库中含有广播设备>>>>>>更新广播设备信息:"+js_endpoint_name);

                haveItme.setName(js_endpoint_name);
                haveItme.setSort(0);

                if(js_endpoint_online!=null&&!js_endpoint_online.equals("")){
                    haveItme.setStatus(Integer.parseInt(js_endpoint_online));
                }else{
                    haveItme.setStatus(0);
                }
                if(js_endpoint_address!=null&&!js_endpoint_address.equals("")){
                    haveItme.setIp(js_endpoint_address);
                }
                haveItme.setUpdateTime(now);

                dimTourDevcPublicBroadcastService.update(haveItme);
            }else{
                logger.info("数据库中没有广播设备>>>>>>添加广播设备信息:"+js_endpoint_name);
                DimTourDevcPublicBroadcast dimTourDevcPublicBroadcast = new DimTourDevcPublicBroadcast();
                dimTourDevcPublicBroadcast.setId(js_endpoint_id);
                dimTourDevcPublicBroadcast.setName(js_endpoint_name);
                dimTourDevcPublicBroadcast.setSort(0);
                if(js_endpoint_online!=null&&!js_endpoint_online.equals("")){
                    dimTourDevcPublicBroadcast.setStatus(Integer.parseInt(js_endpoint_online));
                }else{
                    dimTourDevcPublicBroadcast.setStatus(0);
                }
                if(js_endpoint_address!=null&&!js_endpoint_address.equals("")){
                    dimTourDevcPublicBroadcast.setIp(js_endpoint_address);
                }
                dimTourDevcPublicBroadcast.setCreateTime(now);
                dimTourDevcPublicBroadcast.setUpdateTime(now);
                dimTourDevcPublicBroadcastService.insert(dimTourDevcPublicBroadcast);
            }
        }else{
            logger.info("数据库中没有广播设备>>>>>>添加广播设备信息:"+js_endpoint_name);
            DimTourDevcPublicBroadcast dimTourDevcPublicBroadcast = new DimTourDevcPublicBroadcast();
            dimTourDevcPublicBroadcast.setId(js_endpoint_id);
            dimTourDevcPublicBroadcast.setName(js_endpoint_name);
            dimTourDevcPublicBroadcast.setSort(0);
            if(js_endpoint_online!=null&&!js_endpoint_online.equals("")){
                dimTourDevcPublicBroadcast.setStatus(Integer.parseInt(js_endpoint_online));
            }else{
                dimTourDevcPublicBroadcast.setStatus(0);
            }
            if(js_endpoint_address!=null&&!js_endpoint_address.equals("")){
                haveItme.setIp(js_endpoint_address);
            }
            dimTourDevcPublicBroadcast.setCreateTime(now);
            dimTourDevcPublicBroadcast.setUpdateTime(now);
            dimTourDevcPublicBroadcastService.insert(dimTourDevcPublicBroadcast);
        }
    }
    public String loginIPCGetToken(){
        String jsonString = "{" +
            "\""+ITC_ADMIN_NAME+"\":\""+ITC_ADMIN+"\"," +
            "\""+ITCIP_POSSWORD_NAME+"\":\""+ITCIP_POSSWORD+"\"," +
            "\""+ITCIP_JSN_USER_INFO_NAME+"\":"+ITCIP_JSN_USER_INFO+"," +
        "}";
        String ipAddress = ITCIP+ITC_URL_LOGIN;
        net.sf.json.JSONObject obj = new net.sf.json.JSONObject();
        obj.element(ITC_VERSION_NAME,ITC_VERSION);
        obj.element(ITC_ID_NAME,ITC_ID_LOGIN);
        obj.element(ITC_TOKEN_NAME,"");
        obj.element(ITC_PARA_NAME,jsonString);
        obj.element(ITC_RESULT_NAME,ITC_RESULT);
        String res = HttpRequest.POSturl(ipAddress,obj).toString();
        logger.info(res);
        JSONObject resJsonObject = JSON.parseObject(res);
        logger.info(resJsonObject.getString(ITC_TOKEN_NAME));
        return resJsonObject.getString(ITC_TOKEN_NAME);
    }

    private static final String WIFI_URL = "http://172.16.50.100:8080/imcrs/wlan/apInfo/queryApBasicInfo";
    @Autowired
    private DimTourDevcWifiService dimTourDevcWifiService;

    //wifi设备状态位及连接数
//    @Scheduled(fixedRate=1800000) //5分钟
    public void wifiAnalysis(){
        String sql = "SELECT * FROM `actour`.`dim_tour_devc_wifi`";
        HttpRequest httpRequest = new  HttpRequest();
        net.sf.json.JSONObject json = HttpRequest.sendGetForWIFI(WIFI_URL);
        if(json!=null) {
            net.sf.json.JSONArray array = json.getJSONArray("apBasicInfo");
            for (int i = 0; i < array.size(); i++) {
                DimTourDevcWifi dimTourDevcWifi = new DimTourDevcWifi();
                net.sf.json.JSONObject jsb = array.getJSONObject(i);  // 遍历 jsonarray 数组，把每一个对象转成 json 对象
                //String mac = jsb.get("macAddress").toString();  //MAC地址
                //String ip = jsb.get("ipAddress").toString();   //IP地址
                //String status = jsb.get("onlineStatus").toString();    //在线状态  0表示不在线，1表示在线
                //String count = jsb.get("onlineClientCount").toString();   //在线移动用户数
                String name = jsb.get("apAlias").toString(); //AP别名
                //String code = jsb.get("serialId").toString(); //AC的设备ID。
                dimTourDevcWifi.setId(UUID.randomUUID().toString().trim().replaceAll("-", ""));
                dimTourDevcWifi.setResourceId("");
                dimTourDevcWifi.setName(jsb.get("apAlias").toString());
                dimTourDevcWifi.setCode(jsb.get("serialId").toString());
                dimTourDevcWifi.setIp(jsb.get("ipAddress").toString());
                dimTourDevcWifi.setMac(jsb.get("macAddress").toString());
                dimTourDevcWifi.setStatus(jsb.getInt("onlineStatus"));
                dimTourDevcWifi.setRemark(jsb.get("onlineClientCount").toString());
                dimTourDevcWifi.setSort(i+1);
                dimTourDevcWifi.setUpdateTime(new Date());
                dimTourDevcWifi.setCreateTime(new Date());
                //System.out.println("mac:  " + mac + "  -  IP:  " + ip + "   -  状态:  " + status + "   -  ID:  " + code + "  - 名称:  " + name + "  -  连接数:  " + count);
                sql = "SELECT * FROM `dim_tour_devc_wifi` WHERE name = '"+name+"';";
                System.out.println(sql);
                List<DimTourDevcWifi> list = dimTourDevcWifiService.findBySql(sql);
                if(list.size()==0){
                    dimTourDevcWifiService.insert(dimTourDevcWifi);
                }else{
                    dimTourDevcWifiService.update(dimTourDevcWifi);
                }
            }
         }
    }

    private static final String PARK_URL = "http://172.16.10.66:18080";
    private static final String getParkingInfo = "/getParkingInfo";//不需要参数获取停车场信息
    private static final String getVehicleInOutRecordPage = "/getVehicleInOutRecordPage";//分页查询停车场内的车辆进出记录


    @Autowired
    private DwdTourParkMonitorRtService dwdTourParkMonitorRtService;
    @Autowired
    private OdsTourTrlCarInfoService odsTourTrlCarInfoService;
    @Scheduled(fixedRate=1800000) //6分钟
    public void getVehicleInOutRecordPage(){
        try {
//            if(Ping.ping("172.16.10.66")){
                HttpRequest httpRequest = new  HttpRequest();
                String ipAddress = PARK_URL + getVehicleInOutRecordPage;
        //        1 pageNo int 页号 页号从 1 开始,当前查询页数
        //        2 pageSize int 页大小 每页数据条数
        //        3 plateNo string (可选)车牌号码 苏 E88888
        //        4 cardNo string (可选)卡号 4578945614
        //        5 beginTime string (可选)开始时间 2016-04-28 12:12:12
        //        6 endTime string (可选)结束时间 2016-05-28 12:12:12
        //        7 direction int (可选)行驶方向
        //        1 仅获取入场
        //        2 仅获取出场
        //                默认入场和出场全部获取
        //        8 specialPass int (可选)特殊放行
        //        1 仅软件异常放行
        //        2 仅遥控或摇杆放行(道闸开关到位信号需接入报警输入 端)
        //        默认获取全部的过车数据

                //获取本地数据库中最新过车记录的时间->用此时间去查询数据->有记录插入
                int pageNo = 1;
                //String beginTime = "2018-11-01 01:00:00";
                String beginTime = "2019-12-17 00:00:00";
                String sql = "select * from ods_tour_trl_car_info order by passTime desc limit 0,1";
                logger.info(">>>>>>>>>>>>>>>>>>>>>>查询本地数据库中的过车记录");
                List<OdsTourTrlCarInfo> odsTourTrlCarInfos = odsTourTrlCarInfoService.findBySql(sql);
                if(!odsTourTrlCarInfos.isEmpty()){
                    beginTime =  odsTourTrlCarInfos.get(0).getPassTime();
                    logger.info(">>>>>>>>>>>>>>>>>>>>>>查询本地数据库中的已有部分过车记录"+beginTime);
                }else{
                    logger.info(">>>>>>>>>>>>>>>>>>>>>>查询本地数据库中的没有过车记录"+beginTime);
                }
                int timeOut = 0;
                while(true){
                    String jsonString = "{" +
                            "\"pageNo\":"+pageNo+"," +
                            "\"pageSize\":100," +
                            "\"beginTime\":\""+beginTime+"\"" +//已经判断 对方的接口中的查询方式是 >beginTime
                        "}";
                    String res = httpRequest.sendPostJsonStr(ipAddress,jsonString);
                    if(res!="error"){
                        logger.info(res);
                        JSONObject resJsonObject = JSON.parseObject(res);
                        OdsTourTrlCarInfo odsTourTrlCarInfo = new OdsTourTrlCarInfo();
                        if(resJsonObject.getInteger("code")==0){
                            int pageSize = resJsonObject.getInteger("pageSize");
                            int total = resJsonObject.getInteger("total");
                            JSONArray jsonArray = resJsonObject.getJSONArray("VehicleInOutRecords");
                            for (Object obj : jsonArray) {
                                JSONObject jsonObj = (JSONObject) obj;
                                String uniqueNo = jsonObj.getString("uniqueNo");//1
                                int direction = jsonObj.getInteger("direction");//2
                                String plateNo = jsonObj.getString("plateNo");//3
                                String cardNo = jsonObj.getString("cardNo");//4
                                String passTime = jsonObj.getString("passTime");//5
                                int vehType = jsonObj.getInteger("vehType");//6
                                int vehColor = jsonObj.getInteger("vehColor");//7
                                String operatorName = jsonObj.getString("operatorName");//8
                                String terminalNo = jsonObj.getString("terminalNo");//9
                                String gateName = jsonObj.getString("gateName");//10
                                String laneName = jsonObj.getString("laneName");//11
                //                String laneCode = jsonObj.getString("laneCode");//12 此行不加
                                String passType = jsonObj.getString("passType");//13
                                String inPassTime = jsonObj.getString("inPassTime");//14
                                String inUniqueNo = jsonObj.getString("inUniqueNo");//15
                //                int shouldPay = jsonObj.getInteger("shouldPay");//16
                //                int actualPay = jsonObj.getInteger("actualPay");//17
                                String picFilePath = jsonObj.getString("picFilePath");//18
                                String plateFilePath = jsonObj.getString("plateFilePath");//19

                                odsTourTrlCarInfo.setUniqueNo(uniqueNo);
                                odsTourTrlCarInfo.setDirection(direction);
                                odsTourTrlCarInfo.setPlateNo(plateNo);
                                odsTourTrlCarInfo.setCardNo(cardNo);
                                odsTourTrlCarInfo.setPassTime(passTime);
                                odsTourTrlCarInfo.setVehType(vehType);
                                odsTourTrlCarInfo.setVehColor(vehColor);
                                odsTourTrlCarInfo.setOperatorName(operatorName);
                                odsTourTrlCarInfo.setTerminalNo(terminalNo);
                                odsTourTrlCarInfo.setGateName(gateName);
                                odsTourTrlCarInfo.setLaneName(laneName);
                                odsTourTrlCarInfo.setPassType(passType);
                                odsTourTrlCarInfo.setInPassTime(inPassTime);
                                odsTourTrlCarInfo.setInUniqueNo(inUniqueNo);
                //                odsTourTrlCarInfo.setShouldPay(shouldPay);
                //                odsTourTrlCarInfo.setActualPay(actualPay);
                                odsTourTrlCarInfo.setPicFilePath(picFilePath);
                                odsTourTrlCarInfo.setPlateFilePath(plateFilePath);
                                logger.info(">>>>>>>>>>>>>>>>>>>>>>插入记录");
                                odsTourTrlCarInfoService.insert(odsTourTrlCarInfo);
                            }
                            if(pageSize>total) break;
                            else beginTime = odsTourTrlCarInfo.getPassTime();
                        }else{
                            logger.info(">>>>>>>>>>>>>>>>>>>>>>请求失败");
                            return ;
                        }
                    }
                }
//            }else{
//                logger.info(">>>>>>>>>>>>>>>>>>>>>>网络不通");
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //net.sf.json.JSONObject json = HttpRequest.sendGetForWIFI(PARK_URL+getPassVehicle);
    }

    @Scheduled(fixedRate=600000) //5分钟
    public void getParkingInfo(){
        try {
//            if(Ping.ping("172.16.10.66")){
                HttpRequest httpRequest = new  HttpRequest();
                String ipAddress = PARK_URL + getParkingInfo;
                String res = httpRequest.sendPostJsonStr(ipAddress,null);
                logger.info(res);
                JSONObject resJsonObject = JSON.parseObject(res);
                if(resJsonObject.getInteger("code")==0){
                    String parkCode = resJsonObject.getString("regionName");//停车场code 名称不改
                    logger.info(parkCode);
                    JSONArray jsonArray = resJsonObject.getJSONArray("regionInfos");
                    int total = 0;
                    int current = 0;
                    //遍历方式2
                    for (Object obj : jsonArray) {
                        JSONObject jsonObj = (JSONObject) obj;
                        String regionName = jsonObj.getString("regionName");//区域名称
                        int regionId = jsonObj.getInteger("regionId");//区域 ID
                        int totalLots = jsonObj.getInteger("totalLots");//区域总车位数
                        int currentLots = jsonObj.getInteger("currentLots");//区域剩余车位数
                        total = total + totalLots;
                        current = current + currentLots;
                    }
                    DwdTourParkMonitorRt dwdTourParkMonitorRt = new DwdTourParkMonitorRt();
                    dwdTourParkMonitorRt.setId("10118110514421501000");//由于只有一个停车场 默认其id
                    dwdTourParkMonitorRt.setObjectname("安昌古镇停车场");
                    dwdTourParkMonitorRt.setAllnum(total);
                    dwdTourParkMonitorRt.setResidualNumber(current);
                    dwdTourParkMonitorRt.setDateTime(new Date());
                    logger.info(">>>>>>>>>>>>>>>>>>>>>>更新停车场信息");
                    dwdTourParkMonitorRtService.update(dwdTourParkMonitorRt);
                    logger.info(">>>>>>>>>>>>>>>>>>>>>>更新停车场信息>>>>>>>>>>>>>>>>>>>>>>成功");
                }else{
                    logger.info("请求失败");
                }
//            }else{
//                logger.info(">>>>>>>>>>>>>>>>>>>>>>网络不通");
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Autowired
    private DwsTourWeatherMonitorRtService dwsTourWeatherMonitorRtService;
    @Scheduled(fixedRate=7200000) //2H
    public void getrWeatherMonitorRt(){
        String res = HttpRequest.sendGet("https://free-api.heweather.com/s6/weather/now","key=270644c56e2f464091d39083bb83f23a&location=CN101210507&lang=zh-cn&unit=m");
        logger.info(res);
        JSONObject resJson = JSON.parseObject(res);
        JSONArray result5=resJson.getJSONArray("HeWeather6");//获取json数组
        JSONObject info5=result5.getJSONObject(0);
        JSONObject basic5=info5.getJSONObject("basic");
        String name=basic5.getString("location");//获取地点信息
        JSONObject now5=info5.getJSONObject("now");
        String temperature=now5.getString("tmp");//获取当前温度
        String windSpeed=now5.getString("wind_spd");//获取风速
        String type=now5.getString("cond_txt");//获取天气描述
        String code_img=now5.getString("cond_code");//获取天气图标
        String windDir=now5.getString("wind_dir");//获取风力描述

        DwsTourWeatherMonitorRt dwsTourWeatherMonitorRt = new DwsTourWeatherMonitorRt();

        dwsTourWeatherMonitorRt.setCode("79a3b2d72dcb40298aad150eb23452da");
        dwsTourWeatherMonitorRt.setTemperature(Integer.parseInt(temperature));
        dwsTourWeatherMonitorRt.setWindSpeed(windSpeed);
        dwsTourWeatherMonitorRt.setImgCode(code_img);
        dwsTourWeatherMonitorRt.setType(type);
        dwsTourWeatherMonitorRt.setWindDir(windDir);
        dwsTourWeatherMonitorRt.setCreatedate(new Date());
        dwsTourWeatherMonitorRtService.update(dwsTourWeatherMonitorRt);
    }

}
