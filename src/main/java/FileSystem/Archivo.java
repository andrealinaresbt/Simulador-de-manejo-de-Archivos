/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FileSystem;

/**
 *
 * @author Andrea
 */
public class Archivo {
    private String nombre;
    private int tamano; // Tamaño en bloques
    private int primerBloque; // Dirección del primer bloque
    private String color; // Para diferenciar en la tabla

    public Archivo(String nombre, int tamano, int primerBloque, String color) {
        this.nombre = nombre;
        this.tamano = tamano;
        this.primerBloque = primerBloque;
        this.color = color;
    }
    
    //Getters and Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getTamano() {
        return tamano;
    }

    public void setTamano(int tamano) {
        this.tamano = tamano;
    }

    public int getPrimerBloque() {
        return primerBloque;
    }

    public void setPrimerBloque(int primerBloque) {
        this.primerBloque = primerBloque;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    

}
