package Clases;

import java.util.*;

public class Lote {
    private int numLote;
    private int superficie;
    private Persona titular;
    private boolean moroso;
    private List<Expensas> historialExpensas=new ArrayList<>();
    private Queue<Expensas> expensasPendientes=new Queue<Expensas>() {
        @Override
        public boolean add(Expensas expensas) {
            return false;
        }

        @Override
        public boolean offer(Expensas expensas) {
            return false;
        }

        @Override
        public Expensas remove() {
            return null;
        }

        @Override
        public Expensas poll() {
            return null;
        }

        @Override
        public Expensas element() {
            return null;
        }

        @Override
        public Expensas peek() {
            return null;
        }

        @Override
        public int size() {
            return 0;
        }

        @Override
        public boolean isEmpty() {
            return false;
        }

        @Override
        public boolean contains(Object o) {
            return false;
        }

        @Override
        public Iterator<Expensas> iterator() {
            return null;
        }

        @Override
        public Object[] toArray() {
            return new Object[0];
        }

        @Override
        public <T> T[] toArray(T[] a) {
            return null;
        }

        @Override
        public boolean remove(Object o) {
            return false;
        }

        @Override
        public boolean containsAll(Collection<?> c) {
            return false;
        }

        @Override
        public boolean addAll(Collection<? extends Expensas> c) {
            return false;
        }

        @Override
        public boolean removeAll(Collection<?> c) {
            return false;
        }

        @Override
        public boolean retainAll(Collection<?> c) {
            return false;
        }

        @Override
        public void clear() {

        }
    };

    public Lote(Persona titular, int superficie, int numLote) {
        this.titular = titular;
        this.superficie = superficie;
        this.numLote = numLote;
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
