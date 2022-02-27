package lab4;

import java.applet.*;
import java.awt.*;
import java.awt.event.*;
//сам апплет реализует интерфейсы,т.е. является слушателем
public class Circle extends Applet implements
        MouseListener, MouseMotionListener {
    private int last_x, last_y;
    private Color c;
    public void init() {
// Сообщает данному апплету о том, какие объекты
// классов MouseListener и MouseMotionListener он должен оповещать
// о событиях, связанных с мышью и ее перемещением.
// Поскольку интерфейс реализуется в самом апплете,
// при этом будут вызываться методы апплета.
        this.addMouseListener(this) ;
        this.addMouseMotionListener(this);
        //repaint();

    }
    // Метод интерфейса MouseListener. Вызывается при нажатии
// пользователем кнопки мыши.
    public void mousePressed(MouseEvent e) {
        if ((e.getModifiers() & MouseEvent.BUTTON1_MASK) != 0) {
            last_x = e.getX();
            last_y = e.getY();
        }

        if ((e.getModifiers() & MouseEvent.BUTTON1_MASK) == 0)  {
            c = new Color((int)(Math.random() * 255), (int)(Math.random() * 255), (int)(Math.random() * 255));
        }
    }
    // Метод интерфейса MouseMotionListener. Вызывается при
    // перемещении мыши с нажатой кнопкой.
    public void mouseDragged(MouseEvent e) {
        Graphics g = this.getGraphics();
        int x = e.getX(), y = e.getY();
        if ((e.getModifiers() & MouseEvent.BUTTON1_MASK) != 0) {
            g.setColor(c);
            //g.drawLine(last_x, last_y, x, y);
            g.drawRect(last_x, last_y, 50, 50);
            last_x = x; last_y = y;

        }
//        if ((e.getModifiers() & MouseEvent.BUTTON1_MASK) == 0) {
//            g.setColor(Color.MAGENTA);
//        }
    }
    // Другие, не используемые методы интерфейса MouseListener.
    public void mouseReleased(MouseEvent e) {;}
    public void mouseClicked(MouseEvent e) {


    }
    public void mouseEntered(MouseEvent e) {;}
    public void mouseExited(MouseEvent e) {;}
    // Другой метод интерфейса MouseMotionListener.
    public void mouseMoved(MouseEvent e) {;}
}
