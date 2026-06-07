/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Quevedo
 */

package Archivos;

import Estructuras.Grafo;
import java.io.BufferedReader;
import java.io.FileReader;

public class CargadorCVS {
    
    public static void cargarGrafo(String ruta, Grafo grafo ){
        try (BufferedReader br = new BufferedReader(new FileReader(ruta))){
            
            String linea;
            br.readLine(); // Leer la cabecera
            
            while ((linea= br.readLine())!= null){
                String datos[] = linea.split(",");
                
                String origen = datos[0].trim();
                String destino = datos[1].trim();
                double distancia = Double.parseDouble(datos[2].trim());
                String nt = datos[3].trim();
                double K = Double.parseDouble(datos[4].trim());
                
                grafo.agregarArista(origen, destino, distancia, nt, K);
            }
        } catch (Exception e){
            System.out.println("Error CVS: " + e.getMessage());
        }
    } 
}