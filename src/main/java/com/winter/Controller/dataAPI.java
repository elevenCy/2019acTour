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

    private  String inDevc3 = "5d8217d47bde44938e80760e702bc1e2";
    private  String inDevc2 = "70baf7dcd203419994d6e1214c4f4e8f";
    private  String userName = "acgz";
    private  String key = "dDPIIEdOXL2ye1IzSsprOHrOlnB1NJ6P";

    @RequestMapping("test")
    @ResponseBody
    public void test(@RequestBody  String str){
        JSONObject jsonObject = JSON.parseObject(str);
        System.out.println(jsonObject);
    }

    /**
     * 实时在园人数接口数据
     */
    @Scheduled(cron="0 0/5 * * * ? ") //5分钟
    public void realTimeNumberInPark(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss") ;

        //时间点&加密&url
        String timestamp = Long.toString(System.currentTimeMillis() / 1000);
        String sign = signMD5(timestamp);
        String url = "http://kq.tour-ma.com/api/realtime_data.jspx";
        //获取数据
        List<DwdTourTouristNumberRt> list = dwdTourTouristNumberRtService.findBySql("select * from dwd_tour_tourist_number_rt");
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
    }

    /**
     * 客流(进入)接口数据 —— 十五分钟一次
     */
    @Scheduled(cron="0 0/15 * * * ?") //15分钟
    public void touristEntryNumberQuarter(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        //时间点&加密&url
        String timestamp = Long.toString(System.currentTimeMillis() / 1000);
        String sign = signMD5(timestamp);
        String url = "http://kq.tour-ma.com/api/in_data.jspx";
        //获取数据
        String sql ="SELECT sum(number) number from dwd_tour_tourist_number_devc_middle WHERE code in ('"+inDevc3+"','"+inDevc2+"') GROUP BY date_time,hour,quarter ORDER BY createdate desc LIMIT 2;";
        List<DwdTourTouristNumberDevcMiddle> list = dwdTourTouristNumberDevcMiddleService.findBySql(sql);
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
            jsonObject.put("api_value",num1-num2);
            jsonObject.put("api_time",dateFormat.format(new Date()));
            jsonArray.add(jsonObject);
        }
        json.put("data",jsonArray);
        //发送
        String a = HttpRequest.sendPostJsonStr(url,JSONObject.toJSONString(json));
        System.out.println(a);
    }

    /**
     * 客流(进入)接口数据 —— 一小时一次
     */
    @Scheduled(cron="0 0 0/1 * * ?")//一小时一次
    public void touristEntryNumberHour(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        //时间点&加密&url
        String timestamp = Long.toString(System.currentTimeMillis() / 1000);
        String sign = signMD5(timestamp);
        String url = "http://kq.tour-ma.com/api/in_data.jspx";
        //获取数据
        String sql ="SELECT sum(number) number from dwd_tour_tourist_number_devc_h WHERE code in ('"+inDevc3+"','"+inDevc2+"') GROUP BY date_time,hour ORDER BY createdate desc LIMIT 2;";
        List<DwdTourTouristNumberDevcRt> list = dwdTourTouristNumberDevcRtService.findBySql(sql);
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
    }

    /**
     * 客流(进入)接口数据 —— 一天一次
     */
    @Scheduled(cron="0 58 23 * * ? ")
    public void touristEntryNumberDay(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        //时间点&加密&url
        String timestamp = Long.toString(System.currentTimeMillis() / 1000);
        String sign = signMD5(timestamp);
        String url = "http://kq.tour-ma.com/api/in_data.jspx";
        //获取数据
        String sql ="SELECT sum(number) number FROM dwd_tour_tourist_number_devc_h WHERE code in ('"+inDevc3+"','"+inDevc2+"') GROUP BY date_time,hour  ORDER BY createdate desc LIMIT 1;";
        List<DwdTourTouristNumberDevcRt> list = dwdTourTouristNumberDevcRtService.findBySql(sql);
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
    }

    /**
     * 游客来源地消息 —— 前一天的数据
     */
    @Scheduled(cron="0 0 6 * * ? ")
    public void sourceOfTourists(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyyMMdd");
        //昨天日期
        Integer yesterday = Integer.parseInt(dateFormat2.format(new Date()))-1;

        //时间点&加密&url
        String timestamp = Long.toString(System.currentTimeMillis() / 1000);
        String sign = signMD5(timestamp);
        String url = "http://kq.tour-ma.com/api/lyd_data.jspx";
        //获取数据
        String provinceSql = "SELECT * FROM dwd_tour_park_car_from_province_d WHERE date_time = '"+yesterday+"'";
        List<DwdTourParkCarFromProvinceD> provinceList = dwdTourParkCarFromProvinceDService.findBySql(provinceSql);
        String citySql = "SELECT * FROM dwd_tour_park_car_from_city_d WHERE date_time = '"+yesterday+"'";
        List<DwdTourParkCarFromCityD> cityList = dwdTourParkCarFromCityDService.findBySql(citySql);
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
        }
        json.put("city",jsonArray);
        //发送
        String a = HttpRequest.sendPostJsonStr(url,JSONObject.toJSONString(json));
        System.out.println(a);
    }

    /**
     * 停车场实时车位信息
     */
    @Scheduled(cron="0 0/5 * * * ? ")
    public void realTimeParkingSpace(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss") ;

        //时间点&加密&url
        String timestamp = Long.toString(System.currentTimeMillis() / 1000);
        String sign = signMD5(timestamp);
        String url = "http://kq.tour-ma.com/park/realtime_data.jspx";
        //获取数据
        List<DwdTourParkMonitorRt> list = dwdTourParkMonitorRtService.findBySql("select * from dwd_tour_park_monitor_rt");
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
    }

    /**
     * 入场记录
     */
    @Scheduled(cron="0 0/15 * * * ?")
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
        //获取 入场数据
        List<OdsTourTrlCarInfo> list = odsTourTrlCarInfoService.findBySql("SELECT * FROM ods_tour_trl_car_info WHERE passTime>='"+before15+"' and direction = 0 ORDER BY passTime desc;");
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
    }

    /**
     * 出场记录
     */
    @Scheduled(cron="0 0/15 * * * ?")
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
        //获取 出场数据
        List<OdsTourTrlCarInfo> list = odsTourTrlCarInfoService.findBySql("SELECT * FROM ods_tour_trl_car_info WHERE passTime>='"+before15+"' and direction = 1 ORDER BY passTime desc;");
        //List<OdsTourTrlCarInfo> list = odsTourTrlCarInfoService.findBySql("SELECT * FROM ods_tour_trl_car_info WHERE passTime>='2019-10-22 15:13:15' and direction = 1 ORDER BY passTime desc;");
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


}
