package Clases;

import Interfaces.PersistenciaReclamos;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class PersistenciaTXTReclamos implements PersistenciaReclamos {
    @Override
    public boolean guardarReclamo(Reclamos reclamos) {
        String reclamoGuardar = "numeroReclamo:"+reclamos.getNumeroReclamo()+"|fecha:"+reclamos.getFecha()+"|tipoReclamo:"+reclamos.getTipoReclamo()+"|descripcion:"+reclamos.getDescripcion()+"|prioridad:"+reclamos.getPrioridad()+"|estadoReclamo:"+reclamos.getEstadoReclamo();
        try (FileWriter fw = new FileWriter("C:/Users/ignac/IdeaProjects/TPO-POO-GestionBarrioCerrado/src/Persistencia/Reclamos.txt", true);
             BufferedWriter bw = new BufferedWriter(fw)) {
            bw.write(reclamoGuardar);
            bw.newLine();
            return true;
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        }
    }
}
