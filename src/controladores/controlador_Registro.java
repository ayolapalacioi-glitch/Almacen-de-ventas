
package controladores;

import javax.swing.*;
import vista.Vista_usuario;


public class controlador_Registro {
  private static String[] admins = new String[50];
private static String[] proveedores = new String[50];
private static String[] usuarios = new String[50];


private static int contadorAdmins = 0;
private static int contadorProveedores = 0;
private static int contadorUsuarios = 0;


public static void registrarUsuario(JTextField txt_registro_nombre, JTextField txt_registro_correo,
                                   JTextField txt_registro_numero, JPasswordField txt_registro_contraseña, 
                                   JComboBox<String> cmb_Rol, JTextField txt_Ciudad_Registro, 
                                   javax.swing.JTable tablaUsuarios) {
    
    String nombre = txt_registro_nombre.getText().trim();
    String email = txt_registro_correo.getText().trim();
    String password = new String(txt_registro_contraseña.getPassword()).trim();
    String rol = (String) cmb_Rol.getSelectedItem();
    String numero = txt_registro_numero.getText().trim();
    String ciudad = txt_Ciudad_Registro.getText().trim();
    

    if (nombre.isEmpty() || email.isEmpty() || password.isEmpty() || numero.isEmpty() || ciudad.isEmpty()) {
        JOptionPane.showMessageDialog(null, 
            "Por favor, complete todos los campos", 
            "Campos Vacíos", 
            JOptionPane.WARNING_MESSAGE);
        return;
    }
    

    if (rol == null || rol.equals("Seleccionar") || rol.isEmpty()) {
        JOptionPane.showMessageDialog(null, 
            "Por favor, seleccione un rol válido", 
            "Rol No Seleccionado", 
            JOptionPane.WARNING_MESSAGE);
        return;
    }
    
  
    String datosUsuario = nombre + "|" + email + "|" + password + "|" + numero + "|" + ciudad;

    if (usuarioExiste(email)) {
        JOptionPane.showMessageDialog(null, 
            "El email ya está registrado", 
            "Usuario Existente", 
            JOptionPane.ERROR_MESSAGE);
        return;
    }

    if (numeroExiste(numero)) {
        JOptionPane.showMessageDialog(null, 
            "El número ya está registrado", 
            "Número Existente", 
            JOptionPane.ERROR_MESSAGE);
        return;
    }
    
 
    boolean registroExitoso = false;
    
    switch (rol) {
        case "Admin":
            if (contadorAdmins < admins.length) {
                admins[contadorAdmins] = datosUsuario;
                contadorAdmins++;
                registroExitoso = true;
                mostrarMensajeExito("Administrador");
            } else {
                mostrarMensajeCapacidad();
            }
            break;
            
        case "Proveedor":
            if (contadorProveedores < proveedores.length) {
                proveedores[contadorProveedores] = datosUsuario;
                contadorProveedores++;
                registroExitoso = true;
                mostrarMensajeExito("Proveedor");
            } else {
                mostrarMensajeCapacidad();
            }
            break;
            
        case "Usuario":
            if (contadorUsuarios < usuarios.length) {
                usuarios[contadorUsuarios] = datosUsuario;
                contadorUsuarios++;
                registroExitoso = true;
                mostrarMensajeExito("Usuario");
            } else {
                mostrarMensajeCapacidad();
            }
            break;
    }
    

    if (registroExitoso) {
        limpiarCampos(txt_registro_nombre, txt_registro_correo, txt_registro_contraseña, 
                     cmb_Rol, txt_registro_numero, txt_Ciudad_Registro);
        

        if (tablaUsuarios != null) {
            cargarDatosEnTabla(tablaUsuarios);
        }
    }
}


private static boolean usuarioExiste(String email) {

    for (int i = 0; i < contadorAdmins; i++) {
        String[] datos = admins[i].split("\\|");
        if (datos[1].equals(email)) {
            return true;
        }
    }

    for (int i = 0; i < contadorProveedores; i++) {
        String[] datos = proveedores[i].split("\\|");
        if (datos[1].equals(email)) {
            return true;
        }
    }

    for (int i = 0; i < contadorUsuarios; i++) {
        String[] datos = usuarios[i].split("\\|");
        if (datos[1].equals(email)) {
            return true;
        }
    }
    
    return false;
}


private static boolean numeroExiste(String numero) {
  
    for (int i = 0; i < contadorAdmins; i++) {
        String[] datos = admins[i].split("\\|");
        if (datos[3].equals(numero)) {
            return true;
        }
    }
    

    for (int i = 0; i < contadorProveedores; i++) {
        String[] datos = proveedores[i].split("\\|");
        if (datos[3].equals(numero)) {
            return true;
        }
    }
    

    for (int i = 0; i < contadorUsuarios; i++) {
        String[] datos = usuarios[i].split("\\|");
        if (datos[3].equals(numero)) {
            return true;
        }
    }
    
    return false;
}


private static void mostrarMensajeExito(String rol) {
    JOptionPane.showMessageDialog(null, 
        rol + " registrado exitosamente", 
        "Registro Exitoso", 
        JOptionPane.INFORMATION_MESSAGE);
}


private static void mostrarMensajeCapacidad() {
    JOptionPane.showMessageDialog(null, 
        "No hay espacio disponible para más registros de este tipo", 
        "Capacidad Máxima", 
        JOptionPane.ERROR_MESSAGE);
}

private static void limpiarCampos(JTextField txtNombre, JTextField txtEmail, 
                                 JPasswordField txtPassword, JComboBox<String> comboRol,
                                 JTextField txtNumero, JTextField txtCiudad) {
    txtNombre.setText("");
    txtEmail.setText("");
    txtPassword.setText("");
    txtNumero.setText("");
    txtCiudad.setText("");
    comboRol.setSelectedIndex(0);
}


public static String[] getAdmins() {
    return admins;
}

public static String[] getProveedores() {
    return proveedores;
}

public static String[] getUsuarios() {
    return usuarios;
}

public static int getContadorAdmins() {
    return contadorAdmins;
}

public static int getContadorProveedores() {
    return contadorProveedores;
}

public static int getContadorUsuarios() {
    return contadorUsuarios;
}


public static String validarLogin(String email, String password) {

    for (int i = 0; i < contadorAdmins; i++) {
        String[] datos = admins[i].split("\\|");
        if (datos[1].equals(email) && datos[2].equals(password)) {
            return "Admin";
        }
    }
    

    for (int i = 0; i < contadorProveedores; i++) {
        String[] datos = proveedores[i].split("\\|");
        if (datos[1].equals(email) && datos[2].equals(password)) {
            return "Proveedor";
        }
    }
    

    for (int i = 0; i < contadorUsuarios; i++) {
        String[] datos = usuarios[i].split("\\|");
        if (datos[1].equals(email) && datos[2].equals(password)) {
            return "Usuario";
        }
    }
    
    return null; 
}

// NUEVO MÉTODO: Valida el login y retorna el rol y el email
public static String[] validarLoginCompleto(String email, String password) {
    // Buscar en admins
    for (int i = 0; i < contadorAdmins; i++) {
        String[] datos = admins[i].split("\\|");
        if (datos[1].equals(email) && datos[2].equals(password)) {
            return new String[]{"Admin", email};
        }
    }
    
    // Buscar en proveedores
    for (int i = 0; i < contadorProveedores; i++) {
        String[] datos = proveedores[i].split("\\|");
        if (datos[1].equals(email) && datos[2].equals(password)) {
            return new String[]{"Proveedor", email};
        }
    }
    
    // Buscar en usuarios
    for (int i = 0; i < contadorUsuarios; i++) {
        String[] datos = usuarios[i].split("\\|");
        if (datos[1].equals(email) && datos[2].equals(password)) {
            return new String[]{"Usuario", email};
        }
    }
    
    return null;
}

// NUEVO MÉTODO: Obtiene todos los datos de un usuario por su email
public static String[] obtenerDatosUsuario(String email) {
    // Buscar en admins
    for (int i = 0; i < contadorAdmins; i++) {
        String[] datos = admins[i].split("\\|");
        if (datos[1].equals(email)) {
            return datos; // Retorna [nombre, email, password, numero, ciudad]
        }
    }
    
    // Buscar en proveedores
    for (int i = 0; i < contadorProveedores; i++) {
        String[] datos = proveedores[i].split("\\|");
        if (datos[1].equals(email)) {
            return datos;
        }
    }
    
    // Buscar en usuarios
    for (int i = 0; i < contadorUsuarios; i++) {
        String[] datos = usuarios[i].split("\\|");
        if (datos[1].equals(email)) {
            return datos;
        }
    }
    
    return null; // Si no encuentra el usuario
}

// Variable estática para guardar el email del usuario logueado
private static String emailUsuarioActual = "";

// Método para guardar el email cuando el usuario hace login
public static void setEmailUsuarioActual(String email) {
    emailUsuarioActual = email;
}

// NUEVO MÉTODO: Carga los datos del usuario logueado en los JTextField
public static void cargarDatosUsuarioEnTextFields(javax.swing.JTextField jTextField2, 
                                                   javax.swing.JTextField jTextField4, 
                                                   javax.swing.JTextField jTextField6, 
                                                   javax.swing.JTextField jTextField7) {
    String[] datosUsuario = obtenerDatosUsuario(emailUsuarioActual);
    
    if (datosUsuario != null) {
        jTextField2.setText(datosUsuario[0]);    // Nombre
        jTextField4.setText(datosUsuario[1]);     // Email
        jTextField6.setText(datosUsuario[4]);    // Ciudad
        jTextField7.setText(datosUsuario[3]);  // Número
    } else {
        jTextField2.setText("No disponible");
        jTextField4.setText("No disponible");
        jTextField6.setText("No disponible");
        jTextField7.setText("No disponible");
    }
}


// NUEVO MÉTODO: Registra la hora exacta del pago en el JTextField
public static void registrarHoraPago(javax.swing.JTextField txtHoraPago) {
    // Obtener la fecha y hora actual
    java.time.LocalDateTime ahora = java.time.LocalDateTime.now();
    
    // Formatear la fecha y hora (puedes personalizar el formato)
    java.time.format.DateTimeFormatter formato = java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    String horaFormateada = ahora.format(formato);
    
    // Mostrar en el JTextField
    txtHoraPago.setText(horaFormateada);
}

// VERSIÓN ALTERNATIVA: Solo muestra la hora (sin fecha)
public static void registrarSoloHoraPago(javax.swing.JTextField txtHoraPago) {
    java.time.LocalTime ahora = java.time.LocalTime.now();
    java.time.format.DateTimeFormatter formato = java.time.format.DateTimeFormatter.ofPattern("HH:mm:ss");
    String horaFormateada = ahora.format(formato);
    txtHoraPago.setText(horaFormateada);
}


public static void cargarDatosEnTabla(javax.swing.JTable tabla) {
    javax.swing.table.DefaultTableModel modelo = (javax.swing.table.DefaultTableModel) tabla.getModel();
    modelo.setRowCount(0); // Limpiar la tabla
    

    for (int i = 0; i < contadorProveedores; i++) {
        String[] datos = proveedores[i].split("\\|");
        modelo.addRow(new Object[]{
            datos[0],  // Nombre
            datos[1],  // Email
            datos[3],  // Número
            datos[4],  // Ciudad
            "Proveedor" // Rol
        });
    }
    

    for (int i = 0; i < contadorUsuarios; i++) {
        String[] datos = usuarios[i].split("\\|");
        modelo.addRow(new Object[]{
            datos[0],  // Nombre
            datos[1],  // Email
            datos[3],  // Número
            datos[4],  // Ciudad
            "Usuario"  // Rol
        });
    }
}
    }
    
    

