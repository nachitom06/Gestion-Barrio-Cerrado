package Controladores;

import Clases.Autorizaciones;
import Clases.RegistroVisitas;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class GestorAcceso {
    private List<Autorizaciones> autorizaciones=new ArrayList<>();
    private List<RegistroVisitas> registroVisitas=new ArrayList<>();

    public void setAutorizaciones(List<Autorizaciones> autorizaciones) {
        this.autorizaciones = autorizaciones;
    }

    public List<RegistroVisitas> getRegistroVisitas() {
        return registroVisitas;
    }

    public void setRegistroVisitas(List<RegistroVisitas> registroVisitas) {
        this.registroVisitas = registroVisitas;
    }
    public Autorizaciones buscarAutorizacionEnElSistemaDevoolverAutorizacion(String documento) {
        Autorizaciones autorizacion = null;
        for (Autorizaciones autorizaciones1 : autorizaciones) {
            if (autorizaciones1.getDocumento().equals(documento)) {
                autorizacion = autorizaciones1;
                break;
            }
        }
        return autorizacion;
    }
    public boolean buscarAutorizacionEnElSistema(String documento){
        Autorizaciones autorizacion=null;
        for (Autorizaciones autorizaciones1: autorizaciones) {
            if (autorizaciones1.getDocumento().equals(documento)) {
                autorizacion = autorizaciones1;
                break;
            }
        }
        if(autorizacion==null){
            return false;
        }
        boolean validacion=autorizacion.validarAutorizacion();
        if(validacion){
            RegistroVisitas nuevoRegistro=new RegistroVisitas(autorizacion,LocalDate.now());
            this.registroVisitas.add(nuevoRegistro);
            return true;
        }
        return false;

    }
    public void registrarEgreso(RegistroVisitas registroVisitas){
        registroVisitas.setFechaEgreso(LocalDate.now());
        registroVisitas.getAutorizaciones().marcarUtilizada();
    }
}
