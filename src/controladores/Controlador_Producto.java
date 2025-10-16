/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controladores;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Ing_heskin
 */
public class Controlador_Producto {
    public Controlador_Producto() {}
    
     // 🔸 Arrays simulando base de datos
    public static int[] idProducto = new int[100];
    public static String[] nombreProducto = new String[100];
    public static String[] categoria = new String[100];
    public static int[] cantidad = new int[100];
    public static double[] precio = new double[100];
    public static int tam = 0;

    // ➕ Crear producto
    public String insertarProductoArray(int id, String nom, String cat, int cant, double prec) {
        for (int i = 0; i < tam; i++) {
            if (idProducto[i] == id) {
                return "ID already exists.";
            }
        }

        idProducto[tam] = id;
        nombreProducto[tam] = nom;
        categoria[tam] = cat;
        cantidad[tam] = cant;
        precio[tam] = prec;
        tam++;
        return "Product saved successfully.";
    }

    // 🔍 Consultar producto por ID
    public int consultarProductoEspecifico(int id) {
        int pos = consultarProductoEspecifico(id); // busca la posición
        for (int i = 0; i < tam; i++) {
            if (idProducto[i] == id) {
                return i;
            }
        }
        return -1; // no encontrado
    }

    // ✏️ Actualizar producto
    public String actualizarProducto(int id, String nom, String cat, int cant, double prec) {
        int pos = consultarProductoEspecifico(id);
        if (pos != -1) {
            nombreProducto[pos] = nom;
            categoria[pos] = cat;
            cantidad[pos] = cant;
            precio[pos] = prec;
            return "Product updated successfully.";
        } else {
            return "Product not found.";
        }
    }

    // ❌ Eliminar producto
    public String eliminarProductoArray(int id) {
        int pos = consultarProductoEspecifico(id);
        if (pos != -1) {
            for (int i = pos; i < tam - 1; i++) {
                idProducto[i] = idProducto[i + 1];
                nombreProducto[i] = nombreProducto[i + 1];
                categoria[i] = categoria[i + 1];
                cantidad[i] = cantidad[i + 1];
                precio[i] = precio[i + 1];
            }
            tam--;
            return "Product deleted successfully.";
        } else {
            return "Product not found.";
        }
    }

    // 📋 Mostrar todos los productos en la tabla
    public void mostrarProductosEnTabla(DefaultTableModel modelo) {
        modelo.setRowCount(0); // Limpia la tabla antes de llenarla
        for (int i = 0; i < tam; i++) {
            Object[] fila = {
                idProducto[i],
                nombreProducto[i],
                categoria[i],
                cantidad[i],
                precio[i]
            };
            modelo.addRow(fila);
        }
    }
}
