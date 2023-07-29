package es.cic.gestorentradas.gestion;

import java.util.Arrays;
import java.util.List;

public enum CineDatos {

    CINE_1(Arrays.asList(SalaDatos.SALA_1, SalaDatos.SALA_2, SalaDatos.SALA_3));

    private final String id;
    private final List<SalaDatos> salas;


    CineDatos(List<SalaDatos> salas) {
        this.id = name();
        this.salas = salas;
    }

    public String getId() {
        return id;
    }

    public List<SalaDatos> getSalas() {
        return salas;
    }

}
