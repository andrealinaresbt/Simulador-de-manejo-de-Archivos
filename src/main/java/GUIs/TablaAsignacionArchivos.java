/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUIs;
import EDD.Node;
import FileSystem.Archivo;
import FileSystem.Directorio;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import FileSystem.SistemaArchivos;
import java.awt.*;


/**
 *
 * @author Andrea
 */


public class TablaAsignacionArchivos {
    private SistemaArchivos sistemaArchivos;
    private JTable tabla;

    public TablaAsignacionArchivos(SistemaArchivos sistema) {
        this.sistemaArchivos = sistema;
        this.tabla = new JTable();
        inicializarTabla();
    }

    public void inicializarTabla() {
        String[] columnas = {"Nombre de Archivo", "Cantidad de Bloques", "Primer Bloque", "Color"};
        DefaultTableModel modelo = new DefaultTableModel(columnas, 0);
        tabla.setModel(modelo);
        actualizarTabla();
    }

    public void actualizarTabla() {
        DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
        modelo.setRowCount(0);  // Limpiar la tabla antes de agregar nuevos datos

        // Recorrer los directorios y archivos del sistema
        recorrerDirectoriosYArchivos(sistemaArchivos.getRaiz(), modelo);
    }

    private void recorrerDirectoriosYArchivos(Directorio directorio, DefaultTableModel modelo) {
        // Mostrar los archivos del directorio
        for (Node<Archivo> archivoNode = directorio.getArchivos().getCabeza(); archivoNode != null; archivoNode = archivoNode.getSiguiente()) {
            Archivo archivo = archivoNode.getDato();
            Object[] fila = {
                archivo.getNombre(),
                archivo.getTamano(),
                archivo.getPrimerBloque(),
                archivo.getColor()
            };
            modelo.addRow(fila);
        }

        // Recursivamente, añadir los archivos de los subdirectorios
        for (Node<Directorio> subdirNode = directorio.getSubdirectorios().getCabeza(); subdirNode != null; subdirNode = subdirNode.getSiguiente()) {
            recorrerDirectoriosYArchivos(subdirNode.getDato(), modelo);
        }
    }

    public JTable getTabla() {
        return tabla;
    }
}


