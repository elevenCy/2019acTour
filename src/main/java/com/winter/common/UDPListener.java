package com.winter.common;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class UDPListener implements ServletContextListener {

    public static final int MAX_UDP_DATA_SIZE = 1024*65;
    public static final int UDP_PORT = 1234;

    @Override
    public void contextInitialized(ServletContextEvent sce) {

        System.out.println("========UDPListener Initialized=========");
        try {
        // 启动一个线程，监听UDP数据报
            //new Thread(new UDPProcess(UDP_PORT)).start();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    class UDPProcess implements Runnable {
        DatagramSocket socket = null;

        public UDPProcess(final int port) throws SocketException {
            socket = new DatagramSocket(port);
        }

        @Override
        public void run() {
            // TODO Auto-generated method stub
            System.out.println("=======UDPProcess======");
            while (true) {
                byte[] buffer = new byte[MAX_UDP_DATA_SIZE];
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                try {
                    socket.receive(packet);
                    new Thread(new Process(packet)).start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    class Process implements Runnable {

        public Process(DatagramPacket packet) throws UnsupportedEncodingException {
            // TODO Auto-generated constructor stub
            byte[] buffer = packet.getData();// 接收到的UDP信息，然后解码
            String srt1 = new String(buffer,"GBK").trim();
            String srt2 = new String(buffer, "UTF-8").trim();
            String srt3 = new String(buffer,"ISO-8859-1").trim();
            System.out.println("=======Process srt1 GBK======" + srt1);
            System.out.println("=======Process srt2 UTF-8======" + srt2);
            System.out.println("=======Process srt3 ISO-8859-1======" + srt3);
        }

        @Override
        public void run() {
            // TODO Auto-generated method stub
            System.out.println("====Process run=====");
        }

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("========UDPListener Destroyed=========");
    }

}