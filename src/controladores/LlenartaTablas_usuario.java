
package controladores;


public class LlenartaTablas_usuario {

    
    
    public void agregarAlCarrito(javax.swing.JTable i, javax.swing.JTable carrito) {
    javax.swing.table.DefaultTableModel modeloI = (javax.swing.table.DefaultTableModel) i.getModel();
    javax.swing.table.DefaultTableModel modeloCarrito = (javax.swing.table.DefaultTableModel) carrito.getModel();
    
    int filaSeleccionada = i.getSelectedRow();
    
    // Validar que haya una fila seleccionada
    if (filaSeleccionada == -1) {
        javax.swing.JOptionPane.showMessageDialog(null, 
            "Por favor, seleccione un producto de la tabla");
        return;
    }
    
    // Columnas del inventario: 0=ID, 1=Nombre, 2=Precio, 3=Stock, 4=Categoria
    int colStock = 3;
    int stockDisponible = Integer.parseInt(modeloI.getValueAt(filaSeleccionada, colStock).toString());
    
    
    if (stockDisponible <= 0) {
        javax.swing.JOptionPane.showMessageDialog(null, 
            "No hay stock disponible para este producto");
        return;
    }
    
    // Obtener datos del producto
    String idProducto = modeloI.getValueAt(filaSeleccionada, 0).toString();
    String nombreProducto = modeloI.getValueAt(filaSeleccionada, 1).toString();
    double precio = Double.parseDouble(modeloI.getValueAt(filaSeleccionada, 2).toString());
    
    // Solicitar la cantidad
    int cantidadIngresada = 0;
    boolean cantidadValida = false;
    
    while (!cantidadValida) {
        String input = javax.swing.JOptionPane.showInputDialog(null,
            "Producto: " + nombreProducto + "\n" +
            "Stock disponible: " + stockDisponible + "\n\n" +
            "Ingrese la cantidad que desea agregar al carrito:",
            "Agregar al Carrito",
            javax.swing.JOptionPane.QUESTION_MESSAGE);
        
        if (input == null) {
            return;
        }
        
        if (input.trim().isEmpty()) {
            javax.swing.JOptionPane.showMessageDialog(null, 
                "Debe ingresar una cantidad",
                "Error",
                javax.swing.JOptionPane.ERROR_MESSAGE);
            continue;
        }
        
        try {
            cantidadIngresada = Integer.parseInt(input.trim());
        } catch (NumberFormatException e) {
            javax.swing.JOptionPane.showMessageDialog(null, 
                "Debe ingresar un número válido",
                "Error",
                javax.swing.JOptionPane.ERROR_MESSAGE);
            continue;
        }
        
        if (cantidadIngresada <= 0) {
            javax.swing.JOptionPane.showMessageDialog(null, 
                "La cantidad debe ser mayor que 0",
                "Error",
                javax.swing.JOptionPane.ERROR_MESSAGE);
            continue;
        }
        
        if (cantidadIngresada > stockDisponible) {
            javax.swing.JOptionPane.showMessageDialog(null, 
                "La cantidad ingresada (" + cantidadIngresada + ") excede el stock disponible (" + stockDisponible + ")",
                "Error",
                javax.swing.JOptionPane.ERROR_MESSAGE);
            continue;
        }
        
        cantidadValida = true;
    }
    
    // Agregar al CARRITO (solo 4 columnas: ID, Nombre, Precio, Cantidad)
    modeloCarrito.addRow(new Object[]{
        idProducto,
        nombreProducto,
        precio,
        cantidadIngresada
    });
    
    // Actualizar el stock en el inventario
    int nuevoStock = stockDisponible - cantidadIngresada;
    modeloI.setValueAt(nuevoStock, filaSeleccionada, colStock);
    
    javax.swing.JOptionPane.showMessageDialog(null, 
        "Producto agregado al carrito exitosamente\n" +
        "Cantidad: " + cantidadIngresada + "\n" +
        "Nuevo stock: " + nuevoStock);
}


public void confirmarPedido(javax.swing.JTable carrito, javax.swing.JTable pedido) {
    javax.swing.table.DefaultTableModel modeloCarrito = (javax.swing.table.DefaultTableModel) carrito.getModel();
    javax.swing.table.DefaultTableModel modeloPedido = (javax.swing.table.DefaultTableModel) pedido.getModel();
    
    if (modeloCarrito.getRowCount() == 0) {
        javax.swing.JOptionPane.showMessageDialog(null, 
            "El carrito está vacío");
        return;
    }
    
    // Mover del carrito al pedido
    while (modeloCarrito.getRowCount() > 0) {
        Object[] filaCarrito = new Object[modeloCarrito.getColumnCount()];
        for (int col = 0; col < modeloCarrito.getColumnCount(); col++) {
            filaCarrito[col] = modeloCarrito.getValueAt(0, col);
        }
        
        modeloPedido.addRow(filaCarrito);
        modeloCarrito.removeRow(0);
    }
    
    javax.swing.JOptionPane.showMessageDialog(null, 
        "Pedido confirmado exitosamente");
}



public void pasarPedidoAFactura(javax.swing.JTable pedido, javax.swing.JTable factura) {
    javax.swing.table.DefaultTableModel modeloPedido = (javax.swing.table.DefaultTableModel) pedido.getModel();
    javax.swing.table.DefaultTableModel modeloFactura = (javax.swing.table.DefaultTableModel) factura.getModel();
    
    int filasPedido = modeloPedido.getRowCount();
    int colsPedido = modeloPedido.getColumnCount();
    int colsFactura = modeloFactura.getColumnCount();
    
    System.out.println("=== DEBUG pasarPedidoAFactura ===");
    System.out.println("Filas en pedido: " + filasPedido);
    System.out.println("Columnas en pedido: " + colsPedido);
    System.out.println("Columnas en factura: " + colsFactura);
    
    if (filasPedido == 0) {
        javax.swing.JOptionPane.showMessageDialog(null, 
            "No hay productos en el pedido");
        return;
    }
    
    // Imprimir nombres de columnas del pedido
    System.out.println("Columnas del pedido:");
    for (int c = 0; c < colsPedido; c++) {
        System.out.println("  Col " + c + ": " + modeloPedido.getColumnName(c));
    }
    

    final double IVA = 0.19;
    int productosAgregados = 0;
    
 
    for (int i = 0; i < filasPedido; i++) {
        try {
            System.out.println("\n--- Procesando fila " + i + " ---");
            
            // Imprimir todos los valores de la fila
            for (int c = 0; c < colsPedido; c++) {
                Object val = modeloPedido.getValueAt(i, c);
                System.out.println("  Col " + c + ": " + (val == null ? "NULL" : val.toString()));
            }
            
         
            Object idObj = modeloPedido.getValueAt(i, 0);
            Object nombreObj = modeloPedido.getValueAt(i, 1);
            Object precioObj = modeloPedido.getValueAt(i, 2);
            Object cantidadObj = modeloPedido.getValueAt(i, 3);
            
            // Validar que los valores importantes no sean null
            if (idObj == null || nombreObj == null || precioObj == null || cantidadObj == null) {
                System.err.println("Fila " + i + " tiene datos null, omitiendo...");
                continue;
            }
            
            String id = idObj.toString();
            String nombre = nombreObj.toString();
            double precio = Double.parseDouble(precioObj.toString());
            int cantidad = Integer.parseInt(cantidadObj.toString());
            
            System.out.println("Datos leídos: ID=" + id + ", Nombre=" + nombre + ", Precio=" + precio + ", Cant=" + cantidad);
            
          
            double subtotal = cantidad * precio;
            double impuestos = subtotal * IVA;
            double total = subtotal + impuestos;
            
            System.out.println("Calculado: Subtotal=" + subtotal + ", IVA=" + impuestos + ", Total=" + total);
            
         
            Object[] filaFactura = new Object[colsFactura];
            
            filaFactura[0] = id;                                    // Codigo
            filaFactura[1] = nombre;                                // Descripcion
            if (colsFactura > 2) filaFactura[2] = "";              // Columna extra
            filaFactura[3] = cantidad;                              // Cantidad
            filaFactura[4] = precio;                                // Valor
            
         
            if (colsFactura > 5) filaFactura[5] = String.format("%.2f", subtotal);
            if (colsFactura > 6) filaFactura[6] = String.format("%.2f", impuestos);
            if (colsFactura > 7) filaFactura[7] = String.format("%.2f", total);
            
            modeloFactura.addRow(filaFactura);
            productosAgregados++;
            System.out.println("Producto agregado a factura exitosamente");
            
        } catch (NumberFormatException e) {
            System.err.println("Error al convertir valores numéricos en fila " + i + ": " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Error general en fila " + i + ": " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    System.out.println("\n=== Resumen ===");
    System.out.println("Productos agregados a factura: " + productosAgregados);
    
 
    modeloPedido.setRowCount(0);
    
    if (productosAgregados > 0) {
        javax.swing.JOptionPane.showMessageDialog(null, 
            productosAgregados + " producto(s) pasado(s) a la factura con cálculos completados");
    } else {
        javax.swing.JOptionPane.showMessageDialog(null, 
            "No se pudieron pasar productos a la factura. Revise la consola.",
            "Error",
            javax.swing.JOptionPane.ERROR_MESSAGE);
    }
}


public void eliminarDelCarrito(javax.swing.JTable i, javax.swing.JTable carrito) {
    javax.swing.table.DefaultTableModel modeloCarrito = (javax.swing.table.DefaultTableModel) carrito.getModel();
    javax.swing.table.DefaultTableModel modeloI = (javax.swing.table.DefaultTableModel) i.getModel();
    
    int filaSeleccionada = carrito.getSelectedRow();
    
    if (filaSeleccionada == -1) {
        javax.swing.JOptionPane.showMessageDialog(null, 
            "Seleccione un producto del carrito para eliminar");
        return;
    }
    
    // En el CARRITO: columna 3 es la cantidad
    int cantidad = Integer.parseInt(modeloCarrito.getValueAt(filaSeleccionada, 3).toString());
    Object idProducto = modeloCarrito.getValueAt(filaSeleccionada, 0);
    
    // Devolver el stock al inventario
    for (int fila = 0; fila < modeloI.getRowCount(); fila++) {
        if (modeloI.getValueAt(fila, 0).equals(idProducto)) {
            int colStock = 3; // Columna de stock en inventario
            int stockActual = Integer.parseInt(modeloI.getValueAt(fila, colStock).toString());
            modeloI.setValueAt(stockActual + cantidad, fila, colStock);
            break;
        }
    }
    
    modeloCarrito.removeRow(filaSeleccionada);
    
    javax.swing.JOptionPane.showMessageDialog(null, 
        "Producto eliminado del carrito");
}

}

