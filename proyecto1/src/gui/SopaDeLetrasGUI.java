package gui;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import estructuras.Grafo;
import estructuras.NodoGrafo;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class SopaDeLetrasGUI extends JFrame {

    private JButton cargarArchivoButton, reiniciarButton;
    private JLabel[][] letrasLabels;
    private Diccionario diccionario;
    private char[][] tablero;
    private List<JLabel> letrasSeleccionadas;
    private JLabel palabrasEncontradasLabel;
    private JTextArea palabrasEncontradasTextArea;
    private Grafo grafo; // Agregar referencia al Grafo
    private Busqueda busqueda; // Agregar referencia a la lógica de búsqueda

    public SopaDeLetrasGUI() {
        setTitle("Sopa de Letras");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(Color.WHITE);

        diccionario = new Diccionario();
        letrasSeleccionadas = new ArrayList<>();

        JPanel panelCentral = new JPanel(new GridLayout(4, 4, 5, 5));
        panelCentral.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        letrasLabels = new JLabel[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                letrasLabels[i][j] = new JLabel();
                letrasLabels[i][j].setOpaque(true);
                letrasLabels[i][j].setBackground(Color.WHITE);
                letrasLabels[i][j].setHorizontalAlignment(SwingConstants.CENTER);
                letrasLabels[i][j].setFont(new Font("Arial", Font.BOLD, 30));
                letrasLabels[i][j].setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
                letrasLabels[i][j].setPreferredSize(new Dimension(50, 50));
                letrasLabels[i][j].addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        JLabel label = (JLabel) e.getSource();
                        if (letrasSeleccionadas.size() < 4) {
                            label.setBackground(Color.GREEN);
                            letrasSeleccionadas.add(label);
                            verificarPalabra();
                        }
                    }
                });
                panelCentral.add(letrasLabels[i][j]);
            }
        }
        add(panelCentral, BorderLayout.CENTER);

        palabrasEncontradasLabel = new JLabel("Palabras Encontradas:");
        palabrasEncontradasLabel.setFont(new Font("Arial", Font.BOLD, 16));
        palabrasEncontradasLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        palabrasEncontradasTextArea = new JTextArea();
        palabrasEncontradasTextArea.setFont(new Font("Arial", Font.BOLD, 16));
        palabrasEncontradasTextArea.setEditable(false);
        palabrasEncontradasTextArea.setBackground(Color.LIGHT_GRAY);
        JScrollPane scrollPane = new JScrollPane(palabrasEncontradasTextArea);
        scrollPane.setPreferredSize(new Dimension(200, 100));

        JPanel panelPalabrasEncontradas = new JPanel(new BorderLayout());
        panelPalabrasEncontradas.add(palabrasEncontradasLabel, BorderLayout.NORTH);
        panelPalabrasEncontradas.add(scrollPane, BorderLayout.CENTER);

        add(panelPalabrasEncontradas, BorderLayout.SOUTH);

        JPanel botonesPanel = new JPanel(new FlowLayout());

        cargarArchivoButton = new JButton("Cargar Archivo");
        cargarArchivoButton.setFont(new Font("Arial", Font.BOLD, 18));
        cargarArchivoButton.setBackground(Color.BLUE);
        cargarArchivoButton.setForeground(Color.WHITE);
        cargarArchivoButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        cargarArchivoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cargarArchivo();
            }
        });
        botonesPanel.add(cargarArchivoButton);

        reiniciarButton = new JButton("Reiniciar");
        reiniciarButton.setFont(new Font("Arial", Font.BOLD, 18));
        reiniciarButton.setBackground(Color.RED);
        reiniciarButton.setForeground(Color.WHITE);
        reiniciarButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        reiniciarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reiniciarJuego();
            }
        });
        botonesPanel.add(reiniciarButton);

        add(botonesPanel, BorderLayout.NORTH);
    }

    private void cargarArchivo() {
        JFileChooser fileChooser = new JFileChooser();
        int returnValue = fileChooser.showOpenDialog(this);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            try {
                List<String> lines = Files.readAllLines(selectedFile.toPath());
                parsearArchivo(lines);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    private void parsearArchivo(List<String> lines) {
        boolean isDic = false;
        boolean isTab = false;
        diccionario = new Diccionario();
        tablero = new char[4][4];
        int row = 0;

        for (String line : lines) {
            if (line.equals("dic")) {
                isDic = true;
            } else if (line.equals("/dic")) {
                isDic = false;
            } else if (line.equals("tab")) {
                isTab = true;
            } else if (line.equals("/tab")) {
                isTab = false;
            } else if (isDic) {
                diccionario.agregarPalabra(line.trim());
            } else if (isTab) {
                String[] letras = line.split(",");
                for (int i = 0; i < letras.length; i++) {
                    tablero[row][i % 4] = letras[i].charAt(0);
                    if (i % 4 == 3) {
                        row++;
                    }
                }
            }
        }
        mostrarTablero();
        grafo = new Grafo(tablero); // Crear el Grafo
        busqueda = new Busqueda(grafo); // Crear la lógica de búsqueda
    }

    private void mostrarTablero() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                letrasLabels[i][j].setText(String.valueOf(tablero[i][j]));
                letrasLabels[i][j].setBackground(Color.WHITE);
            }
        }
    }

    private void verificarPalabra() {
        if (letrasSeleccionadas.size() == 3 || letrasSeleccionadas.size() == 4) {
            StringBuilder palabra = new StringBuilder();
            for (JLabel label : letrasSeleccionadas) {
                palabra.append(label.getText());
            }
            String palabraBuscada = palabra.toString();
            Point puntoInicio = getLabelPosition(letrasSeleccionadas.get(0));
            NodoGrafo nodoInicial = grafo.obtenerNodo(puntoInicio.x, puntoInicio.y);

            if (busqueda.buscarPalabraDFS(nodoInicial, palabraBuscada, 0)) {
                diccionario.agregarPalabraEncontrada(palabraBuscada);
                actualizarPalabrasEncontradas();
                titilarLetras(letrasSeleccionadas, Color.GREEN);
                verificarTodasPalabrasEncontradas();
            } else {
                titilarLetras(letrasSeleccionadas, Color.RED);
                notificarPalabraInvalida();
            }
        }
    }

    private void permitirLetraAdicional() {
        // Implementar lógica para permitir seleccionar letra adicional
    }

    private void notificarPalabraInvalida() {
        JOptionPane.showMessageDialog(this, "La palabra no es válida.", "Error", JOptionPane.ERROR_MESSAGE);
    }

    private void titilarLetras(List<JLabel> labels, Color color) {
        new Thread(() -> {
            try {
                for (int i = 0; i < 3; i++) {
                    for (JLabel label : labels) {
                        label.setBackground(color);
                    }
                    Thread.sleep(300);
                    for (JLabel label : labels) {
                        label.setBackground(Color.WHITE);
                    }
                    Thread.sleep(300);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                SwingUtilities.invokeLater(() -> {
                    for (JLabel label : labels) {
                        label.setBackground(Color.WHITE);
                    }
                    letrasSeleccionadas.clear();
                });
            }
        }).start();
    }

    private Point getLabelPosition(JLabel label) {
        for (int i = 0; i < letrasLabels.length; i++) {
            for (int j = 0; j < letrasLabels[i].length; j++) {
                if (letrasLabels[i][j] == label) {
                    return new Point(i, j);
                }
            }
        }
        return null;
    }

    private void actualizarPalabrasEncontradas() {
        palabrasEncontradasTextArea.setText("");
        for (String palabra : diccionario.getPalabrasEncontradas()) {
            palabrasEncontradasTextArea.append(palabra + "\n");
        }
    }

    private void verificarTodasPalabrasEncontradas() {
        // Verificar si todas las palabras del diccionario han sido encontradas
    }

    private void reiniciarJuego() {
        diccionario.limpiarPalabrasEncontradas();
        palabrasEncontradasTextArea.setText("");
        for (JLabel[] fila : letrasLabels) {
            for (JLabel label : fila) {
                label.setBackground(Color.WHITE);
            }
        }
        letrasSeleccionadas.clear();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new SopaDeLetrasGUI().setVisible(true);
        });
    }

    private static class Busqueda {

        public Busqueda(Grafo grafo) {
        }

        private boolean buscarPalabraDFS(NodoGrafo nodoInicial, String palabraBuscada, int i) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }
    }

    private static class Diccionario {

        public Diccionario() {
        }

        private void limpiarPalabrasEncontradas() {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        private Iterable<String> getPalabrasEncontradas() {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        private void agregarPalabraEncontrada(String palabraBuscada) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }

        private void agregarPalabra(String trim) {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }
    }
}







