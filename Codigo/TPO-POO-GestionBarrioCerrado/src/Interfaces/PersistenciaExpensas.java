package Interfaces;

import Clases.Expensas;
import Clases.GestorExpensas;
import Clases.Lote;

import java.util.List;

public interface PersistenciaExpensas {
    boolean guardarExpensa(Expensas expensas);
    List<Expensas> cargarExpensas(GestorExpensas gestorExpensas);
}
