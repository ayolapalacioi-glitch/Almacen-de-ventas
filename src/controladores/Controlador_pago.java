
package controladores;
import static controladores.Controlador_Producto.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import static vista.Vista_usuario.i;


public class Controlador_pago {
    
   
    public static int[] numerosCompra = new int[1000];
    public static int contadorCompras = 0;
    
    
    public static int contadorFactura = 1;
    
    
    public static final double IVA = 0.19;
    
   
         public static void procesarPago(JTable tabla, JTextField fecha, JTextField factura_n, 
                            JTextField V_Total) {
        
      
        String fechaHora = obtenerFechaActual() + " " + obtenerHoraActual();
        fecha.setText(fechaHora);
        
      
        int numeroFactura = obtenerNumeroFactura();
        factura_n.setText(String.valueOf(numeroFactura));
        
     
        guardarNumeroCompra(numeroFactura);
        
       
        double totalGeneral = calcularTotalDesdeTabla(tabla);
        
       
        String totalFormateado = String.format("%.2f", totalGeneral);
        V_Total.setText(totalFormateado);
        
        // Mostrar en consola para verificar
        System.out.println("Total General: " + totalGeneral);
    }
    
    // Método simplificado que calcula el total directamente
    public static double calcularTotalDesdeTabla(JTable tabla) {
        DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
        int filas = modelo.getRowCount();
        double totalGeneral = 0.0;
        final double IVA = 0.19;
        
        // Si la tabla tiene 7 o menos columnas, calcular sobre la marcha
        for (int i = 0; i < filas; i++) {
            try {
                Object cantidadObj = modelo.getValueAt(i, 3);
                Object valorObj = modelo.getValueAt(i, 4);
                
                if (cantidadObj != null && valorObj != null) {
                    double cantidad = Double.parseDouble(cantidadObj.toString().trim());
                    double valorUnitario = Double.parseDouble(valorObj.toString().trim());
                    
                   
                    double subtotal = cantidad * valorUnitario;
                    
                  
                    double impuestos = subtotal * IVA;
                    
                   
                    double total = subtotal + impuestos;
                    
                    // Si la tabla tiene columnas para mostrar, llenarlas
                    if (modelo.getColumnCount() > 5) {
                        modelo.setValueAt(String.format("%.2f", subtotal), i, 5);
                    }
                    if (modelo.getColumnCount() > 6) {
                        modelo.setValueAt(String.format("%.2f", impuestos), i, 6);
                    }
                   
                    
                    // Sumar al total general
                    totalGeneral += total;
                }
            } catch (Exception e) {
                System.err.println("Error al calcular fila " + i + ": " + e.getMessage());
            }
        }
        
        return totalGeneral;
    }
    
    // Método para calcular las columnas Subtotal, Impuestos y Total por cada fila
    public static void calcularColumnasFactura(JTable tabla) {
        DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
        int filas = modelo.getRowCount();
        int columnas = modelo.getColumnCount();
        
        System.out.println("Calculando para " + filas + " filas y " + columnas + " columnas");
        
        // Columnas: 0=Codigo, 1=Descripcion, 2=?, 3=Cantidad, 4=Valor, 5=Subtotal, 6=Impuestos, 7=Total
        for (int i = 0; i < filas; i++) {
            try {
                // Obtener cantidad (columna 3) y valor unitario (columna 4)
                Object cantidadObj = modelo.getValueAt(i, 3);
                Object valorObj = modelo.getValueAt(i, 4);
                
                if (cantidadObj != null && valorObj != null) {
                    double cantidad = Double.parseDouble(cantidadObj.toString().trim());
                    double valorUnitario = Double.parseDouble(valorObj.toString().trim());
                    
                   
                    double subtotal = cantidad * valorUnitario;
                    
                    
                    double impuestos = subtotal * IVA;
                    
                
                    double total = subtotal + impuestos;
                    
                    // Establecer los valores SOLO si las columnas existen
                    if (columnas > 5) {
                        modelo.setValueAt(String.format("%.2f", subtotal), i, 5);
                    }
                    if (columnas > 6) {
                        modelo.setValueAt(String.format("%.2f", impuestos), i, 6);
                    }
                    if (columnas > 7) {
                        modelo.setValueAt(String.format("%.2f", total), i, 7);
                    }
                }
            } catch (NumberFormatException e) {
                System.err.println("Error al calcular valores en fila " + i + ": " + e.getMessage());
            } catch (ArrayIndexOutOfBoundsException e) {
                System.err.println("Error de índice en fila " + i + ": columnas=" + columnas);
                break;
            }
        }
    }
    
    // Método para calcular el total general sumando la columna Total (columna 7)
    public static double calcularTotalGeneral(JTable tabla) {
        double totalGeneral = 0.0;
        int filas = tabla.getRowCount();
        int columnas = tabla.getColumnCount();
        
        // Si tiene columna 7 (Total), sumarla
        if (columnas > 7) {
            for (int i = 0; i < filas; i++) {
                try {
                    Object totalObj = tabla.getValueAt(i, 7); // Columna 7 = Total
                    
                    if (totalObj != null) {
                        String totalStr = totalObj.toString().trim();
                        if (!totalStr.isEmpty()) {
                            totalGeneral += Double.parseDouble(totalStr);
                        }
                    }
                } catch (NumberFormatException e) {
                    System.err.println("Error al sumar total en fila " + i);
                }
            }
        } else {
            // Si no tiene columna Total, calcular sobre la marcha
            totalGeneral = calcularTotalDesdeTabla(tabla);
        }
        
        return totalGeneral;
    }
    
