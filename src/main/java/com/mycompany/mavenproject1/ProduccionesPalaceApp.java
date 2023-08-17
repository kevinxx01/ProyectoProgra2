/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject1;

/**
 *
 * @author Kevin
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

public class ProduccionesPalaceApp extends JFrame {
    private LinkedList<Usuario> usuarios = new LinkedList<>();

    private class GradientPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            Graphics2D g2d = (Graphics2D) g;
            int width = getWidth();
            int height = getHeight();

            Color startColor = new Color(30, 30, 30); // Negro más claro
            Color endColor = new Color(50, 50, 50); // Gris oscuro

            GradientPaint gradient = new GradientPaint(0, 0, startColor, 0, height, endColor);
            g2d.setPaint(gradient);
            g2d.fillRect(0, 0, width, height);
        }
    }

    public ProduccionesPalaceApp() {
        super("Producciones Palace - Sistema de Usuarios");

        Color primaryColor = new Color(70, 70, 70); // Gris oscuro
        Color secondaryColor = new Color(100, 100, 100); // Gris medio
        Color accentColor = new Color(0, 173, 239); // Azul claro

        Font titleFont = new Font("Roboto", Font.BOLD, 40);
        Font labelFont = new Font("Roboto", Font.PLAIN, 20);

        JLabel lblTitulo = new JLabel("Bienvenido");
        lblTitulo.setFont(titleFont);
        lblTitulo.setForeground(Color.WHITE);

        JLabel lblUsuario = new JLabel("Usuario:");
        lblUsuario.setForeground(Color.WHITE);
        JLabel lblPassword = new JLabel("Contraseña:");
        lblPassword.setForeground(Color.WHITE);

        JTextField txtUsuario = new JTextField(20);
        JPasswordField txtPassword = new JPasswordField(20);

        JButton btnIngresar = new JButton("Ingresar");
        JButton btnCrearUsuario = new JButton("Nuevo usuario");

        JPanel panel = new GradientPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(20, 20, 20, 20);

        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        panel.add(lblTitulo, c);

        c.gridy = 2;
        c.gridwidth = 1;
        panel.add(lblUsuario, c);
        c.gridx = 1;
        panel.add(txtUsuario, c);

        c.gridx = 0;
        c.gridy = 4;
        panel.add(lblPassword, c);
        c.gridx = 1;
        panel.add(txtPassword, c);

        c.gridy = 6;
        c.gridwidth = 2;
        c.fill = GridBagConstraints.HORIZONTAL;
        panel.add(btnIngresar, c);

        c.gridy = 8;
        panel.add(btnCrearUsuario, c);

        btnIngresar.setBackground(primaryColor);
        btnIngresar.setForeground(Color.WHITE);
        btnCrearUsuario.setBackground(secondaryColor);
        btnCrearUsuario.setForeground(Color.WHITE);

        btnIngresar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnIngresar.setBackground(primaryColor.brighter());
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnIngresar.setBackground(primaryColor);
            }
        });
        btnCrearUsuario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnCrearUsuario.setBackground(secondaryColor.brighter());
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnCrearUsuario.setBackground(secondaryColor);
            }
        });

        btnIngresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String usuario = txtUsuario.getText();
                String password = new String(txtPassword.getPassword());

                if (autenticarUsuario(usuario, password)) {
                    JOptionPane.showMessageDialog(ProduccionesPalaceApp.this, "¡Bienvenido, " + usuario + "!");
                    SwingUtilities.invokeLater(() -> new EventosApp());

                } else {
                    JOptionPane.showMessageDialog(ProduccionesPalaceApp.this, "Credenciales incorrectas. Intente de nuevo.");
                }
            }
        });

        btnCrearUsuario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JPanel panelCrearUsuario = new JPanel(new GridLayout(0, 1));
                panelCrearUsuario.setBackground(primaryColor);

                JTextField txtNombre = new JTextField(20);
                txtNombre.setBackground(Color.WHITE);
                panelCrearUsuario.add(createLabel("Nombre:"));
                panelCrearUsuario.add(txtNombre);

                JTextField txtApellidos = new JTextField(20);
                txtApellidos.setBackground(Color.WHITE);
                panelCrearUsuario.add(createLabel("Primer apellido:"));
                panelCrearUsuario.add(txtApellidos);

                JTextField txtNuevoUsuario = new JTextField(20);
                txtNuevoUsuario.setBackground(Color.WHITE);
                panelCrearUsuario.add(createLabel("Usuario:"));
                panelCrearUsuario.add(txtNuevoUsuario);

                JPasswordField txtNuevaPassword = new JPasswordField(20);
                txtNuevaPassword.setBackground(Color.WHITE);
                panelCrearUsuario.add(createLabel("Contraseña:"));
                panelCrearUsuario.add(txtNuevaPassword);

                JCheckBox chkActivo = new JCheckBox("Activo");
                chkActivo.setForeground(Color.WHITE);
                chkActivo.setBackground(primaryColor);
                panelCrearUsuario.add(chkActivo);

                JTextField txtCorreo = new JTextField(20);
                txtCorreo.setBackground(Color.WHITE);
                panelCrearUsuario.add(createLabel("Correo:"));
                panelCrearUsuario.add(txtCorreo);

                int result = JOptionPane.showConfirmDialog(ProduccionesPalaceApp.this, panelCrearUsuario, "Crear Nuevo Usuario",
                        JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

                if (result == JOptionPane.OK_OPTION) {
                    String nombre = txtNombre.getText();
                    String apellidos = txtApellidos.getText();
                    String nuevoUsuario = txtNuevoUsuario.getText();
                    String nuevaPassword = new String(txtNuevaPassword.getPassword());
                    boolean estadoActivo = chkActivo.isSelected();
                    String correo = txtCorreo.getText();

                    Usuario nuevoUsuarioObj = new Usuario(nombre, apellidos, nuevoUsuario, nuevaPassword, estadoActivo, correo);
                    agregarUsuario(nuevoUsuarioObj);
                    JOptionPane.showMessageDialog(ProduccionesPalaceApp.this, "Usuario creado exitosamente!");

                } else {
                    JOptionPane.showMessageDialog(ProduccionesPalaceApp.this, "Operación cancelada.");
                }
            }
        });

        add(panel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null); // Centrar ventana en la pantalla
        setVisible(true);
    }

    private boolean autenticarUsuario(String usuario, String password) {
        for (Usuario u : usuarios) {
            if (u.getUsuario().equals(usuario) && u.getPassword().equals(password) && u.isEstadoActivo()) {
                return true;
            }
        }
        return false;
    }
    public void agregarUsuario(Usuario usuario){
        usuarios.add(usuario);
    }
    

    public LinkedList<Usuario> consultarUsuarios() {
        return usuarios;
    }

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Roboto", Font.PLAIN, 16));
        label.setForeground(Color.WHITE);
        return label;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ProduccionesPalaceApp());
    }
}



