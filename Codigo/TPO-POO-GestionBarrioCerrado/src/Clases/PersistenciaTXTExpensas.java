package Clases;

import Controladores.GestorExpensas;
import Enumeradores.EstadoExpensas;
import Enumeradores.Mes;
import Interfaces.PersistenciaExpensas;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PersistenciaTXTExpensas implements PersistenciaExpensas {
    @Override
    public boolean guardarExpensa(Expensas expensas) {
        String reclamoGuardar = "mes:"+expensas.getMes()+"|importe:"+expensas.getImporte()+"|importePendiente:"+expensas.getImportePendiente()+"|estado:"+expensas.getEstadoExpensas()+"|numLote:"+expensas.getLote().getNumLote()+"|fechaVencimiento:"+expensas.getFechaVencimiento();
        try (FileWriter fw = new FileWriter("C:/Users/ignac/IdeaProjects/TPO-POO-GestionBarrioCerrado/src/Persistencia/Expensas.txt", true);
             BufferedWriter bw = new BufferedWriter(fw)) {
            bw.write(reclamoGuardar);
            bw.newLine();
            return true;
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        }
    }
    @Override
    public List<Expensas> cargarExpensas(GestorExpensas gestorExpensas) {
        List<Expensas> listaExpensas = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("C:/Users/ignac/IdeaProjects/TPO-POO-GestionBarrioCerrado/src/Persistencia/Expensas.txt"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split("\\|");
                Mes mes= Mes.ENERO;
                double importe=0.0;
                double importePendiente=0.0;
                EstadoExpensas estadoExpensas=EstadoExpensas.PENDIENTE;
                int numLote=0;
                LocalDate fechaVencimiento=null;
                for (String parte : partes) {
                    String[] claveValor = parte.split(":");
                    if (claveValor.length == 2) {
                        switch (claveValor[0]) {
                            case "mes":
                                mes = Mes.valueOf(claveValor[1]);
                                break;
                            case "importe":
                                importe = Double.parseDouble(claveValor[1]);
                                break;
                            case "importePendiente":
                                importePendiente = Double.parseDouble(claveValor[1]);
                                break;
                            case "estado":
                                estadoExpensas = EstadoExpensas.valueOf(claveValor[1]);
                                break;
                            case "numLote":
                                numLote = Integer.parseInt(claveValor[1]);
                                break;
                            case "fechaVencimiento":
                                fechaVencimiento = LocalDate.parse(claveValor[1]);
                                break;
                            }
                        }
                    }
                    Lote loteEcontrado=gestorExpensas.buscarLoteEnElSistema(numLote);
                    if(loteEcontrado!=null){
                        Expensas expensas=new Expensas(mes,importe,fechaVencimiento,loteEcontrado);
                        loteEcontrado.getExpensasPendientes().offer(expensas);
                        expensas.setImportePendiente(importePendiente);
                        expensas.setEstadoExpensas(estadoExpensas);
                        listaExpensas.add(expensas);
                    }
                }
            } catch (IOException e) {
                System.out.println("Error al leer lotes: " + e.getMessage());
            }
            return listaExpensas;
        }
    }
