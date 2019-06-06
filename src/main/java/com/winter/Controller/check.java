package com.winter.Controller;

import com.winter.common.SendMsg;
import com.winter.model.*;
import com.winter.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by h on 2018/12/6.
 */
//@RestController//请求
@Component
public class check {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

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
    private static int getHour(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.HOUR_OF_DAY);
    }
    private static int getDay(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_MONTH);
    }
    public static int getMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MONTH) + 1;
    }
    public static String getTimeString_YYYY(Date date) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy");
        Calendar calendar = Calendar.getInstance();
        return df.format(calendar.getTime());
    }
    public static String getTimeString_YYYYMM(Date date) {
        SimpleDateFormat df = new SimpleDateFormat("yyyyMM");
        Calendar calendar = Calendar.getInstance();
        return df.format(calendar.getTime());
    }

    @Autowired
    private DwdTourParkMonitorRtService dwdTourParkMonitorRtService;//停车场实时数据 1 访问接口 5分钟访问一次并更新数据-->故而判断方式时是否有15分钟内的数据 有则表示数据正常
    private int checkDwdTourParkMonitorRt(){
        //检查是否有今天的数据且在15分钟内更新的数据
        String sql = "select * from dwd_tour_park_monitor_rt where DATE_FORMAT(date_time,'%Y%m%d') =  DATE_FORMAT(now(),'%Y%m%d') and date_time >= DATE_SUB(NOW(),INTERVAL 10 MINUTE)";
        List<DwdTourParkMonitorRt> os = dwdTourParkMonitorRtService.findBySql(sql);
        int result = 1;
        if(os==null){
            System.out.println("dwd_tour_park_monitor_rt-->>>>>>停车场实时数据10分钟内未更新>>>>>>os>>>>>>null");
            result = 0;
        }else{
            if(os.isEmpty()){
                System.out.println("dwd_tour_park_monitor_rt-->>>>>>停车场实时数据10分钟内未更新>>>>>>os>>>>>>empty");
                result = 0;
            }else{
                System.out.println("dwd_tour_park_monitor_rt-->>>>>>停车场实时数据正常更新");
            }
        }
        return result;
    }
    @Autowired
    private DwdTourParkHService dwdTourParkHService;//停车场时数据 2 无分时数据

    @Autowired
    private DwdTourParkCarDService dwdTourParkCarDService;//车辆日数据 3 每30分钟更新一次日数据 每个小时的xx:25和xx:55分更新一次
    private int checkDwdTourParkCarD(){
        //检查是否有今天的数据且在1H内更新的数据
        String sql = "select * from dwd_tour_park_car_d where date_time = DATE_FORMAT(now(),'%Y%m%d') and createdate >= DATE_SUB(NOW(),INTERVAL 1 HOUR)";
        List<DwdTourParkCarD> os = dwdTourParkCarDService.findBySql(sql);
        int result = 1;
        if(os==null){
            System.out.println("dwd_tour_park_car_d-->>>>>>停车场车辆>>天>>数据0.5小时内未更新>>>>>>os>>>>>>null");
            result = 0;
        }else{
            if(os.isEmpty()){
                System.out.println("dwd_tour_park_car_d-->>>>>>停车场车辆>>天>>数据0.5小时内未更新>>>>>>os>>>>>>empty");
                result = 0;
            }else{
                System.out.println("dwd_tour_park_car_d-->>>>>>停车场车辆>>天>>数据正常更新");
                Date now = new Date();
                int thisHour = getHour(now);
                if(thisHour>=7){
                    DwdTourParkCarD o = os.get(0);
                    if(o.getNumber()==0){
                        System.out.println("dwd_tour_park_car_d-->>>>>>停车场车辆>>天>>数据正常更新>>"+thisHour+"点为止数据为0>>数据可能异常或没有获取成功");
                        result = 2;
                    }
                }
            }
        }
        return result;
    }
    @Autowired
    private DwdTourParkCarWService dwdTourParkCarWService;//车辆周数据 4 每小时xx:57:30更新
    private int checkDwdTourParkCarW(){
        //检查是否有今天的数据且在1H内更新的数据
        Date now = new Date();
        int day = getDay(now);
        String dateTime = getTimeString_YYYYMM(now);
        if(day<=7){
            dateTime = dateTime + "01";
        }else if(day<=14){
            dateTime = dateTime + "02";
        }else if(day<=21){
            dateTime = dateTime + "03";
        }else if(day<=28){
            dateTime = dateTime + "04";
        }else{
            dateTime = dateTime + "05";
        }
        String sql = "select * from dwd_tour_park_car_w where date_time = '"+dateTime+"' and createdate >= DATE_SUB(NOW(),INTERVAL 2 HOUR)";
        List<DwdTourParkCarW> os = dwdTourParkCarWService.findBySql(sql);
        int result = 1;
        if(os==null){
            System.out.println("dwd_tour_park_car_w-->>>>>>停车场车辆>>周>>数据1小时内未更新>>>>>>os>>>>>>null");
            result = 0;
        }else{
            if(os.isEmpty()){
                System.out.println("dwd_tour_park_car_w-->>>>>>停车场车辆>>周>>数据1小时内未更新>>>>>>os>>>>>>empty");
                result = 0;
            }else{
                System.out.println("dwd_tour_park_car_w-->>>>>>停车场车辆>>周>>数据正常更新");
                int thisHour = getHour(now);
                if(thisHour>=7){
                    DwdTourParkCarW o = os.get(0);
                    if(o.getNumber()==0){
                        System.out.println("dwd_tour_park_car_w-->>>>>>停车场车辆>>周>>数据正常更新>>"+thisHour+"点为止数据为0>>数据可能异常或没有获取成功");
                        result = 2;
                    }
                }
            }
        }
        return result;
    }
    @Autowired
    private DwdTourParkCarMService dwdTourParkCarMService;//车辆月数据 5  每小时xx:58:00更新
    private int checkDwdTourParkCarM(){
        Date now = new Date();
        String dateTime = getTimeString_YYYYMM(now);
        String sql = "select * from dwd_tour_park_car_m where date_time = '"+dateTime+"' and createdate >= DATE_SUB(NOW(),INTERVAL 2 HOUR)";
        List<DwdTourParkCarM> os = dwdTourParkCarMService.findBySql(sql);
        int result = 1;
        if(os==null){
            System.out.println("dwd_tour_park_car_m-->>>>>>停车场车辆>>月>>数据1小时内未更新>>>>>>os>>>>>>null");
            result = 0;
        }else{
            if(os.isEmpty()){
                System.out.println("dwd_tour_park_car_m-->>>>>>停车场车辆>>月>>数据1小时内未更新>>>>>>os>>>>>>empty");
                result = 0;
            }else{
                System.out.println("dwd_tour_park_car_m-->>>>>>停车场车辆>>月>>数据正常更新");
                int thisHour = getHour(now);
                if(thisHour>=7){
                    DwdTourParkCarM o = os.get(0);
                    if(o.getNumber()==0){
                        System.out.println("dwd_tour_park_car_m-->>>>>>停车场车辆>>月>>数据正常更新>>"+thisHour+"点为止数据为0>>数据可能异常或没有获取成功");
                        result = 2;
                    }
                }
            }
        }
        return result;
    }
    @Autowired
    private DwdTourParkCarQService dwdTourParkCarQService;//车辆季数据 6  每小时xx:58:30更新
    private int checkDwdTourParkCarQ(){
        Date now = new Date();
        String dateTime = getTimeString_YYYY(now);
        int month = getMonth(now);
        if(month<=3){
            dateTime = dateTime + "01";
        }else if(month<=6){
            dateTime = dateTime + "02";
        }else if(month<=9){
            dateTime = dateTime + "03";
        }else{
            dateTime = dateTime + "04";
        }
        String sql = "select * from dwd_tour_park_car_q where date_time = '"+dateTime+"' and createdate >= DATE_SUB(NOW(),INTERVAL 2 HOUR)";
        List<DwdTourParkCarQ> os = dwdTourParkCarQService.findBySql(sql);
        int result = 1;
        if(os==null){
            System.out.println("dwd_tour_park_car_q-->>>>>>停车场车辆>>季>>数据1小时内未更新>>>>>>os>>>>>>null");
            result = 0;
        }else{
            if(os.isEmpty()){
                System.out.println("dwd_tour_park_car_q-->>>>>>停车场车辆>>季>>数据1小时内未更新>>>>>>os>>>>>>empty");
                result = 0;
            }else{
                System.out.println("dwd_tour_park_car_q-->>>>>>停车场车辆>>季>>数据正常更新");
                int thisHour = getHour(now);
                if(thisHour>=7){
                    DwdTourParkCarQ o = os.get(0);
                    if(o.getNumber()==0){
                        System.out.println("dwd_tour_park_car_q-->>>>>>停车场车辆>>季>>数据正常更新>>"+thisHour+"点为止数据为0>>数据可能异常或没有获取成功");
                        result = 2;
                    }
                }
            }
        }
        return result;
    }
    @Autowired
    private DwdTourParkCarYService dwdTourParkCarYService;//车辆年数据 7  每小时xx:59:00更新
    private int checkDwdTourParkCarY(){
        Date now = new Date();
        String dateTime = getTimeString_YYYY(now);
        String sql = "select * from dwd_tour_park_car_y where date_time = '"+dateTime+"' and createdate >= DATE_SUB(NOW(),INTERVAL 2 HOUR)";
        List<DwdTourParkCarY> os = dwdTourParkCarYService.findBySql(sql);
        int result = 1;
        if(os==null){
            System.out.println("dwd_tour_park_car_y-->>>>>>停车场车辆>>年>>数据1小时内未更新>>>>>>os>>>>>>null");
            result = 0;
        }else{
            if(os.isEmpty()){
                System.out.println("dwd_tour_park_car_y-->>>>>>停车场车辆>>年>>数据1小时内未更新>>>>>>os>>>>>>empty");
                result = 0;
            }else{
                System.out.println("dwd_tour_park_car_y-->>>>>>停车场车辆>>年>>数据正常更新");
                int thisHour = getHour(now);
                if(thisHour>=7){
                    DwdTourParkCarY o = os.get(0);
                    if(o.getNumber()==0){
                        System.out.println("dwd_tour_park_car_y-->>>>>>停车场车辆>>年>>数据正常更新>>"+thisHour+"点为止数据为0>>数据可能异常或没有获取成功");
                        result = 2;
                    }
                }
            }
        }
        return result;
    }
    @Autowired
    private DwdTourParkCarFromCityDService dwdTourParkCarFromCityDService;//车辆来源市-日数据 8 每小时 27 57 分更新
    private int checkDwdTourParkCarFromCityD(){
        //检查是否有今天的数据且在1H内更新的数据
        int result = 1;
        Date now = new Date();
        int thisHour = getHour(now);
        //由于来源市时多条数据 若事件太早可能没有记录所以7点后再进行判定
        if(thisHour>=7) {//七点后若无数据则需注意
            String sql = "select * from dwd_tour_park_car_from_city_d where date_time = DATE_FORMAT(now(),'%Y%m%d') and createdate >= DATE_SUB(NOW(),INTERVAL 1 HOUR)";
            List<DwdTourParkCarFromCityD> os = dwdTourParkCarFromCityDService.findBySql(sql);
            if (os == null) {
                System.out.println("dwd_tour_park_car_from_city_d-->>>>>>车辆来源市>>天>>数据0.5小时内未更新>>>>>>os>>>>>>null");
                result = 0;
            } else {
                if (os.isEmpty()) {
                    System.out.println("dwd_tour_park_car_from_city_d-->>>>>>车辆来源市>>天>>数据0.5小时内未更新>>>>>>os>>>>>>empty");
                    result = 0;
                } else {
                    System.out.println("dwd_tour_park_car_from_city_d-->>>>>>车辆来源市>>天>>数据正常更新");
                }
            }
        }
        return result;
    }
    @Autowired
    private DwdTourParkCarFromCityMService dwdTourParkCarFromCityMService;//车辆来源市-月数据 9 每小时xx:59更新
    private int checkDwdTourParkCarFromCityM(){
        //检查是否有今天的数据且在1H内更新的数据
        int result = 1;
        Date now = new Date();
        int thisHour = getHour(now);
        String dateTime = getTimeString_YYYYMM(now);
        //由于来源市时多条数据 若事件太早可能没有记录所以7点后再进行判定
        if(thisHour>=7) {//七点后若无数据则需注意
            String sql = "select * from dwd_tour_park_car_from_city_m where date_time = '"+dateTime+"' and createdate >= DATE_SUB(NOW(),INTERVAL 2 HOUR)";
            List<DwdTourParkCarFromCityM> os = dwdTourParkCarFromCityMService.findBySql(sql);
            if (os == null) {
                System.out.println("dwd_tour_park_car_from_city_m-->>>>>>车辆来源市>>月>>数据1小时内未更新>>>>>>os>>>>>>null");
                result = 0;
            } else {
                if (os.isEmpty()) {
                    System.out.println("dwd_tour_park_car_from_city_m-->>>>>>车辆来源市>>月>>数据1小时内未更新>>>>>>os>>>>>>empty");
                    result = 0;
                } else {
                    System.out.println("dwd_tour_park_car_from_city_m-->>>>>>车辆来源市>>月>>数据正常更新");
                }
            }
        }
        return result;
    }

    @Autowired
    private DwdTourParkCarFromProvinceDService dwdTourParkCarFromProvinceDService;//车辆来源省-日数据 10 每小时 27 57 分更新
    private int checkDwdTourParkCarFromProvinceD(){
        //检查是否有今天的数据且在1H内更新的数据
        int result = 1;
        Date now = new Date();
        int thisHour = getHour(now);
        //由于来源市时多条数据 若事件太早可能没有记录所以7点后再进行判定
        if(thisHour>=7) {//七点后若无数据则需注意
            String sql = "select * from dwd_tour_park_car_from_province_d where date_time = DATE_FORMAT(now(),'%Y%m%d') and createdate >= DATE_SUB(NOW(),INTERVAL 1 HOUR)";
            List<DwdTourParkCarFromProvinceD> os = dwdTourParkCarFromProvinceDService.findBySql(sql);
            if (os == null) {
                System.out.println("dwd_tour_park_car_from_province_d-->>>>>>车辆来源省>>天>>数据0.5小时内未更新>>>>>>os>>>>>>null");
                result = 0;
            } else {
                if (os.isEmpty()) {
                    System.out.println("dwd_tour_park_car_from_province_d-->>>>>>车辆来源省>>天>>数据0.5小时内未更新>>>>>>os>>>>>>empty");
                    result = 0;
                } else {
                    System.out.println("dwd_tour_park_car_from_province_d-->>>>>>车辆来源省>>天>>数据正常更新");
                }
            }
        }
        return result;
    }
    @Autowired
    private DwdTourParkCarFromProvinceMService dwdTourParkCarFromProvinceMService;//车辆来源省-月数据 11 每小时xx:59更新
    private int checkDwdTourParkCarFromProvinceM(){
        //检查是否有今天的数据且在1H内更新的数据
        int result = 1;
        Date now = new Date();
        int thisHour = getHour(now);
        String dateTime = getTimeString_YYYYMM(now);
        //由于来源市时多条数据 若事件太早可能没有记录所以7点后再进行判定
        if(thisHour>=7) {//七点后若无数据则需注意
            String sql = "select * from dwd_tour_park_car_from_province_m where date_time = '"+dateTime+"' and createdate >= DATE_SUB(NOW(),INTERVAL 2 HOUR)";
            List<DwdTourParkCarFromProvinceM> os = dwdTourParkCarFromProvinceMService.findBySql(sql);
            if (os == null) {
                System.out.println("dwd_tour_park_car_from_province_m-->>>>>>车辆来源省>>月>>数据1小时内未更新>>>>>>os>>>>>>null");
                result = 0;
            } else {
                if (os.isEmpty()) {
                    System.out.println("dwd_tour_park_car_from_province_m-->>>>>>车辆来源省>>月>>数据1小时内未更新>>>>>>os>>>>>>empty");
                    result = 0;
                } else {
                    System.out.println("dwd_tour_park_car_from_province_m-->>>>>>车辆来源省>>月>>数据正常更新");
                }
            }
        }
        return result;
    }
    @Autowired
    private DwdTourTouristFromCityWService dwdTourTouristFromCityWService;//客流来源市-周数据 12 每小时xx:59更新 只是车辆来源乘系数6 产生的数据
    private int checkDwdTourTouristFromCityW(){
        //检查是否有今天的数据且在1H内更新的数据
        int result = 1;
        Date now = new Date();
        int thisHour = getHour(now);
        int day = getDay(now);
        String dateTime = getTimeString_YYYYMM(now);
        if(day<=7){
            dateTime = dateTime + "01";
        }else if(day<=14){
            dateTime = dateTime + "02";
        }else if(day<=21){
            dateTime = dateTime + "03";
        }else if(day<=28){
            dateTime = dateTime + "04";
        }else{
            dateTime = dateTime + "05";
        }
        //由于来源市时多条数据 若事件太早可能没有记录所以7点后再进行判定
        if(thisHour>=7) {//七点后若无数据则需注意
            String sql = "select * from dwd_tour_tourist_from_city_w where date_time = '"+dateTime+"' and createdate >= DATE_SUB(NOW(),INTERVAL 2 HOUR)";
            List<DwdTourTouristFromCityW> os = dwdTourTouristFromCityWService.findBySql(sql);
            if (os == null) {
                System.out.println("dwd_tour_tourist_from_city_w-->>>>>>客流来源市>>周>>数据1小时内未更新>>>>>>os>>>>>>null");
                result = 0;
            } else {
                if (os.isEmpty()) {
                    System.out.println("dwd_tour_tourist_from_city_w-->>>>>>客流来源市>>周>>数据1小时内未更新>>>>>>os>>>>>>empty");
                    result = 0;
                } else {
                    System.out.println("dwd_tour_tourist_from_city_w-->>>>>>客流来源市>>周>>数据正常更新");
                }
            }
        }
        return result;
    }
    @Autowired
    private DwdTourTouristFromCityMService dwdTourTouristFromCityMService;//客流来源市-月数据 13 每小时xx:59更新
    private int checkDwdTourTouristFromCityM(){
        //检查是否有今天的数据且在1H内更新的数据
        int result = 1;
        Date now = new Date();
        int thisHour = getHour(now);
        String dateTime = getTimeString_YYYYMM(now);
        //由于来源市时多条数据 若事件太早可能没有记录所以7点后再进行判定
        if(thisHour>=7) {//七点后若无数据则需注意
            String sql = "select * from dwd_tour_tourist_from_city_m where date_time = '"+dateTime+"' and createdate >= DATE_SUB(NOW(),INTERVAL 2 HOUR)";
            List<DwdTourTouristFromCityM> os = dwdTourTouristFromCityMService.findBySql(sql);
            if (os == null) {
                System.out.println("dwd_tour_tourist_from_city_m-->>>>>>客流来源市>>月>>数据1小时内未更新>>>>>>os>>>>>>null");
                result = 0;
            } else {
                if (os.isEmpty()) {
                    System.out.println("dwd_tour_tourist_from_city_m-->>>>>>客流来源市>>月>>数据1小时内未更新>>>>>>os>>>>>>empty");
                    result = 0;
                } else {
                    System.out.println("dwd_tour_tourist_from_city_m-->>>>>>客流来源市>>月>>数据正常更新");
                }
            }
        }
        return result;
    }
    @Autowired
    private DwdTourTouristFromProvinceWService dwdTourTouristFromProvinceWService;//客流来源省-周数据 14 每小时xx:59更新
    private int checkDwdTourTouristFromProvinceW(){
        //检查是否有今天的数据且在1H内更新的数据
        int result = 1;
        Date now = new Date();
        int thisHour = getHour(now);
        int day = getDay(now);
        String dateTime = getTimeString_YYYYMM(now);
        if(day<=7){
            dateTime = dateTime + "01";
        }else if(day<=14){
            dateTime = dateTime + "02";
        }else if(day<=21){
            dateTime = dateTime + "03";
        }else if(day<=28){
            dateTime = dateTime + "04";
        }else{
            dateTime = dateTime + "05";
        }
        //由于来源市时多条数据 若事件太早可能没有记录所以7点后再进行判定
        if(thisHour>=7) {//七点后若无数据则需注意
            String sql = "select * from dwd_tour_tourist_from_province_w where date_time = '"+dateTime+"' and createdate >= DATE_SUB(NOW(),INTERVAL 2 HOUR)";
            List<DwdTourTouristFromProvinceW> os = dwdTourTouristFromProvinceWService.findBySql(sql);
            if (os == null) {
                System.out.println("dwd_tour_tourist_from_province_w-->>>>>>客流来源省>>周>>数据1小时内未更新>>>>>>os>>>>>>null");
                result = 0;
            } else {
                if (os.isEmpty()) {
                    System.out.println("dwd_tour_tourist_from_province_w-->>>>>>客流来源省>>周>>数据1小时内未更新>>>>>>os>>>>>>empty");
                    result = 0;
                } else {
                    System.out.println("dwd_tour_tourist_from_province_w-->>>>>>客流来源省>>周>>数据正常更新");
                }
            }
        }
        return result;
    }
    @Autowired
    private DwdTourTouristFromProvinceMService dwdTourTouristFromProvinceMService;//客流来源省-月数据 15 每小时xx:59更新
    private int checkDwdTourTouristFromProvinceM(){
        //检查是否有今天的数据且在1H内更新的数据
        int result = 1;
        Date now = new Date();
        int thisHour = getHour(now);
        String dateTime = getTimeString_YYYYMM(now);
        //由于来源市时多条数据 若事件太早可能没有记录所以7点后再进行判定
        if(thisHour>=7) {//七点后若无数据则需注意
            String sql = "select * from dwd_tour_tourist_from_province_m where date_time = '"+dateTime+"' and createdate >= DATE_SUB(NOW(),INTERVAL 2 HOUR)";
            List<DwdTourTouristFromProvinceM> os = dwdTourTouristFromProvinceMService.findBySql(sql);
            if (os == null) {
                System.out.println("dwd_tour_tourist_from_province_m-->>>>>>客流来源省>>月>>数据1小时内未更新>>>>>>os>>>>>>null");
                result = 0;
            } else {
                if (os.isEmpty()) {
                    System.out.println("dwd_tour_tourist_from_province_m-->>>>>>客流来源省>>月>>数据1小时内未更新>>>>>>os>>>>>>empty");
                    result = 0;
                } else {
                    System.out.println("dwd_tour_tourist_from_province_m-->>>>>>客流来源省>>月>>数据正常更新");
                }
            }
        }
        return result;
    }
    @Autowired
    private DwdTourTouristNumberRtService dwdTourTouristNumberRtService;//客流实时数据 16 整点开始每五分钟更新
    private int checkDwdTourTouristNumberRt(){
        //检查是否有今天的数据且在15分钟内更新的数据
        String sql = "select * from dwd_tour_tourist_number_rt where createdate >= DATE_SUB(NOW(),INTERVAL 10 MINUTE)";
        List<DwdTourTouristNumberRt> os = dwdTourTouristNumberRtService.findBySql(sql);
        int result = 1;
        if(os==null){
            System.out.println("dwd_tour_tourist_number_rt-->>>>>>客流>>实时>>数据10分钟内未更新>>>>>>os>>>>>>null");
            result = 0;
        }else{
            if(os.isEmpty()){
                System.out.println("dwd_tour_tourist_number_rt-->>>>>>客流>>实时>>数据10分钟内未更新>>>>>>os>>>>>>empty");
                result = 0;
            }else{
                System.out.println("dwd_tour_tourist_number_rt-->>>>>>客流>>实时>>数据正常更新");
                int thisHour = getHour(new Date());
                if(thisHour>=7){
                    DwdTourTouristNumberRt o = os.get(0);
                    if(o.getNumber()==0){
                        System.out.println("dwd_tour_tourist_number_rt-->>>>>>客流>>实时>>数据正常更新>>"+thisHour+"点为止数据为0>>数据可能异常或没有获取成功");
                        result = 2;
                    }
                }
            }
        }
        return result;
    }
    @Autowired
    private DwdTourTouristNumberHService dwdTourTouristNumberHService;//客流时数据 17 每小时03分统计 即将rt表中的数据插入小时表
    private int checkDwdTourTouristNumberH(){
        //检查是否有今天的数据且在15分钟内更新的数据
        String sql = "select * from dwd_tour_tourist_number_h where  date_time = DATE_FORMAT(now(),'%Y%m%d') and createdate >= DATE_SUB(NOW(),INTERVAL 2 HOUR) order by hour desc";
        List<DwdTourTouristNumberH> os = dwdTourTouristNumberHService.findBySql(sql);
        int result = 1;
        if(os==null){
            System.out.println("dwd_tour_tourist_number_h-->>>>>>客流>>时>>数据5分钟内未更新>>>>>>os>>>>>>null");
            result = 0;
        }else{
            if(os.isEmpty()){
                System.out.println("dwd_tour_tourist_number_h-->>>>>>客流>>时>>数据5分钟内未更新>>>>>>os>>>>>>empty");
                result = 0;
            }else{
                System.out.println("dwd_tour_tourist_number_h-->>>>>>客流实>>时>>数据正常更新");
                int thisHour = getHour(new Date());
                if(thisHour>=7){
                    DwdTourTouristNumberH o = os.get(0);
                    if(o.getNumber()==0){
                        System.out.println("dwd_tour_tourist_number_h-->>>>>>客流>>时>>数据正常更新>>"+thisHour+"点为止数据为0>>数据可能异常或没有获取成功");
                        result = 2;
                    }
                }
            }
        }
        return result;
    }
    @Autowired
    private DwdTourTouristNumberDService dwdTourTouristNumberDService;//客流日数据 18 每五分钟更新一次 从04开始
    private int checkDwdTourTouristNumberD(){
        //检查是否有今天的数据且在15分钟内更新的数据
        String sql = "select * from dwd_tour_tourist_number_d where  date_time = DATE_FORMAT(now(),'%Y%m%d') and createdate >= DATE_SUB(NOW(),INTERVAL 10 MINUTE)";
        List<DwdTourTouristNumberD> os = dwdTourTouristNumberDService.findBySql(sql);
        int result = 1;
        if(os==null){
            System.out.println("dwd_tour_tourist_number_d-->>>>>>客流>>日>>数据5分钟内未更新>>>>>>os>>>>>>null");
            result = 0;
        }else{
            if(os.isEmpty()){
                System.out.println("dwd_tour_tourist_number_d-->>>>>>客流>>日>>数据5分钟内未更新>>>>>>os>>>>>>empty");
                result = 0;
            }else{
                System.out.println("dwd_tour_tourist_number_d-->>>>>>客流实>>日>>数据正常更新");
                int thisHour = getHour(new Date());
                if(thisHour>=7){
                    DwdTourTouristNumberD o = os.get(0);
                    if(o.getNumber()==0){
                        System.out.println("dwd_tour_tourist_number_d-->>>>>>客流>>日>>数据正常更新>>"+thisHour+"点为止数据为0>>数据可能异常或没有获取成功");
                        result = 2;
                    }
                }
            }
        }
        return result;
    }
    @Autowired
    private DwsTourTouristNumberWService dwsTourTouristNumberWService;//客流周数据 19 每小时xx:59:30 d->w
    private int checkDwsTourTouristNumberW(){
        //检查是否有今天的数据且在15分钟内更新的数据
        int result = 1;
        Date now = new Date();
        int day = getDay(now);
        String dateTime = getTimeString_YYYYMM(now);
        if(day<=7){
            dateTime = dateTime + "01";
        }else if(day<=14){
            dateTime = dateTime + "02";
        }else if(day<=21){
            dateTime = dateTime + "03";
        }else if(day<=28){
            dateTime = dateTime + "04";
        }else{
            dateTime = dateTime + "05";
        }
        String sql = "select * from dws_tour_tourist_number_w where  date_time = '"+dateTime+"' and createdate >= DATE_SUB(NOW(),INTERVAL 2 HOUR)";
        List<DwsTourTouristNumberW> os = dwsTourTouristNumberWService.findBySql(sql);
        if(os==null){
            System.out.println("dws_tour_tourist_number_w-->>>>>>客流>>周>>数据1小时内未更新>>>>>>os>>>>>>null");
            result = 0;
        }else{
            if(os.isEmpty()){
                System.out.println("dws_tour_tourist_number_w-->>>>>>客流>>周>>数据1小时内未更新>>>>>>os>>>>>>empty");
                result = 0;
            }else{
                System.out.println("dws_tour_tourist_number_w-->>>>>>客流实>>周>>数据正常更新");
                int thisHour = getHour(now);
                if(thisHour>=7){
                    DwsTourTouristNumberW o = os.get(0);
                    if(o.getNumber()==0){
                        System.out.println("dws_tour_tourist_number_w-->>>>>>客流>>周>>数据正常更新>>"+thisHour+"点为止数据为0>>数据可能异常或没有获取成功");
                        result = 2;
                    }
                }
            }
        }
        return result;
    }
    @Autowired
    private DwdTourTouristNumberMService dwdTourTouristNumberMService;//客流月数据 20 每小时xx:59:30 d->m
    private int checkDwdTourTouristNumberM(){
        //检查是否有今天的数据且在15分钟内更新的数据
        int result = 1;
        Date now = new Date();
        String dateTime = getTimeString_YYYYMM(now);
        String sql = "select * from dwd_tour_tourist_number_m where  date_time = '"+dateTime+"' and createdate >= DATE_SUB(NOW(),INTERVAL 2 HOUR)";
        List<DwdTourTouristNumberM> os = dwdTourTouristNumberMService.findBySql(sql);
        if(os==null){
            System.out.println("dwd_tour_tourist_number_m-->>>>>>客流>>月>>数据1小时内未更新>>>>>>os>>>>>>null");
            result = 0;
        }else{
            if(os.isEmpty()){
                System.out.println("dwd_tour_tourist_number_m-->>>>>>客流>>月>>数据1小时内未更新>>>>>>os>>>>>>empty");
                result = 0;
            }else{
                System.out.println("dwd_tour_tourist_number_m-->>>>>>客流实>>月>>数据正常更新");
                int thisHour = getHour(now);
                if(thisHour>=7){
                    DwdTourTouristNumberM o = os.get(0);
                    if(o.getNumber()==0){
                        System.out.println("dwd_tour_tourist_number_m-->>>>>>客流>>月>>数据正常更新>>"+thisHour+"点为止数据为0>>数据可能异常或没有获取成功");
                        result = 2;
                    }
                }
            }
        }
        return result;
    }
    @Autowired
    private DwsTourTouristNumberQService dwsTourTouristNumberQService;//客流季数据 21 每小时xx:59:40 d->q
    private int checkDwsTourTouristNumberQ(){
        //检查是否有今天的数据且在15分钟内更新的数据
        int result = 1;
        Date now = new Date();
        int month = getMonth(now);
        String dateTime = getTimeString_YYYY(now);
        if(month<=3){
            dateTime = dateTime + "01";
        }else if(month<=6){
            dateTime = dateTime + "02";
        }else if(month<=9){
            dateTime = dateTime + "03";
        }else{
            dateTime = dateTime + "04";
        }
        String sql = "select * from dws_tour_tourist_number_q where date_time = '"+dateTime+"' and createdate >= DATE_SUB(NOW(),INTERVAL 2 HOUR)";
        List<DwsTourTouristNumberQ> os = dwsTourTouristNumberQService.findBySql(sql);
        if(os==null){
            System.out.println("dws_tour_tourist_number_q-->>>>>>客流>>季>>数据1小时内未更新>>>>>>os>>>>>>null");
            result = 0;
        }else{
            if(os.isEmpty()){
                System.out.println("dws_tour_tourist_number_q-->>>>>>客流>>季>>数据1小时内未更新>>>>>>os>>>>>>empty");
                result = 0;
            }else{
                System.out.println("dws_tour_tourist_number_q-->>>>>>客流实>>季>>数据正常更新");
                int thisHour = getHour(now);
                if(thisHour>=7){
                    DwsTourTouristNumberQ o = os.get(0);
                    if(o.getNumber()==0){
                        System.out.println("dws_tour_tourist_number_q-->>>>>>客流>>季>>数据正常更新>>"+thisHour+"点为止数据为0>>数据可能异常或没有获取成功");
                        result = 2;
                    }
                }
            }
        }
        return result;
    }
    @Autowired
    private DwsTourTouristNumberYService dwsTourTouristNumberYService;//客流年数据 22 每小时xx:59:40 d->y
    private int checkDwsTourTouristNumberY(){
        //检查是否有今天的数据且在15分钟内更新的数据
        int result = 1;
        Date now = new Date();
        String dateTime = getTimeString_YYYY(now);
        String sql = "select * from dws_tour_tourist_number_y where  date_time = '"+dateTime+"' and createdate >= DATE_SUB(NOW(),INTERVAL 2 HOUR)";
        List<DwsTourTouristNumberY> os = dwsTourTouristNumberYService.findBySql(sql);
        if(os==null){
            System.out.println("dws_tour_tourist_number_y-->>>>>>客流>>年>>数据1小时内未更新>>>>>>os>>>>>>null");
            result = 0;
        }else{
            if(os.isEmpty()){
                System.out.println("dws_tour_tourist_number_y-->>>>>>客流>>年>>数据1小时内未更新>>>>>>os>>>>>>empty");
                result = 0;
            }else{
                System.out.println("dws_tour_tourist_number_y-->>>>>>客流实>>年>>数据正常更新");
                int thisHour = getHour(now);
                if(thisHour>=7){
                    DwsTourTouristNumberY o = os.get(0);
                    if(o.getNumber()==0){
                        System.out.println("dws_tour_tourist_number_y-->>>>>>客流>>年>>数据正常更新>>"+thisHour+"点为止数据为0>>数据可能异常或没有获取成功");
                        result = 2;
                    }
                }
            }
        }
        return result;
    }
