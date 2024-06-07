/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.util.ArrayList;
import java.util.List;

public class Diccionario {
    private List<String> palabras;
    private List<String> palabrasEncontradas;

    public Diccionario() {
        palabras = new ArrayList<>();
        palabrasEncontradas = new ArrayList<>();
    }

    public void agregarPalabra(String palabra) {
        if (!palabras.contains(palabra)) {
            palabras.add(palabra);
        }
    }

    public boolean esPalabraValida(String palabra) {
        return palabras.contains(palabra);
    }

    public void agregarPalabraEncontrada(String palabra) {
        if (!palabrasEncontradas.contains(palabra)) {
            palabrasEncontradas.add(palabra);
        }
    }

    public List<String> getPalabrasEncontradas() {
        return palabrasEncontradas;
    }

    public void limpiarPalabrasEncontradas() {
        palabrasEncontradas.clear();
    }
}
