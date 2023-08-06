package com.js;

import java.awt.*;
import java.awt.event.KeyEvent;
import static com.js.GameUtil.*;
//Plane class
public class Plane extends GameObject{

    boolean left,up,down,right;
    boolean live = true;
    @Override
    public void drawSelf(Graphics g) {
        if(!live){
            return;
        }

        super.drawSelf(g);
        if(left){
            x-=speed;
        }
        if(right){
            x+=speed;
        }
        if(up){
            y-=speed;
        }
        if(down){
            y+=speed;
        }

    }

    //Control positive direction
    public void addDirection(KeyEvent e){
        switch (e.getKeyCode()){
            case KeyEvent.VK_LEFT:
                left = true;
                break;
            case KeyEvent.VK_RIGHT:
                right = true;
                break;
            case KeyEvent.VK_UP:
                up = true;
                break;
            case KeyEvent.VK_DOWN:
                down = true;
                break;
        }
    }

    //Control negative direction
    public void minusDirection(KeyEvent e){
        switch (e.getKeyCode()){
            case KeyEvent.VK_LEFT:
                left = false;
                break;
            case KeyEvent.VK_RIGHT:
                right = false;
                break;
            case KeyEvent.VK_UP:
                up = false;
                break;
            case KeyEvent.VK_DOWN:
                down = false;
                break;
        }
    }

    public Plane(Image img, int x, int y, int speed, int width, int height) {
        super(img, x, y, speed, width, height);
    }
}
