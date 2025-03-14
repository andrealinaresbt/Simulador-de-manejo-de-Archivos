package FileSystem;

import EDD.LinkedList; // Asegúrate de que esta clase esté correctamente implementada

/**
 *
 * @author Andrea
 */
public class Directorio {
    
    private String nombre;
    private LinkedList<Archivo> archivos;  // Lista de archivos dentro del directorio
    private LinkedList<Directorio> subdirectorios;  // Lista de subdirectorios dentro del directorio
    private Directorio padre;

    public Directorio(String nombre, Directorio padre) {
        this.nombre = nombre;
        this.archivos = new LinkedList<>();  // Inicializa la lista de archivos
        this.subdirectorios = new LinkedList<>();  // Inicializa la lista de subdirectorios
        this.padre = padre;
    }

    // Método para agregar un archivo al directorio
    public void agregarArchivo(Archivo archivo) {
        archivos.add(archivo);  // Agrega el archivo a la lista
    }

    // Método para agregar un subdirectorio al directorio
    public void agregarDirectorio(Directorio directorio) {
        subdirectorios.add(directorio);  // Agrega el subdirectorio a la lista
    }

    // Método para eliminar un archivo del directorio
    public boolean eliminarArchivo(Archivo archivo) {
        // Llama al método remove() de LinkedList para eliminar el archivo
        return archivos.remove(archivo);  // Devuelve true si se eliminó el archivo
    }

    // Método para eliminar un subdirectorio del directorio
    public boolean eliminarDirectorio(Directorio directorio) {
        // Llama al método remove() de LinkedList para eliminar el subdirectorio
        return subdirectorios.remove(directorio);  // Devuelve true si se eliminó el subdirectorio
    }
    
    public String obtenerRuta() {
        if (this.padre == null) { // Si no tiene padre, es la raíz
            return "/" + nombre;
        }
        return padre.obtenerRuta() + "/" + nombre;
    }

    // Getters
    public String getNombre() {
        return nombre;  // Obtiene el nombre del directorio
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    

    public LinkedList<Archivo> getArchivos() {
        return archivos;  // Obtiene la lista de archivos
    }

    public LinkedList<Directorio> getSubdirectorios() {
        return subdirectorios;  // Obtiene la lista de subdirectorios
    }
    
    public void setPadre(Directorio padre) {
        this.padre = padre;
    }

    public Directorio getPadre() {
        return padre;
    }
    
    public String toString() {
        return nombre;
    }
}
