package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import vista.frmIngenierosPantalla;

public class mdlIngenieros {
    
    private String nombre_ingeniero;
    private int edad_ingeniero;
    private double peso_ingeniero;
    private String correo_ingeniero;

    // Getters y Setters
    public String getNombre_ingeniero() {
        return nombre_ingeniero;
    }

    public void setNombre_ingeniero(String nombre_ingeniero) {
        this.nombre_ingeniero = nombre_ingeniero;
    }

    public int getEdad_ingeniero() {
        return edad_ingeniero;
    }

    public void setEdad_ingeniero(int edad_ingeniero) {
        this.edad_ingeniero = edad_ingeniero;
    }

    public double getPeso_ingeniero() {
        return peso_ingeniero;
    }

    public void setPeso_ingeniero(double peso_ingeniero) {
        this.peso_ingeniero = peso_ingeniero;
    }

    public String getCorreo_ingeniero() {
        return correo_ingeniero;
    }

    public void setCorreo_ingeniero(String correo_ingeniero) {
        this.correo_ingeniero = correo_ingeniero;
    }

    // Método para guardar ingeniero
   public void Guardar() {
    Connection conexion = ClaseConexion.getConexion();
    if (conexion != null) {
        try {
            String sql = "INSERT INTO tbIngeniero(UUID_Ingeniero, Nombre_Ingeniero, Edad_Ingeniero, Peso_Ingeniero, Correo_Ingeniero) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement pstmt = conexion.prepareStatement(sql);
            pstmt.setString(1, UUID.randomUUID().toString());
            pstmt.setString(2, getNombre_ingeniero());
            pstmt.setInt(3, getEdad_ingeniero());
            pstmt.setDouble(4, getPeso_ingeniero());
            pstmt.setString(5, getCorreo_ingeniero());
            pstmt.executeUpdate();
            System.out.println("Registro insertado con éxito.");
        } catch (SQLException ex) {
            System.out.println("Error en el método Guardar: " + ex);
        } finally {
            try {
                conexion.close(); // Cerrar la conexión después de la operación
            } catch (SQLException e) {
                System.out.println("Error al cerrar la conexión: " + e.getMessage());
            }
        }
    } else {
        System.out.println("Conexión a la base de datos es nula.");
    }
}

    // Método para mostrar ingenieros en una JTable
   public void Mostrar(JTable tabla) {
    Connection conexion = ClaseConexion.getConexion();
    DefaultTableModel modelo = new DefaultTableModel();
    modelo.setColumnIdentifiers(new Object[]{"UUID_Ingeniero", "Nombre_Ingeniero", "Edad_Ingeniero", "Peso_Ingeniero", "Correo_Ingeniero"});
    
    if (conexion != null) {
        try {
            String query = "SELECT * FROM tbIngeniero";
            Statement statement = conexion.createStatement();
            ResultSet rs = statement.executeQuery(query);
            
            while (rs.next()) {
                modelo.addRow(new Object[]{
                    rs.getString("UUID_Ingeniero"), 
                    rs.getString("Nombre_Ingeniero"), 
                    rs.getInt("Edad_Ingeniero"), 
                    rs.getDouble("Peso_Ingeniero"), 
                    rs.getString("Correo_Ingeniero")
                });
            }
            tabla.setModel(modelo);
        } catch (SQLException e) {
            System.out.println("Error en el método Mostrar: " + e);
        } finally {
            try {
                conexion.close(); // Cerrar la conexión después de la operación
            } catch (SQLException e) {
                System.out.println("Error al cerrar la conexión: " + e.getMessage());
            }
        }
    } else {
        System.out.println("Conexión a la base de datos es nula.");
    }
}
    
    // Método para eliminar ingeniero
    public void Eliminar(JTable tabla) {
        Connection conexion = ClaseConexion.getConexion();
        int filaSeleccionada = tabla.getSelectedRow();

        if (filaSeleccionada != -1) {
            String miId = tabla.getValueAt(filaSeleccionada, 0).toString();
            try {
                String sql = "DELETE FROM tbIngeniero WHERE UUID_Ingeniero = ?";
                PreparedStatement deleteEstudiante = conexion.prepareStatement(sql);
                deleteEstudiante.setString(1, miId);
                deleteEstudiante.executeUpdate();
                System.out.println("Registro eliminado con éxito.");
            } catch (SQLException e) {
                System.out.println("Error en el método Eliminar: " + e);
            }
        } else {
            System.out.println("No se ha seleccionado ninguna fila.");
        }
    }
     
