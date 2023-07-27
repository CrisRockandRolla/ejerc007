package es.cic.gestorentradas.dto;

import java.util.ArrayList;
import java.util.List;

public enum SesionDto {

    SESION_1("17:00", "Film1", 0, 100, new ArrayList<>()),
    SESION_2("20:00", "Film1", 0, 100, new ArrayList<>()),
    SESION_3("22:30", "Film2", 0, 100, new ArrayList<>()),
    SESION_4("17:00", "Film3", 0, 50, new ArrayList<>()),
    SESION_5("20:00", "Film2", 0, 50, new ArrayList<>()),
    SESION_6("22:30", "Film4", 0, 50, new ArrayList<>()),
    SESION_7("17:00", "Film5", 0, 20, new ArrayList<>()),
    SESION_8("20:00", "Film3", 0, 20, new ArrayList<>()),
    SESION_9("22:30", "Film5", 0, 20, new ArrayList<>());

    private String id;
    private String hora;
    private String pelicula;
    private int entradasVendidas;
    private int entradasDisponibles;
    private List<VentaDto> ventas;

    SesionDto(String hora, String pelicula, int entradasVendidas, int entradasDisponibles, List<VentaDto> ventas) {
        this.id = name();
        this.hora = hora;
        this.pelicula = pelicula;
        this.entradasVendidas = entradasVendidas;
        this.entradasDisponibles = entradasDisponibles;
        this.ventas = ventas;
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
}
