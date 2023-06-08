package io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.TreeSet;

import domain.planetas.Planeta;
import domain.planetas.Planeta2;
import domain.planetas.Planeta3;

import java.awt.*;

public class IOFile {
    public static ArrayList<Planeta> readFichero() throws IOException, NumberFormatException{
        ArrayList<Planeta> planetas = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(new File("resources/juego.txt")));

        HashMap<String, Color> colores = new HashMap<>();
        colores.put("azul", Color.BLUE);
        colores.put("rojo", Color.RED);
        colores.put("verde", Color.GREEN);
        colores.put("amarillo", Color.yellow);

        // Inicializo variables
        int x = 0; 
        int y = 0; 
        int animacion = 0; 
        Color color = Color.RED;
      

        String linea = br.readLine();
        while (linea != null){
            String[] campos = linea.split("true");
            boolean relleno = true; 

            if (linea.contains("false")){
                campos = linea.split("false"); 
                relleno = false; 
            }

            x = Integer.parseInt(campos[0]);

            // Colores
            for (String c : colores.keySet()){
                if (campos[1].contains(c)){
                    color = colores.get(c);
                    String[] campos2 = campos[1].split(c);
                    y = Integer.parseInt(campos2[0]);
                    animacion = Integer.parseInt(campos2[1]);
                }
            }

            Planeta planeta; 
            if (animacion == 1){
                planeta = new Planeta(x, relleno, y, color);
            } else if (animacion == 2){
                planeta = new Planeta2(x, relleno, y, color);
            } else {
                planeta = new Planeta3(x, relleno, y, color);
            }

            planetas.add(planeta);
            
            linea = br.readLine();
        }
        br.close();
        return planetas; 
    }

    public static void writeFile(ArrayList<Planeta> planetas){
        Date date = new Date();
        long time = date.getTime();
        String nombreFichero = "resources/juego-" + time + ".csv";

        TreeSet<Planeta> planetasTree = new TreeSet<>();
        planetas.forEach(p -> planetasTree.add(p));

        // Iterator de TreeSet en orden inverso
        // Iterator<Planeta> iterator = planetasTree.descendingIterator();

        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(new File(nombreFichero)));

            for(Planeta p : planetasTree){
                String linea = p.getX() + ", " + p.isRelleno() + ", " + p.getY() + ", " + p.getColor() + ", " + p.getAnimacion() + "\n";
                bw.append(linea);
            }

            bw.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
