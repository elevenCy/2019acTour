package com.winter.Controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.winter.common.HttpRequest;
import com.winter.common.MD5;
import com.winter.model.*;
import com.winter.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.*;

@RestController
public class dataAPI {
    @Autowired
    private DwdTourTouristNumberRtService dwdTourTouristNumberRtService;
    @Autowired
    private DwdTourTouristNumberDevcMiddleService dwdTourTouristNumberDevcMiddleService;
    @Autowired
    private DwdTourTouristNumberDevcRtService dwdTourTouristNumberDevcRtService;
    @Autowired
    private DwdTourParkCarFromCityDService dwdTourParkCarFromCityDService;
    @Autowired
    private DwdTourParkCarFromProvinceDService dwdTourParkCarFromProvinceDService;
    @Autowired
    private DwdTourParkMonitorRtService dwdTourParkMonitorRtService;
    @Autowired
    private OdsTourTrlCarInfoService odsTourTrlCarInfoService;
    @Autowired
    private DimTourBasPlateProvinceAndCityService dimTourBasPlateProvinceAndCityService;
    @Autowired
    private SysApiLogService sysApiLogService;

    private  String inDevc3 = "5d8217d47bde44938e80760e702bc1e2";
    private  String inDevc2 = "70baf7dcd203419994d6e1214c4f4e8f";
    private  String userName = "acgz";
    private  String key = "dDPIIEdOXL2ye1IzSsprOHrOlnB1NJ6P";

    @RequestMapping("test")
    @ResponseBody
    public JSONObject test(@RequestBody  String str){
        JSONObject jsonObject = JSON.parseObject(str);
        System.out.println(jsonObject);
        JSONObject json = new JSONObject();
        json.put("code",200);
        json.put("message","ok");
        json.put("time","1111");
        return json;
    }

    /**
     * 实时在园人数接口数据
     */
    @Scheduled(cron="10 1/5 * * * ? ") //5分钟
    public void realTimeNumberInPark(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss") ;
        SysApiLog sal = new SysApiLog();

        //时间点&加密&url
        String timestamp = Long.toString(System.currentTimeMillis() / 1000);
        String sign = signMD5(timestamp);
        String url = "http://kq.tour-ma.com/api/realtime_data.jspx";
//        String url = "http://localhost:8071/test";
        //获取数据
        List<DwdTourTouristNumberRt> list = null ;
        try {
            list = dwdTourTouristNumberRtService.findBySql("select * from dwd_tour_tourist_number_rt");
        }catch (Exception e){
            sal.setId(getUUID32());
            sal.setName("实时在园人数接口数据");
            sal.setUrl(url);
            sal.setParam("");
            sal.setValue(e.toString());
            sal.setSendTime(new Date());
            sysApiLogService.insert(sal);
        }
        if(list.size()>0){
            //组合
            JSONObject json = new JSONObject();
            json.put("userName",userName);
            json.put("timestamp",timestamp);
            json.put("sign",sign);
            json.put("api_value",list.get(0).getNumber());
            json.put("api_time",dateFormat.format(new Date()));
            json.put("uuid","");//TODO uuid
            //data内容
            JSONArray jsonArray = new JSONArray();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("spot_id","");
            jsonObject.put("spot_name","安昌古镇");
            jsonObject.put("spot_value",list.get(0).getNumber());
            jsonObject.put("spot_time",dateFormat.format(list.get(0).getCreatedate()));
            jsonArray.add(jsonObject);
            json.put("data",jsonArray);
            //发送
            String s = HttpRequest.sendPostJsonStr(url,JSONObject.toJSONString(json));
            System.out.println(s);
            JSONObject httpjson = JSONObject.parseObject(s);

            sal.setId(getUUID32());
            sal.setName("实时在园人数接口数据");
            sal.setParam(JSONObject.toJSONString(json));
            sal.setUrl(url);
            sal.setValue(httpjson.toJSONString());
            sal.setSendTime(new Date());
            sysApiLogService.insert(sal);
        }else{
            sal.setId(getUUID32());
            sal.setName("实时在园人数接口数据");
            sal.setUrl(url);
            sal.setParam("");
            sal.setValue("无数据");
            sal.setSendTime(new Date());
            sysApiLogService.insert(sal);
        }

    }

