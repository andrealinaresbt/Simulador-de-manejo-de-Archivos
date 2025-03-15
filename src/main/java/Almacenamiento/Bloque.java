/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Almacenamiento;

/**
 *
 * @author Andrea
 */
public class Bloque {
    private int id;
    private Bloque siguiente; // Para asignación encadenada

    public Bloque(int id) {
        this.id = id;
        this.siguiente = null;
    }

    public int getId() {
        return id;
    }

    public Bloque getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(Bloque siguiente) {
        this.siguiente = siguiente;
    }
}
