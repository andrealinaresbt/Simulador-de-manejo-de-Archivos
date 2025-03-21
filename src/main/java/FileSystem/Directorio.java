package FileSystem;

import Almacenamiento.Disco;
import Auditoria.Auditoria;
import EDD.LinkedList; // Asegúrate de que esta clase esté correctamente implementada
import com.google.gson.annotations.Expose;

/**
 *
 * @author Andrea
 */
public class Directorio {
    
    private String nombre;
    private LinkedList<Archivo> archivos;  // Lista de archivos dentro del directorio
    private LinkedList<Directorio> subdirectorios;  // Lista de subdirectorios dentro del directorio
    private Auditoria auditoria;
    private Disco disco;  // Referencia a Disco
    
    @Expose
    private transient Directorio padre;

    public Directorio(String nombre, Directorio padre, Auditoria auditoria) {
        this.nombre = nombre;
        this.archivos = new LinkedList<>();  // Inicializa la lista de archivos
        this.subdirectorios = new LinkedList<>();  // Inicializa la lista de subdirectorios
        this.auditoria = auditoria;
        this.padre = padre;
        
    }

    // Método para agregar un archivo al directorio
    public void agregarArchivo(Archivo archivo, String usuario) {
        archivos.add(archivo);  // Agrega el archivo a la lista
         System.out.println("Archivo " + archivo.getNombre() + " creado en " + this.nombre);
        
        // Registrar la operación en el log
        auditoria.registrarOperacion(usuario, "Creó el archivo " + archivo.getNombre() + " en el directorio " + this.nombre);
    }

    // Método para agregar un subdirectorio al directorio
    public void agregarDirectorio(Directorio directorio) {
        subdirectorios.add(directorio);  // Agrega el subdirectorio a la lista
          auditoria.registrarOperacion("Usuario", "Directorio creado: " + directorio.getNombre());
    }

    // Método para eliminar un archivo del directorio
    public boolean eliminarArchivo(Archivo archivo, String usuario) {
    System.out.println("Archivo " + archivo.getNombre() + " eliminado del directorio " + this.nombre);
        
        // Registrar la operación en el log
        auditoria.registrarOperacion(usuario, "Eliminó el archivo " + archivo.getNombre() + " del directorio " + this.nombre);
    return archivos.remove(archivo);  // Devuelve true si el archivo se eliminó correctamente
     
}

public Archivo getPrimerArchivo() {
    if (archivos == null || archivos.getCabeza() == null) {
        return null;  // Retorna null si la lista no está inicializada o está vacía
    }
    return archivos.getCabeza().getDato();  // Retorna el primer archivo
}


   public boolean eliminarDirectorio(Directorio directorio) {
    if (directorio == null) {
        return false;
    }

    // Eliminar archivos del directorio antes de eliminarlo
    while (directorio.getPrimerArchivo() != null) {
        String usuario = System.getProperty("user.name");
        directorio.eliminarArchivo(directorio.getPrimerArchivo(), usuario);
    }
    auditoria.registrarOperacion("Usuario", "Directorio eliminado: " + directorio.getNombre());

    // Ahora eliminar el subdirectorio
    return subdirectorios.remove(directorio);
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
    
    
    public boolean tieneSubdirectorios() {
        return subdirectorios.tamaño() > 0;
    }
    
    @Override
    public String toString() {
        return "Directorio[nombre=" + nombre + ", archivos=" + archivos + "]";
    }

    public Auditoria getAuditoria() {
        return auditoria;
    }
}
