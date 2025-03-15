/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUIs;

import EDD.LinkedList;
import EDD.Node;
import FileSystem.Archivo;
import FileSystem.Directorio;
import FileSystem.SistemaArchivos;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreeSelectionModel;

/**
 *
 * @author Gabriel
 */
public class ActualizarArchivo extends javax.swing.JFrame {
    private static SistemaArchivos sistemaArchivos;
    private String rutaSeleccionada;
    private String rutaPadre;
    private String nombreViejo;
    /**
     * Creates new form ActualizarArchivo
     */
    public ActualizarArchivo(SistemaArchivos sistemaArchivos) {
        this.sistemaArchivos=sistemaArchivos;
        initComponents();
        setLocationRelativeTo(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setText("Actualizar archivo");

        jLabel2.setText("Nuevo nombre");

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jButton1.setText("Seleccionar archivo");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Actualizar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(107, 107, 107)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(106, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButton2)
                .addGap(157, 157, 157))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jLabel1)
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 58, Short.MAX_VALUE)
                .addComponent(jButton2)
                .addGap(48, 48, 48))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // Nuevo nombre
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // Actualizar
        String nombre=jTextField1.getText();
        String nombreVerificacion=jTextField1.getText().trim();
        
        if (nombreVerificacion.isEmpty()) {
        JOptionPane.showMessageDialog(this, "El nombre no puede estar vacío.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
        }
        
        if (rutaPadre == null || rutaPadre.trim().isEmpty()) {
        JOptionPane.showMessageDialog(this, "Seleccione un archivo.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
        }
        
        Directorio directorio =sistemaArchivos.buscarDirectorioPorRuta(sistemaArchivos.getRaiz(), rutaPadre);
        LinkedList<Archivo> lista = directorio.getArchivos();
        Node<Archivo> actual = lista.getCabeza();

        while (actual != null) {  
            if (actual.getDato().getNombre().equals(nombreViejo)) {  
                actual.getDato().setNombre(nombre); // Cambia el nombre del archivo  
                JOptionPane.showMessageDialog(this, "Nombre modificado a: " + actual.getDato().getNombre(), "Error", JOptionPane.ERROR_MESSAGE);
                System.out.println("Creando vistaDisco...");
    viewDisco vistaDisco = new viewDisco(sistemaArchivos);
    vistaDisco.actualizarVista();
    vistaDisco.setVisible(true);
    System.out.println("Ventana vistaDisco visible.");

    // Actualizar vista
    vistaDisco.actualizarVista();
    System.out.println("Vista actualizada.");
    
    // Crear una instancia de la clase TablaAsignacionArchivos para actualizar la tabla
    TablaAsignacionArchivos tablaAsignacion = new TablaAsignacionArchivos(sistemaArchivos);
    tablaAsignacion.actualizarTabla();  // Llamar al método para actualizar la tabla
    System.out.println("Tabla actualizada.");

    // Obtener la tabla actualizada
    JTable tabla = tablaAsignacion.getTabla();

    // Crear un TableCellRenderer para la columna de color
    tabla.getColumnModel().getColumn(3).setCellRenderer(new DefaultTableCellRenderer() {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            Component comp = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

            if (value != null) {
                try {
                    // Si el valor es un nombre de color, lo decodificamos a un color
                    // Asegúrate de que el valor sea un color reconocido
                    if (value instanceof String) {
                        String colorStr = (String) value;
                        Color colorObj = getColorByName(colorStr);
                        comp.setBackground(colorObj);  // Cambiar el color de fondo
                    }
                } catch (Exception e) {
                    comp.setBackground(null);  // Si no es un color válido, dejar el fondo por defecto
                }
            }
            return comp;
        }

        // Método para obtener un color por su nombre
        private Color getColorByName(String colorName) {
            switch (colorName.toLowerCase()) {
                case "azul":
                    return Color.BLUE;
                case "rojo":
                    return Color.RED;
                case "verde":
                    return Color.GREEN;
                case "amarillo":
                    return Color.YELLOW;
                // Puedes agregar más colores si lo necesitas
                default:
                    return Color.WHITE;  // Color por defecto si no es reconocido
            }
        }
    });

    // Crear un JScrollPane para la tabla (esto hace que la tabla sea desplazable)
    JScrollPane scrollPane = new JScrollPane(tabla);

    // Asegúrate de agregar el JScrollPane a un contenedor gráfico visible
    JFrame frame = new JFrame("Tabla de Asignación de Archivos");
    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    // Suponiendo que tienes un JPanel o algún contenedor para agregar la tabla
    JPanel panel = new JPanel();
    panel.setLayout(new BorderLayout());  // Usamos BorderLayout para que ocupe todo el espacio
    panel.add(scrollPane, BorderLayout.CENTER);  // Agregamos el JScrollPane al centro

    frame.add(panel);  // Agregamos el panel al JFrame
    frame.setSize(500, 400);  // Define el tamaño del frame
    frame.setVisible(true);  // Hacemos visible la ventana
                break; // Sale del bucle después de modificar  
            }  
            actual = actual.getSiguiente(); 
        }  

        if (actual == null) {  
            System.out.println("Archivo no encontrado.");  
        }
        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // Seleccionar archivo
        // Crear una nueva ventana emergente (JDialog)
        JDialog dialog = new JDialog(this, "Seleccionar Archivo", true);
        dialog.setLayout(new BorderLayout());

        // Construir el JTree con los datos del sistema de archivos
        JTree tree = sistemaArchivos.construirJTree();
        tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);

        // Evento para seleccionar un archivo y cerrar la ventana
        tree.addTreeSelectionListener(e -> {
            DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
            if (selectedNode != null) {
                String ruta = obtenerRutaDesdeNodo(selectedNode).split(" \\(")[0]; // Elimina " (X bloques)"
                ruta = "/" + ruta;
                System.out.println("Ruta generada: " + ruta);

                Archivo archivo = sistemaArchivos.buscarArchivoPorRuta(sistemaArchivos.getRaiz(), ruta);
                if (archivo != null) { // Si es un archivo
                    rutaSeleccionada = ruta;
                    nombreViejo=archivo.getNombre();
                    JOptionPane.showMessageDialog(this, "Archivo seleccionado: " + ruta);
                    dialog.dispose(); // Cierra el diálogo al seleccionar

                    // Obtener el directorio padre
                    DefaultMutableTreeNode parentNode = (DefaultMutableTreeNode) selectedNode.getParent();
                    if (parentNode != null) {
                        rutaPadre = "/" +obtenerRutaDesdeNodo(parentNode);
                        System.out.println("Directorio padre: " + rutaPadre);
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Por favor, seleccione un archivo y no un directorio.");
                }
            }
        });

        // Mostrar el árbol en un JScrollPane
        JScrollPane scrollPane = new JScrollPane(tree);
        dialog.add(scrollPane, BorderLayout.CENTER);

        // Configurar tamaño y visibilidad de la ventana
        dialog.setSize(400, 500);
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed
    
        //️ Método para obtener la ruta desde el nodo seleccionado
    private String obtenerRutaDesdeNodo(DefaultMutableTreeNode node) {
        StringBuilder ruta = new StringBuilder(node.toString());
        TreeNode parent = node.getParent();

        while (parent != null) {
            ruta.insert(0, parent.toString() + "/");
            parent = parent.getParent();
        }

        // Eliminar la última "/" si existe
        if (ruta.length() > 1 && ruta.charAt(ruta.length() - 1) == '/') {
            ruta.deleteCharAt(ruta.length() - 1);
        }
        return ruta.toString();
    }

    //️ GETTER para la ruta seleccionada
    public String getRutaSeleccionada() {
        return rutaSeleccionada;
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ActualizarArchivo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ActualizarArchivo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ActualizarArchivo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ActualizarArchivo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ActualizarArchivo(sistemaArchivos).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
