/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main;

/**
 *
 * @author Quevedo
 */
public class Main {
    
    public static void main(String[] args){ 
        
        //crear la tabla de neurotransmisores
        HashTable tabla = new HashTable(50);
        
        tabla.put("GLU", new Neurotransmisor("GLU","Glutamato","Excitatorio",2.5));
        tabla.put("DA", new Neurotransmisor("DA","Dopamina","Modulador", 1.5));
        tabla.put("GABA",new Neurotransmisor("GABA","Inhibitorio","Inhibitorio", 1.2));
        
        //Crear el Grafo 
        Grafo grafo = new Grafo(tabla);
        
        grafo.agregarArista("1","2",10,"GLU",1);
        grafo.agregarArista("1","3",8,"DA",1);
        grafo.agregarArista("2","4",5,"GLU",1);
        grafo.agregarArista("3","4",3,"GABA",1);
        
        //Hacer las pruebas
        System.out.println("BFS:");
        grafo.BFS("1");
        
        System.out.println("\nDFS:");
        grafo.DFS("1");
        
        System.out.println("\nDijkstra:");
        grafo.dijkstra("1");
    }
}
