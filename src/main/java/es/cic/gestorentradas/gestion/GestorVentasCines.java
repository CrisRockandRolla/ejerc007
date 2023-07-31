package es.cic.gestorentradas.gestion;

import es.cic.gestorentradas.excepciones.VentaException;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static es.cic.gestorentradas.excepciones.AvisosExcepciones.ID_NO_ENCONTRADO;
import static es.cic.gestorentradas.excepciones.AvisosExcepciones.ID_REPETIDO;
import static es.cic.gestorentradas.gestion.CineDatos.CINE_1;
import static es.cic.gestorentradas.gestion.EstadisticasDatos.*;
import static es.cic.gestorentradas.gestion.SesionDatos.SESION_2;

public class GestorVentasCines {
    private static final Map<String, List<SalaDatos>> mapaCine = new HashMap<>();

    public static List<SalaDatos> getSalasCine(String cineId) {
        return mapaCine.get(cineId);
    }

    public static void addCine(String id) {
        mapaCine.put(id, Arrays.asList(SalaDatos.SALA_1, SalaDatos.SALA_2, SalaDatos.SALA_3));
    }

    public static void removeVentaPorId(String idVenta) {
        getSalasCine(CINE_1.getId()).forEach(salaDatos ->
                salaDatos.getSesiones().forEach(sesionDatos ->
                        sesionDatos.getVentas().removeIf(venta -> venta.getId().equals(idVenta))));
    }

    public static void addVenta(VentaDatos venta, String idCine) {
        FUERA:
        for (SalaDatos salaDatos : getSalasCine(CINE_1.getId())) {
            for (SesionDatos sesionDatos : salaDatos.getSesiones()) {
                if (!sesionDatos.getId().equalsIgnoreCase(venta.getSesionDto().getId())) continue;
                sesionDatos.getVentas().stream()
                        .filter(ventaDatos -> ventaDatos.getId().equalsIgnoreCase(venta.getId()))
                        .findFirst()
                        .ifPresent(ventaDatos -> {
                            throw new VentaException(ID_REPETIDO);
                        });
                sesionDatos.getVentas().add(venta);
                break FUERA;
            }
        }
    }

    public static void actualizarEntradasDisponibles(SesionDatos sesionDatos, int entradas, String idCine) {
        mapaCine.get(idCine).forEach(sala ->
                sala.getSesiones().forEach(sesion -> {
                    if (sesion.getId().equalsIgnoreCase(sesionDatos.getId())) {
                        sesion.setEntradasDisponibles((sesion.getEntradasDisponibles() - entradas));
                    }
                }));
    }

    public static String mostrar(CineDatos cine) {
        final StringBuilder sb = new StringBuilder();
        getSalasCine(cine.getId()).forEach(sala ->
                sala.getSesiones().forEach(sesion ->
                        pintarDatos(sb, sala, sesion, cine)));
        sb.append("\n").append(datosCine(cine.getSalas()))
                .append("\n");
        return sb.toString();
    }

    public static String mostrar(SalaDatos sal, CineDatos cine) {
        StringBuilder sb = new StringBuilder();
        getSalasCine(CINE_1.getId()).forEach(sala -> {
            if (sala.getId().equalsIgnoreCase(sal.getId())) {
                sala.getSesiones().forEach(sesion ->
                        pintarDatos(sb, sala, sesion, cine));
            }
        });
        sb.append("\n").append(datosSala(getSalasCine(cine.getId()), sal.getId())).append("\n");
        return sb.toString();
    }

    public static String mostrar(SesionDatos session, CineDatos cine) {
        StringBuilder sb = new StringBuilder();
        getSalasCine(cine.getId()).forEach(sala ->
                sala.getSesiones().forEach(sesion -> {
                    if (sesion.getId().equalsIgnoreCase(session.getId())) {
                        pintarDatos(sb, sala, sesion, cine);
                    }
                }));
        sb.append("\n").append(datosSesion(getSalasCine(cine.getId()), session.getId()));
        sb.append("\n");
        return sb.toString();
    }

    public static void pintarDatos(StringBuilder sb, SalaDatos sala, SesionDatos sesion, CineDatos cine) {
        sb.append(cine.getId())
                .append("\t\t").append(sala.getId())
                .append("\t\t").append(sesion.getId())
                .append(": ").append(sesion.getHora())
                .append(", ").append(sesion.getPelicula())
                .append("\t\t Entradas disponibles: ").append(sesion.getEntradasDisponibles())
                .append("\n");
    }

    public static VentaDatos buscarVenta(String idVenta) {
        return getSalasCine("CINE_1").stream()
                .flatMap(salaDatos -> salaDatos.getSesiones().stream())
                .flatMap(sesion -> sesion.getVentas().stream())
                .filter(venta -> venta.getId().equalsIgnoreCase(idVenta))
                .findFirst()
                .orElseThrow(() -> new VentaException(ID_NO_ENCONTRADO + idVenta + "\n"));
    }

    public static void eliminarVenta(String idVenta) {
        VentaDatos venta = buscarVenta(idVenta);
        SesionDatos sesion = venta.getSesionDto();
        sesion.getVentas().remove(venta);
    }

    public static int entradasDisponibles(SesionDatos session, CineDatos cine) {
        final int[] entradasDisponibles = {0};
        getSalasCine(cine.getId()).forEach(sala ->
                sala.getSesiones().forEach(sesion -> {
                    if (sesion.getId().equalsIgnoreCase(session.getId())) {
                        entradasDisponibles[0] = sesion.getEntradasDisponibles();
                    }
                }));
        return entradasDisponibles[0];
    }

    public static VentaDatos preCargarVenta(int numEntradas) {
        VentaDatos ventaDatos = new VentaDatos();
        ventaDatos.setNumEntradas(numEntradas);
        ventaDatos.setTotalPagar(ventaDatos.calcularTotalPagar());
        ventaDatos.setDescuento(ventaDatos.calcularDescuento());
        ventaDatos.setSesionDto(SESION_2);
        GestorVentasCines.addVenta(ventaDatos, CINE_1.getId());
        return ventaDatos;
    }
}
