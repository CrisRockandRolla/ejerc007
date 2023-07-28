package es.cic.gestorentradas.cache;

import es.cic.gestorentradas.dto.SalaDto;
import es.cic.gestorentradas.dto.SesionDto;
import es.cic.gestorentradas.dto.VentaDto;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static es.cic.gestorentradas.dto.CineDto.CINE_1;

public class CineCache {
    private static Map<String, List<SalaDto>> mapaCine = new HashMap<>();

    public static void addCine() {
        mapaCine.put(CINE_1.getId(), Arrays.asList(SalaDto.SALA_1, SalaDto.SALA_2, SalaDto.SALA_3));
    }

    public static void addVenta(VentaDto ventaDto) {
        List<SalaDto> salasCine = getSalasCine(CINE_1.getId());
        for (SalaDto sala : salasCine) {
            for (SesionDto sesion : sala.getSesiones()) {
                if (sesion.getId().equalsIgnoreCase(ventaDto.getSesionDto().getId())) {
                    sesion.getVentas().add(ventaDto);
                }
            }
        }
    }


    public static List<SalaDto> getSalasCine(String cineId) {
        return mapaCine.get(cineId);
    }

    public static Map<String, List<SalaDto>> getMapaCine() {
        return mapaCine;
    }

    public static void setMapaCine(Map<String, List<SalaDto>> mapaCine) {
        CineCache.mapaCine = mapaCine;
    }

    public static String miToString() {
        final StringBuilder sb = new StringBuilder();
        mapaCine.get(CINE_1.getId()).forEach(sala -> {
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
