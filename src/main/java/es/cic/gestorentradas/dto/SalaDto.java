package es.cic.gestorentradas.dto;

import java.util.List;

public class SalaDto {
    private Long id;
    private int aforo;
    private List<SesionDto> sesiones;

    public SalaDto() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
}
