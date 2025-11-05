
package controladores;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.*;


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
        
        // Guardar el número de compra en el arreglo
        guardarNumeroCompra(numeroFactura);
        
       
        double subtotal = calcularSubtotalTabla(tabla);
        
      
        double valorIVA = calcularIVA(subtotal);
        
       
        double total = subtotal + valorIVA;
        V_Total.setText(String.format("%.2f", total));
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
        
       
        int filas = tabla.getRowCount();
        
       
        for (int i = 0; i < filas; i++) {
           
            Object valor = tabla.getValueAt(i, tabla.getColumnCount() - 1);
            
            if (valor != null) {
                try {
                    
                    subtotal += Double.parseDouble(valor.toString());
                } catch (NumberFormatException e) {
                    System.err.println("Error al convertir valor en fila " + i);
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
        
       
        if (input == null) {
            return false;
        }
        
        
        try {
            double montoPagado = Double.parseDouble(input);
            
           
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
  
}
