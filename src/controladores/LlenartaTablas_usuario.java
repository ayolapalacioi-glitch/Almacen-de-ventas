
package controladores;


public class LlenartaTablas_usuario {

    
    // MÉTODO PARA AGREGAR AL CARRITO 
    // Uso: agregarAlCarrito(tablaInventario, tablaCarrito);
    public void agregarAlCarrito(javax.swing.JTable i, javax.swing.JTable carrito) {
        javax.swing.table.DefaultTableModel modeloI = (javax.swing.table.DefaultTableModel) i.getModel();
        javax.swing.table.DefaultTableModel modeloCarrito = (javax.swing.table.DefaultTableModel) carrito.getModel();
        
        boolean haySeleccion = false;
        
        // Recorrer todas las filas de la tabla inventario
        for (int fila = 0; fila < modeloI.getRowCount(); fila++) {
            // Obtener la cantidad seleccionada (asume que está en la última columna)
            Object cantidadObj = modeloI.getValueAt(fila, modeloI.getColumnCount() - 1);
            int cantidad = 0;
            
            if (cantidadObj != null && !cantidadObj.toString().isEmpty()) {
                try {
                    cantidad = Integer.parseInt(cantidadObj.toString());
                } catch (NumberFormatException e) {
                    continue;
                }
            }
            
            // Si hay cantidad seleccionada, agregar al carrito
            if (cantidad > 0) {
                haySeleccion = true;
                
                // Obtener todos los datos de la fila
                Object[] filaData = new Object[modeloI.getColumnCount()];
                for (int col = 0; col < modeloI.getColumnCount(); col++) {
                    filaData[col] = modeloI.getValueAt(fila, col);
                }
                
                // Agregar al carrito
                modeloCarrito.addRow(filaData);
                
                // Actualizar stock en inventario (asume que stock está en columna antes de cantidad)
                int colStock = modeloI.getColumnCount() - 2;
                int stockActual = Integer.parseInt(modeloI.getValueAt(fila, colStock).toString());
                int nuevoStock = stockActual - cantidad;
                modeloI.setValueAt(nuevoStock, fila, colStock);
                
                // Resetear la cantidad a 0
                modeloI.setValueAt(0, fila, modeloI.getColumnCount() - 1);
            }
        }
        
        if (!haySeleccion) {
            javax.swing.JOptionPane.showMessageDialog(null, 
                "Por favor, ingrese cantidades para agregar al carrito");
        } else {
            javax.swing.JOptionPane.showMessageDialog(null, 
                "Productos agregados al carrito exitosamente");
        }
    }
    
    // MÉTODO PARA CONFIRMAR PEDIDO
    // Uso: confirmarPedido(tablaCarrito, tablaPedido);
    public void confirmarPedido(javax.swing.JTable carrito, javax.swing.JTable pedido) {
        javax.swing.table.DefaultTableModel modeloCarrito = (javax.swing.table.DefaultTableModel) carrito.getModel();
        javax.swing.table.DefaultTableModel modeloPedido = (javax.swing.table.DefaultTableModel) pedido.getModel();
        
        // Verificar que el carrito no esté vacío
        if (modeloCarrito.getRowCount() == 0) {
            javax.swing.JOptionPane.showMessageDialog(null, 
                "El carrito está vacío");
            return;
        }
        
        // Mover todas las filas del carrito al pedido
        while (modeloCarrito.getRowCount() > 0) {
            // Obtener la primera fila
            Object[] filaData = new Object[modeloCarrito.getColumnCount()];
            for (int col = 0; col < modeloCarrito.getColumnCount(); col++) {
                filaData[col] = modeloCarrito.getValueAt(0, col);
            }
            
            // Agregar a pedido
            modeloPedido.addRow(filaData);
            
            // Eliminar del carrito
            modeloCarrito.removeRow(0);
        }
        
        javax.swing.JOptionPane.showMessageDialog(null, 
            "Pedido confirmado exitosamente");
    }
    
    // MÉTODO PARA ELIMINAR DEL CARRITO (OPCIONAL)
    // Uso: eliminarDelCarrito(tablaInventario, tablaCarrito);
    public void eliminarDelCarrito(javax.swing.JTable i, javax.swing.JTable carrito) {
        javax.swing.table.DefaultTableModel modeloCarrito = (javax.swing.table.DefaultTableModel) carrito.getModel();
        javax.swing.table.DefaultTableModel modeloI = (javax.swing.table.DefaultTableModel) i.getModel();
        
        int filaSeleccionada = carrito.getSelectedRow();
        
        if (filaSeleccionada == -1) {
            javax.swing.JOptionPane.showMessageDialog(null, 
                "Seleccione un producto del carrito para eliminar");
            return;
        }
        
        // Obtener cantidad del producto seleccionado
        int cantidad = Integer.parseInt(modeloCarrito.getValueAt(filaSeleccionada, 
            modeloCarrito.getColumnCount() - 1).toString());
        
        // Obtener ID del producto (asume que está en la primera columna)
        Object idProducto = modeloCarrito.getValueAt(filaSeleccionada, 0);
        
        // Devolver al inventario
        for (int fila = 0; fila < modeloI.getRowCount(); fila++) {
            if (modeloI.getValueAt(fila, 0).equals(idProducto)) {
                int colStock = modeloI.getColumnCount() - 2;
                int stockActual = Integer.parseInt(modeloI.getValueAt(fila, colStock).toString());
                modeloI.setValueAt(stockActual + cantidad, fila, colStock);
                break;
            }
        }
        
        // Eliminar del carrito
        modeloCarrito.removeRow(filaSeleccionada);
        
        javax.swing.JOptionPane.showMessageDialog(null, 
            "Producto eliminado del carrito");
    }
}