    // Método para actualizar ingeniero
    public void Actualizar(JTable tabla) {
        Connection conexion = ClaseConexion.getConexion();
        int filaSeleccionada = tabla.getSelectedRow();

        if (filaSeleccionada != -1) {
            String miUUId = tabla.getValueAt(filaSeleccionada, 0).toString();
            try {
                String sql = "UPDATE tbIngeniero SET Nombre_Ingeniero = ?, Edad_Ingeniero = ?, Peso_Ingeniero = ?, Correo_Ingeniero = ? WHERE UUID_Ingeniero = ?";
                PreparedStatement updateIngeniero = conexion.prepareStatement(sql);
                updateIngeniero.setString(1, getNombre_ingeniero());
                updateIngeniero.setInt(2, getEdad_ingeniero());
                updateIngeniero.setDouble(3, getPeso_ingeniero());
                updateIngeniero.setString(4, getCorreo_ingeniero());
                updateIngeniero.setString(5, miUUId);
                updateIngeniero.executeUpdate();
                System.out.println("Registro actualizado con éxito.");

            } catch (SQLException e) {
                System.out.println("Error en el método Actualizar: " + e);
            }
        } else {
            System.out.println("No se ha seleccionado ninguna fila.");
        }
    }
     
    // Método para buscar ingenieros
    public void Buscar(JTable tabla, JTextField miTextField) {
        Connection conexion = ClaseConexion.getConexion();
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.setColumnIdentifiers(new Object[]{"UUID_Ingeniero", "Nombre_Ingeniero", "Edad_Ingeniero", "Peso_Ingeniero", "Correo_Ingeniero"});
        
        try {
            String sql = "SELECT * FROM tbIngeniero WHERE Nombre_Ingeniero LIKE ?";
            PreparedStatement pstmt = conexion.prepareStatement(sql);
            pstmt.setString(1, miTextField.getText() + "%");
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                modelo.addRow(new Object[]{
                    rs.getString("UUID_Ingeniero"), 
                    rs.getString("Nombre_Ingeniero"), 
                    rs.getInt("Edad_Ingeniero"), 
                    rs.getDouble("Peso_Ingeniero"), 
                    rs.getString("Correo_Ingeniero")
                });
            }
            tabla.setModel(modelo);
            tabla.getColumnModel().getColumn(0).setMinWidth(0);
            tabla.getColumnModel().getColumn(0).setMaxWidth(0);
            tabla.getColumnModel().getColumn(0).setWidth(0);
            
        } catch (SQLException e) {
            System.out.println("Error en el método Buscar: " + e);
        }
    }
    
    
    
     public void limpiar(frmIngenierosPantalla vista) {
        vista.txtNombreIngeniero.setText("");
        vista.txtEdadIngeniero.setText("");
        vista.txtPesoIngeniero.setText("");
        vista.txtCorreoIngeniero.setText("");

    }

    public void cargarDatosTabla(frmIngenierosPantalla vista) {
        // Obtén la fila seleccionada 
        int filaSeleccionada = vista.tbIngenieros.getSelectedRow();

        // Debemos asegurarnos que haya una fila seleccionada antes de acceder a sus valores
        if (filaSeleccionada != -1) {
            String UUID_Ingeniero = vista.tbIngenieros.getValueAt(filaSeleccionada, 0).toString();
            String Nombre = vista.tbIngenieros.getValueAt(filaSeleccionada, 1).toString();
            String Edad = vista.tbIngenieros.getValueAt(filaSeleccionada, 2).toString();
            String Peso = vista.tbIngenieros.getValueAt(filaSeleccionada, 3).toString();
            String Correo = vista.tbIngenieros.getValueAt(filaSeleccionada, 4).toString();

            // Establece los valores en los campos de texto
            vista.txtNombreIngeniero.setText(Nombre);
            vista.txtEdadIngeniero.setText(Edad);
            vista.txtPesoIngeniero.setText(Peso);
            vista.txtCorreoIngeniero.setText(Correo);

        }
    }  
}
