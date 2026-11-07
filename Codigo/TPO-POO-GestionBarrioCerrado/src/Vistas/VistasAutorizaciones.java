package Vistas;

import Clases.*;
import Controladores.GestorAutorizaciones;
import Controladores.GestorExpensas;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.List;

public class VistasAutorizaciones extends JFrame{
    private GestorAutorizaciones gestorAutorizaciones;
    private JPanel panelPrincipal;
    private JTextField txtNombre;
    private JTextField txtDocumento;
    private JTextField txtNumeroLote;
    private JTextField txtFranjaHoraria;
    private JButton btnRegistrarAutorizacion;

    public void llamarRegistrarAutorizaciones(){
        try {
            String nombre=txtNombre.getText().trim();
            String documento=txtDocumento.getText().trim();
            int numLote=Integer.parseInt(txtNumeroLote.getText());
            LocalDate fecha=LocalDate.now();
            String franjaHoraria=txtFranjaHoraria.getText();
            boolean realizado=gestorAutorizaciones.registrarAutorizaciones(nombre,documento,numLote,fecha,franjaHoraria);
            if(realizado){
                JOptionPane.showMessageDialog(panelPrincipal,"Se registro la autorizacion");
            } else {
                JOptionPane.showMessageDialog(panelPrincipal,"Error: no se puedo registrar la autorizacion");
            }
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(panelPrincipal,"ingreso no valido");
        }
    }
    public VistasAutorizaciones(GestorAutorizaciones gestorAutorizaciones) {
        this.gestorAutorizaciones=gestorAutorizaciones;
        setContentPane(panelPrincipal);
        setTitle("Formulario de gestión de acceso");
        setSize(600,400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        inicializar();
        btnRegistrarAutorizacion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                llamarRegistrarAutorizaciones();
            }
        });
    }
    private void inicializar(){
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        GestorExpensas gestorExpensas=new GestorExpensas();
        GestorAutorizaciones gestorAutorizaciones=new GestorAutorizaciones(gestorExpensas);
        PersistenciaTXTLote persistenciaTXTLote=new PersistenciaTXTLote();
        List<Lote> lotesGuardados=persistenciaTXTLote.cargarLotes();
        for(Lote lote1:lotesGuardados){
            gestorExpensas.agregarLotes(lote1);
        }
        SwingUtilities.invokeLater(() -> {
            VistasAutorizaciones ventana = new VistasAutorizaciones(gestorAutorizaciones);
            ventana.setVisible(true);
        });
    }
}
