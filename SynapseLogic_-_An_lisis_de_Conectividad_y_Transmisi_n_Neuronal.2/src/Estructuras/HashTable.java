/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Estructuras;

/**
 *
 * @author Quevedo
 */

import Modelo.Neurotransmisor;

public class HashTable {
    
    private ListaPropia<Neurotransmisor>[] tabla;
    private int tamaño;
    
    @SuppressWarnings("unchecked")
    public HashTable(int tamaño){
        this.tamaño = tamaño;
        tabla = new ListaPropia[tamaño];
        for (int i = 0; i < tamaño; i++){
            tabla[i] = new ListaPropia<>();
        }
    }
    
    private int hash(String clave){
        return Math.abs(clave.hashCode() % tamaño);
    }
    
    public void put(String clave, Neurotransmisor valor){
        int index = hash(clave);
        ListaPropia<Neurotransmisor> lista = tabla[index];
        
        for (int i = 0; i < lista.getTamaño(); i++) {
            Neurotransmisor nt = lista.obtener(i);
            if (nt.getId().equals(clave)){
                lista.eliminar(nt); // Si existe, lo borra para actualizarlo
                break;
            }
        }
        lista.agregar(valor);
    }
    
    public Neurotransmisor get(String clave){
        int index = hash(clave);
        ListaPropia<Neurotransmisor> lista = tabla[index];
        
        for (int i = 0; i < lista.getTamaño(); i++){
            Neurotransmisor n = lista.obtener(i);
            if (n.getId().equals(clave)){
                return n;
            }
        }
        return null; 
    }
}