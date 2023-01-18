/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.view;

import mvc.model.DSquare;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;
import javax.swing.JPanel;
import mvc.controller.Controller;
import mvc.model.GenerateMap;

/**
 *
 * @author Netbeans
 */
public class MyPanel extends JPanel implements Observer{

    Controller c;

    public MyPanel(Controller c) {
        this.c = c;
        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                c.mousePressed(e.getPoint());
            }
        });
        addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                c.mousePressed(e.getPoint());
                
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.blue);
        Graphics2D g2 = (Graphics2D) g;
        if(c.getMap().getHeights()!= null){
            c.drawMap(g,c.getFrame().getWidth(),c.getFrame().getHeight());
        }
        c.draw(g2);
    }

    public void setC(Controller c) {
        this.c = c;
    }

    @Override
    public void update(Observable o, Object arg) {
        repaint();
    }
    
    public int stateH(int var , int countColor){
        int count = 1;
        for(int i = 0; i<256;i++){
            if(var == i) return count;
            if(i == count * (256/countColor))count++;
        }
        return 0;
    }
}
