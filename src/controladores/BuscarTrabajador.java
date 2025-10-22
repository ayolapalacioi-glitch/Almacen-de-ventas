
package controladores;

import javax.swing.table.DefaultTableModel;
import static modelos.Trabajador.*;
import static vista.Vista_admin.tabla_traba;


public class BuscarTrabajador {
    public static void buscar(String text){
        for (int i = 0; i < contTrabajador; i++) {
            if (trabajador[i].nombre.equals(text)||trabajador[i].id.equals(text)) {
                DefaultTableModel modelo = (DefaultTableModel) tabla_traba.getModel();
                modelo.setRowCount(0);
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
}