    public static String obtenerFechaActual() {
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
        return formatoFecha.format(new Date());
    }
   
    public static String obtenerHoraActual() {
        SimpleDateFormat formatoHora = new SimpleDateFormat("HH:mm:ss");
        return formatoHora.format(new Date());
    }
    
    public static int obtenerNumeroFactura() {
        return contadorFactura++;
    }
    
    public static void guardarNumeroCompra(int numeroCompra) {
        if (contadorCompras < numerosCompra.length) {
            numerosCompra[contadorCompras] = numeroCompra;
            contadorCompras++;
        }
    }
    
    public static double calcularSubtotalTabla(JTable tabla) {
        double subtotal = 0.0;
        
        // Obtener número de filas
        int filas = tabla.getRowCount();
        
        // Recorrer todas las filas
        for (int i = 0; i < filas; i++) {
            // Obtener valor de la última columna
            Object valor = tabla.getValueAt(i, tabla.getColumnCount() - 1);
            
            if (valor != null) {
                String valorStr = valor.toString().trim();
             
                if (!valorStr.isEmpty()) {
                    try {
                        // Convertir a double y sumar
                        subtotal += Double.parseDouble(valorStr);
                    } catch (NumberFormatException e) {
                        System.err.println("Error al convertir valor en fila " + i + ": " + valorStr);
                    }
                }
            }
        }
        
        return subtotal;
    }
    
    public static double calcularIVA(double subtotal) {
        return subtotal * IVA;
    }
    
    public static double calcularTotal(double subtotal) {
        return subtotal + calcularIVA(subtotal);
    }
    
    public static int[] obtenerNumerosCompra() {
        return numerosCompra;
    }
    
    public static int obtenerCantidadCompras() {
        return contadorCompras;
    }
    
    public static void reiniciarContadorFacturas() {
        contadorFactura = 1;
    }
    
    public static void limpiarHistorialCompras() {
        for (int i = 0; i < numerosCompra.length; i++) {
            numerosCompra[i] = 0;
        }
        contadorCompras = 0;
    }
    
    public static boolean validarPago(double total) {
        String input = JOptionPane.showInputDialog(null, 
            "Total a pagar: $" + String.format("%.2f", total) + "\n\nIngrese el monto con el que va a pagar:",
            "Procesar Pago",
            JOptionPane.QUESTION_MESSAGE);
        
        // Si el usuario cancela
        if (input == null) {
            return false;
        }
        
        try {
            double montoPagado = Double.parseDouble(input);
            
            // Validar monto positivo
            if (montoPagado <= 0) {
                JOptionPane.showMessageDialog(null,
                    "Por favor ingrese un monto válido mayor a 0",
                    "Monto Inválido",
                    JOptionPane.ERROR_MESSAGE);
                return validarPago(total); 
            }
            
            // Validar si el monto es suficiente
            if (montoPagado < total) {
                JOptionPane.showMessageDialog(null,
                    "Fondos Insuficientes\n\n" +
                    "Total a pagar: $" + String.format("%.2f", total) + "\n" +
                    "Monto ingresado: $" + String.format("%.2f", montoPagado) + "\n" +
                    "Faltan: $" + String.format("%.2f", (total - montoPagado)),
                    "Fondos Insuficientes",
                    JOptionPane.ERROR_MESSAGE);
                return validarPago(total); 
            }
            
            // Pago exitoso
            double cambio = montoPagado - total;
            if (cambio > 0) {
                JOptionPane.showMessageDialog(null,
                    "Pago Exitoso\n\n" +
                    "Total: $" + String.format("%.2f", total) + "\n" +
                    "Pagado: $" + String.format("%.2f", montoPagado) + "\n" +
                    "Cambio: $" + String.format("%.2f", cambio),
                    "Pago Completado",
                    JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null,
                    "Pago Exitoso\n\n" +
                    "Total: $" + String.format("%.2f", total),
                    "Pago Completado",
                    JOptionPane.INFORMATION_MESSAGE);
            }
            return true;
            
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null,
                "Por favor ingrese solo números.\nEjemplo: 50000 o 50000.50",
                "Error de Formato",
                JOptionPane.ERROR_MESSAGE);
            return validarPago(total); 
        }
    }
    
    public static void actualizarTablaIDesdeInventario() {
        DefaultTableModel modeloI = (DefaultTableModel) i.getModel();
        modeloI.setRowCount(0);
        
        for (int j = 0; j < contadorProductos; j++) {
            modeloI.addRow(new Object[]{
                idProducto[j],
                nombre[j],
                precio[j],
                cantidad[j],
                categoria[j]
            });
        }
    }
  
}
