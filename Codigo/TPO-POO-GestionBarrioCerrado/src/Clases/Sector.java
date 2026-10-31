package Clases;

import java.util.ArrayList;
import java.util.List;

public class Sector {
    private int idSector;
    private String nombre;
    private List<Equipo> luminariasDelSector=new ArrayList<>();

    public List<Equipo> getLuminariasDelSector() {
        return luminariasDelSector;
    }
}
