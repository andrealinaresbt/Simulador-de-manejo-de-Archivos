/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.proyecto2so;

import FileSystem.Directorio;
import FileSystem.SistemaArchivos;
import GUIs.ModoAdmin;
import javax.swing.JOptionPane;
/**
 *
 * @author Princ
 */
public class Main {

    public static void main(String[] args) {
        //pruebas main
        SistemaArchivos sistema = new SistemaArchivos(1000000);
        Directorio docs = new Directorio("Documentos", sistema.getRaiz());
        sistema.getRaiz().agregarDirectorio(docs);

        Directorio proyectos = new Directorio("Proyectos", docs);
        docs.agregarDirectorio(proyectos);
        
        Directorio proyectos2 = new Directorio("Proyectos2", proyectos);
        proyectos.agregarDirectorio(proyectos2);
        
        sistema.crearArchivo("tarea1.txt", 5, "azul", "/Raiz/Documentos");
        //sistema.construirJTree();
        //sistema.mostrarJTree();  

        
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
            ModoAdmin modoAdmin=new ModoAdmin(sistema);
            modoAdmin.setVisible(true);
            
        } else if (modo.equals("usuario")) {
            
        }
    }
}