    /**
     * 客流(进入)接口数据 —— 十五分钟一次
     */
    @Scheduled(cron="20 1/15 * * * ?") //15分钟
//    @RequestMapping("/touristEntryNumberQuarter")
//    @Scheduled(fixedRate=15000)
    public void touristEntryNumberQuarter(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SysApiLog sal = new SysApiLog();

        //时间点&加密&url
        String timestamp = Long.toString(System.currentTimeMillis() / 1000);
        String sign = signMD5(timestamp);
        String url = "http://kq.tour-ma.com/api/in_data.jspx";

        List<DwdTourTouristNumberDevcMiddle> list = null;
        //获取数据
        try{
            String sql ="SELECT sum(number) number,date_time,hour,quarter from dwd_tour_tourist_number_devc_middle "
                    +"WHERE code in ('"+inDevc3+"','"+inDevc2+"') GROUP BY date_time,hour,quarter ORDER BY date_time desc,hour desc,quarter desc LIMIT 2;";
            list = dwdTourTouristNumberDevcMiddleService.findBySql(sql);
        }catch (Exception e){
            sal.setId(getUUID32());
            sal.setName("客流(进入)接口数据(十五分钟)");
            sal.setParam("");
            sal.setUrl(url);
            sal.setValue(e.toString());
            sal.setSendTime(new Date());
            sysApiLogService.insert(sal);
        }

        if(list.size()>0){
            //组合
            JSONObject json = new JSONObject();
            json.put("userName",userName);
            json.put("timestamp",timestamp);
            json.put("sign",sign);
            json.put("api_id","1001");
            json.put("spot_name","安昌古镇");
            //data内容
            JSONArray jsonArray = new JSONArray();
            if(list.size()>0){
                Integer num1 = list.get(0).getNumber();
                Integer num2 = 0;
                if(list.size() == 2){
                    num2 = list.get(1).getNumber();
                }
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("spot_id","");
                jsonObject.put("spot_name","安昌古镇");
                int value = num1 - num2;
                if(value<0){
                    value = 0;
                }
                jsonObject.put("api_value",value);
                jsonObject.put("api_time",dateFormat.format(new Date()));
                jsonArray.add(jsonObject);
            }
            json.put("data",jsonArray);
            //发送
            String a = HttpRequest.sendPostJsonStr(url,JSONObject.toJSONString(json));
            System.out.println(a);
            JSONObject httpjson = JSONObject.parseObject(a);

            sal.setId(getUUID32());
            sal.setName("客流(进入)接口数据(十五分钟)");
            sal.setParam(JSONObject.toJSONString(json));
            sal.setUrl(url);
            sal.setValue(httpjson.toJSONString());
            sal.setSendTime(new Date());
            sysApiLogService.insert(sal);
        }else {
            sal.setId(getUUID32());
            sal.setName("客流(进入)接口数据(十五分钟)");
            sal.setParam("");
            sal.setUrl(url);
            sal.setValue("无数据");
            sal.setSendTime(new Date());
            sysApiLogService.insert(sal);
        }
    }

