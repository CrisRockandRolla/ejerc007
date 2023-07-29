package es.cic.gestorentradas.gestion;

import java.util.ArrayList;
import java.util.List;

import static es.cic.gestorentradas.gestion.SalaDatos.*;

public enum SesionDatos {

    SESION_1("17:00", "Film1", 100, new ArrayList<>(), SALA_1),
    SESION_2("20:00", "Film1", 100, new ArrayList<>(), SALA_1),
    SESION_3("22:30", "Film2", 100, new ArrayList<>(), SALA_1),
    SESION_4("17:00", "Film3", 50, new ArrayList<>(), SALA_2),
    SESION_5("20:00", "Film2", 50, new ArrayList<>(), SALA_2),
    SESION_6("22:30", "Film4", 50, new ArrayList<>(), SALA_2),
    SESION_7("17:00", "Film5", 20, new ArrayList<>(), SALA_3),
    SESION_8("20:00", "Film3", 20, new ArrayList<>(), SALA_3),
    SESION_9("22:30", "Film5", 20, new ArrayList<>(), SALA_3);

    private String id;
    private String hora;
    private String pelicula;
    private int entradasVendidas;
    private int entradasDisponibles;
    private List<VentaDatos> ventas;
    private SalaDatos salaDatos;

    SesionDatos(String hora, String pelicula, int entradasDisponibles, List<VentaDatos> ventas, SalaDatos salaDatos) {
        this.id = name();
        this.hora = hora;
        this.pelicula = pelicula;
        this.entradasVendidas = 0;
        this.entradasDisponibles = entradasDisponibles;
        this.ventas = ventas;
        this.salaDatos = salaDatos;
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

    public List<VentaDatos> getVentas() {
        return ventas;
    }

    public void setVentas(List<VentaDatos> ventas) {
        this.ventas = ventas;
    }

    public SalaDatos getSalaDto() {
        return salaDatos;
    }

    public void setSalaDto(SalaDatos salaDatos) {
        this.salaDatos = salaDatos;
    }

}
