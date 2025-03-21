/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Auditoria;
import EDD.LinkedList;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
/**
 *
 * @author Andrea
 */
public class Auditoria {
    private LinkedList<String> logs; // Usamos LinkedList en lugar de ArrayList

    public Auditoria() {
        this.logs = new LinkedList<>();
    }

    // Método para registrar una operación
    public void registrarOperacion(String usuario, String operacion) {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        String log = String.format("[%s] - Usuario: %s - Acción: %s", timestamp, usuario, operacion);
        logs.add(log); // Guardar el log en la LinkedList
        System.out.println(log); // Imprimir en consola
    }

    // Método para obtener todos los logs registrados (convertir LinkedList a un array o lista estándar si se necesita)
    public LinkedList<String> obtenerLogs() {
        return logs;
    }
}
