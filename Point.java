/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author zyte4
 */
public class Point {
    private int x;
    private int y;
    
    public Point(int _x, int _y){
        x = _x;
        y = _y;
    }
    
    public Point(){
        
    }
    
    public void setX(int _x){
        x = _x;
    }
    
    public void setY(int _y){
        y = _y;
    }
    
    public int getX(){
        return x;
    }
    
    public int getY() {
        return y;
    }
}
