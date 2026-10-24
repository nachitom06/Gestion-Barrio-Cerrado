package Clases;

import Enumeradores.MetodoPago;

import java.time.LocalDate;

public abstract class Pago {
    protected LocalDate fechaPago;
    protected double importe;
    protected MetodoPago metodoPago;
    protected Expensas expensas;

    public void setFechaPago(LocalDate fechaPago) {
        this.fechaPago = fechaPago;
    }

    public Expensas getExpensas() {
        return expensas;
    }

    public Pago() {
    }

    public Pago( Expensas expensas, MetodoPago metodoPago, double importe) {
        this.fechaPago = null;
        this.expensas = expensas;
        this.metodoPago = metodoPago;
        this.importe = importe;
    }

    public abstract boolean realizarPago(double importe);
}
