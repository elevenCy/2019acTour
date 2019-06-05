package com.tujing;

import com.winter.common.HttpRequest;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.*;
import java.net.*;
public class Test {
    public static void main(String[] args) throws IOException {
        HttpRequest httpRequest = new  HttpRequest();
        String WIFI_URL = "http://172.16.50.100:8080/imcrs/wlan/apInfo/queryApBasicInfo";
        JSONObject json = HttpRequest.sendGetForWIFI(WIFI_URL);
        System.out.println(json);
        JSONArray array = json.getJSONArray("apBasicInfo");
        System.out.println(array);
        for (int i = 0; i < array.size(); i++) {
            JSONObject jsb = array.getJSONObject(i);  // 遍历 jsonarray 数组，把每一个对象转成 json 对象
            String mac = jsb.get("macAddress").toString();  //MAC地址
            String ip =  jsb.get("ipAddress").toString();   //IP地址
            String status = jsb.get("onlineStatus").toString();    //在线状态  0表示不在线，1表示在线
            String count = jsb.get("onlineClientCount").toString();   //在线移动用户数
            String name =  jsb.get("apAlias").toString(); //AP别名
            String code = jsb.get("serialId").toString(); //AC的设备ID。
            System.out.println("mac:  "+mac+"  -  IP:  "+ip+"   -  状态:  "+status+"   -  ID:  "+code+"  - 名称:  "+name+"  -  连接数:  "+count);
        }
    }
}
