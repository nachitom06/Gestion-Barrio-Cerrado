package Clases;

import java.time.LocalDate;

public class AutorizacionesRecurrentes extends Autorizaciones{
    private int cantDias;

    public int getCantDias() {
        return cantDias;
    }

    public AutorizacionesRecurrentes(String nombre, String documento, Lote lote, LocalDate fecha, String franjaHoraria, int cantDias) {
        super(nombre, documento, lote, fecha, franjaHoraria);
        this.cantDias = cantDias;
    }
}