    /**
     * 客流(进入)接口数据 —— 一小时一次
     */
//    @Scheduled(cron="0 0 0/1 * * ?")//一小时一次
    public void touristEntryNumberHour(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SysApiLog sal = new SysApiLog();

        //时间点&加密&url
        String timestamp = Long.toString(System.currentTimeMillis() / 1000);
        String sign = signMD5(timestamp);
        String url = "http://kq.tour-ma.com/api/in_data.jspx";

        List<DwdTourTouristNumberDevcRt> list = null;
        try{
            //获取数据
            String sql ="SELECT sum(number) number,date_time,hour from dwd_tour_tourist_number_devc_h " +
                    "WHERE code in ('"+inDevc3+"','"+inDevc2+"') GROUP BY date_time,hour ORDER BY date_time desc,hour desc desc LIMIT 2;";
            list = dwdTourTouristNumberDevcRtService.findBySql(sql);
        }catch (Exception e){
            sal.setId(getUUID32());
            sal.setName("客流(进入)接口数据(一小时)");
            sal.setParam("");
            sal.setUrl(url);
            sal.setValue(e.toString());
            sal.setSendTime(new Date());
            sysApiLogService.insert(sal);
        }

        if(list.size()>0){
            //组合
            JSONObject json = new JSONObject();
            json.put("userName",userName);
            json.put("timestamp",timestamp);
            json.put("sign",sign);
            json.put("api_id","1002");
            json.put("spot_name","安昌古镇");
            //data内容
            JSONArray jsonArray = new JSONArray();
            if(list.size()>0){
                Integer num1 = list.get(0).getNumber();
                Integer num2 = 0;
                if(list.size() == 2){
                    num2 = list.get(1).getNumber();
                }
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("spot_id","");
                jsonObject.put("spot_name","安昌古镇");
                jsonObject.put("api_value",num1-num2);
                jsonObject.put("api_time",dateFormat.format(new Date()));
                jsonArray.add(jsonObject);
            }
            json.put("data",jsonArray);
            //发送
            String a = HttpRequest.sendPostJsonStr(url,JSONObject.toJSONString(json));
            System.out.println(a);
            JSONObject httpjson = JSONObject.parseObject(a);

            sal.setId(getUUID32());
            sal.setName("客流(进入)接口数据(一小时)");
            sal.setParam(JSONObject.toJSONString(json));
            sal.setUrl(url);
            sal.setValue(httpjson.toJSONString());
            sal.setSendTime(new Date());
            sysApiLogService.insert(sal);
        }else{
            sal.setId(getUUID32());
            sal.setName("客流(进入)接口数据(一小时)");
            sal.setParam("");
            sal.setUrl(url);
            sal.setValue("无数据");
            sal.setSendTime(new Date());
            sysApiLogService.insert(sal);
        }

    }

    /**
     * 客流(进入)接口数据 —— 一天一次
     */
//    @Scheduled(cron="0 58 23 * * ? ")
    public void touristEntryNumberDay(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SysApiLog sal = new SysApiLog();

        //时间点&加密&url
        String timestamp = Long.toString(System.currentTimeMillis() / 1000);
        String sign = signMD5(timestamp);
        String url = "http://kq.tour-ma.com/api/in_data.jspx";

        List<DwdTourTouristNumberDevcRt> list = null;
        //获取数据
        try {
            String sql ="SELECT sum(number) number FROM dwd_tour_tourist_number_devc_h WHERE code in ('"+inDevc3+"','"+inDevc2+"') GROUP BY date_time,hour  ORDER BY createdate desc LIMIT 1;";
            list = dwdTourTouristNumberDevcRtService.findBySql(sql);
        }catch (Exception e){
            sal.setId(getUUID32());
            sal.setName("客流(进入)接口数据(一天)");
            sal.setParam("");
            sal.setUrl(url);
            sal.setValue(e.toString());
            sal.setSendTime(new Date());
            sysApiLogService.insert(sal);
        }

        if(list.size()>0){
            //组合
            JSONObject json = new JSONObject();
            json.put("userName",userName);
            json.put("timestamp",timestamp);
            json.put("sign",sign);
            json.put("api_id","1003");
            json.put("spot_name","安昌古镇");
            //data内容
            JSONArray jsonArray = new JSONArray();
            if(list.size()>0){
                Integer num1 = list.get(0).getNumber();
                Integer num2 = 0;
                if(list.size() == 2){
                    num2 = list.get(1).getNumber();
                }
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("spot_id","");
                jsonObject.put("spot_name","安昌古镇");
                jsonObject.put("api_value",num1-num2);
                jsonObject.put("api_time",dateFormat.format(new Date()));
                jsonArray.add(jsonObject);
            }
            json.put("data",jsonArray);
            //发送
            String a = HttpRequest.sendPostJsonStr(url,JSONObject.toJSONString(json));
            System.out.println(a);

            JSONObject httpjson = JSONObject.parseObject(a);
            sal.setId(getUUID32());
            sal.setName("客流(进入)接口数据(一天)");
            sal.setParam(JSONObject.toJSONString(json));
            sal.setUrl(url);
            sal.setValue(JSONObject.toJSONString(httpjson));
            sal.setSendTime(new Date());
            sysApiLogService.insert(sal);
        }else{
            sal.setId(getUUID32());
            sal.setName("客流(进入)接口数据(一天)");
            sal.setParam("");
            sal.setUrl(url);
            sal.setValue("无数据");
            sal.setSendTime(new Date());
            sysApiLogService.insert(sal);
        }

    }

