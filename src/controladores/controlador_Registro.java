
package controladores;

import modelos.Usuarios;
import static modelos.Usuarios.*;

public class controlador_Registro {
    public static void registrarUsuario(String contraseña, String email, String rol){
        
        Usuarios nuevoUsuario = new Usuarios();
        nuevoUsuario.email= email;
        nuevoUsuario.Rol= rol;
        nuevoUsuario.contraseña= contraseña;
        usuarios[contUsuario++] = nuevoUsuario;
    }
}
