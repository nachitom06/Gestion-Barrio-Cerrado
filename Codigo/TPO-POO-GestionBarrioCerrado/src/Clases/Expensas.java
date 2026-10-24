package Clases;

import Enumeradores.EstadoExpensas;
import Enumeradores.Mes;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class Expensas {
    private Mes mes;
    private double importe;
    private LocalDate fechaVencimiento;
    private EstadoExpensas estadoExpensas;
    private Lote lote;

    public Expensas(Mes mes, double importe, Lote lote) {
        this.mes = mes;
        this.importe = importe;
        this.fechaVencimiento = LocalDate.now().plusMonths(1).withDayOfMonth(11);
        this.estadoExpensas = EstadoExpensas.PENDIENTE;
        this.lote = lote;
    }

    public double getImporte() {
        return importe;
    }

    public double calcularExpensas(double importeTotal){
        return importeTotal/150; //son 150 lotes
    }

    public void setImporte(double importe) {
        this.importe = importe;
    }

    public void setEstadoExpensas(EstadoExpensas estadoExpensas) {
        this.estadoExpensas = estadoExpensas;
    }
    public double calcularInteres(){
        LocalDate ahora=LocalDate.now();
        double multiplicadorInteres=1.0;
        double interes=0.0;
        long mesesQuePasaron=ChronoUnit.MONTHS.between(this.fechaVencimiento,ahora);
        if (ahora.isAfter(this.fechaVencimiento) && mesesQuePasaron == 0){
            interes=0.03;
        } else if (ahora.isAfter(this.fechaVencimiento)){
            interes=0.03*(mesesQuePasaron+1);
        }
        multiplicadorInteres+=interes;
        return multiplicadorInteres;
    }
}
