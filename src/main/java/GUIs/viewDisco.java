/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUIs;

/**
 *
 * @author Andrea
 */

    
import FileSystem.SistemaArchivos;
    import javax.swing.*;
import java.awt.*;

public class viewDisco extends JFrame {
    private JPanel panelBloques; // Panel que contiene los bloques
    private JButton[] bloques;  // Arreglo de botones que representarán los bloques
    private SistemaArchivos sistemaDeArchivos; // Referencia al sistema de archivos

public viewDisco(SistemaArchivos sistemaDeArchivos) {
    this.sistemaDeArchivos = sistemaDeArchivos;

    // Calcular el número de filas y columnas en función del número de bloques
    int totalBloques = sistemaDeArchivos.getDisco().getTotalBloques();
    int filas = (int) Math.ceil(totalBloques / 5.0); // 5 columnas por fila (ajustar según lo desees)
    int columnas = 5; // Por ejemplo, 5 columnas

    // Configurar la ventana
    setTitle("Simulación de Disco");
    setSize(400, 300);
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    
    // Configurar el layout dinámico
    panelBloques = new JPanel();
    panelBloques.setLayout(new GridLayout(filas, columnas)); // GridLayout con filas y columnas dinámicas
    
    // Crear botones para representar los bloques del disco
    bloques = new JButton[totalBloques]; // Usamos el total de bloques de la clase SistemaDeArchivos
    
    for (int i = 0; i < totalBloques; i++) {
        bloques[i] = new JButton("Bloque " + (i + 1));
        bloques[i].setBackground(Color.GREEN); // Establecemos un color inicial para los bloques libres
        panelBloques.add(bloques[i]);
    }

    // Agregar el panel de bloques a la ventana principal
    add(panelBloques);
    
    // Actualizar la visualización para reflejar el estado inicial de los bloques
    actualizarVista();

    // Hacer visible la ventana
    setVisible(true); // Esto es necesario para mostrar la ventana
}


// Método para actualizar la vista y cambiar los colores de los bloques
public void actualizarVista() {
    // Itera sobre todos los bloques del disco
    for (int i = 0; i < sistemaDeArchivos.getDisco().getTotalBloques(); i++) {
        if (sistemaDeArchivos.getDisco().getOcupado()[i]) { // Verificar si el bloque está ocupado
            bloques[i].setBackground(Color.RED); // Bloque ocupado (rojo)
        } else {
            bloques[i].setBackground(Color.GREEN); // Bloque libre (verde)
        }
    }
}}



    
