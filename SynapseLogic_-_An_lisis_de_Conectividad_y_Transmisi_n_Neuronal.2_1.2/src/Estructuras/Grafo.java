/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Estructuras;

/**
 *
 * @author Quevedo
 */

import Modelo.Neurona;
import Modelo.Sinapsis;
import Modelo.Neurotransmisor;

public class Grafo {
    private ListaPropia<Neurona> neuronas;
    private HashTable tablaNT;
    
    public Grafo(HashTable tablaNT){
        this.neuronas = new ListaPropia<>();
        this.tablaNT = tablaNT;
    }
    
    // Método auxiliar para buscar neuronas sin usar HashMap
    public Neurona buscarNeurona(String id) {
        for (int i = 0; i < neuronas.getTamaño(); i++) {
            Neurona n = neuronas.obtener(i);
            if (n.getId().equals(id)) {
                return n;
            }
        }
        return null;
    }
    
    public void agregarNeurona(String id){
        if (buscarNeurona(id) == null) {
            neuronas.agregar(new Neurona(id));
        }
    }
    
    public void eliminarNeurona(String id){
        Neurona nEliminar = buscarNeurona(id);
        if (nEliminar == null) return;
        
        neuronas.eliminar(nEliminar);
        
        for (int i = 0; i < neuronas.getTamaño(); i++){
            Neurona n = neuronas.obtener(i);
            ListaPropia<Sinapsis> conexiones = n.getConexiones();
            for (int j = 0; j < conexiones.getTamaño(); j++) {
                Sinapsis s = conexiones.obtener(j);
                if (s.getDestino().getId().equals(id)) {
                    conexiones.eliminar(s);
                    j--; // Ajustar índice al eliminar
                }
            }
        }
    }
    
    public void simularFatiga(){
        for (int i = 0; i < neuronas.getTamaño(); i++){
            Neurona n = neuronas.obtener(i);
            ListaPropia<Sinapsis> conexiones = n.getConexiones();
            for (int j = 0; j < conexiones.getTamaño(); j++){
                Sinapsis s = conexiones.obtener(j);
                s.setK(s.getK() * 1.2);
            }
        }
    }
    
    public void agregarArista(String origen, String destino, double distancia, String nt, double k){
        agregarNeurona(origen);
        agregarNeurona(destino);
        
        Neurona n1 = buscarNeurona(origen);
        Neurona n2 = buscarNeurona(destino);
        
        n1.agregarConexion(new Sinapsis(n2, distancia, nt, k));
    }
    
    // Auxiliar para reemplazar HashSet
    private boolean contiene(ListaPropia<String> lista, String id) {
        for (int i = 0; i < lista.getTamaño(); i++) {
            if (lista.obtener(i).equals(id)) return true;
        }
        return false;
    }
    
    // BFS Modificado para retornar Texto
    public String BFS(String inicio){
        Neurona nInicio = buscarNeurona(inicio);
        if (nInicio == null) return "Neurona inicial no encontrada.";

        StringBuilder sb = new StringBuilder();
        ListaPropia<String> visitados = new ListaPropia<>();
        ColaPropia<Neurona> cola = new ColaPropia<>();
        
        cola.encolar(nInicio);
        visitados.agregar(inicio);
        
        while(!cola.estaVacia()){
            Neurona actual = cola.desencolar();
            sb.append("Visitando neurona: ").append(actual.getId()).append("\n");
            
            ListaPropia<Sinapsis> conexiones = actual.getConexiones();
            for (int i = 0; i < conexiones.getTamaño(); i++){
                Sinapsis s = conexiones.obtener(i);
                String idDestino = s.getDestino().getId();
                
                if (!contiene(visitados, idDestino)){
                    visitados.agregar(idDestino);
                    cola.encolar(s.getDestino());
                }
            }
        }
        return sb.toString();
    }
    
    // DFS Modificado para retornar Texto
    public String DFS(String inicio){
        Neurona nInicio = buscarNeurona(inicio);
        if (nInicio == null) return "Neurona inicial no encontrada.";
        
        StringBuilder sb = new StringBuilder();
        ListaPropia<String> visitados = new ListaPropia<>();
        dfsRec(nInicio, visitados, sb);
        return sb.toString();
    }
    
