package com.company;

import java.awt.*;
import java.io.Serializable;

public class DrawableRect extends Rectangle implements Serializable {
    protected Color outColor;
    public DrawableRect (Color c, int x1, int y1, int x2, int y2) {
        super(x1, y1, x2, y2);
        outColor = c;
    }
    public DrawableRect (Color c, int height, int width) {
        super(height, width);
        outColor = c;
    }
    public void draw(Graphics g) {
        g.setColor(outColor);
        g.drawRect(x1, y1, x2 - x1, y2 - y1);
    }

}
