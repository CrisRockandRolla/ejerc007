package es.cic.gestorentradas.dto;

import java.util.List;

public class CineDto {
    List<SalaDto> salas;
//    List<VentaDto> pedidos;

    public CineDto() {
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
}