//    @Autowired
//    private DwdTourTouristNumberDevcRtService dwdTourTouristNumberDevcRtService;//客流实时数据 23
//    @Autowired
//    private DwdTourTouristNumberDevcHService dwdTourTouristNumberDevcHService;//客流时数据 24
//
    @Autowired
    private DwsTourWeatherMonitorRtService dwsTourWeatherMonitorRtService;//实时天气25
    private int checkDwsTourWeatherMonitorRt(){
        int result = 1;
        String sql = "select * from dws_tour_weather_monitor_rt where createdate >= DATE_SUB(NOW(),INTERVAL 4 HOUR)";
        List<DwsTourWeatherMonitorRt> os = dwsTourWeatherMonitorRtService.findBySql(sql);
        if(os==null){
            System.out.println("dws_tour_weather_monitor_rt-->>>>>>天气数据2小时内未更新>>>>>>os>>>>>>null");
            result = 0;
        }else{
            if(os.isEmpty()){
                System.out.println("dws_tour_weather_monitor_rt-->>>>>>天气数据2小时内未更新>>>>>>os>>>>>>null");
                result = 0;
            }
        }
        return result;
    }

    //监控点位
    @Scheduled(fixedRate=3600000)
    public void checkDb(){
        String title = "安昌古镇";
        StringBuffer msg =  new StringBuffer("");
        // 0 更新异常
        // 1 一切正常
        // 2 数据异常 即监测的数据为0
        int checkDwdTourParkMonitorRt_result = checkDwdTourParkMonitorRt();//仅仅有0和1
        if(checkDwdTourParkMonitorRt_result == 0){
            msg.append("停车场<实时>数据15分钟内未更新;");
        }

        int checkDwdTourParkCarD_result = checkDwdTourParkCarD();
        if(checkDwdTourParkCarD_result == 0){
            msg.append("车辆<日>数据半小时内未更新;");
        }
        if(checkDwdTourParkCarD_result == 2){
            msg.append("车辆<日>数据检测为0请注意;");
        }

        int checkDwdTourParkCarW_result = checkDwdTourParkCarW();
        if(checkDwdTourParkCarW_result == 0){
            msg.append("车辆<周>数据1小时内未更新;");
        }
        if(checkDwdTourParkCarW_result == 2){
            msg.append("车辆<周>数据检测为0请注意;");
        }

        int checkDwdTourParkCarM_result = checkDwdTourParkCarM();
        if(checkDwdTourParkCarM_result == 0){
            msg.append("车辆<月>数据1小时内未更新;");
        }
        if(checkDwdTourParkCarM_result == 2){
            msg.append("车辆<月>数据检测为0请注意;");
        }

        int checkDwdTourParkCarQ_result = checkDwdTourParkCarQ();
        if(checkDwdTourParkCarQ_result == 0){
            msg.append("车辆<季>数据1小时内未更新;");
        }
        if(checkDwdTourParkCarQ_result == 2){
            msg.append("车辆<季>数据检测为0请注意;");
        }

        int checkDwdTourParkCarY_result = checkDwdTourParkCarY();
        if(checkDwdTourParkCarY_result == 0){
            msg.append("车辆<年>数据1小时内未更新;");
        }
        if(checkDwdTourParkCarY_result == 2){
            msg.append("车辆<年>数据检测为0请注意;");
        }

        int checkDwdTourParkCarFromCityD_result = checkDwdTourParkCarFromCityD();
        if(checkDwdTourParkCarFromCityD_result == 0){
            msg.append("车辆来源市<日>数据半小时内未更新;");
        }

        int checkDwdTourParkCarFromCityM_result = checkDwdTourParkCarFromCityM();
        if(checkDwdTourParkCarFromCityM_result == 0){
            msg.append("车辆来源市<月>数据1小时内未更新;");
        }

        int checkDwdTourParkCarFromProvinceD_result = checkDwdTourParkCarFromProvinceD();
        if(checkDwdTourParkCarFromProvinceD_result == 0){
            msg.append("车辆来源省<日>数据1小时内未更新;");
        }

        int checkDwdTourParkCarFromProvinceM_result = checkDwdTourParkCarFromProvinceM();
        if(checkDwdTourParkCarFromProvinceM_result == 0){
            msg.append("车辆来源省<月>数据1小时内未更新;");
        }
        int checkDwdTourTouristFromCityW_result = checkDwdTourTouristFromCityW();
        if(checkDwdTourTouristFromCityW_result == 0){
            msg.append("客流来源市<周>数据1小时内未更新;");
        }
        int checkDwdTourTouristFromCityM_result = checkDwdTourTouristFromCityM();
        if(checkDwdTourTouristFromCityM_result == 0){
            msg.append("客流来源市<月>数据1小时内未更新;");
        }
        int checkDwdTourTouristFromProvinceW_result = checkDwdTourTouristFromProvinceW();
        if(checkDwdTourTouristFromProvinceW_result == 0){
            msg.append("客流来源省<周>数据1小时内未更新;");
        }
        int checkDwdTourTouristFromProvinceM_result = checkDwdTourTouristFromProvinceM();
        if(checkDwdTourTouristFromProvinceM_result == 0){
            msg.append("客流来源省<月>数据1小时内未更新;");
        }

        /*客流*/
        int checkDwdTourTouristNumberRt_result = checkDwdTourTouristNumberRt();
        if(checkDwdTourTouristNumberRt_result == 0){
            msg.append("客流<实时>数据5分钟内未更新;");
        }
        if(checkDwdTourTouristNumberRt_result == 2){
            msg.append("客流<实时>数据检测为0请注意;");
        }

        int checkDwdTourTouristNumberH_result = checkDwdTourTouristNumberH();
        if(checkDwdTourTouristNumberH_result == 0){
            msg.append("客流<时>数据1小时内未更新;");
        }
        if(checkDwdTourTouristNumberH_result == 2){
            msg.append("客流<时>数据检测为0请注意;");
        }

        int checkDwdTourTouristNumberD_result = checkDwdTourTouristNumberD();
        if(checkDwdTourTouristNumberD_result == 0){
            msg.append("客流<日>数据5分钟内未更新;");
        }
        if(checkDwdTourTouristNumberD_result == 2){
            msg.append("客流<日>数据检测为0请注意;");
        }
        int checkDwsTourTouristNumberW_result = checkDwsTourTouristNumberW();
        if(checkDwsTourTouristNumberW_result == 0){
            msg.append("客流<周>数据1小时内未更新;");
        }
        if(checkDwsTourTouristNumberW_result == 2){
            msg.append("客流<周>数据检测为0请注意;");
        }

        int checkDwdTourTouristNumberM_result = checkDwdTourTouristNumberM();
        if(checkDwdTourTouristNumberM_result == 0){
            msg.append("客流<月>数据1小时内未更新;");
        }
        if(checkDwdTourTouristNumberM_result == 2){
            msg.append("客流<月>数据检测为0请注意;");
        }

        int checkDwsTourTouristNumberQ_result = checkDwsTourTouristNumberQ();
        if(checkDwsTourTouristNumberQ_result == 0){
            msg.append("客流<季>数据1小时内未更新;");
        }
        if(checkDwsTourTouristNumberQ_result == 2){
            msg.append("客流<季>数据检测为0请注意;");
        }

        int checkDwsTourTouristNumberY_result = checkDwsTourTouristNumberY();
        if(checkDwsTourTouristNumberY_result == 0){
            msg.append("客流<年>数据1小时内未更新;");
        }
        if(checkDwsTourTouristNumberY_result == 2){
            msg.append("客流<年>数据检测为0请注意;");
        }

        int checkDwsTourWeatherMonitorRt_result = checkDwsTourWeatherMonitorRt();
        if(checkDwsTourWeatherMonitorRt_result == 0){
            msg.append("天气数据2小时内未更新;");
        }
        //陈一17855827500,吴江15695881085,戴总18058153655
        String phonelist = "17855827500,15695881085,18058153655";
        if(!msg.toString().equals("")){
            SendMsg.MsgPost(phonelist,title,msg.toString());
        }else{
            if(getHour(new Date())==7){
                msg.append("正常运行");
                SendMsg.MsgPost(phonelist,title,msg.toString());
            }
            if(getHour(new Date())==22){
                msg.append("正常运行");
                SendMsg.MsgPost(phonelist,title,msg.toString());
            }
        }
    }
}
