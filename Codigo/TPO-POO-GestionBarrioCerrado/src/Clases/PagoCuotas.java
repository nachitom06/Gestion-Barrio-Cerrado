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
            if (importe>= getExpensas().getImporte()){
                throw new IllegalArgumentException("pago mayor a importe, ponga pago directo");
            } else {
                double importeResto=getExpensas().getImporte()-importe;
                getExpensas().setImporte(importeResto);
            }

        }
        return true;
    }

    public PagoCuotas(Expensas expensas, MetodoPago metodoPago, double importe) {
        super(expensas, metodoPago, importe);
    }
}
