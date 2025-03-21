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
    private boolean ocupado;

    public Bloque(int id) {
        this.id = id;
        this.siguiente = null;
    }
    public Bloque(int id, boolean ocupado, int siguienteBloqueId){
     this.id = id;
        this.siguiente.id = siguienteBloqueId;
        this.ocupado= ocupado;
        
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
