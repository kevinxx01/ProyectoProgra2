/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject1;
import java.util.List;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author kenca
 */
    // Clase Factura
public class Factura {
    private String nombreCliente;
    private List<String> asientosElegidos;
    private double precioUnitario;
    private double total;

    public Factura(String nombreCliente, List<String> asientosElegidos, double precioUnitario) {
        this.nombreCliente = nombreCliente;
        this.asientosElegidos = new ArrayList<>(asientosElegidos);
        this.precioUnitario = precioUnitario;
        this.total = precioUnitario * asientosElegidos.size();
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public List<String> getAsientosElegidos() {
        return asientosElegidos;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public double getTotal() {
        return total;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Cliente: ").append(nombreCliente).append("\n");
        sb.append("Asientos: ").append(asientosElegidos).append("\n");
        sb.append("Precio Unitario: $").append(precioUnitario).append("\n");
        sb.append("Total: $").append(total);
        return sb.toString();
    }
}

    

