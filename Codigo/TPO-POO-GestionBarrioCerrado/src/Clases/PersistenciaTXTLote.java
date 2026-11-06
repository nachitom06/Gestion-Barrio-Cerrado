package Clases;

import Interfaces.PersistenciaLotes;
import Interfaces.PersistenciaReclamos;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PersistenciaTXTLote implements PersistenciaLotes {
    @Override
    public boolean guardarLote(Lote lote) {
        String reclamoGuardar = "numLote:" + lote.getNumLote() + "|superficie:" + lote.getSuperficie() + "|dni:" + lote.getTitular().getDocumento() + "|nombre:" + lote.getTitular().getNombre() + "|apellido:" + lote.getTitular().getApellido() + "|telefono:" + lote.getTitular().getTelefono() + "|email:" + lote.getTitular().getMail();
        try (FileWriter fw = new FileWriter("C:/Users/ignac/IdeaProjects/TPO-POO-GestionBarrioCerrado/src/Persistencia/Lotes.txt", true);
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
    public List<Lote> cargarLotes() {
        List<Lote> listaLotes = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("C:/Users/ignac/IdeaProjects/TPO-POO-GestionBarrioCerrado/src/Persistencia/Lotes.txt"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] partes=linea.split("\\|");
                int numLote=0;
                int superficie=0;
                String dni="";
                String nombre="";
                String apellido="";
                String telefono="";
                String email="";
                for (String parte : partes) {
                    String[] claveValor = parte.split(":");
                    if (claveValor.length == 2) {
                        switch (claveValor[0]) {
                            case "numLote":
                                numLote=Integer.parseInt(claveValor[1]);
                                break;
                            case "superficie":
                                superficie=Integer.parseInt(claveValor[1]);
                                break;
                            case "dni":
                                dni=claveValor[1];
                                break;
                            case "nombre":
                                nombre=claveValor[1];
                                break;
                            case "apellido":
                                apellido=claveValor[1];
                                break;
                            case "telefono":
                                telefono=claveValor[1];
                                break;
                            case "email":
                                email=claveValor[1];
                                break;
                            }
                        }
                    }
                    Persona propietario = new Persona(nombre, apellido, dni, telefono, email);
                    Lote lote = new Lote(propietario, superficie, numLote);
                    listaLotes.add(lote);
                }
            } catch (IOException e) {
                System.out.println("Error al leer lotes: " + e.getMessage());
            }
            return listaLotes;
        }
    }