    /**
     * 游客来源地消息 —— 前一天的数据
     */
    @Scheduled(cron="30 0 6 * * ? ")
//    @RequestMapping("/sourceOfTourists")
    public void sourceOfTourists(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyyMMdd");
        //昨天日期
        Integer yesterday = Integer.parseInt(dateFormat2.format(new Date().getTime()-24*60*60*1000));
        yesterday = 20191231;
        //时间点&加密&url
        String timestamp = Long.toString(System.currentTimeMillis() / 1000);
        String sign = signMD5(timestamp);
        String url = "http://kq.tour-ma.com/api/lyd_data.jspx";

        List<DwdTourParkCarFromProvinceD> provinceList = null;
        List<DwdTourParkCarFromCityD> cityList = null;
        try {
            //获取数据
            String provinceSql = "SELECT * FROM dwd_tour_park_car_from_province_d WHERE date_time = '"+yesterday+"'";
            provinceList = dwdTourParkCarFromProvinceDService.findBySql(provinceSql);
            String citySql = "SELECT * FROM dwd_tour_park_car_from_city_d WHERE date_time = '"+yesterday+"'";
            cityList = dwdTourParkCarFromCityDService.findBySql(citySql);
        }catch (Exception e){
            SysApiLog sal = new SysApiLog();
            sal.setId(getUUID32());
            sal.setName("游客来源地消息");
            sal.setParam("");
            sal.setUrl(url);
            sal.setValue(e.toString());
            sal.setSendTime(new Date());
            sysApiLogService.insert(sal);
        }
        //组合
        JSONObject json = new JSONObject();
        json.put("userName",userName);
        json.put("timestamp",timestamp);
        json.put("sign",sign);
        json.put("api_time",dateFormat.format(new Date()));
        //province 内容
        JSONArray jsonArray = new JSONArray();
        if(provinceList.size()>0){
            for(int i = 0;i < provinceList.size();i++){
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("api_province",provinceList.get(i).getProvince());
                jsonObject.put("api_value",provinceList.get(i).getNumber());
                jsonArray.add(jsonObject);
            }
        }else{
            SysApiLog sal = new SysApiLog();
            sal.setId(getUUID32());
            sal.setName("游客来源省份数据");
            sal.setParam("");
            sal.setUrl(url);
            sal.setValue("游客来源省份无数据");
            sal.setSendTime(new Date());
            sysApiLogService.insert(sal);
        }
        json.put("province",jsonArray);
        //city 内容
        jsonArray = new JSONArray();
        if(cityList.size()>0){
            for(int i = 0;i < cityList.size();i++){
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("api_city",cityList.get(i).getProvince());
                jsonObject.put("api_value",cityList.get(i).getNumber());
                jsonArray.add(jsonObject);
            }
        }else{
            SysApiLog sal = new SysApiLog();
            sal.setId(getUUID32());
            sal.setName("游客来源城市数据");
            sal.setParam("");
            sal.setUrl("");
            sal.setValue("游客来源城市无数据");
            sal.setSendTime(new Date());
            sysApiLogService.insert(sal);
        }
        json.put("city",jsonArray);
        //发送
        String a = HttpRequest.sendPostJsonStr(url,JSONObject.toJSONString(json));
        System.out.println(a);
        JSONObject httpjson = JSONObject.parseObject(a);
        SysApiLog sal = new SysApiLog();
        sal.setId(getUUID32());
        sal.setName("游客来源地消息");
        sal.setParam(JSONObject.toJSONString(json));
        sal.setUrl(url);
        sal.setValue(httpjson.toJSONString());
        sal.setSendTime(new Date());
        sysApiLogService.insert(sal);
    }

