package Clases;

import Enumeradores.EstadoExpensas;
import Enumeradores.MetodoPago;

import java.time.LocalDate;

public class PagoCuotas extends Pago {
    @Override
    public boolean realizarPago(double importe){
        setFechaPago(LocalDate.now());
        if (importe<=0){
            throw new IllegalArgumentException("importe tiene que ser mayor a 0");
        } else {
            if (importe>= getExpensas().getImportePendiente()){
                getExpensas().setImportePendiente(0);
                getExpensas().setEstadoExpensas(EstadoExpensas.PAGO);
            } else {
                double importeResto=getExpensas().getImportePendiente()-importe;
                getExpensas().setImportePendiente(importeResto);
            }

        }
        return true;
    }

    public PagoCuotas(Expensas expensas, MetodoPago metodoPago, double importe) {
        super(expensas, metodoPago, importe);
    }
}
