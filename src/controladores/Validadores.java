
package controladores;

import javax.swing.*;
public class Validadores {
      /**
     * Valida que el texto contenga solo números y muestra mensaje de error
     * @param texto El texto a validar
     * @param campo Nombre del campo para el mensaje de error
     * @param componente El JTextField para limpiar y enfocar
     * @return true si solo contiene números, false si contiene letras
     */
    public static boolean soloNumeros(String texto, String campo, javax.swing.JTextField componente) {
        if (texto == null || texto.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, 
                campo + " no puede estar vacío", 
                "Error de Validación", 
                JOptionPane.ERROR_MESSAGE);
            componente.setText("");
            componente.requestFocus();
            return false;
        }
        if (!texto.matches("\\d+")) {
            JOptionPane.showMessageDialog(null, 
                campo + " debe contener solo números", 
                "Error de Validación", 
                JOptionPane.ERROR_MESSAGE);
            componente.setText("");
            componente.requestFocus();
            return false;
        }
        return true;
    }
    
    /**
     * Valida que el número de teléfono tenga máximo 10 dígitos y solo números, muestra mensaje de error
     * @param telefono El número de teléfono a validar
     * @param campo Nombre del campo para el mensaje de error
     * @param componente El JTextField para limpiar y enfocar
     * @return true si es válido (máximo 10 dígitos, solo números), false en caso contrario
     */
    public static boolean validarTelefono(String telefono, String campo, javax.swing.JTextField componente) {
        if (telefono == null || telefono.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, 
                campo + " no puede estar vacío", 
                "Error de Validación", 
                JOptionPane.ERROR_MESSAGE);
            componente.setText("");
            componente.requestFocus();
            return false;
        }
        // Valida que solo contenga números y tenga máximo 10 dígitos
        if (!telefono.matches("\\d{1,10}")) {
            JOptionPane.showMessageDialog(null, 
                campo + " debe tener máximo 10 dígitos y solo números", 
                "Error de Validación", 
                JOptionPane.ERROR_MESSAGE);
            componente.setText("");
            componente.requestFocus();
            return false;
        }
        return true;
    }
    
    /**
     * Valida que el texto contenga solo letras (con espacios permitidos) y tenga un orden coherente
     * No permite números ni caracteres especiales, muestra mensaje de error
     * @param texto El texto a validar
     * @param campo Nombre del campo para el mensaje de error
     * @param componente El JTextField para limpiar y enfocar
     * @return true si es válido (solo letras y espacios), false en caso contrario
     */
    public static boolean soloLetrasCoherente(String texto, String campo, javax.swing.JTextField componente) {
        if (texto == null || texto.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, 
                campo + " no puede estar vacío", 
                "Error de Validación", 
                JOptionPane.ERROR_MESSAGE);
            componente.setText("");
            componente.requestFocus();
            return false;
        }
        // Valida que solo contenga letras (incluyendo acentos) y espacios
        // No permite números ni caracteres especiales
        if (!texto.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+")) {
            JOptionPane.showMessageDialog(null, 
                campo + " debe contener solo letras sin números ni caracteres especiales", 
                "Error de Validación", 
                JOptionPane.ERROR_MESSAGE);
            componente.setText("");
            componente.requestFocus();
            return false;
        }
        return true;
    }
    
    /**
     * Método alternativo para validar nombres/texto con validaciones adicionales de coherencia
     * Muestra mensajes de error específicos
     * @param texto El texto a validar
     * @param campo Nombre del campo para el mensaje de error
     * @param componente El JTextField para limpiar y enfocar
     * @return true si es válido, false en caso contrario
     */
    public static boolean validarTextoCoherente(String texto, String campo, javax.swing.JTextField componente) {
        if (texto == null || texto.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, 
                campo + " no puede estar vacío", 
                "Error de Validación", 
                JOptionPane.ERROR_MESSAGE);
            componente.setText("");
            componente.requestFocus();
            return false;
        }
        
        String textoLimpio = texto.trim();
        
        // Valida que solo contenga letras y espacios
        if (!textoLimpio.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+")) {
            JOptionPane.showMessageDialog(null, 
                campo + " debe contener solo letras sin números ni caracteres especiales", 
                "Error de Validación", 
                JOptionPane.ERROR_MESSAGE);
            componente.setText("");
            componente.requestFocus();
            return false;
        }
        
        // Valida que no tenga múltiples espacios consecutivos
        if (textoLimpio.matches(".*\\s{2,}.*")) {
            JOptionPane.showMessageDialog(null, 
                campo + " no puede tener espacios múltiples consecutivos", 
                "Error de Validación", 
                JOptionPane.ERROR_MESSAGE);
            componente.setText("");
            componente.requestFocus();
            return false;
        }
        
        // Valida que tenga al menos 2 caracteres (coherencia mínima)
        if (textoLimpio.length() < 2) {
            JOptionPane.showMessageDialog(null, 
                campo + " debe tener al menos 2 caracteres", 
                "Error de Validación", 
                JOptionPane.ERROR_MESSAGE);
            componente.setText("");
            componente.requestFocus();
            return false;
        }
        
        return true;
    }
    
    /**
     * Valida que el campo no esté vacío
     * @param texto El texto a validar
     * @param campo Nombre del campo para el mensaje de error
     * @param componente El JTextField para limpiar y enfocar
     * @return true si no está vacío, false en caso contrario
     */
    public static boolean noVacio(String texto, String campo, javax.swing.JTextField componente) {
        if (texto == null || texto.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, 
                campo + " no puede estar vacío", 
                "Error de Validación", 
                JOptionPane.ERROR_MESSAGE);
            componente.setText("");
            componente.requestFocus();
            return false;
        }
        return true;
    }
     public static boolean validacionamail(String email){
        return email.endsWith("@gmail.com");
    }
}

