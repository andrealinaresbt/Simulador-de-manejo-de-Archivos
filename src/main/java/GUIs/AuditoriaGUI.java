/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUIs;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.*;


/**
 *
 * @author Andrea
 */
public class AuditoriaGUI {
    
    private JTextArea jTextArea; // Para mostrar los registros en la GUI

    public AuditoriaGUI() {
        JFrame frame = new JFrame("Registro de Auditoría");
        jTextArea = new JTextArea(20, 50); // Tamaño del área de texto
        JScrollPane scrollPane = new JScrollPane(jTextArea); // Agregar scroll al JTextArea
        jTextArea.setEditable(false); // Solo lectura

        // Configuración del JFrame
        frame.add(scrollPane);
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    // Método para registrar operaciones en la GUI
    public void registrarOperacion(String usuario, String operacion) {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        String log = String.format("[%s] - Usuario: %s - Acción: %s", timestamp, usuario, operacion);
        jTextArea.append(log + "\n"); // Agregar el log al JTextArea
    }
}


