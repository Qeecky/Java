package com.company;

import java.applet.*;
import java.awt.*;
import java.awt.Rectangle;
import java.util.Random;

public class BouncingCircle extends Applet implements Runnable {
    //public com.company.ColoredRect coloredRect = new com.company.ColoredRect(Color.BLUE, Color.red, 0,200, 100, 100);
    public ColoredRect coloredRect1 = new ColoredRect(Color.red, Color.CYAN, 100, 200);
    public  int[] dislocationX = new int[30];
    public  int[] dislocationY = new int[30];
    int x = 150, y = 50, r = 50;
    int dx = 11, dy = 7;
    int rdx = 10, rdy = 10;
    Thread animator;
    volatile boolean pleaseStop;
    com.company.Rectangle[] rectangles = new com.company.Rectangle[30];

    public void paint(Graphics g) {
        setBackground(Color.yellow);
        g.setColor(Color.red);
        g.fillOval(x-r, y-r, r*2, r*2);
        coloredRect1.draw(g);
        for (int i = 0; i < rectangles.length; i++) {
            rectangles[i].draw(g);
        }
    }

    public void animate() {
        Rectangle bounds = getBounds();
        if ((x - r + dx < 0) || (x + r + dx > bounds.width)) dx = -dx;
        if ((y - r + dy < 0) || (y + r + dy > bounds.height)) dy = -dy;
        x += dx; y += dy;
        if ((coloredRect1.x1 + rdx < 0) || (coloredRect1.x2 + rdx > bounds.width)) {
            rdx = -rdx;
        }
        if ((coloredRect1.y1 + rdy < 0) || (coloredRect1.y2 + rdy > bounds.height)) {
            rdy = -rdy;
        }
        coloredRect1.move(rdx, rdy);
        for (int i = 0; i < rectangles.length; i++) {
            if ((rectangles[i].x1 + dislocationX[i] < 0) || (rectangles[i].x2 + dislocationX[i] > bounds.width)) {
                dislocationX[i] = -dislocationX[i];

            }
            if ((rectangles[i].y1 + dislocationY[i] < 0) || (rectangles[i].y2 + dislocationY[i] > bounds.height)) {
                dislocationY[i] = -dislocationY[i];
            }
            rectangles[i].move(dislocationX[i], dislocationY[i]);
        }
        repaint();
    }
    public void run() {
        while(!pleaseStop) {
            animate();
            try { Thread.sleep(100); }
            catch(InterruptedException e) {}
        }
    }
    public void start() {
        for (int i = 0; i < 30; i++) {
            if (i < 10) {
                rectangles[i] = new com.company.Rectangle(600, 200, 800, 400);
                dislocationX[i] = i * 2;
                dislocationY[i] = i * 4;
            }
            else if (i < 20) {
                rectangles[i] = new DrawableRect(Color.MAGENTA, i * 10, i * 10);
                dislocationX[i] = i * 3;
                dislocationY[i] = i * 5;
            }
            else {
                rectangles[i] = new ColoredRect(Color.red, Color.green, i * 5,  i * 5);
                dislocationX[i] = i ;
                dislocationY[i] = i * 2;
            }
        }
        animator = new Thread(this);
        pleaseStop = false;
        animator.start();
    }
    public void stop() { pleaseStop = true; }
}
