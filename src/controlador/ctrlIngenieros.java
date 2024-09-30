package controlador;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;
import modelo.mdlIngenieros; 
import vista.frmIngenierosPantalla; 

public class ctrlIngenieros implements MouseListener, KeyListener {

    private mdlIngenieros modelo;
    private frmIngenierosPantalla vista;

    public ctrlIngenieros(mdlIngenieros modelo, frmIngenierosPantalla vista) {
        this.modelo = modelo;
        this.vista = vista;

        vista.btnGuardar.addMouseListener(this);
        vista.btnEliminar.addMouseListener(this);
        vista.btnActualizar.addMouseListener(this);
        vista.btnLimpiar.addMouseListener(this);
        vista.txtBuscar.addKeyListener(this);
        vista.tbIngenieros.addMouseListener(this);

        modelo.Mostrar(vista.tbIngenieros);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == vista.btnGuardar) {
            if (vista.txtNombreIngeniero.getText().isEmpty() || vista.txtEdadIngeniero.getText().isEmpty() || 
                vista.txtPesoIngeniero.getText().isEmpty() || vista.txtCorreoIngeniero.getText().isEmpty()) {
                JOptionPane.showMessageDialog(vista, "Debes llenar todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                try {
                    modelo.setNombre_ingeniero(vista.txtNombreIngeniero.getText());
                    modelo.setEdad_ingeniero(Integer.parseInt(vista.txtEdadIngeniero.getText()));
                    modelo.setPeso_ingeniero(Double.parseDouble(vista.txtPesoIngeniero.getText()));
                    modelo.setCorreo_ingeniero(vista.txtCorreoIngeniero.getText());
                    modelo.Guardar();
                    modelo.Mostrar(vista.tbIngenieros);
                    modelo.limpiar(vista);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(vista, "La edad y el peso deben ser números", "Error", JOptionPane.WARNING_MESSAGE);
                }
            }
        }

        if (e.getSource() == vista.btnEliminar) {
            if (vista.txtNombreIngeniero.getText().isEmpty()) {
                JOptionPane.showMessageDialog(vista, "Debes seleccionar un registro para eliminar", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                modelo.Eliminar(vista.tbIngenieros);
                modelo.Mostrar(vista.tbIngenieros);
                modelo.limpiar(vista);
            }
        }

        if (e.getSource() == vista.btnActualizar) {
            if (vista.txtNombreIngeniero.getText().isEmpty() || vista.txtEdadIngeniero.getText().isEmpty() || 
                vista.txtPesoIngeniero.getText().isEmpty() || vista.txtCorreoIngeniero.getText().isEmpty()) {
                JOptionPane.showMessageDialog(vista, "Debes seleccionar un registro para actualizar", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                try {
                    modelo.setNombre_ingeniero(vista.txtNombreIngeniero.getText());
                    modelo.setEdad_ingeniero(Integer.parseInt(vista.txtEdadIngeniero.getText()));
                    modelo.setPeso_ingeniero(Double.parseDouble(vista.txtPesoIngeniero.getText()));
                    modelo.setCorreo_ingeniero(vista.txtCorreoIngeniero.getText());
                    modelo.Actualizar(vista.tbIngenieros);
                    modelo.Mostrar(vista.tbIngenieros);
                    modelo.limpiar(vista);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(vista, "La edad y el peso deben ser números", "Error", JOptionPane.WARNING_MESSAGE);
                }
            }
        }

        if (e.getSource() == vista.btnLimpiar) {
            modelo.limpiar(vista);
        }

        if (e.getSource() == vista.tbIngenieros) {
            modelo.cargarDatosTabla(vista);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getSource() == vista.txtBuscar) {
            modelo.Buscar(vista.tbIngenieros, vista.txtBuscar);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {}
}
