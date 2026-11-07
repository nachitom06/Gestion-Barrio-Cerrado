package Interfaces;

import Clases.Autorizaciones;
import Controladores.GestorAutorizaciones;
import Controladores.GestorExpensas;

import java.util.List;

public interface PersistenciaAutorizaciones {
    boolean guardarAutorizacion(Autorizaciones autorizaciones);
    List<Autorizaciones> cargarAutorizaciones(GestorAutorizaciones gestorAutorizaciones, GestorExpensas gestorExpensas);

}
