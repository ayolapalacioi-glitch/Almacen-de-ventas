
package controladores;


public class controlador_Pago {
     // Array para guardar las facturas
    private static String[] facturas = new String[1000];
    private static int contadorFacturas = 0;
    
    // Método para registrar la hora exacta del pago y generar número de factura
    public static void registrarPago(javax.swing.JTextField jTextField1, javax.swing.JTextField jTextField3) {
        // Obtener la fecha y hora actual
        java.time.LocalDateTime ahora = java.time.LocalDateTime.now();
        
        // Formatear la fecha y hora
        java.time.format.DateTimeFormatter formato = 
            java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String horaFormateada = ahora.format(formato);
        
        // Incrementar contador de facturas
        contadorFacturas++;
        
        // Generar número de factura (formato: FACT-0001, FACT-0002, etc.)
        String numeroFactura = String.format("FACT-%04d", contadorFacturas);
        
        // Guardar en el array
        facturas[contadorFacturas - 1] = numeroFactura + "|" + horaFormateada;
        
        // Mostrar en los JTextField
        jTextField1.setText(horaFormateada);      // Hora
        jTextField3.setText(numeroFactura);        // Número de factura
    }
    
    // Método alternativo: solo registrar la hora
    public static void registrarHoraPago(javax.swing.JTextField jTextField1) {
        java.time.LocalDateTime ahora = java.time.LocalDateTime.now();
        java.time.format.DateTimeFormatter formato = 
            java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String horaFormateada = ahora.format(formato);
        jTextField1.setText(horaFormateada);
    }
    
    // Obtener el array de facturas
    public static String[] getFacturas() {
        return facturas;
    }
    
    // Obtener el contador de facturas
    public static int getContadorFacturas() {
        return contadorFacturas;
    }
    
    // Obtener la última factura registrada
    public static String getUltimaFactura() {
        if (contadorFacturas > 0) {
            return facturas[contadorFacturas - 1];
        }
        return null;
    }
    
}
