
package controladores;

import javax.swing.table.DefaultTableModel;
import static modelos.Trabajador.*;
import static vista.Vista_admin.tabla_traba;



public class ListarTrabajadores {
    public static void cargartrabajadores(){
        DefaultTableModel modelo = (DefaultTableModel) tabla_traba.getModel();
        modelo.setRowCount(0);
        for (int i = 0; i < contTrabajador; i++) {
         modelo.addRow(new Object[]{
            trabajador[i].id,
            trabajador[i].nombre,
            trabajador[i].cargo,
            trabajador[i].telefono,
            trabajador[i].email
        });
            
        }
    }
    
}
