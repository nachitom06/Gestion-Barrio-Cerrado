package Clases;

import Interfaces.PersistenciaReportes;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class PersistenciaJSON implements PersistenciaReportes {
    @Override
    public boolean guardarReportes(Reportes reportes) {
        String reporteGuardar="{\"fechaGeneracion\":"+reportes.getFechaGeneracion()+","+"\"TipoReporte\":"+reportes.getTipoReporte()+","+"\"idReporte\":"+reportes.getIdReporte()+","+"\"descripcion\":"+reportes.getDescripcion()+"},";
        try (FileWriter fw = new FileWriter("C:/Users/ignac/IdeaProjects/TPO-POO-GestionBarrioCerrado/src/Persistencia/Reportes.JSON", true);
             BufferedWriter bw = new BufferedWriter(fw)) {
            bw.write(reporteGuardar);
            bw.newLine();
            return true;
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        }
    }
}