    /**
     * 停车场实时车位信息
     */
    @Scheduled(cron="40 0/5 * * * ? ")
    public void realTimeParkingSpace(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss") ;

        //时间点&加密&url
        String timestamp = Long.toString(System.currentTimeMillis() / 1000);
        String sign = signMD5(timestamp);
        String url = "http://kq.tour-ma.com/park/realtime_data.jspx";
        List<DwdTourParkMonitorRt> list = null;
        try{
            //获取数据
            list = dwdTourParkMonitorRtService.findBySql("select * from dwd_tour_park_monitor_rt");
        }catch (Exception e){
            SysApiLog sal = new SysApiLog();
            sal.setId(getUUID32());
            sal.setName("停车场实时车位信息");
            sal.setParam("");
            sal.setUrl(url);
            sal.setValue(e.toString());
            sal.setSendTime(new Date());
            sysApiLogService.insert(sal);
        }
        if(list.size()>0){
            //组合
            JSONObject json = new JSONObject();
            json.put("userName",userName);
            json.put("timestamp",timestamp);
            json.put("sign",sign);
            //data内容
            JSONArray jsonArray = new JSONArray();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("park_name",list.get(0).getObjectname());
            //已用车位数
            jsonObject.put("park_in",list.get(0).getAllnum()-list.get(0).getResidualNumber());
            //剩余车位数
            jsonObject.put("idle",list.get(0).getResidualNumber());
            jsonObject.put("park_time",dateFormat.format(list.get(0).getDateTime()));
            jsonArray.add(jsonObject);
            json.put("data",jsonArray);
            //发送
            String a = HttpRequest.sendPostJsonStr(url,JSONObject.toJSONString(json));
            System.out.println(a);
            JSONObject httpjson = JSONObject.parseObject(a);
            SysApiLog sal = new SysApiLog();
            sal.setId(getUUID32());
            sal.setName("停车场实时车位信息");
            sal.setParam(JSONObject.toJSONString(json));
            sal.setUrl(url);
            sal.setValue(httpjson.toJSONString());
            sal.setSendTime(new Date());
            sysApiLogService.insert(sal);
        }else{
            SysApiLog sal = new SysApiLog();
            sal.setId(getUUID32());
            sal.setName("停车场实时车位信息");
            sal.setParam("");
            sal.setUrl(url);
            sal.setValue("无数据");
            sal.setSendTime(new Date());
            sysApiLogService.insert(sal);
        }
    }

