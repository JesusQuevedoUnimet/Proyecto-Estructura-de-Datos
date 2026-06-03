/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main;

/**
 *
 * @author Quevedo
 */
public class Sinapsis {
    
    private Neurona destino;
    private double distancia;
    private String neurotransmisor;
    private double K;
    
    public Sinapsis(Neurona destino, double distancia, String neurotransmisor, double K){
        this.destino = destino;
        this.distancia = distancia; 
        this.neurotransmisor = neurotransmisor;
        this.K = K;
    }
    
    public Neurona getDestino(){
        return destino;
    }
    
    public double getDistancia(){
        return distancia;
    }
    
    public String getNeurotransmisor(){
        return neurotransmisor;
    }
    
    public double getK(){
        return K;
    }
    
    public void setK(double K){
        this.K = K;
    }
}
