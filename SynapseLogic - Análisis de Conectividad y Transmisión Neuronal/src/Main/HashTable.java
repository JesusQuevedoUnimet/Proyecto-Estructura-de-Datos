/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main;

/**
 *
 * @author Quevedo
 */

import java.util.LinkedList;

public class HashTable {
    
    private LinkedList<Neurotransmisor>[] tabla;
    private int tamaño;
    
    @SuppressWarnings("unchecked")
    public HashTable(int tamaño){
        this.tamaño = tamaño;
        tabla = new LinkedList[tamaño];
        for (int i = 0; i < tamaño; i++){
            tabla[i] = new LinkedList<>();
        }
    }
    
    private int hash(String clave){
        return Math.abs(clave.hashCode()% tamaño);
    }
    
    public void put(String clave, Neurotransmisor valor){
        int index = hash(clave);
        for (Neurotransmisor nt : tabla[index]){
            if (nt.getId().equals(clave)){
                tabla[index].remove(nt);
                break;
            }
        }
        tabla[index].add(valor);
    }
    
    public Neurotransmisor get(String clave){
        int index = hash(clave);
        
        for (Neurotransmisor n: tabla[index]){
            if ( n.getId().equals(clave)){
                return n;
            }
        }
        return null; 
    }
}
