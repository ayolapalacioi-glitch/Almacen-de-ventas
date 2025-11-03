
package controladores;


public class controlador_Pago {
  
    private static String[] facturas = new String[1000];
    private static int contadorFacturas = 0;
    
    
    public static void registrarPago(javax.swing.JTextField jTextField1, javax.swing.JTextField jTextField3,
                                      javax.swing.JTextField jTextField5, javax.swing.JTextField jTextField8,
                                      javax.swing.JTextField jTextField9) {
        // Obtener la fecha y hora actual
        java.time.LocalDateTime ahora = java.time.LocalDateTime.now();
        
        // Formatear la fecha y hora
        java.time.format.DateTimeFormatter formato = 
            java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String horaFormateada = ahora.format(formato);
        
        
        contadorFacturas++;
        
        
        String numeroFactura = String.format("FACT-%04d", contadorFacturas);
        
       
        try {
            
            String valorTotalStr = jTextField5.getText().trim();
            if (valorTotalStr.isEmpty()) {
                javax.swing.JOptionPane.showMessageDialog(null, 
                    "No hay un valor total para pagar", 
                    "Error", 
                    javax.swing.JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            double valorTotal = Double.parseDouble(valorTotalStr);
            
           
            String dineroIngresadoStr = jTextField8.getText().trim();
            if (dineroIngresadoStr.isEmpty()) {
                javax.swing.JOptionPane.showMessageDialog(null, 
                    "Por favor, ingrese el dinero recibido", 
                    "Campo vacío", 
                    javax.swing.JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            double dineroIngresado = Double.parseDouble(dineroIngresadoStr);
            
          
            if (dineroIngresado < valorTotal) {
                javax.swing.JOptionPane.showMessageDialog(null, 
                    "El dinero ingresado no es suficiente.\nFaltan: $" + 
                    String.format("%.2f", (valorTotal - dineroIngresado)), 
                    "Dinero insuficiente", 
                    javax.swing.JOptionPane.ERROR_MESSAGE);
                return;
            }
            
           
            double vuelto = dineroIngresado - valorTotal;
            
          
            facturas[contadorFacturas - 1] = numeroFactura + "|" + horaFormateada + "|" + valorTotal;
            
           
            jTextField1.setText(horaFormateada);     
            jTextField3.setText(numeroFactura);        
            jTextField9.setText(String.format("%.2f", vuelto)); 
            
           
            javax.swing.JOptionPane.showMessageDialog(null, 
                "Pago realizado exitosamente\nVuelto: $" + String.format("%.2f", vuelto), 
                "Pago Exitoso", 
                javax.swing.JOptionPane.INFORMATION_MESSAGE);
            
        } catch (NumberFormatException e) {
            javax.swing.JOptionPane.showMessageDialog(null, 
                "Por favor, ingrese valores numéricos válidos", 
                "Error de formato", 
                javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }
    
  
    public static void registrarHoraPago(javax.swing.JTextField jTextField1) {
        java.time.LocalDateTime ahora = java.time.LocalDateTime.now();
        java.time.format.DateTimeFormatter formato = 
            java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String horaFormateada = ahora.format(formato);
        jTextField1.setText(horaFormateada);
    }
    
   
    public static String[] getFacturas() {
        return facturas;
    }
    
   
    public static int getContadorFacturas() {
        return contadorFacturas;
    }
    
   
    public static String getUltimaFactura() {
        if (contadorFacturas > 0) {
            return facturas[contadorFacturas - 1];
        }
        return null;
    }
    
}
