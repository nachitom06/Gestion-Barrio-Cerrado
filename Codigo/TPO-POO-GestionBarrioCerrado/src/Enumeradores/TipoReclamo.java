package Enumeradores;

public enum TipoReclamo {
    SEGURIDAD("ALTA"),
    MANTENIMIENTO("MEDIA"),
    CONVIVENCIA("BAJA"),
    ADMINISTRATIVO("BAJA");

    private final String prioridad;

    TipoReclamo(String prioridad){
        this.prioridad = prioridad;
    }

    public String getPrioridad() {
        return prioridad;
    }
}
