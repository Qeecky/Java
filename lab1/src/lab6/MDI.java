package lab6;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.beans.PropertyVetoException;
import java.io.File;

public class MDI extends JFrame {
    public MDI() {
        super("SimpleMDI");
        setSize(1000, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
// создаем рабочий стол Swing
        JDesktopPane desktopPane = new JDesktopPane();
// добавляем его в центр окна
        add(desktopPane);
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        JMenu menu = new JMenu("File");
        menuBar.add(menu);
        JMenuItem menuItemNew = new JMenuItem("New");
        menuItemNew.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //System.out.println("new button clicked");
                JInternalFrame frame2 = new JInternalFrame(
                        "lab6", true, true, true, true);
                desktopPane.add(frame2);
                frame2.setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
                frame2.setSize(400, 200);
                frame2.setLocation(350, 450);
                frame2.setVisible(true);
                JButton jButton = new JButton("show chosen table");
                jButton.setSize(100, 50);
                frame2.add(jButton);
                JRadioButton jRadioButton1 = new JRadioButton("отображать таблицу умножения");
                //jRadioButton1.setSize(frame2.getWidth(), 10);
                //jRadioButton1.setLocation(0, frame2.getHeight() / 2);
                frame2.add(jRadioButton1);
                JRadioButton jRadioButton2 = new JRadioButton("отображать таблицу сложения");
                //jRadioButton1.setSize(frame2.getWidth(), 10);
                //jRadioButton1.setLocation(0, frame2.getHeight() - 40);
                ButtonGroup group = new ButtonGroup();
                group.add(jRadioButton1);
                group.add(jRadioButton2);
                frame2.add(jRadioButton2);
                frame2.setLayout(new FlowLayout());

                jButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        //try {
                            //frame2.setClosed(true);
                            //frame2.dispatchEvent(new WindowEvent(frame2, WindowEvent.WINDOW_CLOSED));
                        frame2.setVisible(false);
                        frame2.dispose();
                        //frame2.repaint();
//                            //Thread.sleep(100);
//                        } catch (PropertyVetoException ex) {
//                            ex.printStackTrace();
//                        }
                       // if (!frame2.isClosed()) System.out.println("hiii");
                        if (!jRadioButton1.isSelected() && !jRadioButton2.isSelected()) {
                            JInternalFrame erframe = new JInternalFrame("ERROR", true, true);
                            desktopPane.add(erframe);
                            erframe.setSize(400, 200);
                            erframe.setLocation(350, 250);
                            erframe.setVisible(true);
                            System.out.println("error");
                        }
                        else if (jRadioButton1.isSelected()) {
//                            removeAll();
//                            revalidate();
//                            repaint();
                            Object[][] mulData = new String[][] {{"0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0"},
                                    {"1", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9"},
                                    {"2", "0", "2", "4", "6", "8", "10", "12", "14", "16", "18"},
                                    {"3", "0", "3", "6", "9", "12", "15", "18", "21", "24", "27"},
                                    {"4", "0", "4", "8", "12", "16", "20", "24", "28", "32", "36"},
                                    {"5", "0", "5", "10", "15", "20", "25", "30", "35", "40", "45"},
                                    {"6", "0", "6", "12", "18", "24", "30", "36", "42", "48", "54"},
                                    {"7", "0", "7", "14", "21", "28", "35", "42", "49", "56", "63"},
                                    {"8", "0", "8", "16", "24", "32", "40", "48", "56", "64", "72"},
                                    {"9", "0", "9", "18", "27", "36", "45", "54", "63", "72", "81"}};

                            Object[] mulName = new String[] {"X", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
                            JTable mulTable = new JTable(mulData, mulName);
                            mulTable.setRowHeight(30);
                            //mulTable.setIntercellSpacing(new Dimension(10,10));
                            //mulTable.setFillsViewportHeight(true);
                            mulTable.setSelectionBackground(Color.BLUE);
                            mulTable.setSelectionForeground(Color.WHITE);
                            mulTable.setVisible(true);
                            JScrollPane scrollPane = new JScrollPane(mulTable);
                            //scrollPane.setVisible(true);
                            add(scrollPane);
                            //add(mulTable);
                        }
                        else {
                            Object[][] addData = new String[][] {{"0", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9"},
                                    {"1", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"},
                                    {"2", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11"},
                                    {"3", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"},
                                    {"4", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13"},
                                    {"5", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14"},
                                    {"6", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15"},
                                    {"7", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16"},
                                    {"8", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17"},
                                    {"9", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18"}};

                            Object[] addName = new String[] {"X", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
                            JTable addTable = new JTable(addData, addName);
                            addTable.setRowHeight(30);
                            //addTable.setIntercellSpacing(new Dimension(12,12));
                            addTable.setSelectionBackground(Color.BLUE);
                            addTable.setSelectionForeground(Color.WHITE);
                            addTable.setVisible(true);
                            JScrollPane scrollPane = new JScrollPane(addTable);
                            //scrollPane.setVisible(true);
                            add(scrollPane);
                            //add(addTable);
                        }

                    }
                });
            }
        });
        menu.add(menuItemNew);
        setVisible(true);
    }
    public static void main(String[] args) {
        new MDI();
    }
}