/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvc.model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Random;
import mvc.model.shape.MyShape;

/**
 *
 * @author maksi
 */
public class GenerateMap extends Observable{
    int sizeRect;
    int dimension;
    Random rand;
    int width; 
    int height;
    int seed;
    int[][] heights;

    public GenerateMap(int sizeRect, int width, int height) {
        this.sizeRect = sizeRect;
        this.width = width;
        this.height = height;
        rand = new Random();
        seed = rand.nextInt();
        dimension = 9;
    }
    public void setSeed(int seed){
        this.seed = seed;
    }
    
    
    public void generate(int widthN,int heightN){
        heights = DSquare.generateHeightMapWithBound(new Random(seed), 0.01, 255,widthN/sizeRect, heightN/sizeRect, dimension);       
    }
    public void drawMap(Graphics g,int widthN,int heightN){
        int var;
        for(int i = 0; i < widthN ; i+=sizeRect){
            for(int j = 0; j < heightN; j+=sizeRect){
                var = heights[i/sizeRect][j/sizeRect];
                if(0<=var && var<=160) g.setColor(new Color(0,var+20,var+var/2));
                if(160<var && var<=190)g.setColor(Color.yellow);;
                if(190<var && var<=220)g.setColor(Color.green);;
                if(220<var && var<=255)g.setColor(Color.gray);;

                g.fillRect(i, j, sizeRect, sizeRect);
//                setChanged();
//                notifyObservers();
            }
        }
    }
    public void smoothing(int widthN,int heightN){
        int var;
        int x1 ,x2,y1,y2,mid,t,d,l,r;
        Random rand = new Random();
        for(int x = 0; x < heights.length;x+=3){
            for(int y = 0; y < heights[0].length; y+=3){
                x1 = heights[x][y];
                x2 = heights[x+2][y];
                y1 = heights[x][y+2];
                y2 = heights[x+2][y+2];
                mid = (x1+x2+y1+y2)/4;//+(1-rand.nextInt()%3);
                heights[x+1][y+1] = mid;
                t =(x1+y1+mid)/3;//+(1-rand.nextInt()%3);
                d =(x2+y2+mid)/3;//+(1-rand.nextInt()%3);
                l =(x1+x2+mid)/3;//+(1-rand.nextInt()%3);
                r =(y1+y2+mid)/3;//+(1-rand.nextInt()%3);
                heights[x][y+1] = t;
                heights[x+2][y+1] = d;
                heights[x+1][y] = l;
                heights[x+1][y+2] = r;
            }
        }
    }
    public void correct(ArrayList<MyShape> listShape,int sizeRect){
        int x1,y1;
        for (MyShape x : listShape) {
           if(x.getShape().getMinX()>=0 && x.getShape().getMinY()>=0){
               x1 = (int) (x.getShape().getMinX())/sizeRect;
               y1 = (int) (x.getShape().getMinY())/sizeRect;
               heights[x1][y1] = 10;
           }
        }
    }
    public int[][] getHeights() {
        return heights;
    }
    
}
