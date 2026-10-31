package Clases;

import java.time.LocalDate;

public class Autorizaciones {
    private String nombre;
    private String documento;
    private Lote lote;
    private LocalDate fecha;
    private String franjaHoraria;
    private boolean utilizada;

    public Autorizaciones() {
    }

    public Autorizaciones(String nombre, String documento, Lote lote, LocalDate fecha, String franjaHoraria) {
        this.nombre = nombre;
        this.documento = documento;
        this.lote = lote;
        this.fecha = fecha;
        this.franjaHoraria = franjaHoraria;
    }
    public boolean validarAutorizacion(){
        if(this.utilizada){
            return false;
        }
        this.utilizada=true;
        return true;

    }
    public String getDocumento() {
        return documento;
    }

    public String getNombre() {
        return nombre;
    }

    public Lote getLote() {
        return lote;
    }

    public String getFranjaHoraria() {
        return franjaHoraria;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public boolean isUtilizada() {
        return utilizada;
    }
    public void marcarUtilizada() {
        this.utilizada=true;
    }
}
