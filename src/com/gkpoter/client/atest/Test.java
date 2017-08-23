package com.gkpoter.client.atest;

import com.gkpoter.client.SocketServerUDP;

import java.io.IOException;
import java.net.*;

public class Test {

    public static void main(String... args){
        DatagramPacket packet = null;
        try {
            packet = new DatagramPacket("Hello".getBytes(),
                    "Hello".getBytes().length, InetAddress.getByName("localhost"), 8080);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        DatagramSocket socket = null;
        try {
            socket = new DatagramSocket();
        } catch (SocketException e) {
            e.printStackTrace();
        }
        try {
            socket.send(packet);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
