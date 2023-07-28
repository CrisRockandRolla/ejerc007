package es.cic.gestorentradas.dto;

import java.util.Arrays;
import java.util.List;

public enum CineDto {

    CINE_1(Arrays.asList(SalaDto.SALA_1, SalaDto.SALA_2, SalaDto.SALA_3));

    private final String id;
    private final List<SalaDto> salas;


    CineDto(List<SalaDto> salas) {
        this.id = name();
        this.salas = salas;
    }

    public String getId() {
        return id;
    }

    public List<SalaDto> getSalas() {
        return salas;
    }

}
