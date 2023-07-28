package es.cic.gestorentradas.dto;

import java.util.Arrays;
import java.util.List;

public enum SalaDto {

    SALA_1(100, Arrays.asList(SesionDto.SESION_1, SesionDto.SESION_2, SesionDto.SESION_3)),
    SALA_2(50, Arrays.asList(SesionDto.SESION_4, SesionDto.SESION_5, SesionDto.SESION_6)),
    SALA_3(20, Arrays.asList(SesionDto.SESION_7, SesionDto.SESION_8, SesionDto.SESION_9));

    private String id;
    private int aforo;


    private List<SesionDto> sesiones;

    SalaDto(int aforo, List<SesionDto> sesiones) {
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

    public List<SesionDto> getSesiones() {
        return sesiones;
    }

    public void setSesiones(List<SesionDto> sesiones) {
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
