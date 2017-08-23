package com.gkpoter.client;

import java.io.ByteArrayOutputStream;
import java.net.*;

public class SocketServerUDP {

    private ScreenImg thread;
    private int PORT = 8080;

    private InetAddress address;


    public SocketServerUDP() throws UnknownHostException {
        address = InetAddress.getByName("localhost");
    }

    public void start(){

        thread = new ScreenImg(img -> {
            try {
                ByteArrayOutputStream ByteArray = Utils.bufferImage2Byte(img);
                byte[] datas = ByteArray.toByteArray();

                DatagramPacket packet = new DatagramPacket(datas, 65500, address, PORT);
                DatagramSocket socket = new DatagramSocket();
                socket.send(packet);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        thread.start();
    }
}
