package es.cic.gestorentradas.dto;

import java.util.Arrays;
import java.util.List;

public enum CineDto {

    CINE_1(Arrays.asList(SalaDto.SALA_1, SalaDto.SALA_2, SalaDto.SALA_3));
    private String id;
    private List<SalaDto> salas;
//    List<VentaDto> pedidos;

//    public CineDto() {
//    }


    CineDto(List<SalaDto> salas) {
        this.id = name();
        this.salas = salas;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<SalaDto> getSalas() {
        return salas;
    }

    public void setSalas(List<SalaDto> salas) {
        this.salas = salas;
    }

//    public List<VentaDto> getPedidos() {
//        return pedidos;
//    }
//
//    public void setPedidos(List<VentaDto> pedidos) {
//        this.pedidos = pedidos;
//    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(id).append("\n");
        salas.forEach(sala -> {
            sb.append("\t").append(sala.getId()).append("\n");
            sala.getSesiones().forEach(sesion -> {
                sb.append("\t\t").append(sesion.getId())
                        .append(": ").append(sesion.getHora())
                        .append(", ").append(sesion.getPelicula())
                        .append("\n\t\t\t Entradas disponibles: ").append(sesion.getEntradasDisponibles())
                        .append("\n");
            });
        });
        return sb.toString();
    }

}
