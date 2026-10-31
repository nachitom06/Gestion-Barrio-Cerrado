package Clases;

import Enumeradores.EstadoReclamo;

import java.time.LocalDate;

public class HistorialReclamos {
    private LocalDate fecha;
    private EstadoReclamo estadoReclamo;
    private String observacion;

    public HistorialReclamos(LocalDate fecha, EstadoReclamo estadoReclamo, String observacion) {
        this.fecha = fecha;
        this.estadoReclamo = estadoReclamo;
        this.observacion = observacion;
    }
}
