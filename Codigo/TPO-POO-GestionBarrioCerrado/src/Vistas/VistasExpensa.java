package Vistas;

import Clases.*;
import Enumeradores.EstadoExpensas;
import Enumeradores.MetodoPago;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VistasExpensa extends JFrame{
    private GestorExpensas gestorExpensas;
    private Lote lote;
    private Expensas expensas;
    private JPanel panelPrincipal;
    private JTextField txtLoteNum;
    private JButton btnBuscarDeuda;
    private JPanel panelBuscarDeuda;
    private JPanel panelPago;
    private JTextArea textArea1;
    private JButton btnPagoDirecto;
    private JButton btnPagoCuotas;
    private JTextField txtImporte;
    private JComboBox<MetodoPago> cmbMetodoPago;
    public void inicializar(){
        setLocationRelativeTo(null);
    }
    public static void main(String[] args) {
        GestorExpensas gestorExpensas1=new GestorExpensas();
        SwingUtilities.invokeLater(() -> {
            VistasExpensa ventana = new VistasExpensa(gestorExpensas1);
            ventana.setVisible(true);
        });
    }

    public VistasExpensa(GestorExpensas gestorExpensas) {
        this.gestorExpensas=gestorExpensas;
        setContentPane(panelPrincipal);
        setTitle("Formulario de gestión de acceso");
        setSize(600,400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        inicializar();
        btnBuscarDeuda.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String textoLote=txtLoteNum.getText().trim();
                    if(textoLote.isEmpty()){
                        JOptionPane.showMessageDialog(panelPrincipal,"no se ingreso nada");
                    }
                    int numLote=Integer.parseInt(textoLote);
                    lote=gestorExpensas.buscarLoteEnElSistema(numLote);
                    if(lote==null){
                        JOptionPane.showMessageDialog(panelPrincipal,"no se encontro el lote");
                        return;
                    }
                    expensas=gestorExpensas.obtenerDeudaMasAntigua(lote);
                    if(expensas==null){
                        JOptionPane.showMessageDialog(panelPrincipal,"no se encontro el lote");
                        return;
                    }
                    double montoInteres=gestorExpensas.calcularMontoTotalAPagar(expensas);
                    textArea1.setText("Detalles de la deuda\n"+"Numero de lote: "+lote.getNumLote()+"\n"+"Importe original: "+expensas.getImporte()+"\n"+"Importe restante: "+expensas.getImportePendiente()+"\n"+"Total a pagar con intereses: "+montoInteres);
                    CardLayout cardLayout=(CardLayout) panelPrincipal.getLayout();
                    cardLayout.show(panelPrincipal,"Pago");
                } catch(NumberFormatException f){
                    JOptionPane.showMessageDialog(panelPrincipal,"error de tipeo en el numero de lote");
                }

            }
        });
        btnPagoDirecto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String importeExpesnsas=txtImporte.getText().trim();
                    MetodoPago metodoPago=(MetodoPago) cmbMetodoPago.getSelectedItem();
                    if(importeExpesnsas.isEmpty()){
                        JOptionPane.showMessageDialog(panelPrincipal,"no se ingreso nada");
                    }
                    double importeNumero=Double.parseDouble(importeExpesnsas);
                    int respuestaUsuario=JOptionPane.showConfirmDialog(panelPrincipal,"Confirmacion de pago de: "+importeNumero);
                    if(respuestaUsuario==JOptionPane.YES_OPTION){
                        Pago pago=new PagoDirecto(expensas,metodoPago,importeNumero);
                        pago.realizarPago(importeNumero);
                        if(expensas.getEstadoExpensas()== EstadoExpensas.PAGO){
                            gestorExpensas.registrarPago(lote,importeNumero);
                        }
                        boolean moroso=gestorExpensas.verificarMorosidad(lote);
                        JOptionPane.showMessageDialog(panelPrincipal,"pago registrado");
                        CardLayout cardLayout=(CardLayout) panelPrincipal.getLayout();
                        cardLayout.show(panelPrincipal,"BuscarDeuda");
                    } else {
                        JOptionPane.showMessageDialog(panelPrincipal,"operacion cancelada");
                        CardLayout cardLayout=(CardLayout) panelPrincipal.getLayout();
                        cardLayout.show(panelPrincipal,"BuscarDeuda");
                    }
                } catch (NumberFormatException f){
                    JOptionPane.showMessageDialog(panelPrincipal,"El importe debe ser un valor numerico");
                } catch (IllegalArgumentException f){
                    JOptionPane.showMessageDialog(panelPrincipal,"El importe debe ser numerico y se debe seleccionar un campo en la caja de selección");
                }
            }
        });
        btnPagoCuotas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String importeExpesnsasCuota=txtImporte.getText().trim();
                    MetodoPago metodoPagoCuota=(MetodoPago) cmbMetodoPago.getSelectedItem();
                    if(importeExpesnsasCuota.isEmpty()){
                        JOptionPane.showMessageDialog(panelPrincipal,"no se ingreso nada");
                    }
                    double importeNumeroCuota=Double.parseDouble(importeExpesnsasCuota);
                    int respuestaUsuarioCuota=JOptionPane.showConfirmDialog(panelPrincipal,"Confirmacion de pago de: "+importeNumeroCuota);
                    if(respuestaUsuarioCuota==JOptionPane.YES_OPTION){
                        Pago pagoCuota=new PagoCuotas(expensas,metodoPagoCuota,importeNumeroCuota);
                        pagoCuota.realizarPago(importeNumeroCuota);
                        if(expensas.getEstadoExpensas()== EstadoExpensas.PAGO){
                            gestorExpensas.registrarPago(lote,importeNumeroCuota);
                        }
                        boolean morosoCuota=gestorExpensas.verificarMorosidad(lote);
                        JOptionPane.showMessageDialog(panelPrincipal,"pago registrado");
                        CardLayout cardLayout=(CardLayout) panelPrincipal.getLayout();
                        cardLayout.show(panelPrincipal,"BuscarDeuda");
                    } else {
                        JOptionPane.showMessageDialog(panelPrincipal,"operacion cancelada");
                        CardLayout cardLayout=(CardLayout) panelPrincipal.getLayout();
                        cardLayout.show(panelPrincipal,"BuscarDeuda");
                    }
                } catch (NumberFormatException f){
                    JOptionPane.showMessageDialog(panelPrincipal,"El importe debe ser un valor numerico");
                } catch (IllegalArgumentException f){
                    JOptionPane.showMessageDialog(panelPrincipal,"El importe debe ser numerico y se debe seleccionar un campo en la caja de selección");
                }
            }
        });

    }
}
