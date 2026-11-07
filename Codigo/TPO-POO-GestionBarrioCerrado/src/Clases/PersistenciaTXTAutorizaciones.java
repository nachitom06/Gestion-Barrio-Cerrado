package Clases;

import Controladores.GestorAutorizaciones;
import Controladores.GestorExpensas;
import Interfaces.PersistenciaAutorizaciones;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PersistenciaTXTAutorizaciones implements PersistenciaAutorizaciones {
    @Override
    public boolean guardarAutorizacion(Autorizaciones autorizaciones) {
        String autorizacionGuardar = "nombre:"+autorizaciones.getNombre()+"|documento:"+autorizaciones.getDocumento()+"|lote:"+autorizaciones.getLote().getNumLote()+"|fecha:"+autorizaciones.getFecha()+"|franjaHoraria:"+autorizaciones.getFranjaHoraria()+"|utilizada:"+autorizaciones.isUtilizada();
        try (FileWriter fw = new FileWriter("C:/Users/ignac/IdeaProjects/TPO-POO-GestionBarrioCerrado/src/Persistencia/Autorizaciones.txt", true);
             BufferedWriter bw = new BufferedWriter(fw)) {
            bw.write(autorizacionGuardar);
            bw.newLine();
            return true;
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        }
    }
    @Override
    public List<Autorizaciones> cargarAutorizaciones(GestorAutorizaciones gestorAutorizaciones, GestorExpensas gestorExpensas){
        List<Autorizaciones> listaAutorizaciones = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("C:/Users/ignac/IdeaProjects/TPO-POO-GestionBarrioCerrado/src/Persistencia/Autorizaciones.txt"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split("\\|");
                String nombre="";
                String documento="";
                int lote=0;
                LocalDate fecha=null;
                String franjaHoraria="";
                boolean utilizada=false;
                for (String parte : partes) {
                    String[] claveValor = parte.split(":");
                    if (claveValor.length == 2) {
                        switch (claveValor[0]) {
                            case "nombre":
                                nombre = claveValor[1];
                                break;
                            case "documento":
                                documento = claveValor[1];
                                break;
                            case "lote":
                                lote = Integer.parseInt(claveValor[1]);
                                break;
                            case "fecha":
                                fecha = LocalDate.parse(claveValor[1]);
                                break;
                            case "franjaHoraria":
                                franjaHoraria = claveValor[1];
                                break;
                            case "utilizada":
                                utilizada = Boolean.getBoolean(claveValor[1]);
                                break;
                        }
                    }
                }
                Lote loteEcontrado=gestorExpensas.buscarLoteEnElSistema(lote);
                if(loteEcontrado!=null){
                    loteEcontrado=new Lote(null, 100, 1);
                    Autorizaciones autorizaciones=new Autorizaciones(nombre,documento,loteEcontrado,fecha,franjaHoraria);
                    listaAutorizaciones.add(autorizaciones);
                } else {
                    Autorizaciones autorizaciones=new Autorizaciones(nombre,documento,loteEcontrado,fecha,franjaHoraria);
                    listaAutorizaciones.add(autorizaciones);
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer autorizaciones: " + e.getMessage());
        }
        return listaAutorizaciones;
    }
}
