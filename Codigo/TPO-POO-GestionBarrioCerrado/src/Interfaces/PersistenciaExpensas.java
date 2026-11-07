package Interfaces;

import Clases.Expensas;
import Controladores.GestorExpensas;

import java.util.List;

public interface PersistenciaExpensas {
    boolean guardarExpensa(Expensas expensas);
    List<Expensas> cargarExpensas(GestorExpensas gestorExpensas);
}
