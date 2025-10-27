
package modelos;


public class Usuarios {
    public String email;
    public String contraseña;
    public String Ciudad;
    public int Numero ;
    public String Rol;
    public String Nombre;

    public String getNombre() {
        return Nombre;
    }

    public Usuarios(String email, String contraseña, String Ciudad, int Numero, String Rol, String Nombre) {
        this.email = email;
        this.contraseña = contraseña;
        this.Ciudad = Ciudad;
        this.Numero = Numero;
        this.Rol = Rol;
        this.Nombre = Nombre;
    }

    public void setNombre(String nombre) {
        this.Nombre = nombre;
    }
    
    public static Usuarios [] usuarios = new Usuarios [100];
    public static int contUsuario = 0;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getCiudad() {
        return Ciudad;
    }

    public void setCiudad(String Ciudad) {
        this.Ciudad = Ciudad;
    }

    public int getNumero() {
        return Numero;
    }

    public void setNumero(int Numero) {
        this.Numero = Numero;
    }

    public String getRol() {
        return Rol;
    }

    public void setRol(String Rol) {
        this.Rol = Rol;
    }

    public static Usuarios[] getUsuarios() {
        return usuarios;
    }

    public static void setUsuarios(Usuarios[] usuarios) {
        Usuarios.usuarios = usuarios;
    }

    public static int getContUsuario() {
        return contUsuario;
    }

    public static void setContUsuario(int contUsuario) {
        Usuarios.contUsuario = contUsuario;
    }
    
 
    
    
}
