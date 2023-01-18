/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.controller;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import mvc.model.GenerateMap;
import mvc.model.Model;
import mvc.model.shape.MyShape;
import mvc.view.MyFrame;
import mvc.view.MyPanel;

/**
 *
 * @author Netbeans
 */
public class Controller {

    Model model;
    MyFrame frame;
    MyPanel panel;
    MyShape shape;
    Point2D pl;
    GenerateMap map;

    public void draw(Graphics2D g2) {
        model.draw(g2);
    }
    
    public Controller() {
        shape = new MyShape(new Rectangle2D.Double(), Color.RED);
        model = new Model();
        model.setShape(shape);
        panel = new MyPanel(this);   
        model.addObserver(panel);
        frame = new MyFrame(panel);
        frame.setSize(600, 600);
        map = new GenerateMap(9, frame.getWidth(), frame.getHeight());
        map.generate(frame.getWidth(), frame.getHeight());
        map.addObserver(panel);
        frame.setController(this);
        frame.setLocation(600, 250);
        
        
    }

    public GenerateMap getMap() {
        return map;
    }
    
    public void mousePressed(Point p) {
        this.pl = p;
        model.actionPressDraw(pl);
    }
    public void generate(int widht,int height){
        map.generate(widht,height);
    }
    public void drawMap(Graphics g,int widthN,int heightN){
        map.drawMap(g, widthN, heightN);
    }
    

    public MyFrame getFrame() {
        return frame;
    }
    public void clearList(){
        model.clear();
    }
    public void correct(){
        map.correct(model.getListShape(), 9);
    }

}
