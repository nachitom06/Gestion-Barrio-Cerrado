package Vistas;

import Clases.*;
import Controladores.GestorAcceso;
import Controladores.GestorAutorizaciones;
import Controladores.GestorExpensas;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.List;

public class VistasAcceso extends JFrame{
    private GestorAcceso gestorAcceso;
    private PersistenciaTXTRegistroVisitas persistenciaTXTRegistroVisitas;
    private JPanel panelPrincipal;
    private JTextField txtDocumento;
    private JButton btnBuscarAutorizacion;
    private JButton btnRegistrarEgreso;

    public void registrarEgreso(GestorAcceso gestorAcceso){
        String documento=null;
        try {
            documento=txtDocumento.getText();
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this,"ingreso no valido");
        }
        if(documento!=null&&!documento.trim().isEmpty()){
            boolean actualizado=false;
            for(RegistroVisitas registroVisitas:gestorAcceso.getRegistroVisitas()){
                if(registroVisitas.getAutorizaciones().getDocumento().equals(documento)&&registroVisitas.getFechaEgreso()==null){
                    registroVisitas.setFechaEgreso(LocalDate.now());
                    actualizado=true;
                }
            }
            if(actualizado){
                persistenciaTXTRegistroVisitas.guardarTodosLosRegistros(gestorAcceso.getRegistroVisitas());
                JOptionPane.showMessageDialog(this,"Egreso registrado correctamente");
            }else{
                JOptionPane.showMessageDialog(this,"No se encontro un ingreso activo para este documento");
            }
        }
    }

    public void registrarAcceso(GestorAcceso gestorAcceso){
        String documento=null;
        try {
            documento=txtDocumento.getText();
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this,"ingreso no valido");
        }
        if(documento!=null&&!documento.trim().isEmpty()){
            boolean yaEstaAdentro=false;
            for(RegistroVisitas r:gestorAcceso.getRegistroVisitas()){
                if(r.getAutorizaciones().getDocumento().equals(documento)&&r.getFechaEgreso()==null){
                    yaEstaAdentro=true;
                    break;
                }
            }
            if(yaEstaAdentro){
                JOptionPane.showMessageDialog(this,"Error: Esta persona ya ingreso y aun no registro su egreso.");
            }else{
                boolean resultado=gestorAcceso.buscarAutorizacionEnElSistema(documento);
                Autorizaciones autorizacionEncontrada=gestorAcceso.buscarAutorizacionEnElSistemaDevoolverAutorizacion(documento);
                if(resultado&&autorizacionEncontrada!=null){
                    JOptionPane.showMessageDialog(this,"El ingreso es valido");
                    RegistroVisitas nuevoRegistro=new RegistroVisitas(autorizacionEncontrada,LocalDate.now());
                    gestorAcceso.getRegistroVisitas().add(nuevoRegistro);
                    persistenciaTXTRegistroVisitas.guardarTodosLosRegistros(gestorAcceso.getRegistroVisitas());
                }else{
                    JOptionPane.showMessageDialog(this,"NO VALIDO");
                }
            }
        }
    }

    public VistasAcceso(GestorAcceso gestorAcceso,PersistenciaTXTRegistroVisitas persistenciaTXTRegistroVisitas){
        this.gestorAcceso=gestorAcceso;
        this.persistenciaTXTRegistroVisitas=persistenciaTXTRegistroVisitas;
        setContentPane(panelPrincipal);
        setTitle("Formulario de gestion de acceso");
        setSize(600,400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        inicializar();
        btnBuscarAutorizacion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registrarAcceso(gestorAcceso);
            }
        });
        btnRegistrarEgreso.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registrarEgreso(gestorAcceso);
            }
        });
    }

    private void inicializar(){
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        GestorAcceso gestorAcceso1=new GestorAcceso();
        GestorExpensas gestorExpensas=new GestorExpensas();
        GestorAutorizaciones gestorAutorizaciones=new GestorAutorizaciones(gestorExpensas);
        PersistenciaTXTAutorizaciones persistenciaTXTAutorizaciones=new PersistenciaTXTAutorizaciones();
        List<Autorizaciones> autorizacionesGuardados=persistenciaTXTAutorizaciones.cargarAutorizaciones(gestorAutorizaciones,gestorExpensas);
        gestorAutorizaciones.setAutorizaciones(autorizacionesGuardados);
        gestorAcceso1.setAutorizaciones(autorizacionesGuardados);
        PersistenciaTXTRegistroVisitas persistenciaTXTRegistroVisitas=new PersistenciaTXTRegistroVisitas();
        List<RegistroVisitas> visitasGuardadas=persistenciaTXTRegistroVisitas.cargarRegistroVisitas(gestorAutorizaciones);
        gestorAcceso1.setRegistroVisitas(visitasGuardadas);
        SwingUtilities.invokeLater(() -> {
            VistasAcceso ventana=new VistasAcceso(gestorAcceso1,persistenciaTXTRegistroVisitas);
            ventana.setVisible(true);
        });
    }
}