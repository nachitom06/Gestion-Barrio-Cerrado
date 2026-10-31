package Clases;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class GestorAcceso {
    private List<Autorizaciones> autorizaciones=new ArrayList<>();
    private List<RegistroVisitas> registroVisitas=new ArrayList<>();

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
