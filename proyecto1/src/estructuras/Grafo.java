/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package estructuras;

import java.util.ArrayList;
import java.util.List;

public class Grafo {
    private NodoGrafo[][] tablero;
    private int filas;
    private int columnas;

    public Grafo(char[][] tablero) {
        this.filas = tablero.length;
        this.columnas = tablero[0].length;
        this.tablero = new NodoGrafo[filas][columnas];

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                this.tablero[i][j] = new NodoGrafo(i, j, tablero[i][j]);
            }
        }
    }

    public List<NodoGrafo> obtenerAdyacentes(NodoGrafo nodo) {
        List<NodoGrafo> adyacentes = new ArrayList<>();
        int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};

        for (int i = 0; i < 8; i++) {
            int nuevaFila = nodo.fila + dx[i];
            int nuevaColumna = nodo.columna + dy[i];
            if (nuevaFila >= 0 && nuevaFila < filas && nuevaColumna >= 0 && nuevaColumna < columnas) {
                adyacentes.add(tablero[nuevaFila][nuevaColumna]);
            }
        }
        return adyacentes;
    }

    public NodoGrafo obtenerNodo(int fila, int columna) {
        return tablero[fila][columna];
    }
}
