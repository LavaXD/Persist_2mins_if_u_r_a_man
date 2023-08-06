package com.js;

import java.awt.*;

//Shell class
public class Shell extends GameObject{
    double degree;

    @Override
    void drawSelf(Graphics g) {
        Color c = g.getColor();
        Color c1 = new Color(180, 82, 91);
        g.setColor(c1);
        g.fillOval(x,y,width,height);

        //shell flying
        x+= speed*Math.cos(degree);
        y+= speed*Math.sin(degree);

        //realize rebound in case of boundary collision
        if(y>GameUtil.Frame_Height-height || y<0){
            degree = -degree;
        }

        if(x>GameUtil.Frame_Width-width || x<0){
            degree = Math.PI-degree;
        }

        g.setColor(c);
    }

    public Shell() {
        degree = Math.random()*Math.PI*2;
        x = 300;
        y = 200;
        width = 20;
        height = 20;
        speed = 4 ;
    }
}
