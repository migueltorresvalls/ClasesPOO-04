package domain.planetas;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import ui.Juego;

public class Planeta implements Comparable{
    protected double alto = 100; 
    protected double ancho = 100; 

    protected int x; 
    protected int y; 

    protected Color color; 
    protected boolean relleno;

    protected Random random = new Random(); 

    public Planeta(int x, boolean relleno, int y, Color color){
        
        if (x == -1 || y == -1){
            this.x = random.nextInt(Juego.ANCHO);
            this.y = random.nextInt(Juego.ALTO);
        } else {
            this.x = x; 
            this.y = y; 
        }
        
        this.relleno = relleno; 
        this.color = color; 
    }

    public int getX(){
        return x; 
    }

    public int getY(){
        return y; 
    }

    public double getAlto() {
        return alto;
    }

    public int getAnimacion(){
        return 1; 
    }

    public double getAncho() {
        return ancho;
    }

    public boolean isRelleno() {
        return relleno;
    }

    public Random getRandom() {
        return random;
    }
    

    public void setX(int x){
        this.x = x; 
    }

    public void setY(int y){
        this.y = y; 
    }

    public Color getColor(){
        return color;
    }

    public void paint(Graphics g){
        g.setColor(color);
        if (relleno){
            g.fillOval(x, y, (int)ancho, (int)alto);
        } else {
            g.drawOval(x, y, (int)ancho, (int)alto);
        }
    }

    @Override
    public int compareTo(Object o){
        if (o instanceof Planeta planeta){
            return Integer.compare(x, planeta.getX());
        }
        return 0;
    }

    @Override
    public String toString(){
        return "x: " + x + " y: " + y 
        + " relleno: " + relleno + " color: " + color; 
    }
}
