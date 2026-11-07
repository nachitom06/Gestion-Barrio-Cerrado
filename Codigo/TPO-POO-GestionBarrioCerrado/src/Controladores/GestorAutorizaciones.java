package Controladores;

import Clases.Autorizaciones;
import Clases.PersistenciaTXTAutorizaciones;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class GestorAutorizaciones {
    private static int contadorAutorizaciones=0;
    private List<Autorizaciones> autorizaciones=new ArrayList<>();
    private GestorExpensas gestorExpensas;

    public void setAutorizaciones(List<Autorizaciones> autorizaciones) {
        this.autorizaciones = autorizaciones;
    }

    public boolean registrarAutorizaciones(String nombre, String documento, int numLote, LocalDate fecha, String franjaHoraria){
        Autorizaciones autorizacionNueva=new Autorizaciones(nombre,documento,gestorExpensas.buscarLoteEnElSistema(numLote),fecha,franjaHoraria);
        this.autorizaciones.add(autorizacionNueva);
        contadorAutorizaciones++;
        PersistenciaTXTAutorizaciones persistenciaTXTAutorizaciones=new PersistenciaTXTAutorizaciones();
        persistenciaTXTAutorizaciones.guardarAutorizacion(autorizacionNueva);
        return true;
    }
    public GestorAutorizaciones(GestorExpensas gestorExpensas) {
        this.gestorExpensas = gestorExpensas;
    }

    public List<Autorizaciones> getAutorizaciones() {
        return autorizaciones;
    }
}
