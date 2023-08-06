package com.js;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Date;

import static com.js.GameUtil.*;

public class MyGameFrame extends Frame {
    Image bg = getImage("Images/bg.png");
    Image xhy = getImage("Images/xhy.png");
    Plane plane = new Plane(xhy,300,750,8,40,40);
    Shell[] shells = new Shell[1000];

    //Initialize window
    void launchFrame(){
        this.setTitle("Persist two minutes if you are a man");     //title of window
        this.setVisible(true);                                   //invisible in default, make the window visible
        this.setSize(Frame_Width, Frame_Height);    //size of window
        this.setLocation(500,70);                          //the location on screen

        //add "closing window" function
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        //Start the window drawing thread
        new PaintThread().start();

        //Start keyboard listener
        this.addKeyListener(new KeyMonitor());

        //initialize the array for shells
        for(int i = 0; i<shells.length; i++){
            shells[i] = new Shell();
        }

    }

    Explode explode; //create explosion

    Date startTime = new Date();
    Date endTime;
    int period; //persistence time

    @Override
    //g is a drawing brush
    public void paint(Graphics g) {  //add attributes
        g.drawImage(bg,0,0,Frame_Width,Frame_Height,null);
        plane.drawSelf(g);
        for(int i = 0; i<shells.length; i++){
            if(shells[i]!=null){
                shells[i].drawSelf(g);

                // TestCollision
                boolean TestCollision = shells[i].getRec().intersects((plane.getRec()));
                if(TestCollision){
                    plane.live = false;
                    endTime = new Date();
                    //calculation of persistence time
                    period = (int) ((endTime.getTime() - startTime.getTime())/1000);
                    if(explode == null){
                        explode = new Explode(plane.x,plane.y);
                    }
                    explode.draw(g);
                }
            }
        }
        if(!plane.live){
            info(g,"You are toooo weak!!!",50,30,400,Color.white);
            info(g,"You only persisted  "+period+" seconds",20,150,500,Color.white);
            info(g,"press anywhere to restart",20,150,700,Color.white);
            g.drawImage(xhy,200, 100, 200, 200, null);
            addMouseListener(adapter);
            addMouseMotionListener(adapter);
        }
    }

    //set up content on the screen when plane dies
    public void info(Graphics g,String str, int size, int x,int y, Color color){
        Font oldFont = g.getFont();
        Color oldColor = g.getColor();

        Font f = new Font("微软雅黑", Font.BOLD,size);
        g.setFont(f);
        g.setColor(color);
        g.drawString(str,x,y);

        g.setColor(oldColor);
        g.setFont(oldFont);
    }

    //inner class of keyboard listener
    class KeyMonitor extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            plane.addDirection(e);
        }

        @Override
        public void keyReleased(KeyEvent e) {
            plane.minusDirection(e);
        }
    }


    //mouse click to restart the game
    MouseAdapter adapter = new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            plane.live = true;
            Plane plane = new Plane(xhy,300,750,6,50,40);
            Shell[] shells = new Shell[1000];
            repaint();
        }

    };


    //redraw thread
    class PaintThread extends Thread {
        public  void run(){
            while(true){
                repaint();
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        MyGameFrame f = new MyGameFrame();
        f.launchFrame();
    }

    //Resolve the blinking screen problem
    private Image offScreenImage = null;
    public void update(Graphics g) {
        if(offScreenImage == null)
            offScreenImage = this.createImage(Frame_Width,Frame_Height);

        Graphics gOff = offScreenImage.getGraphics();
        paint(gOff);
        g.drawImage(offScreenImage,0,0,null);
    }

}


