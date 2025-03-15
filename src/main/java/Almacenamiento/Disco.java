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

        while (actual != null && ocupado[actual.getId()]) {
            ocupado[actual.getId()] = false;
            bloquesLibres++;
            actual = actual.getSiguiente();
        }
    }

    public boolean hayEspacio(int cantidad) {
        return cantidad <= bloquesLibres;
    }
}
