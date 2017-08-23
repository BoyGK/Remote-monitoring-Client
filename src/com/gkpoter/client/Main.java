package com.gkpoter.client;

import javax.swing.*;
import java.io.IOException;
import java.net.Socket;

public class Main {


    private static Socket socket = null;
    private static String URL = null;

    public static void main(String... args) {

        Runtime.getRuntime().addShutdownHook(new Thread(() -> System.out.println("Closed")));

        //opWindowStart();
        while (true) {
            if (socket == null || !(socket.isConnected())) {
                try {
                    if (URL != null && !URL.equals("")) {
                        openSocket();
                    } else {
                        URL = JOptionPane.showInputDialog("Please input ServerHost");
                    }
                } catch (Exception e) {
                    //e.printStackTrace();
                    System.out.println("连接服务器失败，10秒后将重连");
                    try {
                        Thread.sleep(10000);
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }
                }
            } else {
                try {
                    Thread.sleep(30000);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }

    /**
     * 设置开机自启
     */
    private static void opWindowStart() {
        String key = "HKEY_LOCAL_MACHINE\\SOFTWARE\\Microsoft\\Windows\\CurrentVersion\\Run";
        String name = "ClientWay";//启动项名称
        String value = System.getProperty("user.dir");//程序路径
        String command = "reg add " + key + " /v " + name + " /d " + value;
        try {
            Runtime.getRuntime().exec(command);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void openSocket() throws IOException {
        socket = new Socket(URL, 8080);
        new SocketServerTCP(socket, new SocketServerTCP.SocketListener() {
            @Override
            public void close() {
                try {
                    socket.close();
                    socket = null;
                    System.out.println("Socket Closed");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void jg() {
                System.out.println("警告!!!");
                JOptionPane.showMessageDialog(null, "禁止做违反课堂纪律的操作!!!");
            }
        }).start();
    }
}
