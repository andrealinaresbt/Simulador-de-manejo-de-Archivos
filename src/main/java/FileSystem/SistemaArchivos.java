/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FileSystem;

import Almacenamiento.Disco;
import EDD.LinkedList;
import EDD.Node;
import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

/**
 *
 * @author Andrea
 */
public class SistemaArchivos {

    private Directorio raiz;
    private Disco disco;

    public SistemaArchivos(int tamanoDisco) {
        this.raiz = new Directorio("Raiz",null);
        this.disco = new Disco(tamanoDisco);
    }

    public void crearArchivo(String nombre, int tamano, String color, String rutaDirectorio) {
        if (!disco.hayEspacio(tamano)) {
            System.out.println("No hay espacio suficiente en el disco.");
            return;
        }

        Directorio dirSeleccionado = buscarDirectorioPorRuta(raiz, rutaDirectorio);
        if (dirSeleccionado == null) {
            System.out.println("Directorio no encontrado.");
            return;
        }

        int primerBloque = disco.asignarBloques(tamano);
        Archivo nuevoArchivo = new Archivo(nombre, tamano, primerBloque, color);
        dirSeleccionado.agregarArchivo(nuevoArchivo);
    }

    public void mostrarArchivos() {
        Node<Archivo> actual = raiz.getArchivos().getCabeza();
        while (actual != null) {
            Archivo archivo = actual.getDato(); // Usamos getDato()
            System.out.println("Archivo: " + archivo.getNombre() + ", Bloques: " + archivo.getTamano());
            actual = actual.getSiguiente();
        }
    }
     
    public Directorio buscarDirectorioPorRuta(Directorio actual, String rutaBuscada) {
        if (actual.obtenerRuta().equals(rutaBuscada)) {
            return actual;
        }

        for (Node<Directorio> dir = actual.getSubdirectorios().getCabeza(); dir != null; dir = dir.getSiguiente()) {
            Directorio encontrado = buscarDirectorioPorRuta(dir.getDato(), rutaBuscada);
            if (encontrado != null) {
                return encontrado;
            }
        }
        return null;
    }
    
    public JTree construirJTree() {
        if (raiz == null) {
            return new JTree(new DefaultMutableTreeNode("Sistema vacío"));
        }

        DefaultMutableTreeNode raizNode = new DefaultMutableTreeNode(raiz.getNombre());
        construirNodos(raiz, raizNode);
        return new JTree(raizNode);
    }

    private void construirNodos(Directorio dir, DefaultMutableTreeNode nodoPadre) {
        if (dir == null) return;

        // Añadir archivos del directorio
        Node<Archivo> archivoActual = dir.getArchivos().getCabeza();
        while (archivoActual != null) {
            Archivo archivo = archivoActual.getDato();
            DefaultMutableTreeNode archivoNode = new DefaultMutableTreeNode(
                archivo.getNombre() + " (" + archivo.getTamano() + " bloques)"
            );
            nodoPadre.add(archivoNode);
            archivoActual = archivoActual.getSiguiente();
        }

        // Añadir subdirectorios de forma recursiva
        Node<Directorio> subdirActual = dir.getSubdirectorios().getCabeza();
        while (subdirActual != null) {
            Directorio subdirectorio = subdirActual.getDato();
            DefaultMutableTreeNode subdirNode = new DefaultMutableTreeNode(subdirectorio.getNombre());
            nodoPadre.add(subdirNode);
            construirNodos(subdirectorio, subdirNode); // Llamada recursiva
            subdirActual = subdirActual.getSiguiente();
        }
    }
    
    public void mostrarJTree() {
        JFrame frame = new JFrame("Sistema de Archivos");
        JTree tree = construirJTree();

        // Mostrar el árbol en un JScrollPane
        JScrollPane scrollPane = new JScrollPane(tree);
        frame.add(scrollPane);
        
        frame.setSize(400, 500);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }
    
    public Archivo buscarArchivoPorRuta(Directorio actual, String rutaBuscada) {
    // Verificar si el directorio actual contiene el archivo
        for (Node<Archivo> archivoNode = actual.getArchivos().getCabeza(); archivoNode != null; archivoNode = archivoNode.getSiguiente()) {
            Archivo archivo = archivoNode.getDato();
            if ((actual.obtenerRuta() + "/" + archivo.getNombre()).equals(rutaBuscada)) {
                return archivo;
            }
        }

        // Buscar en los subdirectorios de manera recursiva
        for (Node<Directorio> dirNode = actual.getSubdirectorios().getCabeza(); dirNode != null; dirNode = dirNode.getSiguiente()) {
            Archivo encontrado = buscarArchivoPorRuta(dirNode.getDato(), rutaBuscada);
            if (encontrado != null) {
                return encontrado;
            }
        }

        return null; // No se encontró el archivo
    }
    
    public Directorio getRaiz() {
        return raiz;
    }

    public void setRaiz(Directorio raiz) {
        this.raiz = raiz;
    }

    public Disco getDisco() {
        return disco;
    }

    public void setDisco(Disco disco) {
        this.disco = disco;
    }
    
    
}
