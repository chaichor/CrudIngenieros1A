package main;

import com.formdev.flatlaf.FlatLightLaf;
import vista.frmIngenierosPantalla;

public class main {

    public static void main(String[] args) {
        FlatLightLaf.install();

        java.awt.EventQueue.invokeLater(() -> {
            new frmIngenierosPantalla().setVisible(true); 
        });
    }
}

