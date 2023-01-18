/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.model.shape;

import mvc.model.*;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.geom.Point2D;
import java.awt.geom.RectangularShape;

/**
 *
 * @author Netbeans
 */
public class MyShape {
    RectangularShape shape;
    Color color;

    public RectangularShape getShape() {
        return shape;
    }

    public void setShape(RectangularShape shape) {
        this.shape = shape;
    }


    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
    public void draw(Graphics2D g){
        Paint p = g.getPaint();
        g.setColor(color);
        g.fill(shape);
        g.setPaint(p);
    }

    public MyShape(RectangularShape shape,  Color color) {
        this.shape = shape;
        this.color = color;
       
    }

    public MyShape() {
    }

   

    
    
    @Override
    public MyShape clone(){
        MyShape clone = new MyShape((RectangularShape)shape.clone(),color);
        return clone;
    }

    
    
}
