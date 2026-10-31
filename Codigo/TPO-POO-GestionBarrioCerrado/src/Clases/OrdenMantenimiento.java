package Clases;

import Enumeradores.Prioridad;
import Enumeradores.PrioridadOrden;

import java.time.LocalDate;

public class OrdenMantenimiento {
    private LocalDate fechaApertura;
    private String descripcion;
    private PrioridadOrden prioridadOrden;
    private Administrador administrador;
    private Personal mantenimiento;
    private Equipo equipo;

    public OrdenMantenimiento(Administrador administrador, String descripcion,Personal mantenimiento, Equipo equipo) {
        this.fechaApertura=LocalDate.now();
        this.descripcion=descripcion;
        if (equipo instanceof Camaras || equipo.sector.getLuminariasDelSector().size()>=3) {
            this.prioridadOrden=PrioridadOrden.CRITICA;
        } else {
            this.prioridadOrden=PrioridadOrden.NORMAL;
        }
        this.administrador = administrador;
        this.mantenimiento = mantenimiento;
        this.equipo = equipo;
    }
}
