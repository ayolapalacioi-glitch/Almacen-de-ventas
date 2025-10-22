
package controladores;

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
}
