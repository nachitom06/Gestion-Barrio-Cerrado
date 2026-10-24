package Clases;

import Enumeradores.EstadoReclamo;
import Enumeradores.Prioridad;
import Enumeradores.TipoReclamo;

import java.time.LocalDate;

public class Reclamos {
    private int numeroReclamo;
    private LocalDate fecha;
    private TipoReclamo tipoReclamo;
    private String descripcion;
    private Prioridad prioridad;
    private EstadoReclamo estadoReclamo;
    private Persona persona;
    private Lote lote;
    private Administrador administrador;

    public EstadoReclamo getEstadoReclamo() {
        return estadoReclamo;
    }

    public void cambiarEstado(EstadoReclamo estadoReclamo){
        this.estadoReclamo=estadoReclamo;
    }

    public Reclamos(int numeroReclamo, LocalDate fecha, TipoReclamo tipoReclamo, String descripcion, Prioridad prioridad, EstadoReclamo estadoReclamo, Persona persona, Lote lote, Administrador administrador) {
        this.numeroReclamo = numeroReclamo;
        this.fecha = fecha;
        this.tipoReclamo = tipoReclamo;
        this.descripcion = descripcion;
        this.prioridad = prioridad;
        this.estadoReclamo = estadoReclamo;
        this.persona = persona;
        this.lote = lote;
        this.administrador = administrador;
    }

    @Override
    public String toString() {
        return "Reclamos{" +
                "numeroReclamo=" + numeroReclamo +
                ", fecha=" + fecha +
                ", tipoReclamo=" + tipoReclamo +
                ", descripcion='" + descripcion + '\'' +
                ", prioridad=" + prioridad +
                ", estadoReclamo=" + estadoReclamo +
                ", persona=" + persona +
                ", lote=" + lote +
                ", administrador=" + administrador +
                '}';
    }
}
