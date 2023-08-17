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
import java.util.ArrayList;
import java.util.List;

public class EventosApp {
    private JFrame frame;
    private DefaultListModel<Evento> eventosListModel;
    private JList<Evento> eventosList;
    private List<Factura> facturas = new ArrayList<>();

    public EventosApp() {
        eventosListModel = new DefaultListModel<>();
        eventosList = new JList<>(eventosListModel);

        frame = new JFrame("Gestión de Eventos");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JPanel eventosPanel = new JPanel(new BorderLayout());
        eventosPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        eventosPanel.add(new JScrollPane(eventosList), BorderLayout.CENTER);

        JButton agregarButton = new JButton("Agregar Evento");
        JButton editarButton = new JButton("Editar Evento");
        JButton inactivarButton = new JButton("Inactivar Evento");
        JButton generarFacturaButton = new JButton("Generar Factura");
        JButton verFacturasButton = new JButton("Ver Facturas");

        agregarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                agregarEvento();
            }
        });

        editarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                editarEvento();
            }
        });

        inactivarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                inactivarEvento();
            }
        });

        generarFacturaButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                generarFactura();
            }
        });

        verFacturasButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                verFacturas();
            }
        });

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(agregarButton);
        buttonPanel.add(editarButton);
        buttonPanel.add(inactivarButton);
        buttonPanel.add(generarFacturaButton);
        buttonPanel.add(verFacturasButton);
        eventosPanel.add(buttonPanel, BorderLayout.SOUTH);

        eventosList.addListSelectionListener(e -> {
            Evento eventoSeleccionado = eventosList.getSelectedValue();
            if (eventoSeleccionado != null) {
                mostrarSimulacionAsientos(eventoSeleccionado);
            }
        });

        frame.add(eventosPanel, BorderLayout.WEST);
        frame.setSize(800, 400);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void agregarEvento() {
        String ciudad = JOptionPane.showInputDialog(frame, "Ciudad:", "Agregar Evento", JOptionPane.PLAIN_MESSAGE);
        String direccion = JOptionPane.showInputDialog(frame, "Dirección:", "Agregar Evento", JOptionPane.PLAIN_MESSAGE);
        String categoria = JOptionPane.showInputDialog(frame, "Categoría:", "Agregar Evento", JOptionPane.PLAIN_MESSAGE);
        String fecha = JOptionPane.showInputDialog(frame, "Fecha:", "Agregar Evento", JOptionPane.PLAIN_MESSAGE);
        int capacidad = Integer.parseInt(JOptionPane.showInputDialog(frame, "Capacidad:", "Agregar Evento", JOptionPane.PLAIN_MESSAGE));

        Evento nuevoEvento = new Evento(ciudad, direccion, categoria, fecha, capacidad);
        eventosListModel.addElement(nuevoEvento);
    }

    private void editarEvento() {
        Evento eventoSeleccionado = eventosList.getSelectedValue();
        if (eventoSeleccionado != null) {
            String ciudad = JOptionPane.showInputDialog(frame, "Ciudad:", eventoSeleccionado.getCiudad());
            String direccion = JOptionPane.showInputDialog(frame, "Dirección:", eventoSeleccionado.getDireccion());
            String categoria = JOptionPane.showInputDialog(frame, "Categoría:", eventoSeleccionado.getCategoria());
            String fecha = JOptionPane.showInputDialog(frame, "Fecha:", eventoSeleccionado.getFecha());
            int capacidad = Integer.parseInt(JOptionPane.showInputDialog(frame, "Capacidad:", eventoSeleccionado.getCapacidad()));

            eventoSeleccionado.editarEvento(ciudad, direccion, categoria, fecha, capacidad);
            eventosList.repaint();
        } else {
            JOptionPane.showMessageDialog(frame, "Seleccione un evento para editar.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void inactivarEvento() {
        Evento eventoSeleccionado = eventosList.getSelectedValue();
        if (eventoSeleccionado != null) {
            int opcion = JOptionPane.showConfirmDialog(frame, "¿Estás seguro de inactivar el evento?", "Confirmar Inactivar Evento", JOptionPane.YES_NO_OPTION);
            if (opcion == JOptionPane.YES_OPTION) {
                eventoSeleccionado.inactivarEvento();
                eventosList.repaint();
            }
        } else {
            JOptionPane.showMessageDialog(frame, "Seleccione un evento para inactivar.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void generarFactura() {
        Evento eventoSeleccionado = eventosList.getSelectedValue();
        if (eventoSeleccionado != null) {
            List<String> asientosElegidos = eventoSeleccionado.getAsientos();
            String nombreCliente = JOptionPane.showInputDialog(frame, "Nombre del Cliente:", "Generar Factura", JOptionPane.PLAIN_MESSAGE);

            if (nombreCliente != null && !nombreCliente.isEmpty()) {
                double precioUnitario = 3300.0;

                eventoSeleccionado.facturar(nombreCliente, asientosElegidos);
                Factura factura = new Factura(nombreCliente, asientosElegidos, precioUnitario);
                agregarFactura(factura);

                JOptionPane.showMessageDialog(frame, "Factura generada exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(frame, "Seleccione un evento para generar factura.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void agregarFactura(Factura factura) {
        facturas.add(factura);
    }

    private void verFacturas() {
        StringBuilder sb = new StringBuilder();
        for (Factura factura : facturas) {
            sb.append(factura).append("\n\n");
        }
        JTextArea textArea = new JTextArea(sb.toString());
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        JOptionPane.showMessageDialog(frame, scrollPane, "Facturas Generadas", JOptionPane.PLAIN_MESSAGE);
    }

    private void mostrarSimulacionAsientos(Evento evento) {
        JFrame asientosFrame = new JFrame("Simulación de Asientos - " + evento.getCiudad() + " - " + evento.getFecha());
        asientosFrame.setLayout(new BorderLayout());
        asientosFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel asientosPanel = new JPanel(new GridLayout(10, 10, 2, 2));

        for (int i = 1; i <= evento.getCapacidad(); i++) {
            String asiento = Integer.toString(i);
            JButton asientoButton = new JButton(asiento);

            if (evento.getAsientos().contains(asiento)) {
                asientoButton.setBackground(Color.RED);
                asientoButton.setEnabled(false);
            } else {
                asientoButton.setBackground(Color.GREEN);
                asientoButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        int opcion = JOptionPane.showConfirmDialog(asientosFrame, "¿Deseas reservar el asiento " + asiento + "?", "Confirmar Reserva", JOptionPane.YES_NO_OPTION);
                        if (opcion == JOptionPane.YES_OPTION) {
                            evento.agregarAsiento(asiento);
                            asientoButton.setBackground(Color.RED);
                            asientoButton.setEnabled(false);
                        }
                    }
                });
            }

            asientosPanel.add(asientoButton);
        }

        asientosFrame.add(asientosPanel, BorderLayout.CENTER);
        asientosFrame.setSize(600, 400);
        asientosFrame.setLocationRelativeTo(null);
        asientosFrame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new EventosApp();
            }
        });
    }
}
