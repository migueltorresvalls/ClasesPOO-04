package ui;

import javax.swing.JPanel;

import domain.GestionJuego;

import java.awt.*;
import java.util.HashMap;

public class Tablero extends JPanel{
    private GestionJuego gestion; 
    public static final int FPS = 60;

    private HashMap<Color, Boolean> colores = new HashMap<>();

    public Tablero(GestionJuego gestion){
        colores.put(Color.RED, true);
        colores.put(Color.BLUE, true);
        colores.put(Color.GREEN, true);

        this.gestion = gestion;

        setBackground(Color.BLACK);
    }

    public void showAllColores(){
        colores.forEach((k,v) -> colores.put(k, true));
    }

    public void setColorActivo(Color color){
        colores.forEach((k,v) -> {
            if (k.equals(color)){
                colores.put(k, true);
            } else {
                colores.put(k, false);
            }
        });
    }

    /** En milisegundos */
    private int calcularDelay(){
        return (int) (1000/(FPS*1.));
    }

    public void initMov(){
        new Thread(() -> {
            while (true){
                gestion.recalcularPosiciones();
                repaint();
                try {
                    Thread.sleep(calcularDelay());
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

        }).start();
    }
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);


        gestion.getPlanetas().forEach(planeta -> {
            if (colores.get(planeta.getColor())){
                planeta.paint(g);
            }
        });
    }
}
