package Vistas;

import Clases.*;
import Enumeradores.TipoReclamo;
import Interfaces.Notificador;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class VistaReclamo extends JFrame{
    private Lote lote;
    private Administrador administrador;
    private GestorReclamos gestor;
    private Notificador notificador;
    private static int contadorReclamos=0;
    private List<Reclamos> reclamosLista=new ArrayList<>();
    private List<HistorialReclamos> historialReclamos=new ArrayList<>();
    private JPanel panelPrincipal;
    private JTextField txtTipoReclamo;
    private JTextField txtDescripcion;
    private JButton btnRegistrarReclamo;

    public void registrarReclamos(Lote lote, Administrador administrador, Clases.GestorReclamos gestor){
        TipoReclamo tipoReclamo1=null;
        String descripcionReclamo=null;
        try {
            String tipo = txtTipoReclamo.getText();
            tipoReclamo1 = TipoReclamo.valueOf(tipo);
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this,"ingreso no valido");
        }
        try {
            descripcionReclamo = txtDescripcion.getText().trim();
            if (descripcionReclamo.isEmpty()){
                descripcionReclamo=null;
                throw new IllegalArgumentException("no puede estar vacia la descripcion");
            }
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this,"ingreso no valido");
        }
        String mensaje=(gestor.registrarReclamos(tipoReclamo1,descripcionReclamo,lote,administrador));
        if (mensaje!=null){
            JOptionPane.showMessageDialog(this,mensaje);
        }
    }
    public VistaReclamo(Lote lote,Administrador administrador,GestorReclamos gestor) {
        this.lote=lote;
        this.administrador=administrador;
        this.gestor=gestor;
        setContentPane(panelPrincipal);
        setTitle("Formulario de inicio de sesión");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        inicializar();
        btnRegistrarReclamo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registrarReclamos(lote,administrador,gestor);
            }
        });
    }
    private void inicializar() {
        setLocationRelativeTo(null);
    }
    public static void main(String[] args) {
        Lote lote1=new Lote(new Persona(),100,1);
        Administrador administrador1=new Administrador();
        GestorReclamos gestorReclamos=new GestorReclamos();
        SwingUtilities.invokeLater(() -> {
            VistaReclamo ventana = new VistaReclamo(lote1,administrador1,gestorReclamos);
            ventana.setVisible(true);
        });
    }
}

