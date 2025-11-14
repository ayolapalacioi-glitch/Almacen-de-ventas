package controladores;

import static controladores.Controlador_Producto.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import static vista.Vista_admin.*;

public class ControladorPro {

    public static void enlistarProductoAdmin() {
        DefaultTableModel modelo = (DefaultTableModel) Inventario_table.getModel();
        modelo.setRowCount(0);
        for (int i = 0; i < contadorProductos; i++) {
            modelo.addRow(new Object[]{
                idProducto[i],
                nombre[i],
                precio[i],
                cantidad[i],
                categoria[i]

            });
        }
    }

    public static void eliminarId(int indice) {
        if (indice >= 0 && indice < contadorProductos) {
            for (int j = 0; j < contadorProductos - 1; j++) {
                idProducto[j] = idProducto[j + 1];
                nombre[j] = nombre[j + 1];
                categoria[j] = categoria[j + 1];
                precio[j] = precio[j + 1];
                cantidad[j] = cantidad[j + 1];
            }

            idProducto[contadorProductos - 1] = null;
            contadorProductos--;
        }
    }

    public static void sumarContador() {
        String contador = String.valueOf(contadorProductos);
        txt_contProducto.setText(contador);

    }

    public static void EditarPRoducto(int indice, String nombres, String categorias, String Cantidades, String Precios) {
        if (Cantidades.matches("\\d+") && Precios.matches("\\d+")) {

            if (!nombres.trim().isEmpty()) {
                nombre[indice] = nombres.trim();
            }
            if (!categorias.trim().isEmpty()) {
                categoria[indice] = categorias.trim();
            }
            if (!Cantidades.trim().isEmpty()) {
                int cat = Integer.parseInt(Cantidades);
                cantidad[indice] = cat;
            }
            if (!Precios.trim().isEmpty()) {
                double pre = Double.parseDouble(Precios);
                precio[indice] = pre;
            }
            JOptionPane.showMessageDialog(null,"Usuario modificado con exito");
        } else {
            JOptionPane.showMessageDialog(null, "Solo se aceptan numeros en las casillas Precio y Cantidad");
        }
    }
}
