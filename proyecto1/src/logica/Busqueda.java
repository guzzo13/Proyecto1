/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import estructuras.Grafo;
import estructuras.NodoGrafo;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.LinkedList;

public class Busqueda {
    private final Grafo grafo;
    private final List<NodoGrafo> visitados;

    public Busqueda(Grafo grafo) {
        this.grafo = grafo;
        this.visitados = new ArrayList<>();
    }

    public boolean buscarPalabraDFS(NodoGrafo nodo, String palabra, int index) {
        if (index == palabra.length()) {
            return true;
        }

        if (nodo.letra != palabra.charAt(index) || visitados.contains(nodo)) {
            return false;
        }

        visitados.add(nodo);

        for (NodoGrafo adyacente : grafo.obtenerAdyacentes(nodo)) {
            if (buscarPalabraDFS(adyacente, palabra, index + 1)) {
                return true;
            }
        }

        visitados.remove(nodo);
        return false;
    }

    public boolean buscarPalabraBFS(NodoGrafo nodo, String palabra) {
        Queue<NodoGrafo> cola = new LinkedList<>();
        cola.add(nodo);
        int index = 0;

        while (!cola.isEmpty() && index < palabra.length()) {
            NodoGrafo actual = cola.poll();

            if (actual.letra == palabra.charAt(index)) {
                if (index == palabra.length() - 1) {
                    return true;
                }
                index++;

                for (NodoGrafo adyacente : grafo.obtenerAdyacentes(actual)) {
                    if (!visitados.contains(adyacente)) {
                        visitados.add(adyacente);
                        cola.add(adyacente);
                    }
                }
            }
        }
        return false;
    }
}
