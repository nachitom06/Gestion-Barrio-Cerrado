package Clases;

import Enumeradores.TipoReporte;

import java.time.LocalDate;

public class Reportes {
    private int idReporte;
    private TipoReporte tipoReporte;
    private LocalDate fechaGeneracion;
    private String descripcion;

    public int getIdReporte() {
        return idReporte;
    }

    public TipoReporte getTipoReporte() {
        return tipoReporte;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public LocalDate getFechaGeneracion() {
        return fechaGeneracion;
    }

    @Override
    public String toString() {
        return "Reportes{}";
    }
}