    /**
     * 入场记录
     */
    @Scheduled(cron="50 0/15 * * * ?")
    public void inParkRecords(){
        Calendar beforeTime = Calendar.getInstance();
        // 5分钟之前的时间
        beforeTime.add(Calendar.MINUTE, -15);
        Date beforeD = beforeTime.getTime();
        // 前五分钟时间
        String before15 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(beforeD);

        //时间点&加密&url
        String timestamp = Long.toString(System.currentTimeMillis() / 1000);
        String sign = signMD5(timestamp);
        String url = "http://kq.tour-ma.com/park/in_data.jspx";
        List<OdsTourTrlCarInfo> list = null;
        try{
            //获取 入场数据
            list = odsTourTrlCarInfoService.findBySql("SELECT * FROM ods_tour_trl_car_info WHERE passTime>='"+before15+"' and direction = 0 ORDER BY passTime desc;");
        }catch (Exception e){
            SysApiLog sal = new SysApiLog();
            sal.setId(getUUID32());
            sal.setName("入场记录");
            sal.setParam("");
            sal.setUrl(url);
            sal.setValue(e.toString());
            sal.setSendTime(new Date());
            sysApiLogService.insert(sal);
        }

        if(list.size()>0){
            //组合
            JSONObject json = new JSONObject();
            json.put("userName",userName);
            json.put("timestamp",timestamp);
            json.put("sign",sign);
            //data内容
            JSONArray jsonArray = new JSONArray();
            if(list.size()>0){
                for(int i = 0;i < list.size();i++){
                    OdsTourTrlCarInfo ottci = list.get(i);
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("cardId",ottci.getUniqueNo());
                    jsonObject.put("carNo",ottci.getPlateNo());
                    jsonObject.put("in_name",ottci.getGateName());
                    jsonObject.put("in_time",ottci.getPassTime());
                    if(ottci.getPlateNo().equals("无车牌")){
                        jsonObject.put("province","");
                        jsonObject.put("city","");
                    }else{
                        String firstPlateNo = ottci.getPlateNo().substring(0,1);
                        if(firstPlateNo.equals("浙")){
                            //省内
                            firstPlateNo = ottci.getPlateNo().substring(0,2);
                        }
                        String sql = "SELECT * from dim_tour_bas_plate_province_and_city WHERE code = '"+firstPlateNo+"'";
                        List<DimTourBasPlateProvinceAndCity> list2 =dimTourBasPlateProvinceAndCityService.findBySql(sql);
                        if(list2.size()>0){
                            jsonObject.put("province",list2.get(0).getProvince());
                            jsonObject.put("city",list2.get(0).getCity());
                        }else{
                            jsonObject.put("province","");
                            jsonObject.put("city","");
                        }
                    }
                    jsonObject.put("type",VehType.getVehType(ottci.getVehType()));
                    jsonArray.add(jsonObject);
                }
            }
            json.put("data",jsonArray);
            //发送
            String a = HttpRequest.sendPostJsonStr(url,JSONObject.toJSONString(json));
            System.out.println(a);
            JSONObject httpjson = JSONObject.parseObject(a);
            SysApiLog sal = new SysApiLog();
            sal.setId(getUUID32());
            sal.setName("入场记录");
            sal.setParam(json.toJSONString());
            sal.setUrl(url);
            sal.setValue(httpjson.toJSONString());
            sal.setSendTime(new Date());
            sysApiLogService.insert(sal);
        }else {
            SysApiLog sal = new SysApiLog();
            sal.setId(getUUID32());
            sal.setName("入场记录");
            sal.setParam("");
            sal.setUrl(url);
            sal.setValue("无数据");
            sal.setSendTime(new Date());
            sysApiLogService.insert(sal);
        }
    }

