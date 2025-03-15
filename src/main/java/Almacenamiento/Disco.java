/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Almacenamiento;

/**
 *
 * @author Andrea
 */
public class Disco {
    private Bloque[] bloques;
    private boolean[] ocupado;
    private int bloquesLibres;

    public Disco(int totalBloques) {
        this.bloques = new Bloque[totalBloques];
        this.ocupado = new boolean[totalBloques];
        this.bloquesLibres = totalBloques;

        for (int i = 0; i < totalBloques; i++) {
            bloques[i] = new Bloque(i);
            ocupado[i] = false;
        }
    }

    public int asignarBloques(int cantidad) {
        if (cantidad > bloquesLibres) {
            System.out.println("Error: No hay suficientes bloques disponibles.");
            return -1;
        }

        int primerBloque = -1, anterior = -1;

        for (int i = 0; i < bloques.length && cantidad > 0; i++) {
            if (!ocupado[i]) {
                if (primerBloque == -1) {
                    primerBloque = i;
                }
                if (anterior != -1) {
                    bloques[anterior].setSiguiente(bloques[i]);
                }
                anterior = i;
                ocupado[i] = true;
                cantidad--;
                bloquesLibres--;
            }
        }

        return primerBloque;
    }

    public void liberarBloques(int primerBloque) {
    if (primerBloque < 0 || primerBloque >= bloques.length || !ocupado[primerBloque]) {
        System.out.println("Error: Intentando liberar un bloque inválido o ya libre.");
        return;
    }

    Bloque actual = bloques[primerBloque];
    System.out.println("Liberando bloques a partir del bloque: " + primerBloque);

    while (actual != null && ocupado[actual.getId()]) {
        System.out.println("Liberando bloque con ID: " + actual.getId());
        ocupado[actual.getId()] = false;  // Marca el bloque como libre
        bloquesLibres++;
        actual = actual.getSiguiente();
    }
} public void setOcupado(boolean[] estadoOcupado) {
        if (estadoOcupado != null && estadoOcupado.length == ocupado.length) {
            System.arraycopy(estadoOcupado, 0, this.ocupado, 0, estadoOcupado.length);
        } else {
            System.out.println("Error: El tamaño del estado ocupado no coincide.");
        }
    }


    public boolean hayEspacio(int cantidad) {
        return cantidad <= bloquesLibres;
    }
    public int getTotalBloques() {
    return bloques.length;
}

    public boolean[] getOcupado() {
        return ocupado;
    }

    public int getBloquesLibres() {
        return bloquesLibres;
    }

    public void setBloquesLibres(int bloquesLibres) {
        this.bloquesLibres = bloquesLibres;
    }
// Método para marcar los bloques ocupados a partir del primer bloque de un archivo
 // En la clase Disco
public void marcarComoOcupado(int bloque) {
    if (bloque >= 0 && bloque < ocupado.length) {
        ocupado[bloque] = true;  // Marcamos el bloque como ocupado
    } else {
        System.out.println("Error: Índice de bloque fuera de rango.");
    }
}

}
