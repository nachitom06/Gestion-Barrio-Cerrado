package Interfaces;

import Clases.Autorizaciones;
import Clases.Expensas;
import Clases.GestorAutorizaciones;
import Clases.GestorExpensas;

import java.util.List;

public interface PersistenciaAutorizaciones {
    boolean guardarAutorizacion(Autorizaciones autorizaciones);
    List<Autorizaciones> cargarAutorizaciones(GestorAutorizaciones gestorAutorizaciones, GestorExpensas gestorExpensas);

}
