package es.cic.gestorentradas.dto;

import java.util.List;

public class SesionDto {
    private Long id;
    private String hora;
    private String pelicula;
    private List<VentaDto> ventas;


    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getPelicula() {
        return pelicula;
    }

    public void setPelicula(String pelicula) {
        this.pelicula = pelicula;
    }

    public List<VentaDto> getVentas() {
        return ventas;
    }

    public void setVentas(List<VentaDto> ventas) {
        this.ventas = ventas;
    }
}
