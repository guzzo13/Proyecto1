/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import javax.swing.JPanel;
import java.awt.Graphics;

public class TableroPanel extends JPanel {

    private static final int FILAS = 4;
    private static final int COLUMNAS = 4;
    private static final int TAMANO_CELDA = 50;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (int fila = 0; fila < FILAS; fila++) {
            for (int columna = 0; columna < COLUMNAS; columna++) {
                int x = columna * TAMANO_CELDA;
                int y = fila * TAMANO_CELDA;

                g.drawRect(x, y, TAMANO_CELDA, TAMANO_CELDA);
                String letra = obtenerLetraParaCelda(fila, columna);
                int centroX = x + TAMANO_CELDA / 2;
                int centroY = y + TAMANO_CELDA / 2;
                g.drawString(letra, centroX, centroY);
            }
        }
    }

    private String obtenerLetraParaCelda(int fila, int columna) {
        return "A";
    }
}
