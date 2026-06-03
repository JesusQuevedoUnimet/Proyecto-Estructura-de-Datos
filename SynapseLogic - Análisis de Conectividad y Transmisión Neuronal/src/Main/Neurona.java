/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main;

/**
 *
 * @author Quevedo
 */

import java.util.ArrayList;

public class Neurona {
    
    private String id;
    private ArrayList<Sinapsis> conexiones; 
    
    public Neurona(String id){
        this.id = id;
        this.conexiones = new ArrayList<>();
    }
    
    public String getId(){
        return id;
    }
    
    public ArrayList<Sinapsis> getConexiones(){
        return conexiones;
    }
    
    public void agregarConexion(Sinapsis s){
        conexiones.add(s);
    }
}
