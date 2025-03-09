/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FileSystem;

import Almacenamiento.Disco;
import EDD.LinkedList;
import EDD.Node;

/**
 *
 * @author Andrea
 */
public class SistemaArchivos {

    private Directorio raiz;
    private Disco disco;

    public SistemaArchivos(int tamanoDisco) {
        this.raiz = new Directorio("Raíz");
        this.disco = new Disco(tamanoDisco);
    }

    public void crearArchivo(String nombre, int tamano, String color) {
        if (!disco.hayEspacio(tamano)) {
            System.out.println("No hay espacio suficiente en el disco.");
            return;
        }

        int primerBloque = disco.asignarBloques(tamano);
        Archivo nuevoArchivo = new Archivo(nombre, tamano, primerBloque, color);
        raiz.agregarArchivo(nuevoArchivo);
    }

    public void eliminarArchivo(String nombre) {
        LinkedList<Archivo> archivos = raiz.getArchivos();
        Node<Archivo> actual = archivos.getCabeza(); // Obtener el primer nodo de la lista
        Node<Archivo> previo = null;

        while (actual != null) {
            if (actual.getDato().getNombre().equals(nombre)) { // Usamos getDato()
                disco.liberarBloques(actual.getDato().getPrimerBloque());
                archivos.remove(actual.getDato()); // Usamos el método remove() de tu LinkedList
                break;
            }
            previo = actual;
            actual = actual.getSiguiente();
        }
    }

    public void mostrarArchivos() {
        Node<Archivo> actual = raiz.getArchivos().getCabeza();
        while (actual != null) {
            Archivo archivo = actual.getDato(); // Usamos getDato()
            System.out.println("Archivo: " + archivo.getNombre() + ", Bloques: " + archivo.getTamano());
            actual = actual.getSiguiente();
        }
    }

}
