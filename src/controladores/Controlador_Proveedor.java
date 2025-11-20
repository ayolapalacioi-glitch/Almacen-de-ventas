package controladores;

import static controladores.controlador_Registro.*;

public class Controlador_Proveedor {
    
   public static boolean buscarProveedor(
            String emailBuscar,
            javax.swing.JTextField nombre,
            javax.swing.JTextField numero,
            javax.swing.JTextField ciudad
    ) {

        for (int i = 0; i < contadorProveedores; i++) {
            String[] datos = proveedores[i].split("\\|");

            if (datos[1].equalsIgnoreCase(emailBuscar)) {

                nombre.setText(datos[0]);
                numero.setText(datos[3]);
                ciudad.setText(datos[4]);

                return true;
            }
        }
        return false;
    }
    public static boolean buscarProveedorEliminar(
        String emailBuscar,
        javax.swing.JTextField nombre,
        javax.swing.JTextField numero,
        javax.swing.JTextField ciudad
) {

    for (int i = 0; i < contadorProveedores; i++) {

        String[] datos = proveedores[i].split("\\|");

        if (datos[1].equalsIgnoreCase(emailBuscar)) {

            nombre.setText(datos[0]);
            numero.setText(datos[3]);
            ciudad.setText(datos[4]);

            return true;
        }
    }

    return false;
}


    public static boolean actualizarProveedor(
            String emailBuscar,
            String nuevoNombre,
            String nuevoNumero,
            String nuevaCiudad,
            javax.swing.JTable tabla,
            javax.swing.JTextField txtNombre,
            javax.swing.JTextField txtNumero,
            javax.swing.JTextField txtCiudad
    ) {

        if (emailBuscar.isEmpty() || nuevoNombre.isEmpty() || nuevoNumero.isEmpty() || nuevaCiudad.isEmpty()) {
            return false;
        }

        for (int i = 0; i < contadorProveedores; i++) {

            String[] datos = proveedores[i].split("\\|");

            if (datos[1].equalsIgnoreCase(emailBuscar)) {

                proveedores[i] = nuevoNombre + "|" + datos[1] + "|" + nuevoNumero + "|" + nuevaCiudad;

                actualizarTabla(tabla);

                txtNombre.setText("");
                txtNumero.setText("");
                txtCiudad.setText("");

                return true;
            }
        }
        return false;
    }

    public static boolean eliminarProveedor(
            String emailBuscar,
            javax.swing.JTable tabla,
            javax.swing.JTextField txtNombre,
            javax.swing.JTextField txtNumero,
            javax.swing.JTextField txtCiudad
    ) {

        if (emailBuscar.isEmpty()) {
            return false;
        }

        for (int i = 0; i < contadorProveedores; i++) {

            String[] datos = proveedores[i].split("\\|");

            if (datos[1].equalsIgnoreCase(emailBuscar)) {

                for (int j = i; j < contadorProveedores - 1; j++) {
                    proveedores[j] = proveedores[j + 1];
                }

                proveedores[contadorProveedores - 1] = null;
                contadorProveedores--;

                actualizarTabla(tabla);

                txtNombre.setText("");
                txtNumero.setText("");
                txtCiudad.setText("");

                return true;
            }
        }
        return false;
    }

    private static void actualizarTabla(javax.swing.JTable tabla) {

        javax.swing.table.DefaultTableModel modelo =
                (javax.swing.table.DefaultTableModel) tabla.getModel();

        modelo.setRowCount(0);

        for (int j = 0; j < contadorProveedores; j++) {
            if (proveedores[j] != null) {
                String[] fila = proveedores[j].split("\\|");
                modelo.addRow(new Object[]{
                        fila[0], 
                        fila[1],
                        fila[2],
                        fila[3],
                        "Proveedor"
                });
            }
        }
    }

}
