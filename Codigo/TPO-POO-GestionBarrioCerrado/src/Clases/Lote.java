package Clases;

import java.util.*;

public class Lote {
    private int numLote;
    private int superficie;
    private Persona titular;
    private boolean moroso;
    private List<Expensas> historialExpensas=new ArrayList<>();
    private Queue<Expensas> expensasPendientes=new LinkedList<>();

    public Lote(Persona titular, int superficie, int numLote) {
        this.titular = titular;
        this.superficie = superficie;
        this.numLote = numLote;

    }

    public int getSuperficie() {
        return superficie;
    }

    public boolean isMoroso() {
        return moroso;
    }

    public int getNumLote() {
        return numLote;
    }

    public void setMoroso(boolean moroso) {
        this.moroso = moroso;
    }

    public void agregarHistorialExpensas(Expensas expensas) {
        this.historialExpensas.add(expensas);
    }

    public void agregarExpensasPendientes(Expensas expensas) {
        this.expensasPendientes.add(expensas);
    }

    public List<Expensas> getHistorialExpensas() {
        return historialExpensas;
    }

    public Queue<Expensas> getExpensasPendientes() {
        return expensasPendientes;
    }

    public Persona getTitular() {
        return titular;
    }
}
