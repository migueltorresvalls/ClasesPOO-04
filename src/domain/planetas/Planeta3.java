package domain.planetas;

import java.awt.Color;

import ui.Tablero;

public class Planeta3 extends Planeta2{

    private double alturaMax = 1.5*alto;
    private double anchoMax = 1.5*ancho;

    private double deltaAlto = (alturaMax-alto)/Tablero.FPS;
    private double deltaAncho = (anchoMax-ancho)/Tablero.FPS;

    public Planeta3(int x, boolean relleno, int y, Color color) {
        super(x, relleno, y, color);
    }

    @Override
    public int getAnimacion(){
        return 3; 
    }

    public void zoom(){
        if (alto < alturaMax && ancho < anchoMax){
            alto += deltaAlto;
            ancho += deltaAncho;
        }
    }    
}
