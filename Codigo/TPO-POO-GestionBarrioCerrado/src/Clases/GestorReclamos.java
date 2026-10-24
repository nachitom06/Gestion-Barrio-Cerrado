package Clases;

import Enumeradores.EstadoReclamo;
import Enumeradores.Prioridad;
import Enumeradores.TipoReclamo;
import Interfaces.Notificador;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GestorReclamos {
    private Notificador notificador;
    private static int contadorReclamos=0;
    private List<Reclamos> reclamosLista=new ArrayList<>();

    public void registrarReclamos(Scanner scanner,Lote lote,Administrador administrador){
        TipoReclamo tipoReclamo1=null;
        String descripcionReclamo=null;
        while(tipoReclamo1==null) {
            try {
                System.out.println("Ingresar tipo de reclamo SEGURIDAD,MANTENIMIENTO,CONVIVENCIA,ADMINISTRATIVO");
                String tipo = scanner.nextLine().toUpperCase();
                tipoReclamo1 = TipoReclamo.valueOf(tipo);
            } catch (IllegalArgumentException e) {
                System.out.println("ingreso no valido");
            }
        }
        while(descripcionReclamo==null) {
            try {
                System.out.println("Ingresar descripcion del reclamo");
                descripcionReclamo = scanner.nextLine().trim();
                if (descripcionReclamo.isEmpty()){
                    descripcionReclamo=null;
                    throw new IllegalArgumentException("no puede estar vacia la descripcion");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("ingreso no valido");
            }
        }
        Reclamos reclamoNuevo=new Reclamos(contadorReclamos,LocalDate.now(),tipoReclamo1,descripcionReclamo,Prioridad.valueOf(tipoReclamo1.getPrioridad()),EstadoReclamo.ABIERTO,lote.getTitular(),lote,administrador);
        this.reclamosLista.add(reclamoNuevo);
        contadorReclamos++;
    }

    public void asignarReclamos(){

    }

    public void cambiarEstadoReclamo(Reclamos reclamos){
        reclamos.next
    }
}
