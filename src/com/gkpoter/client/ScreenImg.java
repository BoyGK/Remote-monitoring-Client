package com.gkpoter.client;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

/*
 * ly  2014-11-20
 * 该类实时发送截屏消失，多线程实现，不包含鼠标信息，且没有做对每个Client做优化处理
 */
public class ScreenImg extends Thread {

    private Robot robot;
    private  Dimension screen;
    private Rectangle rect ;
    private ImageListener listener;

    private boolean THREAD_STATE=true;

    private long SLEEP_TIME=1;

    public ScreenImg(ImageListener listener) {
        this.listener = listener;
        try {
            robot = new Robot();
        } catch (Exception e) {
            e.printStackTrace();
        }
        screen = Toolkit.getDefaultToolkit().getScreenSize();  //获取主屏幕的大小
        rect = new Rectangle(screen);                          //构造屏幕大小的矩形
    }

    @Override
    public void run() {
        while(THREAD_STATE) {
            BufferedImage img = robot.createScreenCapture(rect);
            listener.imageBack(img);
            try {
                sleep(SLEEP_TIME);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void close() {
        this.THREAD_STATE = false;
    }

    interface ImageListener{
        void imageBack(BufferedImage img);
    }
}