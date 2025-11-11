
package controladores;

import javax.swing.JOptionPane;
import modelos.Trabajador;
import static modelos.Trabajador.contTrabajador;
import static modelos.Trabajador.trabajador;
import static vista.Vista_admin.txt_conT;


public class RegistrarTrabajadores {
    public static void registrarTrabajador(String id, String nombre, String cargo, String telefono, String email) {
    Trabajador nuevo = new Trabajador();
    nuevo.id = id;
    nuevo.nombre = nombre;
    nuevo.cargo = cargo;
    nuevo.telefono = telefono;
    nuevo.email = email;

    trabajador[contTrabajador++] = nuevo;
    txt_conT.setText(String.valueOf(contTrabajador));
    }
    public static void eliminarTrabajador(int indice) {
        if (indice >= 0 && indice < contTrabajador) {
            for (int i = indice; i < contTrabajador - 1; i++) {
                trabajador[i] = trabajador[i + 1];
            }
            trabajador[contTrabajador - 1] = null;
            contTrabajador--;
            JOptionPane.showMessageDialog(null, "Usuario Eliminado Con Exito");
        }
    }
}
