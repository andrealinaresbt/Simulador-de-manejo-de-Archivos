/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.proyecto2so;

import Auditoria.Auditoria;
import FileSystem.Directorio;
import FileSystem.SistemaArchivos;
import GUIs.ModoAdmin;
import GUIs.ModoUsuario;
import GUIs.viewDisco;
import javax.swing.JOptionPane;
/**
 *
 * @author Princ
 */
public class Main {

    public static void main(String[] args) {
        //pruebas main
        Auditoria auditoria = new Auditoria();
        SistemaArchivos sistema = new SistemaArchivos(100, auditoria);
        //sistema.cargarEstadoDesdeArchivoJSON("TXT/ArchivoEstado.json");
        Directorio docs = new Directorio("Documentos", sistema.getRaiz(), auditoria);
        //sistema.getRaiz().agregarDirectorio(docs);

        Directorio proyectos = new Directorio("Proyectos", docs, auditoria);
        //docs.agregarDirectorio(proyectos);
        
        Directorio proyectos2 = new Directorio("Proyectos2", proyectos, auditoria);
        proyectos.agregarDirectorio(proyectos2);   
        //sistema.crearArchivo("tarea1.txt", 5, "azul", "/Raiz/Documentos");


        //------------------------------------------------------------------
        //MAIN REAL
        // Opciones de selección
        
        String[] options = {"Modo Usuario", "Modo Administrador"};
        
        // Mostrar el cuadro de diálogo de selección
        int choice = JOptionPane.showOptionDialog(
            null,
            "Seleccione el modo de ejecución:",
            "Modo de Ejecución",
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.QUESTION_MESSAGE,
            null,
            options,
            options[0] 
        );

        String modo = (choice == 1) ? "admin" : "usuario";
        
        if (modo.equals("admin")) {
             System.out.println("Creando vistaDisco...");
viewDisco vistaDisco = new viewDisco(sistema);
vistaDisco.setVisible(true);
System.out.println("Ventana vistaDisco visible.");

// Actualizar vista
vistaDisco.actualizarVista();
System.out.println("Vista actualizada.");
            ModoAdmin modoAdmin=new ModoAdmin(sistema);
            modoAdmin.setVisible(true);
            
        } 
        else if (modo.equals("usuario")) {
          
            ModoUsuario modoUsuario= new ModoUsuario(sistema);
            modoUsuario.setVisible(true);
        }
    }
}
