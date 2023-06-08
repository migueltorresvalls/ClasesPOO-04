package domain;

import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;

import domain.planetas.Planeta;
import domain.planetas.Planeta2;
import domain.planetas.Planeta3;
import io.IOFile;
import ui.Juego;
import ui.Tablero;


public class GestionJuego {
    // Lo que leo del fichero
    ArrayList<Planeta> planetasFichero = new ArrayList<>();
    // Lo que pinto
    ArrayList<Planeta> planetas = new ArrayList<>();
    private Juego juego; 
    private Tablero tablero; 

    public static boolean MOVIMIENTO = false; 


    public GestionJuego(Juego juego){
        this.juego = juego; 
        
        try {
            planetasFichero = IOFile.readFichero();
        } catch (NumberFormatException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void initJuego(){
        tablero = new Tablero(this);

        juego.add(tablero);
        tablero.initMov();
        addEventos();
    }

    public void addEventos(){        
        juego.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e){
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_SPACE: 
                        // Agregamos un planeta
                        if (planetas.size() < planetasFichero.size()){
                            Planeta planeta = planetasFichero.get(planetas.size());
                            planetas.add(planeta);                            
                        }
                        break;
                    case KeyEvent.VK_ENTER:
                        // Empieza animacion
                        MOVIMIENTO = true; 
                        break; 
                    case KeyEvent.VK_S:
                        IOFile.writeFile(planetas);
                        break;
                    case KeyEvent.VK_R:
                        tablero.setColorActivo(Color.RED);
                        break; 
                    case KeyEvent.VK_G:
                        tablero.setColorActivo(Color.GREEN);
                        break; 
                    case KeyEvent.VK_B:
                        tablero.setColorActivo(Color.BLUE);
                        break; 
                    case KeyEvent.VK_A:
                        tablero.showAllColores();
                    default:
                        break;
                }
            }
        });
    }

    public void recalcularPosiciones(){
        if (MOVIMIENTO){
            planetas.forEach(planeta -> {
                if (planeta instanceof Planeta2 planeta2){
                    // Movimiento cuadratico
                    planeta2.animar();
                } 
                if (planeta instanceof Planeta3 planeta3){
                    // Movimiento cuadratico + zoom 
                    planeta3.zoom();
                }
            });
        }
    }

    public ArrayList<Planeta> getPlanetas(){
        return planetas; 
    }
}
