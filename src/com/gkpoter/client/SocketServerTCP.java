package com.gkpoter.client;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class SocketServerTCP {

    private Socket socket;
    private ScreenImg thread;
    private SocketListener listener;

    public SocketServerTCP(Socket socket, SocketListener listener) {
        this.socket = socket;
        this.listener = listener;
    }

    public void start(){

        thread = new ScreenImg(img -> {
            OutputStream os = null;
            try {
                os = socket.getOutputStream();
                ByteArrayOutputStream ByteArray = Utils.bufferImage2Byte(img);
                byte[] datas = ByteArray.toByteArray();

                //System.out.println(datas.length);
                os.write((datas.length + "").getBytes());
                os.flush();
                os.write(datas, 0, datas.length);
                os.flush();

            } catch (IOException e) {
                thread.close();
                listener.close();
                System.out.println("Thread Closed");
                //e.printStackTrace();
            }
        });
        thread.start();

        new Thread(() -> {
            while(true){
                try {
                    InputStream is=socket.getInputStream();
                    byte[] bytes = new byte[1024];
                    int len = is.read(bytes);
                    if("jg".equals(new String(bytes,0,len))){
                        listener.jg();
                    }
                } catch (Exception e) {
                    //e.printStackTrace();
                }
            }
        }).start();
    }

    interface SocketListener{
        void close();
        void jg();
    }
}
