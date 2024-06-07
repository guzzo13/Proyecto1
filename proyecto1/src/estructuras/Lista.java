/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package estructuras;

/**
 *
 * @author guzzo
 */
public class Lista<T> {
    private Nodo cabeza;
    private Nodo cola;
    private int tamaño;

    public Lista() {
        this.cabeza = null;
        this.cola = null;
        this.tamaño = 0;
    }

    public Nodo getCabeza() {
        return cabeza;
    }

    public void setCabeza(Nodo cabeza) {
        this.cabeza = cabeza;
    }

    public Nodo getCola() {
        return cola;
    }

    public void setCola(Nodo cola) {
        this.cola = cola;
    }

    public int getTamaño() {
        return tamaño;
    }

    public void setTamaño(int tamaño) {
        this.tamaño = tamaño;
    }
    
    
    
    public boolean esVacio(){
        return this.cabeza == null;
    }
    
    public void append(T dato){
        if(esVacio()){
            this.cabeza = new Nodo(dato);
        }
        else{
            Nodo nodo = new Nodo(dato);
            this.cola.setSiguiente(nodo);
            this.cola = nodo;
            this.tamaño++;
        }
    }
    
    public void preappend(T dato){
        if(esVacio()){
            this.cabeza = new Nodo(dato);
        }
        else{
            Nodo nodo = new Nodo(dato);
            nodo.setSiguiente(this.cabeza);
            this.cabeza = nodo;
            this.tamaño++;
        }
    }
    
    public void insert(T dato, int indice){
        if(esVacio()){
            append(dato);
        }
        else{
            Nodo nodo = new Nodo(dato);
            Nodo puntero = this.cabeza;
            for (int i = 0; i < indice-1; i++) {
                puntero = (Nodo) puntero.getSiguiente();                
            }
            nodo.setSiguiente(puntero.getSiguiente());
            puntero.setSiguiente(nodo);
            this.tamaño++;
        }
    }
    
    public void imprimir(){
        if(esVacio()){
            System.out.println("Lista vacia");
        }
        else{
            Nodo puntero = this.cabeza;
            while(puntero != null){
                System.out.println("");
            }
        }
    }
    
    
}
