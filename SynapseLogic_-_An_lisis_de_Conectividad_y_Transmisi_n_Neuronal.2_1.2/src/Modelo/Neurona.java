/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author Quevedo
 */

import Estructuras.ListaPropia;

public class Neurona {
    
    private String id;
    private ListaPropia<Sinapsis> conexiones; 
    
    public Neurona(String id){
        this.id = id;
        this.conexiones = new ListaPropia<>();
    }
    
    public String getId(){
        return id;
    }
    
    public ListaPropia<Sinapsis> getConexiones(){
        return conexiones;
    }
    
    public void agregarConexion(Sinapsis s){
        conexiones.agregar(s);
    }
}
