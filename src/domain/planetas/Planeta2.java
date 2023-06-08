package domain.planetas;

import java.awt.Color;

import domain.Funcion;
import domain.GestionJuego;
import ui.Tablero;

public class Planeta2 extends Planeta {

    protected double[][] puntos; 
    // 0 -> [x, y]
    // 1 -> [x, y]
    // 59 -> [x, y]
    protected int fotograma = 0;

    public Planeta2(int x0, boolean relleno, int y0, Color color) {
        super(x0, relleno, y0, color);

        // Planeta2(-1, true, -1, Colo.Red)
        // x aleatoria
        // puntos = Funcion.generarPuntos(x (-1), y(-1), Tablero.FPS);
        puntos = Funcion.generarPuntos(x, y, Tablero.FPS);
    }

    @Override
    public int getAnimacion(){
        return 2; 
    }

    public void animar(){
        if (fotograma < Tablero.FPS){
            // puntos[0] -> [x0, y0]
            double[] fila = puntos[fotograma]; // [xn, yn]
            setX((int)fila[0]);
            setY((int)fila[1]);
            
            fotograma ++;
        } else {
            GestionJuego.MOVIMIENTO = false; 
            fotograma = 0; 
            puntos = Funcion.generarPuntos(x, y, Tablero.FPS);
        }
    }
}
