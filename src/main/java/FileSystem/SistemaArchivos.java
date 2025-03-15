/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FileSystem;

import Almacenamiento.Disco;
import EDD.LinkedList;
import EDD.Node;
import FileSystem.Directorio;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import java.io.File;
import java.lang.reflect.Type;

/**
 *
 * @author Andrea
 */
public class SistemaArchivos {

    private Directorio raiz;
    private Disco disco;

    public SistemaArchivos(int tamanoDisco) {
        this.raiz = new Directorio("Raiz",null);
        this.disco = new Disco(tamanoDisco);
    }

    public void crearArchivo(String nombre, int tamano, String color, String rutaDirectorio) {
        if (!disco.hayEspacio(tamano)) {
            System.out.println("No hay espacio suficiente en el disco.");
             JOptionPane.showMessageDialog(null, "No hay espacio suficiente en el disco.", "Error", JOptionPane.ERROR_MESSAGE);
            ;
        }

        Directorio dirSeleccionado = buscarDirectorioPorRuta(raiz, rutaDirectorio);
        if (dirSeleccionado == null) {
            System.out.println("Directorio no encontrado.");
             JOptionPane.showMessageDialog(null, "Directorio no encontrado.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int primerBloque = disco.asignarBloques(tamano);
        Archivo nuevoArchivo = new Archivo(nombre, tamano, primerBloque, color);
        dirSeleccionado.agregarArchivo(nuevoArchivo);
    }

    public void mostrarArchivos() {
        Node<Archivo> actual = raiz.getArchivos().getCabeza();
        while (actual != null) {
            Archivo archivo = actual.getDato(); // Usamos getDato()
            System.out.println("Archivo: " + archivo.getNombre() + ", Bloques: " + archivo.getTamano());
            actual = actual.getSiguiente();
        }
    }
     
    public void eliminarArchivoEnDirectorio(Directorio directorio, Archivo archivo) {
        if (archivo != null && archivo.getPrimerBloque() != 0) {
            disco.liberarBloques(archivo.getPrimerBloque());
            directorio.eliminarArchivo(archivo);
        } else {
            System.out.println("No se puede eliminar el archivo.");
             JOptionPane.showMessageDialog(null, "No se puede eliminar el archivo.", "Error", JOptionPane.ERROR_MESSAGE);
        }}
    
    public Directorio buscarDirectorioPorRuta(Directorio actual, String rutaBuscada) {
        if (actual.obtenerRuta().equals(rutaBuscada)) {
            return actual;
        }

        for (Node<Directorio> dir = actual.getSubdirectorios().getCabeza(); dir != null; dir = dir.getSiguiente()) {
            Directorio encontrado = buscarDirectorioPorRuta(dir.getDato(), rutaBuscada);
            if (encontrado != null) {
                return encontrado;
            }
        }
        return null;
    }
    
    public JTree construirJTree() {
        if (raiz == null) {
            return new JTree(new DefaultMutableTreeNode("Sistema vacío"));
        }

        DefaultMutableTreeNode raizNode = new DefaultMutableTreeNode(raiz.getNombre());
        construirNodos(raiz, raizNode);
        return new JTree(raizNode);
    }

    private void construirNodos(Directorio dir, DefaultMutableTreeNode nodoPadre) {
        if (dir == null) return;

        // Añadir archivos del directorio
        Node<Archivo> archivoActual = dir.getArchivos().getCabeza();
        while (archivoActual != null) {
            Archivo archivo = archivoActual.getDato();
            DefaultMutableTreeNode archivoNode = new DefaultMutableTreeNode(
                archivo.getNombre() + " (" + archivo.getTamano() + " bloques)"
            );
            nodoPadre.add(archivoNode);
            archivoActual = archivoActual.getSiguiente();
        }

        // Añadir subdirectorios de forma recursiva
        Node<Directorio> subdirActual = dir.getSubdirectorios().getCabeza();
        while (subdirActual != null) {
            Directorio subdirectorio = subdirActual.getDato();
            DefaultMutableTreeNode subdirNode = new DefaultMutableTreeNode(subdirectorio.getNombre());
            nodoPadre.add(subdirNode);
            construirNodos(subdirectorio, subdirNode); // Llamada recursiva
            subdirActual = subdirActual.getSiguiente();
        }
    }
    
    public void mostrarJTree() {
        JFrame frame = new JFrame("Sistema de Archivos");
        JTree tree = construirJTree();

        // Mostrar el árbol en un JScrollPane
        JScrollPane scrollPane = new JScrollPane(tree);
        frame.add(scrollPane);
        
        frame.setSize(400, 500);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }
    
    public Archivo buscarArchivoPorRuta(Directorio actual, String rutaBuscada) {
    // Verificar si el directorio actual contiene el archivo
        for (Node<Archivo> archivoNode = actual.getArchivos().getCabeza(); archivoNode != null; archivoNode = archivoNode.getSiguiente()) {
            Archivo archivo = archivoNode.getDato();
            if ((actual.obtenerRuta() + "/" + archivo.getNombre()).equals(rutaBuscada)) {
                return archivo;
            }
        }

        // Buscar en los subdirectorios de manera recursiva
        for (Node<Directorio> dirNode = actual.getSubdirectorios().getCabeza(); dirNode != null; dirNode = dirNode.getSiguiente()) {
            Archivo encontrado = buscarArchivoPorRuta(dirNode.getDato(), rutaBuscada);
            if (encontrado != null) {
                return encontrado;
            }
        }

        return null; // No se encontró el archivo
    }
    
    public Directorio getRaiz() {
        return raiz;
    }

    public void setRaiz(Directorio raiz) {
        this.raiz = raiz;
    }

    public Disco getDisco() {
        return disco;
    }

    public void setDisco(Disco disco) {
        this.disco = disco;
    }
    public LinkedList<Directorio> obtenerTodosLosDirectorios() {
        LinkedList<Directorio> directorios = new LinkedList<>();
        obtenerDirectoriosRecursivo(raiz, directorios);
        return directorios;
    }

    // Método recursivo para recorrer directorios y obtener sus archivos
    private void obtenerDirectoriosRecursivo(Directorio directorio, LinkedList<Directorio> directorios) {
        directorios.add(directorio);  // Añadimos el directorio actual
        
        LinkedList<Directorio> subdirectorios = directorio.getSubdirectorios();
Node<Directorio> actual = subdirectorios.getCabeza();  // Obtiene el primer nodo
while (actual != null) {
    Directorio subdirectorio = actual.getDato();  // Obtienes el subdirectorio del nodo
    // Código para procesar el subdirectorio
    actual = actual.getSiguiente();  // Avanzas al siguiente nodo
}

        }

public void guardarEstadoEnArchivoJSON(String rutaArchivo) {
    try (FileWriter writer = new FileWriter(rutaArchivo)) {
        Gson gson = new Gson();

        // Crear un objeto JsonObject para estructurar los datos
        JsonObject jsonObject = new JsonObject();

        // Verificar que la raíz no sea nula
        if (this.raiz != null) {
            this.raiz.setPadre(null);  // Evitar referencias circulares
            // Guardar el directorio en el JsonObject
            jsonObject.add("directorio", gson.toJsonTree(this.raiz)); 
        }

        // Guardar el estado de los bloques ocupados en el JsonObject
        jsonObject.add("bloquesOcupados", gson.toJsonTree(this.disco.getOcupado())); 

        // Escribir el objeto JsonObject en el archivo
        gson.toJson(jsonObject, writer); 

        JOptionPane.showMessageDialog(null, "Estado guardado correctamente en JSON.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
    } catch (IOException e) {
        JOptionPane.showMessageDialog(null, "Error al guardar el estado: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
}


public void cargarEstadoDesdeArchivoJSON(String rutaArchivo) {
    try (FileReader reader = new FileReader(rutaArchivo)) {
        Gson gson = new Gson();

        // Leer el archivo completo como un mapa de claves y valores
        JsonObject jsonObject = gson.fromJson(reader, JsonObject.class);

        // Leer el estado del directorio y los bloques ocupados
        Directorio directorioCargado = gson.fromJson(jsonObject.get("directorio"), Directorio.class);
        boolean[] bloquesOcupados = gson.fromJson(jsonObject.get("bloquesOcupados"), boolean[].class);

        if (directorioCargado != null) {
            this.raiz = directorioCargado;  // Asignar la raíz cargada desde JSON
            reconstruirRelaciones(this.raiz, null);  // Reconstruir relaciones
        }

        // Reconstruir los bloques ocupados
        this.disco.setOcupado(bloquesOcupados);  // Establecer el estado de los bloques ocupados

        // Asegúrate de que los bloques estén correctamente asignados en los archivos cargados
        asignarBloquesAFiles(this.raiz);

        JOptionPane.showMessageDialog(null, "Estado cargado correctamente desde JSON.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
    } catch (IOException e) {
        JOptionPane.showMessageDialog(null, "Error al cargar el estado: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
}

private void reconstruirRelaciones(Directorio directorioActual, Directorio padre) {
    if (directorioActual != null) {
        System.out.println("Reconstruyendo: " + directorioActual.getNombre());

        directorioActual.setPadre(padre); // Asignar el padre correcto

        // Iterar sobre todos los subdirectorios para reconstruir relaciones
        if (directorioActual.getSubdirectorios() != null) {
            Node<Directorio> nodoActual = directorioActual.getSubdirectorios().getCabeza();
            while (nodoActual != null) {
                reconstruirRelaciones(nodoActual.getDato(), directorioActual);
                nodoActual = nodoActual.getSiguiente();
            }
        }
    }
}
private void asignarBloquesAFiles(Directorio directorio) {
    if (directorio != null && directorio.getArchivos() != null) {
        Node<Archivo> archivoActual = directorio.getArchivos().getCabeza();
        while (archivoActual != null) {
            Archivo archivo = archivoActual.getDato();

            // Si el archivo no tiene bloque asignado, asignamos uno
            if (archivo.getPrimerBloque() == -1) {  // Si no tiene bloque asignado
                int bloqueAsignado = this.disco.asignarBloques(1);  // Asignamos un bloque
                if (bloqueAsignado != -1) {
                    archivo.setPrimerBloque(bloqueAsignado);  // Guardamos el bloque asignado
                    this.disco.marcarComoOcupado(bloqueAsignado);  // Marcamos el bloque como ocupado
                }
            }

            archivoActual = archivoActual.getSiguiente();
        }
    }
}
}


