/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main;

/**
 *
 * @author Quevedo
 */
public class Neurotransmisor {
    
    private String id;
    private String nombre;
    private String efecto;
    private double velocidad;
    
    public Neurotransmisor(String id, String nombre, String efecto, double velocidad){
        this.id = id;
        this.nombre = nombre;
        this.efecto = efecto;
        this.velocidad = velocidad;
    }
    
    public String getId(){
        return id;
    }
    
    public double getVelocidad(){
        return velocidad;
    }
}