    private void dfsRec(Neurona n, ListaPropia<String> visitados, StringBuilder sb){
        if (n == null || contiene(visitados, n.getId())) return;
        
        visitados.agregar(n.getId());
        sb.append("Visitando neurona: ").append(n.getId()).append("\n");
        
        ListaPropia<Sinapsis> conexiones = n.getConexiones();
        for (int i = 0; i < conexiones.getTamaño(); i++){
            Sinapsis s = conexiones.obtener(i);
            dfsRec(s.getDestino(), visitados, sb);
        }  
    }
    
    // DIJKSTRA Modificado para retornar Texto
    public String dijkstra(String origen){
        Neurona nOrigen = buscarNeurona(origen);
        if (nOrigen == null) return "Neurona de origen no encontrada.";

        StringBuilder sb = new StringBuilder();
        int nSize = neuronas.getTamaño();
        double[] dist = new double[nSize];
        boolean[] visitado = new boolean[nSize];
        
        for (int i = 0; i < nSize; i++) {
            dist[i] = Double.MAX_VALUE;
            visitado[i] = false;
            if (neuronas.obtener(i).getId().equals(origen)) {
                dist[i] = 0.0;
            }
        }
        
        for (int count = 0; count < nSize - 1; count++) {
            double min = Double.MAX_VALUE;
            int minIndex = -1;
            
            for (int i = 0; i < nSize; i++) {
                if (!visitado[i] && dist[i] <= min) {
                    min = dist[i];
                    minIndex = i;
                }
            }
            
            if (minIndex == -1) break; 
            
            visitado[minIndex] = true;
            Neurona actual = neuronas.obtener(minIndex);
            ListaPropia<Sinapsis> conexiones = actual.getConexiones();
            
            for (int j = 0; j < conexiones.getTamaño(); j++) {
                Sinapsis s = conexiones.obtener(j);
                Neurona vecino = s.getDestino();
                
                int vIndex = -1;
                for (int k = 0; k < nSize; k++) {
                    if (neuronas.obtener(k).getId().equals(vecino.getId())) {
                        vIndex = k;
                        break;
                    }
                }
                
                if (vIndex != -1 && !visitado[vIndex]) {
                    Neurotransmisor nt = tablaNT.get(s.getNeurotransmisor());
                    double velocidad = (nt != null) ? nt.getVelocidad() : 1.0;
                    double peso = s.getDistancia() / (velocidad * s.getK());
                    
                    if (dist[minIndex] + peso < dist[vIndex]) {
                        dist[vIndex] = dist[minIndex] + peso;
                    }
                }
            }
        }
        
        sb.append("Tiempos de transmisión mínimos desde la neurona ").append(origen).append(":\n");
        for (int i = 0; i < nSize; i++) {
            String destinoId = neuronas.obtener(i).getId();
            if (dist[i] == Double.MAX_VALUE) {
                sb.append(" -> Hacia ").append(destinoId).append(": INALCANZABLE (Conexión rota)\n");
            } else {
                sb.append(" -> Hacia ").append(destinoId).append(": ").append(String.format("%.2f", dist[i])).append(" ms\n");
            }
        }
        return sb.toString();
    }

    // Método extra para detectar las Zonas Aisladas que pide el PDF
    public String obtenerZonasAisladas() {
        StringBuilder sb = new StringBuilder();
        int nSize = neuronas.getTamaño();
        if (nSize == 0) return "El grafo está vacío.";
        
        boolean deAlguien = false;
        sb.append("--- Neuronas Aisladas (No reciben estímulos de ninguna otra) ---\n");
        
        for (int i = 0; i < nSize; i++) {
            Neurona actual = neuronas.obtener(i);
            boolean esDestino = false;
            
            // Buscar si alguien apunta a esta neurona
            for (int j = 0; j < nSize; j++) {
                if (i == j) continue;
                Neurona otra = neuronas.obtener(j);
                ListaPropia<Sinapsis> conex = otra.getConexiones();
                for (int k = 0; k < conex.getTamaño(); k++) {
                    if (conex.obtener(k).getDestino().getId().equals(actual.getId())) {
                        esDestino = true;
                        break;
                    }
                }
                if (esDestino) break;
            }
            
            if (!esDestino) {
                sb.append("• Neurona ID: ").append(actual.getId()).append(" está aislada.\n");
                deAlguien = true;
            }
        }
        
        if (!deAlguien) {
            sb.append("¡Ninguna! Todo el sistema nervioso está interconectado correctamente.\n");
        }
        return sb.toString();
    }

    // El método Getter que se había perdido:
    public ListaPropia<Neurona> getNeuronas() {
        return neuronas;
    }
}