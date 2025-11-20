package controladores;

public class AutenticacionRegis {
    public static boolean authEmail(String email){
        return email.endsWith("@gmail.com");
    }
    public static boolean authTelefono(String telefono){
        return telefono.matches("\\d{10}");
    }
}
