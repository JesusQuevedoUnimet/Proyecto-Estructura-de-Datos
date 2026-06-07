/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Estructuras;

/**
 *
 * @author Quevedo
 */
public class ListaPropia<T> {
    
    private Nodo<T> cabeza;
    private int tamaño;

    private static class Nodo<T> {
        T data;
        Nodo<T> siguiente;

        Nodo(T data) {
            this.data = data;
            this.siguiente = null;
        }
    }

    public ListaPropia() {
        this.cabeza = null;
        this.tamaño = 0;
    }

    // Agregar al final de la lista
    public void agregar(T elemento) {
        Nodo<T> nuevoNodo = new Nodo<>(elemento);
        if (cabeza == null) {
            cabeza = nuevoNodo;
        } else {
            Nodo<T> actual = cabeza;
            while (actual.siguiente != null) {
                actual = actual.siguiente;
            }
            actual.siguiente = nuevoNodo;
        }
        tamaño++;
    }

    // Obtener un elemento por su índice
    public T obtener(int indice) {
        if (indice < 0 || indice >= tamaño) {
            throw new IndexOutOfBoundsException("Índice fuera de rango");
        }
        Nodo<T> actual = cabeza;
        for (int i = 0; i < indice; i++) {
            actual = actual.siguiente;
        }
        return actual.data;
    }

    // Eliminar un elemento específico
    public void eliminar(T elemento) {
        if (cabeza == null) return;

        if (cabeza.data.equals(elemento)) {
            cabeza = cabeza.siguiente;
            tamaño--;
            return;
        }

        Nodo<T> actual = cabeza;
        while (actual.siguiente != null) {
            if (actual.siguiente.data.equals(elemento)) {
                actual.siguiente = actual.siguiente.siguiente;
                tamaño--;
                return;
            }
            actual = actual.siguiente;
        }
    }

    public int getTamaño() {
        return tamaño;
    }

    public boolean estaVacia() {
        return tamaño == 0;
    }
}
