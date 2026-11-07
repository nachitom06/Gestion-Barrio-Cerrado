package Clases;

import Controladores.GestorAutorizaciones;
import Interfaces.PersistenciaRegistroVisitas;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PersistenciaTXTRegistroVisitas implements PersistenciaRegistroVisitas {
    @Override
    public void guardarRegistroVisita(RegistroVisitas registroVisitas) {
        String registroGuardar="documentoVisita:"+(registroVisitas.getAutorizaciones().getDocumento()+"|fechaIngreso:"+registroVisitas.getFechaIngreso()+"|fechaEgreso:"+registroVisitas.getFechaEgreso());
        try (FileWriter fw = new FileWriter("C:/Users/ignac/IdeaProjects/TPO-POO-GestionBarrioCerrado/src/Persistencia/RegistroVisitas.txt");
             BufferedWriter bw = new BufferedWriter(fw)) {
            bw.write(registroGuardar);
            bw.newLine();
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @Override
    public List<RegistroVisitas> cargarRegistroVisitas(GestorAutorizaciones gestorAutorizaciones) {
        List<RegistroVisitas> listaRegistros = new ArrayList<>();
        File archivo = new File("C:/Users/ignac/IdeaProjects/TPO-POO-GestionBarrioCerrado/src/Persistencia/RegistroVisitas.txt");
        if (!archivo.exists()) {
            return listaRegistros;
        }
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes=linea.split("\\|");
                String documentoVisita="";
                LocalDate fechaIngreso=null;
                LocalDate fechaEgreso=null;
                for (String parte: partes) {
                    String[] claveValor=parte.split(":");
                    if (claveValor.length==2){
                        switch (claveValor[0]){
                            case "documentoVisita":
                                documentoVisita = claveValor[1];
                                break;
                            case "fechaIngreso":
                                fechaIngreso = LocalDate.parse(claveValor[1]);
                                break;
                            case "fechaEgreso":
                                if (claveValor[1].equals("null")){
                                    fechaEgreso = null;
                                } else {
                                    fechaEgreso = LocalDate.parse(claveValor[1]);
                                }
                                break;
                        }
                    }
                }
                Autorizaciones autorizacionEncontrada=null;
                for (Autorizaciones autorizaciones: gestorAutorizaciones.getAutorizaciones()) {
                    if (autorizaciones.getDocumento().equals(documentoVisita)) {
                        autorizacionEncontrada=autorizaciones;
                        break;
                    }
                }
                if (autorizacionEncontrada!=null && fechaIngreso!=null){
                    RegistroVisitas registro=new RegistroVisitas(autorizacionEncontrada,fechaIngreso);
                    if (fechaEgreso!=null){
                        registro.setFechaEgreso(fechaEgreso);
                    }
                    listaRegistros.add(registro);
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer registros de visitas: " + e.getMessage());
        }
        return listaRegistros;
    }
    public void guardarTodosLosRegistros(List<RegistroVisitas> listaRegistros) {
        try (FileWriter fw = new FileWriter("C:/Users/ignac/IdeaProjects/TPO-POO-GestionBarrioCerrado/src/Persistencia/RegistroVisitas.txt");
             BufferedWriter bw = new BufferedWriter(fw)) {
            for (RegistroVisitas registroVisitas: listaRegistros) {
                String registroGuardar="documentoVisita:"+registroVisitas.getAutorizaciones().getDocumento()+"|fechaIngreso:"+registroVisitas.getFechaIngreso()+"|fechaEgreso:"+ registroVisitas.getFechaEgreso();
                bw.write(registroGuardar);
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}