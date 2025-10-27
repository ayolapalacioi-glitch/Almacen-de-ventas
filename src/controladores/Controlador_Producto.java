/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controladores;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Ing_heskin
 */
public class Controlador_Producto {
    
    private static String[] idProducto = new String[100];
    private static String[] nombre = new String[100];
    private static double[] precio = new double[100];
    private static int[] cantidad = new int[100];
    private static String[] categoria = new String[100];

    private static int contadorProductos = 0;

    public static void registrarProducto(JTextField ID_Producto,
                                         JTextField Name_Producto,
                                         JTextField Precio,
                                         JTextField Cantidad,
                                         JTextField Categoria) {

        try {
            String id = ID_Producto.getText().trim();
            String nom = Name_Producto.getText().trim();
            String pre = Precio.getText().trim();
            String cant = Cantidad.getText().trim();
            String cat = Categoria.getText().trim();

            
            if (id.isEmpty() || nom.isEmpty() || pre.isEmpty() || cant.isEmpty() || cat.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Por favor complete todos los campos.");
                return;
            }

            double precioDouble = Double.parseDouble(pre);
            int cantidadInt = Integer.parseInt(cant);

            idProducto[contadorProductos] = id;
            nombre[contadorProductos] = nom;
            precio[contadorProductos] = precioDouble;
            cantidad[contadorProductos] = cantidadInt;
            categoria[contadorProductos] = cat;

            contadorProductos++;

            JOptionPane.showMessageDialog(null, "Producto guardado correctamente.");

            ID_Producto.setText("");
            Name_Producto.setText("");
            Precio.setText("");
            Cantidad.setText("");
            Categoria.setText("");

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Error: el precio o la cantidad no son válidos.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al registrar el producto: " + e.getMessage());
        }
    }
    
    public static void buscarProductoEditar(JTextField ID_Producto_2, JTextField Nombre_Producto_2,
                                        JTextField Categoria_2, JTextField Cantidad_2, JTextField Precio_2) {

    try {
        String idBuscado = ID_Producto_2.getText().trim();
        int pos = -1;

        for (int i = 0; i < contadorProductos; i++) {
            if (idProducto[i] != null && idProducto[i].equalsIgnoreCase(idBuscado)) {
                pos = i;
                break;
            }
        }

        if (pos != -1) {
            Nombre_Producto_2.setText(nombre[pos]);
            Categoria_2.setText(categoria[pos]);
            Cantidad_2.setText(String.valueOf(cantidad[pos]));
            Precio_2.setText(String.valueOf(precio[pos]));
            JOptionPane.showMessageDialog(null, "Producto encontrado!");
        } else {
            JOptionPane.showMessageDialog(null, "Producto no encontrado!");
            Nombre_Producto_2.setText("");
            Categoria_2.setText("");
            Cantidad_2.setText("");
            Precio_2.setText("");
        }

    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Error al buscar producto: " + e.getMessage());
    }
}


    public static void cargarDatosEnTabla(JTable tabla) {
        DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
        modelo.setRowCount(0);

        for (int i = 0; i < contadorProductos; i++) {
            modelo.addRow(new Object[]{
                idProducto[i],
                nombre[i],
                categoria[i],
                cantidad[i],
                precio[i]
            });
        }
    }

    public static void eliminarProducto(JTextField ID_Producto_3, JTable tabla,
                                    JTextField ID, JTextField Nombre_Producto_3,
                                    JTextField Categoria_3) {
    try {
        String idEliminar = ID_Producto_3.getText().trim();
        boolean eliminado = false;

        for (int i = 0; i < contadorProductos; i++) {
            if (idProducto[i].equalsIgnoreCase(idEliminar)) {
                
                for (int j = i; j < contadorProductos - 1; j++) {
                    idProducto[j] = idProducto[j + 1];
                    nombre[j] = nombre[j + 1];
                    categoria[j] = categoria[j + 1];
                    precio[j] = precio[j + 1];
                    cantidad[j] = cantidad[j + 1];
                }

                contadorProductos--;
                eliminado = true;
                break;
            }
        }

        if (eliminado) {
            cargarDatosEnTabla(tabla);
            JOptionPane.showMessageDialog(null, "Producto eliminado correctamente.");

            ID_Producto_3.setText("");
            ID.setText("");
            Nombre_Producto_3.setText("");
            Categoria_3.setText("");
        } else {
            JOptionPane.showMessageDialog(null, "Producto no encontrado.");
        }

    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Error al eliminar el producto: " + e.getMessage());
    }
}

    public static void buscarProductoEliminar(JTextField ID_Producto_3,
                                          JTextField ID,
                                          JTextField Nombre_Producto_3,
                                          JTextField Categoria_3) {

    try {
        String idBuscado = ID_Producto_3.getText().trim();
        boolean encontrado = false;

        for (int i = 0; i < contadorProductos; i++) {
            if (idProducto[i].equalsIgnoreCase(idBuscado)) {
                ID.setText(idProducto[i]);
                Nombre_Producto_3.setText(nombre[i]);
                Categoria_3.setText(categoria[i]);

                JOptionPane.showMessageDialog(null, "Producto encontrado para eliminar.");
                encontrado = true;
                break;
            }
        }

        if (!encontrado) {
            JOptionPane.showMessageDialog(null, "Producto no encontrado.");
        }

    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Error al buscar el producto: " + e.getMessage());
    }
}

    
    public static void actualizarProducto(JTable tabla,
                                      JTextField ID_Producto_2,
                                      JTextField Nombre_Producto_2,
                                      JTextField Precio_2,
                                      JTextField Cantidad_2,
                                      JTextField Categoria_2) {

    String idBuscado = ID_Producto_2.getText().trim();
    boolean encontrado = false;

    for (int i = 0; i < contadorProductos; i++) {
        if (idProducto[i].equalsIgnoreCase(idBuscado)) {
            try {
                
                nombre[i] = Nombre_Producto_2.getText().trim();
                precio[i] = Double.parseDouble(Precio_2.getText().trim());
                cantidad[i] = Integer.parseInt(Cantidad_2.getText().trim());
                categoria[i] = Categoria_2.getText().trim();

                JOptionPane.showMessageDialog(null, "Producto actualizado correctamente.");

                cargarDatosEnTabla(tabla);

                ID_Producto_2.setText("");
                Nombre_Producto_2.setText("");
                Precio_2.setText("");
                Cantidad_2.setText("");
                Categoria_2.setText("");

                encontrado = true;
                break;

            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Error: formato de número inválido.");
                return;
            }
        }
    }

    if (!encontrado) {
        JOptionPane.showMessageDialog(null, "No se encontró ningún producto con ese ID.");
    }
}

    public static void cargarProductoSeleccionado(JTable tabla,
                                                  JTextField ID_Producto,
                                                  JTextField Name_Producto,
                                                  JTextField Precio,
                                                  JTextField Cantidad,
                                                  JTextField Categoria) {

        int fila = tabla.getSelectedRow();
        if (fila != -1) {
            ID_Producto.setText(idProducto[fila]);
            Name_Producto.setText(nombre[fila]);
            Precio.setText(String.valueOf(precio[fila]));
            Cantidad.setText(String.valueOf(cantidad[fila]));
            Categoria.setText(categoria[fila]);
        }
    }
}
