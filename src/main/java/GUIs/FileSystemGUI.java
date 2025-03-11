/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUIs;
import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
/**
 *
 * @author Gabriel
 */

public class FileSystemGUI extends JFrame{
    private JTree tree;
    private DefaultTreeModel treeModel;
    private DefaultMutableTreeNode root;

    public FileSystemGUI() {
        setTitle("Sistema de Archivos");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        root = new DefaultMutableTreeNode("Raíz");
        treeModel = new DefaultTreeModel(root);
        tree = new JTree(treeModel);

        JScrollPane scrollPane = new JScrollPane(tree);
        add(scrollPane);

    }


}
