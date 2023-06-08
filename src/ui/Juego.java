package ui;

import javax.swing.JFrame;
import domain.GestionJuego;;

public class Juego extends JFrame {
    public static int ALTO = 1000; 
    public static int ANCHO = 1000; 

    public Juego(){
        super("Planetas"); 

        GestionJuego gestion = new GestionJuego(this);
        
        gestion.initJuego();

        setVisible(true);
        setResizable(false);
        //setLocationRelativeTo(null);
        setSize(ANCHO, ALTO);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args){
        new Juego();
    } 
}
