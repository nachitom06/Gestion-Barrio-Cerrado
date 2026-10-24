package Clases;

import Enumeradores.EstadoExpensas;
import Enumeradores.MetodoPago;

import java.time.LocalDate;

public class PagoDirecto extends Pago {
    @Override
    public boolean realizarPago(double importe){
        setFechaPago(LocalDate.now());
        if (importe>= getExpensas().getImporte()){
            getExpensas().setEstadoExpensas(EstadoExpensas.PAGO);
            getExpensas().setImporte(0);
        } else {
            throw new IllegalArgumentException("No se ingreso el monto correcto");
        }
        return true;
    }

    public PagoDirecto(Expensas expensas, MetodoPago metodoPago, double importe) {
        super(expensas, metodoPago, importe);
    }
}
