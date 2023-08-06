package com.js;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.net.URL;

//Util class
public class GameUtil {

    public static final int Frame_Width = 600;
    public static final int Frame_Height = 900;

    //The constructor is private, preventing external object creation
    private GameUtil(){}

    public static Image getImage(String path){
        Image img = null;
        URL u = GameUtil.class.getClassLoader().getResource(path);
        try {
            img = ImageIO.read(u);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return img;
    }

}
