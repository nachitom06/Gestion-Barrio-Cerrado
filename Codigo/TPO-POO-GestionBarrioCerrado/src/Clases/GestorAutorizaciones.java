package Clases;

import Enumeradores.EstadoReclamo;
import Enumeradores.Prioridad;
import Enumeradores.TipoReclamo;
import Interfaces.Notificador;
import Interfaces.PersistenciaAutorizaciones;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class GestorAutorizaciones {
    private static int contadorAutorizaciones=0;
    private List<Autorizaciones> autorizaciones=new ArrayList<>();
    private GestorExpensas gestorExpensas;

    public boolean registrarAutorizaciones(String nombre,String documento,int numLote,LocalDate fecha,String franjaHoraria){
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
}
