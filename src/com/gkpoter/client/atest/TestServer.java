package com.gkpoter.client.atest;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class TestServer extends JFrame {

    public static void main(String... args) {

        DatagramSocket socket = null;
        try {
            socket = new DatagramSocket(8080);
        } catch (SocketException e) {
            e.printStackTrace();
        }

        byte[] data = new byte[1024];
        DatagramPacket packet = new DatagramPacket(data, data.length);

        try {
            socket.receive(packet);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(new String(data,0,packet.getLength()));

    }

    BufferedImage image;
    public TestServer(BufferedImage image){
        this.image = image;
        this.setVisible(true);
        this.setSize(800,800);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(image,0,0,null);
    }
}
