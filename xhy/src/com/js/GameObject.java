package com.js;

//import com.sun.corba.se.impl.orbutil.graph.Graph;
import org.w3c.dom.css.Rect;

import java.awt.*;

//The root class of the game object
public class GameObject {
    Image img;
    int x,y;
    int speed;
    int width,height;

    //drawSelf
    void drawSelf(Graphics g){
        g.drawImage(img,x,y,width,height,null);
    }

    //return the corresponding rectangle of the object
    public Rectangle getRec(){
        return  new Rectangle(x,y,width,height);
    }

    //constructor
    public GameObject(Image img, int x, int y, int speed, int width, int height) {
        this.img = img;
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.width = width;
        this.height = height;
    }

    public GameObject(Image img, int x, int y, int speed) {
        this(img,x,y);
        this.speed = speed;

    }

    public GameObject(Image img, int x, int y) {
        this(img);
        this.x = x;
        this.y = y;
    }

    public GameObject(Image img) {
        this.img = img;
        if(this.img!=null){
            this.width = img.getWidth(null);
            this.height = img.getHeight(null);
        }
    }

    public GameObject(){}
}
