/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Estructuras;

/**
 *
 * @author Quevedo
 */
public class ColaPropia<T> {
    
    private Nodo<T> frente;
    private Nodo<T> fin;
    private int tamaño;

    private static class Nodo<T> {
        T data;
        Nodo<T> siguiente;

        Nodo(T data) {
            this.data = data;
            this.siguiente = null;
        }
    }

    public ColaPropia() {
        this.frente = null;
        this.fin = null;
        this.tamaño = 0;
    }

    // Encolar (Insertar al final)
    public void encolar(T elemento) {
        Nodo<T> nuevoNodo = new Nodo<>(elemento);
        if (estaVacia()) {
            frente = nuevoNodo;
            fin = nuevoNodo;
        } else {
            fin.siguiente = nuevoNodo;
            fin = nuevoNodo;
        }
        tamaño++;
    }

    // Desencolar (Sacar del frente)
    public T desencolar() {
        if (estaVacia()) {
            return null;
        }
        T data = frente.data;
        frente = frente.siguiente;
        if (frente == null) {
            fin = null;
        }
        tamaño--;
        return data;
    }

    public boolean estaVacia() {
        return frente == null;
    }

    public int getTamaño() {
        return tamaño;
    }
}
