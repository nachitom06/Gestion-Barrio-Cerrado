package Clases;

import java.time.LocalDate;
import java.util.List;

public class Personal {
    protected int legajo;
    protected String nombre;
    protected LocalDate fechaIngreso;
    protected Reclamos[] reclamosActivos=new Reclamos[5];

    public boolean agregarReclamo(Reclamos reclamos){
        for (int i=0;i<reclamosActivos.length;i++){
            if (reclamosActivos[i]==null){
                reclamosActivos[i]=reclamos;
                return true;
            }
        }
        return false;
    }
}
