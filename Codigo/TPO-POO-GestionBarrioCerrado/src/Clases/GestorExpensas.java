package Clases;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class GestorExpensas {
    private List<Lote> lotes=new ArrayList<>();

    public void registrarPago(Lote lote,double monto){
        lote.getExpensasPendientes().poll();
        System.out.println("se registro el pago");
    }
    public Expensas obtenerDeudaMasAntigua(Lote lote){
        return lote.getExpensasPendientes().peek();
    }
    public double calcularMontoTotalAPagar(Expensas expensas){
        if(expensas==null){
            return 0.00;
        }
        return expensas.getImporte()*expensas.calcularInteres();
    }
    public boolean verificarMorosidad(Lote lote){
        if (lote.getExpensasPendientes().size()>=3){
            lote.setMoroso(true);
            return true;
        } else {
            return false;
        }
    }
    public Lote buscarLoteEnElSistema(int numLote){
        for (Lote lote: lotes) {
            if (lote.getNumLote()==numLote) {
                return lote;
            }
        }
        return null;
    }
}
