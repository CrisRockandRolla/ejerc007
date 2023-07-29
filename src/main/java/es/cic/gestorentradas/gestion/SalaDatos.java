package es.cic.gestorentradas.gestion;

import java.util.Arrays;
import java.util.List;

public enum SalaDatos {

    SALA_1(100, Arrays.asList(SesionDatos.SESION_1, SesionDatos.SESION_2, SesionDatos.SESION_3)),
    SALA_2(50, Arrays.asList(SesionDatos.SESION_4, SesionDatos.SESION_5, SesionDatos.SESION_6)),
    SALA_3(20, Arrays.asList(SesionDatos.SESION_7, SesionDatos.SESION_8, SesionDatos.SESION_9));

    private String id;
    private int aforo;


    private List<SesionDatos> sesiones;

    SalaDatos(int aforo, List<SesionDatos> sesiones) {
        this.id = name();
        this.aforo = aforo;
        this.sesiones = sesiones;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getAforo() {
        return aforo;
    }

    public void setAforo(int aforo) {
        this.aforo = aforo;
    }

    public List<SesionDatos> getSesiones() {
        return sesiones;
    }

    public void setSesiones(List<SesionDatos> sesiones) {
        this.sesiones = sesiones;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(id).append('\t');
        sb.append("\taforo=").append(aforo);
        return sb.toString();
    }
}
