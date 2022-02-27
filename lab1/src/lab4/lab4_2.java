package lab4;
import com.company.ColoredRect;
import com.company.DrawableRect;
import com.company.Rectangle;
import lab5.*;

import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
public class lab4_2 extends Applet {
    int last_x, last_y;
    //com.company.Rectangle[] rectangles = new Rectangle[1];
    private ArrayList<com.company.Rectangle> rectangles = new ArrayList<Rectangle>();
    public void init() {
// Определяет, создает и регистрирует объект MouseListener.

        this.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                last_x = e.getX(); last_y = e.getY();
            }
        });
// Определяет, создает и регистрирует объект MouseMotionListener.
        this.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                Graphics g = getGraphics();
                g.setColor(getBackground());
                int x = e.getX(), y = e.getY();
                g.fillRect(0, 0, getSize().width, getSize().height);
                for (int i = 0; i < rectangles.size(); i++) {
                    if (x >= rectangles.get(i).x1 && x <= rectangles.get(i).x2 && y >= rectangles.get(i).y1 && y <= rectangles.get(i).y2) {
                        rectangles.get(i).move(e.getX() - last_x, e.getY() - last_y);
                    }

                    //g.setColor(Color.black);
                    //g.drawLine(last_x, last_y, x, y);
                    rectangles.get(i).draw(g);

                    //continue;
                }
                last_x = x;
                last_y = y;
            }
        });
// Создает кнопку Clear.
        Button b = new Button("Rect");
        Button b1 = new Button("ColoredRect");
        Button b2 = new Button("DrawableRect");
        Button b3 = new Button("LoadFromFile");
        Button b4 = new Button("SaveToFile");
// Определяет, создает и регистрирует объект слушателя
// для обработки события, связанного с нажатием кнопки.
        ActionListener btn = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
//                int array_num = 0;
                int xStart = (int)(Math.random() * 924);
                int yStart = (int)(Math.random() * 668);
                Graphics g = getGraphics();
                if(e.getSource() == b) {
                    rectangles.add(new Rectangle(xStart, yStart,xStart + 50,yStart + 50));
                } else if(e.getSource() == b1) {
                    rectangles.add(new ColoredRect(Color.green, Color.MAGENTA, xStart, yStart,xStart + 100,yStart + 70));
                }
                else if (e.getSource() == b2) {
                    rectangles.add(new DrawableRect(Color.red, xStart, yStart,xStart + 80,yStart + 100));
                }
                else if (e.getSource() == b4) {
                    try {
                        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("C:/Users/Maxim/source/lab1/src/lab4/output.bin"));
                        for (int i = 0; i < rectangles.size(); i++) {
                            objectOutputStream.writeObject(rectangles.get(i));
                        }
                        objectOutputStream.close();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
                else if (e.getSource() == b3) {
                    //rectangles = new ArrayList<Rectangle>();
                    boolean notEmpty = true;
                    try {
                        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("C:/Users/Maxim/source/lab1/src/lab4/output.bin"));
                        while(notEmpty) {
                            Object obj = null;
                            try {
                                try {
                                    obj = objectInputStream.readObject();
                                } catch (EOFException ex) {
                                    System.out.print("");
                                    //стоило записывать весь список в файл сразу, а не по одному объекту
                                    objectInputStream.close();
                                }

                            } catch (ClassNotFoundException ex) {
                                ex.printStackTrace();
                            }
                            if(obj != null)
                                rectangles.add((Rectangle) obj);
                            else
                                notEmpty = false;
                        }
                    } catch (FileNotFoundException ex) {
                        ex.printStackTrace();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    for (int i = 0; i < rectangles.size() - 1; i++) {
                        rectangles.get(i).draw(g);
                    }
                }
                rectangles.get(rectangles.size() - 1).draw(g);
            }
        };
        b.addActionListener(btn);
        b1.addActionListener(btn);
        b2.addActionListener(btn);
        b3.addActionListener(btn);
        b4.addActionListener(btn);
// Добавляет кнопку в апплет.
        this.add(b);
        this.add(b1);
        this.add(b2);
        add(b3);
        add(b4);
    }
}