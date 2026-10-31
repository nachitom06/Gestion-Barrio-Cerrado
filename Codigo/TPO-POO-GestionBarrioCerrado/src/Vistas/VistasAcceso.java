package Vistas;

import Clases.Administrador;
import Clases.GestorAcceso;
import Clases.Lote;
import Enumeradores.TipoReclamo;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VistasAcceso extends JFrame{
    private GestorAcceso gestorAcceso;
    private JPanel panelPrincipal;
    private JTextField txtDocumento;
    private JButton btnBuscarAutorizacion;

    public void registrarAcceso(GestorAcceso gestorAcceso){
        String documento=null;
        try {
            documento = txtDocumento.getText();
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this,"ingreso no valido");
        }
        boolean resultado=(gestorAcceso.buscarAutorizacionEnElSistema(documento));
        if (documento!=null){
            if(resultado){
                JOptionPane.showMessageDialog(this,"El ingreso es valido");
            } else {
                JOptionPane.showMessageDialog(this,"NO VALIDO");
            }
        }
    }

    public VistasAcceso(GestorAcceso gestorAcceso){
        this.gestorAcceso=gestorAcceso;
        setContentPane(panelPrincipal);
        setTitle("Formulario de gestión de acceso");
        setSize(600,400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        inicializar();
        btnBuscarAutorizacion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registrarAcceso(gestorAcceso);
            }
        });
    }

    private void inicializar(){
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        GestorAcceso gestorAcceso1=new GestorAcceso();

        SwingUtilities.invokeLater(() -> {
            VistasAcceso ventana = new VistasAcceso(gestorAcceso1);
            ventana.setVisible(true);
        });
    }
}
