/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Proyecto1;

import gui.SopaDeLetrasGUI;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JFrame;

/**
 *
 * @author guzzo
 */
public class Proyecto1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SopaDeLetrasGUI gui = new SopaDeLetrasGUI();
        
        // Configuracion 
        gui.setVisible(true);
        gui.setLocationRelativeTo(null);
        gui.setTitle("Sopa de Letras");
        gui.setSize(600, 600);
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gui.setLayout(new BorderLayout());
        gui.setBackground(Color.WHITE);
    }
    
}
