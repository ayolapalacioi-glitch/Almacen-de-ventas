
package proaula;
import static controladores.controlador_Registro.registrarUsuario;
import vista.*;
public class ProAula {
 
   
    public static void main(String[] args) {
     Login pl = new Login();
     pl.setVisible(true);
     registrarUsuario("123","admin@gmail.com","admin");
    }
    
}