    /**
     * 出场记录
     */
    @Scheduled(cron="15 0/15 * * * ?")
    public void outParkRecords(){
        Calendar beforeTime = Calendar.getInstance();
        // 5分钟之前的时间
        beforeTime.add(Calendar.MINUTE, -15);
        Date beforeD = beforeTime.getTime();
        // 前五分钟时间
        String before15 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(beforeD);

        //时间点&加密&url
        String timestamp = Long.toString(System.currentTimeMillis() / 1000);
        String sign = signMD5(timestamp);
        String url = "http://kq.tour-ma.com/park/out_data.jspx";
        List<OdsTourTrlCarInfo> list = null;
        try{
            //获取 出场数据
            list = odsTourTrlCarInfoService.findBySql("SELECT * FROM ods_tour_trl_car_info WHERE passTime>='"+before15+"' and direction = 1 ORDER BY passTime desc;");
        }catch (Exception e){
            SysApiLog sal = new SysApiLog();
            sal.setId(getUUID32());
            sal.setName("出场记录");
            sal.setParam("");
            sal.setUrl(url);
            sal.setValue(e.toString());
            sal.setSendTime(new Date());
            sysApiLogService.insert(sal);
        }

        if(list.size()>0){
            //组合
            JSONObject json = new JSONObject();
            json.put("userName",userName);
            json.put("timestamp",timestamp);
            json.put("sign",sign);
            //data内容
            JSONArray jsonArray = new JSONArray();
            if(list.size()>0){
                for(int i = 0;i < list.size();i++){
                    OdsTourTrlCarInfo ottci = list.get(i);
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("cardId",ottci.getUniqueNo());
                    jsonObject.put("carNo",ottci.getPlateNo());
                    jsonObject.put("out_name",ottci.getGateName());
                    jsonObject.put("out_time",ottci.getPassTime());

                    if(ottci.getPlateNo().equals("无车牌")){
                        jsonObject.put("in_name",ottci.getGateName());
                        jsonObject.put("in_time",ottci.getPassTime());
                    }else{
                        String sql = "SELECT * FROM ods_tour_trl_car_info WHERE passTime < '"+ottci.getPassTime()+"' and plateNo='"+ottci.getPlateNo()+"'  and direction = 0 ORDER BY passTime desc;";
                        List<OdsTourTrlCarInfo> list2 =odsTourTrlCarInfoService.findBySql(sql);
                        if(list2.size()>0){
                            jsonObject.put("in_name",list2.get(0).getGateName());
                            jsonObject.put("in_time",list2.get(0).getPassTime());
                        }else{
                            jsonObject.put("in_name","");
                            jsonObject.put("in_time","");
                        }
                    }
                    jsonObject.put("type",VehType.getVehType(ottci.getVehType()));
                    jsonArray.add(jsonObject);
                }
            }
            json.put("data",jsonArray);
            //发送
            String a = HttpRequest.sendPostJsonStr(url,JSONObject.toJSONString(json));
            System.out.println(a);
            JSONObject httpjson = JSONObject.parseObject(a);
            SysApiLog sal = new SysApiLog();
            sal.setId(getUUID32());
            sal.setName("出场记录");
            sal.setParam(json.toJSONString());
            sal.setUrl(url);
            sal.setValue(httpjson.toJSONString());
            sal.setSendTime(new Date());
            sysApiLogService.insert(sal);
        }else{
            SysApiLog sal = new SysApiLog();
            sal.setId(getUUID32());
            sal.setName("出场记录");
            sal.setParam("");
            sal.setUrl(url);
            sal.setValue("无数据");
            sal.setSendTime(new Date());
            sysApiLogService.insert(sal);
        }
    }

    //sign加密
    public String signMD5(String timestamp){
        String a= "userName="+userName+"&timestamp="+timestamp+"&key="+key;
        MD5 b = new MD5();
        return b.encryption(a).toUpperCase();
    }

    /**
     * 车辆类型枚举
     */
    public enum VehType{
        /**
         *其他车
         */
        其他车(0),
        /**
         *小型车
         */
        小型车(1),
        /**
         *大型车
         */
        大型车(2);

        private Integer value ;

        VehType(Integer value) {
            this.value = value;
        }

        public Integer getValue() {
            return value;
        }

        /**
         * 根据vehType 获取车辆类型
         * @param vehType 数子 车辆类型
         * @return 中文 车辆类型名
         */
        public static  String getVehType(Integer vehType){
            for (VehType airlineTypeEnum : VehType.values()) {
                if(vehType.equals(airlineTypeEnum.getValue())){
                    return airlineTypeEnum.toString();
                }
            }
            return null;
        }
    }

    /**
     * 32位 uuid
     */
    public String getUUID32(){
        String uuid = UUID.randomUUID().toString();
        uuid = uuid.replace("-", "");
        return uuid;
    }


}
