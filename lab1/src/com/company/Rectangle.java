package com.company;


import java.awt.*;
import java.io.Serializable;

import static java.lang.Math.abs;

public class Rectangle implements Serializable {
    public int x1, x2, y2, y1;
    public Rectangle (int x1, int y1, int x2, int y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }
    public Rectangle (int high, int width) {
        x1 = 0;
        x2 = width;
        y1 = 0;
        y2 = high;
    }
    public Rectangle () {
        x1 = 0;
        y1 = 0;
        x2 = 100;
        y2 = 100;
    }
    public void rect_print() {
        System.out.println("x1 = " + x1);
        System.out.println("y1 = " + y1);
        System.out.println("x2 = " + x2);
        System.out.println("y2 = " + y2);
    }
    public void move(int dx, int dy) {
        x1 += dx;
        x2 += dx;
        y1 += dy;
        y2 += dy;
    }
    public Rectangle Union (Rectangle obj) {
        Rectangle curObject = new Rectangle();
        if (this.x1 < obj.x1) {
            curObject.x1 = this.x1;
        }
        else {
            curObject.x1 = obj.x1;
        }
        if (this.y1 < obj.y1) {
            curObject.y1 = this.y1;
        }
        else {
            curObject.y1 = obj.y1;
        }
        if (this.x2 > obj.x2) {
            curObject.x2 = this.x2;
        }
        else {
            curObject.x2 = obj.x2;
        }
        if (this.y2 > obj.y2) {
            curObject.y2 = this.y2;
        }
        else {
            curObject.y2 = obj.y2;
        }
        return curObject;
    }
    public void draw(Graphics g) {
        g.setColor(Color.BLACK);
        g.drawRect(x1, y1, abs(x1 - x2), abs(y1 - y2));
    }
}

