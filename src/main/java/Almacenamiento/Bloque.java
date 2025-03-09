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
    private Bloque siguiente;

    public Bloque(int id) {
        this.id = id;
        this.siguiente = null;
    }

    public void setSiguiente(Bloque siguiente) {
        this.siguiente = siguiente;
    }

    public Bloque getSiguiente() {
        return siguiente;
    }

    public int getId() {
        return id;
    }
}
