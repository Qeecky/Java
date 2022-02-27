package com.company;

import java.awt.*;
import java.io.Serializable;

public class ColoredRect extends DrawableRect implements Serializable {
    private Color inColor;
    public ColoredRect (Color outColor, Color inColor, int x1, int y1, int x2, int y2) {
        super(outColor, x1, y1, x2, y2);
        this.inColor = inColor;
    }
    public ColoredRect (Color outColor, Color inColor, int height, int width) {
        super(outColor, height, width);
        this.inColor = inColor;
    }
    public void draw (Graphics g) {
        g.setColor(inColor);
        g.fillRect(x1, y1, x2 - x1, y2 - y1);
        super.draw(g);
    }
}
