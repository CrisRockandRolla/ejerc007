package es.cic.gestorentradas.gestion;

import es.cic.gestorentradas.assembler.AssemblerVenta;
import es.cic.gestorentradas.excepciones.VentaException;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        boolean ventaIdExiste = getSalasCine(idCine).stream()
                .flatMap(sala -> sala.getSesiones().stream())
                .anyMatch(sesion -> sesion.getId().equalsIgnoreCase(venta.getSesionDto().getId())
                        && sesion.getVentas().stream().anyMatch(ventaDatos -> ventaDatos.getId().equals(venta.getId())));

        if (!ventaIdExiste) {
            getSalasCine(idCine).stream()
                    .flatMap(sala -> sala.getSesiones().stream())
                    .filter(sesion -> sesion.getId().equalsIgnoreCase(venta.getSesionDto().getId()))
                    .findFirst()
                    .ifPresent(sesion -> sesion.getVentas().add(venta));
        } else {
            throw new VentaException("Ya existe una venta con el mismo ID.");
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

    //TODO hace que mostrar sea buscar y devuelva el objeto a buscar
    public static String mostrar(CineDatos cine) {// etiqueta <pre> para mantener los espacios en blanco, saltos de línea y tabulaciones
        final StringBuilder sb = new StringBuilder("<pre>");
        getSalasCine(cine.getId()).forEach(sala ->
                sala.getSesiones().forEach(sesion ->
                        pintarDatos(sb, sala, sesion, cine)));
        sb.append("\n").append(datosCine(cine.getSalas()))
                .append("</pre>").append("\n");
        return sb.toString();
    }

    public static String mostrar(SalaDatos sal, CineDatos cine) {
        StringBuilder sb = new StringBuilder("<pre>");
        getSalasCine(CINE_1.getId()).forEach(sala -> {
            if (sala.getId().equalsIgnoreCase(sal.getId())) {
                sala.getSesiones().forEach(sesion ->
                        pintarDatos(sb, sala, sesion, cine));
            }
        });
        sb.append("</pre>");
        sb.append("\n").append(datosSala(getSalasCine(cine.getId()), sal.getId())).append("\n").append("</pre>").append("\n");
        return sb.toString();
    }

    public static String mostrar(SesionDatos session, CineDatos cine) {
        StringBuilder sb = new StringBuilder("<pre>");
        getSalasCine(cine.getId()).forEach(sala ->
                sala.getSesiones().forEach(sesion -> {
                    if (sesion.getId().equalsIgnoreCase(session.getId())) {
                        pintarDatos(sb, sala, sesion, cine);
                    }
                }));
        sb.append("\n").append(datosSesion(getSalasCine(cine.getId()), session.getId())).append("</pre>");
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
                .orElseThrow(() -> new VentaException("No se ha encontrado la venta con id " + idVenta + "\n"));
    }

    public static void eliminarVenta(String idVenta) {
        VentaDatos venta = buscarVenta(idVenta);
        SesionDatos sesion = venta.getSesionDto();
        sesion.getVentas().remove(venta);

    }

    public static void reiniciarBD(String idVenta) {
        mapaCine.clear();
        addCine(CINE_1.getId());
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
        AssemblerVenta.assembleVenta(2, SESION_2);
        GestorVentasCines.addVenta(ventaDatos, CINE_1.getId());
        return ventaDatos;
    }
}
