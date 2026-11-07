package Interfaces;

import Controladores.GestorAutorizaciones;
import Clases.RegistroVisitas;
import java.util.List;

public interface PersistenciaRegistroVisitas {
    void guardarRegistroVisita(RegistroVisitas registroVisitas);
    List<RegistroVisitas> cargarRegistroVisitas(GestorAutorizaciones gestorAutorizaciones);
}
