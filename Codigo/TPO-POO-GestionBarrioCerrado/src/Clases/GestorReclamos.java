package Clases;

import Enumeradores.EstadoReclamo;
import Enumeradores.Prioridad;
import Enumeradores.TipoReclamo;
import Interfaces.Notificador;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class GestorReclamos {
    private Notificador notificador;
    private static int contadorReclamos=0;
    private List<Reclamos> reclamosLista=new ArrayList<>();
    private List<HistorialReclamos> historialReclamos=new ArrayList<>();

    public String registrarReclamos(TipoReclamo tipoReclamo1,String descripcionReclamo,Lote lote,Administrador administrador){
        Reclamos reclamoNuevo=new Reclamos(contadorReclamos,LocalDate.now(),tipoReclamo1,descripcionReclamo,Prioridad.valueOf(tipoReclamo1.getPrioridad()),EstadoReclamo.ABIERTO,lote.getTitular(),lote,administrador);
        this.reclamosLista.add(reclamoNuevo);
        contadorReclamos++;
        PersistenciaTXTReclamos persistenciaJSONReclamos=new PersistenciaTXTReclamos();
        persistenciaJSONReclamos.guardarReclamo(reclamoNuevo);
        return "Registrado";
    }

    public void asignarReclamos(Reclamos reclamos, Personal empleado){
        boolean asignado= empleado.agregarReclamo(reclamos);
        if (asignado){
            reclamos.setEmpleado(empleado);
            System.out.println("se asigno un empleado al reclamo correctamente");
        } else {
            System.out.println("no se asigno un empleado al reclamo");
        }
        cambiarEstadoReclamo(reclamos,EstadoReclamo.ASIGNADO,"se asigno a un empleado ppara revisarlo");
    }

    public void cambiarEstadoReclamo(Reclamos reclamos, EstadoReclamo estadoReclamoNuevo, String observacion){
        EstadoReclamo actual=reclamos.getEstadoReclamo();
        boolean cambio=false;
        switch (actual){
            case ABIERTO:
                cambio=(estadoReclamoNuevo==EstadoReclamo.ASIGNADO);
                break;
            case ASIGNADO:
                cambio=(estadoReclamoNuevo==EstadoReclamo.EN_CURSO);
                break;
            case EN_CURSO:
                cambio=(estadoReclamoNuevo==EstadoReclamo.RESUELTO);
                break;
            case RESUELTO:
                cambio=(estadoReclamoNuevo==EstadoReclamo.CERRADO);
                break;
        }
        if (!cambio){
            System.out.println("no se puede relizar la accion, no sigue la regla de solo pasar al siguiente estado");
            return;
        }
        reclamos.cambiarEstado(estadoReclamoNuevo);
        HistorialReclamos historialReclamos1=new HistorialReclamos(LocalDate.now(),estadoReclamoNuevo,observacion);
        historialReclamos.add(historialReclamos1);
        System.out.println("La operacion se relizo correctamente");
    }
}
