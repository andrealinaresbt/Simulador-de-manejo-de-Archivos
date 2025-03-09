/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EDD;

/**
 *
 * @author Andrea
 */



public class LinkedList<T> {
    private Node<T> cabeza;
    private int tamaño;

    public LinkedList() {
        this.cabeza = null;
        this.tamaño = 0;
    }
    
    public Node<T> getCabeza() {
    return cabeza;
}

    public void add(T dato) {
        Node<T> nuevo = new Node<>(dato);
        if (cabeza == null) {
            cabeza = nuevo;
        } else {
            Node<T> actual = cabeza;
            while (actual.siguiente != null) {
                actual = actual.siguiente;
            }
            actual.siguiente = nuevo;
        }
        tamaño++;
    }

    public boolean remove(T dato) {
    if (cabeza == null) return false;

    if (cabeza.getDato().equals(dato)) {  // Usamos getDato()
        cabeza = cabeza.siguiente;
        tamaño--;
        return true;
    }

    Node<T> actual = cabeza;
    while (actual.siguiente != null && !actual.siguiente.getDato().equals(dato)) { // Usamos getDato()
        actual = actual.siguiente;
    }

    if (actual.siguiente != null) {
        actual.siguiente = actual.siguiente.siguiente;
        tamaño--;
        return true;
    }
    return false;
}

    public boolean contiene(T dato) {
    Node<T> actual = cabeza;
    while (actual != null) {
        if (actual.getDato().equals(dato)) { // Usamos getDato()
            return true;
        }
        actual = actual.siguiente;
    }
    return false;
}


    public int tamaño() {
        return tamaño;
    }

    public void imprimir() {
    Node<T> actual = cabeza;
    while (actual != null) {
        System.out.print(actual.getDato() + " -> "); // Usamos getDato()
        actual = actual.siguiente;
    }
    System.out.println("null");
}

    public T obtener(int indice) {
    if (indice < 0 || indice >= tamaño) {
        throw new IndexOutOfBoundsException("Índice fuera de rango");
    }
    Node<T> actual = cabeza;
    for (int i = 0; i < indice; i++) {
        actual = actual.siguiente;
    }
    return actual.getDato();  // Usamos getDato()
}



    public void insertar(int indice, T dato) {
        if (indice < 0 || indice > tamaño) {
            throw new IndexOutOfBoundsException("Índice fuera de rango");
        }

        Node<T> nuevo = new Node<>(dato);
        if (indice == 0) {
            nuevo.siguiente = cabeza;
            cabeza = nuevo;
        } else {
            Node<T> actual = cabeza;
            for (int i = 0; i < indice - 1; i++) {
                actual = actual.siguiente;
            }
            nuevo.siguiente = actual.siguiente;
            actual.siguiente = nuevo;
        }
        tamaño++;
    }

    public void limpiar() {
        cabeza = null;
        tamaño = 0;
    }
}
