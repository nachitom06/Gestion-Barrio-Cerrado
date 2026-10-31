package Clases;

import java.time.LocalDate;

public class RegistroVisitas {
    private Autorizaciones autorizaciones;
    private LocalDate fechaIngreso  ;
    private LocalDate fechaEgreso;

    public RegistroVisitas(Autorizaciones autorizaciones, LocalDate fechaIngreso) {
        this.autorizaciones = autorizaciones;
        this.fechaIngreso = fechaIngreso;
    }

    public void setFechaEgreso(LocalDate fechaEgreso) {
        this.fechaEgreso = fechaEgreso;
    }

    public void setFechaIngreso(LocalDate fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public Autorizaciones getAutorizaciones() {
        return autorizaciones;
    }
}
