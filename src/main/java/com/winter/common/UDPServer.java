package com.winter.common;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPServer {
    public static void main(String[] args) {
        byte[] bufferIn=new byte[26];
        byte[] bufferOut;
        try {
            DatagramSocket ds=new DatagramSocket(1234);
            System.out.println("UDP Server actived on port 1234..." );
            int num=0;
            while(num<100){
                num++;
                DatagramPacket packetIn=new DatagramPacket(bufferIn,bufferIn.length);
                ds.receive(packetIn);
                InetAddress ia=packetIn.getAddress();
                int port=packetIn.getPort();
                String str=new String(bufferIn);
                System.out.println(str);
                str=str.toUpperCase();
                bufferOut=str.getBytes();
                System.out.println("["+num+"] "+ia.toString()+":"+port);
                DatagramPacket packetOut=new DatagramPacket(bufferOut,bufferOut.length,ia,port);
                System.out.println(packetOut.getData());
                ds.send(packetOut);
            }

            ds.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
