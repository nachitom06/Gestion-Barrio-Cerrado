package Interfaces;

import Clases.Lote;
import Clases.Reclamos;

import java.util.List;

public interface PersistenciaLotes {
    boolean guardarLote(Lote lote);
    List<Lote> cargarLotes();
}
