package es.cic.gestorentradas.dto;

import java.util.ArrayList;
import java.util.List;

import static es.cic.gestorentradas.dto.SalaDto.*;

public enum SesionDto {

    SESION_1("17:00", "Film1", 0, 100, new ArrayList<>(), SALA_1),
    SESION_2("20:00", "Film1", 0, 100, new ArrayList<>(), SALA_1),
    SESION_3("22:30", "Film2", 0, 100, new ArrayList<>(), SALA_1),
    SESION_4("17:00", "Film3", 0, 50, new ArrayList<>(), SALA_2),
    SESION_5("20:00", "Film2", 0, 50, new ArrayList<>(), SALA_2),
    SESION_6("22:30", "Film4", 0, 50, new ArrayList<>(), SALA_2),
    SESION_7("17:00", "Film5", 0, 20, new ArrayList<>(), SALA_3),
    SESION_8("20:00", "Film3", 0, 20, new ArrayList<>(), SALA_3),
    SESION_9("22:30", "Film5", 0, 20, new ArrayList<>(), SALA_3);

    private String id;
    private String hora;
    private String pelicula;
    private int entradasVendidas;
    private int entradasDisponibles;
    private List<VentaDto> ventas;
    private SalaDto salaDto;

    SesionDto(String hora, String pelicula, int entradasVendidas, int entradasDisponibles, List<VentaDto> ventas, SalaDto salaDto) {
        this.id = name();
        this.hora = hora;
        this.pelicula = pelicula;
        this.entradasVendidas = entradasVendidas;
        this.entradasDisponibles = entradasDisponibles;
        this.ventas = ventas;
        this.salaDto = salaDto;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public int getEntradasVendidas() {
        return entradasVendidas;
    }

    public int getEntradasDisponibles() {
        return entradasDisponibles;
    }

    public void setEntradasDisponibles(int entradasDisponibles) {
        this.entradasDisponibles = entradasDisponibles;
    }

    public void setEntradasVendidas(int entradasVendidas) {
        this.entradasVendidas = entradasVendidas;
    }

    public List<VentaDto> getVentas() {
        return ventas;
    }

    public void setVentas(List<VentaDto> ventas) {
        this.ventas = ventas;
    }

    public SalaDto getSalaDto() {
        return salaDto;
    }

    public void setSalaDto(SalaDto salaDto) {
        this.salaDto = salaDto;
    }

}
