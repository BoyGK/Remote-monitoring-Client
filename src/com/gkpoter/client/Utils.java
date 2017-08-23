package com.gkpoter.client;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;

public class Utils {

    public static ByteArrayOutputStream bufferImage2Byte(BufferedImage image) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            ImageIO.write(image, "jpg", out);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return out;
    }

}
