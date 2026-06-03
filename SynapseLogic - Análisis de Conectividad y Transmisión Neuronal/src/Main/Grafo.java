/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main;

/**
 *
 * @author Quevedo
 */
import java.util.*;

public class Grafo {
    private HashMap<String, Neurona> neuronas;
    private HashTable tablaNT;
    
    public Grafo(HashTable tablaNT){
        this.neuronas = new HashMap<>();
        this.tablaNT = tablaNT;
    }
    public void agregarNeurona(String id){
        neuronas.putIfAbsent(id, new Neurona(id));
    }
    
    public void eliminarNeurona(String id){
        if (!neuronas.containsKey(id))return;
        neuronas.remove(id);
        for (Neurona n : neuronas.values()){
            n.getConexiones().removeIf(sinapsis -> sinapsis.getDestino().getId().equals(id));
        }
    }
    
    public void simularFatiga(){
        for (Neurona n : neuronas.values()){
            for (Sinapsis s : n.getConexiones()){
                s.setK(s.getK() * 1.2);
            }
        }
    }
    
    public void agregarArista(String origen, String destino, double distancia, String nt, double k){
        agregarNeurona(origen);
        agregarNeurona(destino);
        
        Neurona n1 = neuronas.get(origen);
        Neurona n2 = neuronas.get(destino);
        
        n1.agregarConexion(new Sinapsis(n2, distancia, nt, k));
    }
    
    // BFS
    public void BFS(String inicio){
        Set<String> visitados  = new HashSet<>();
        Queue<Neurona> cola = new LinkedList<>();
        
        cola.add(neuronas.get(inicio));
        visitados.add(inicio);
        
        while(!cola.isEmpty()){
            Neurona actual = cola.poll();
            System.out.println("Visitando: " + actual.getId());
            
            for (Sinapsis s: actual.getConexiones()){
                String idDestino = s.getDestino().getId();
                
                if (!visitados.contains(idDestino)){
                    visitados.add(idDestino);
                    cola.add(s.getDestino());
                }
            }
        }
    }
    
    //DFS
    public void DFS(String inicio){
        Set<String> visitados = new HashSet<>();
        dfsRec(neuronas.get(inicio), visitados);
    }
    
    private void dfsRec(Neurona n, Set<String> visitados){
        if (n == null || visitados.contains(n.getId()))return;
        
        visitados.add(n.getId());
        System.out.println("Visitando: " + n.getId());
        
        for (Sinapsis s: n.getConexiones()){
            dfsRec(s.getDestino(), visitados);
        }  
    }
    
    //DIJKSTRA
    public void dijkstra(String origen){
        
        Map<String, Double> dist = new HashMap<>();
        PriorityQueue<String> pq = new
            PriorityQueue<>(Comparator.comparingDouble(dist::get));
        
        for(String id: neuronas.keySet()){
            dist.put(id, Double.MAX_VALUE);
        }
        dist.put(origen, 0.0);
        pq.add(origen);
        
        while (!pq.isEmpty()){
            
            String actual = pq.poll();
            Neurona n = neuronas.get(actual);
            
            for (Sinapsis s: n.getConexiones()){
                String vecino = s.getDestino().getId();
                
                Neurotransmisor nt = tablaNT.get(s.getNeurotransmisor());
                
                double velocidad = (nt != null) ? nt.getVelocidad(): 1.0;
                
                double peso = s.getDistancia()/(velocidad*s.getK());
                
                double nuevaDist = dist.get(actual) + peso;
                
                if (nuevaDist < dist.get(vecino)){
                    dist.put(vecino, nuevaDist);
                    pq.add(vecino);
                }
            }
        }
        
        // Mostrar el resultado
        System.out.println("Distancias mínimas desde " + origen);
        for (String id: dist.keySet()){
            System.out.println(id + "->" + dist.get(id));
        }
    }
}
