package es.cic.gestorentradas.gestion;

import es.cic.gestorentradas.excepciones.VentaException;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static es.cic.gestorentradas.gestion.CineDatos.CINE_1;

public class GestorVentasCines {
    private static final Map<String, List<SalaDatos>> mapaCine = new HashMap<>();

    public static List<SalaDatos> getSalasCine(String cineId) {
        return mapaCine.get(cineId);
    }

    public static void addCine(String id) {
        mapaCine.put(id, Arrays.asList(SalaDatos.SALA_1, SalaDatos.SALA_2, SalaDatos.SALA_3));
    }

    public static void removeVentas() {
        getSalasCine(CINE_1.getId()).forEach(salaDatos -> {
            salaDatos.getSesiones().forEach(sesionDatos -> {
                sesionDatos.getVentas().clear();
            });
        });
    }

    public static void removeVentaPorId(String idVenta) {
        getSalasCine(CINE_1.getId()).forEach(salaDatos -> {
            salaDatos.getSesiones().forEach(sesionDatos -> {
                sesionDatos.getVentas().removeIf(venta -> venta.getId().equals(idVenta));
            });
        });
    }

    public static void addVenta(VentaDatos venta, String idCine) {
//        getSalasCine(idCine).stream()
//                .flatMap(sala -> sala.getSesiones().stream())
//                .filter(sesion -> sesion.getId().equalsIgnoreCase(venta.getSesionDto().getId()))
//                .findFirst()
//                .ifPresent(sesion -> sesion.getVentas().add(venta));
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
        mapaCine.get(idCine).forEach(sala -> {
            sala.getSesiones().forEach(sesion -> {
                if (sesion.getId().equalsIgnoreCase(sesionDatos.getId())) {
                    sesion.setEntradasDisponibles(sesion.getEntradasDisponibles() - entradas);
                }
            });
        });
    }

    //TODO hace que mostrar sea buscar y devuelva el objeto a buscar
    public static String mostrar(CineDatos cine) {// etiqueta <pre> para mantener los espacios en blanco, saltos de linea y tabulaciones
        final StringBuilder sb = new StringBuilder("<pre>");
        mapaCine.get(cine.getId()).forEach(sala -> {
            sala.getSesiones().forEach(sesion -> {
                pintarDatos(sb, sala, sesion, cine);
            });
        });
        sb.append("</pre>");
        return sb.toString();
    }

    public static String mostrar(SalaDatos sal, CineDatos cine) {
        StringBuilder sb = new StringBuilder("<pre>");
        getSalasCine(CINE_1.getId()).forEach(sala -> {
            if (sala.getId().equalsIgnoreCase(sal.getId())) {
                sala.getSesiones().forEach(sesion -> {
                    pintarDatos(sb, sala, sesion, cine);
                });
            }
        });
        sb.append("</pre>");
        return sb.toString();
    }

    public static String mostrar(SesionDatos session, CineDatos cine) {
        StringBuilder sb = new StringBuilder("<pre>");
        getSalasCine(cine.getId()).forEach(sala -> {
            sala.getSesiones().forEach(sesion -> {
                if (sesion.getId().equalsIgnoreCase(session.getId())) {
                    pintarDatos(sb, sala, sesion, cine);
                }
            });
        });
        sb.append("</pre>");
        return sb.toString();
    }

    private static void pintarDatos(StringBuilder sb, SalaDatos sala, SesionDatos sesion, CineDatos cine) {
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

    public static int entradasDisponibles(SesionDatos session, CineDatos cine) {
        final int[] entradasDisponibles = {0};
        getSalasCine(cine.getId()).forEach(sala -> {
            sala.getSesiones().forEach(sesion -> {
                if (sesion.getId().equalsIgnoreCase(session.getId())) {
                    entradasDisponibles[0] = sesion.getEntradasDisponibles();
                }
            });
        });
        return entradasDisponibles[0];
    }


    /*
     * PARA ESTADÍSTICAS
     */
//    public static String mostrarEstadisticasCine(CineDatos cine) {
//        final StringBuilder sb = new StringBuilder("<pre>" + cine.getId() + "\t");
//        sb.append(datosCineMostrar(cine)).append("\n");
//        getSalasCine(cine.getId()).forEach(sala -> {
//            sb.append("\t").append(sala.getId()).append("\t")
//                    .append(datosSalaMostrar(sala.getId()));
//            sb.append("\n");
//            sala.getSesiones().forEach(sesion -> {
//                sb.append("\t\t").append(sesion.getId())
//                        .append(": ").append(sesion.getHora())
//                        .append(", ").append(sesion.getPelicula())
//                        .append("\t").append(datosSesionMostrar(sesion.getId()))
//                        .append("\n\t\t\t Entradas disponibles: ").append(sesion.getEntradasDisponibles())
//                        .append("\n");
//            });
//        });
//        sb.append("</pre>");
//        return sb.toString();
//    }

//    public static Map<String, Number> datosCineMostrar(CineDatos cine) {
//        int aforo = 0, entradasVendidas = 0;
//        double totalRecaudado = 0, totalDescuentos = 0, ocupacion = 0;
//        for (SalaDatos sala : getSalasCine(cine.getId())) {
//
//            for (SesionDatos sesion : sala.getSesiones()) {
//                aforo += sala.getAforo();
//                for (VentaDatos venta : sesion.getVentas()) {
//                    entradasVendidas += venta.getNumEntradas();
//                    totalRecaudado += venta.getTotalPagar();
//                    totalDescuentos += venta.getDescuento();
//                }
//            }
//        }
//        ocupacion = (double) entradasVendidas * 100 / aforo;
//
//        //        Map.of("Aforo total", aforo, "Entradas vendidas", entradasVendidas, "Total Recaudado", totalRecaudado,
////                "Ocupacion", ocupacion, "Total descuentos", totalDescuentos);
//        return getEstadisticasCineMap(aforo, entradasVendidas, totalRecaudado, totalDescuentos, ocupacion);
//    }

    //
//    public static Map<String, Number> datosSalaMostrar(String idSala) {
//        int aforo = 0, entradasVendidas = 0;
//        double totalRecaudado = 0, totalDescuentos = 0, ocupacion = 0;
//        for (SalaDatos sala : getSalasCine(CINE_1.getId())) {
//            if (!sala.getId().equalsIgnoreCase(idSala)) continue;
//            for (SesionDatos sesion : sala.getSesiones()) {
//                aforo += sala.getAforo();
//                for (VentaDatos venta : sesion.getVentas()) {
//                    entradasVendidas += venta.getNumEntradas();
//                    totalRecaudado += venta.getTotalPagar();
//                    totalDescuentos += venta.getDescuento();
//                }
//            }
//        }
//        ocupacion = (double) entradasVendidas * 100 / aforo;
//
//        //        Map.of("Aforo total", aforo, "Entradas vendidas", entradasVendidas, "Total Recaudado", totalRecaudado,
////                "Ocupacion", ocupacion, "Total descuentos", totalDescuentos);
//        return getEstadisticasCineMap(aforo, entradasVendidas, totalRecaudado, totalDescuentos, ocupacion);
//    }
//
//    //
//    public static Map<String, Number> datosSesionMostrar(String idSesion) {
//        int aforo = 0, entradasVendidas = 0;
//        double totalRecaudado = 0, totalDescuentos = 0, ocupacion = 0;
//        for (SalaDatos sala : getSalasCine(CINE_1.getId())) {
//            for (SesionDatos sesion : sala.getSesiones()) {
//                if (!sesion.getId().equalsIgnoreCase(idSesion)) continue;
//                aforo += sala.getAforo();
//                for (VentaDatos venta : sesion.getVentas()) {
//                    entradasVendidas += venta.getNumEntradas();
//                    totalRecaudado += venta.getTotalPagar();
//                    totalDescuentos += venta.getDescuento();
//                }
//            }
//        }
//        ocupacion = (double) entradasVendidas * 100 / aforo;
//
//
//        //        Map.of("Aforo total", aforo, "Entradas vendidas", entradasVendidas, "Total Recaudado", totalRecaudado,
//        //        "Ocupacion", ocupacion, "Total descuentos", totalDescuentos);
//        return getEstadisticasCineMap(aforo, entradasVendidas, totalRecaudado, totalDescuentos, ocupacion);
//    }


//    private static Map<String, Number> getEstadisticasCineMap(int aforo, int entradasVendidas, double totalRecaudado, double totalDescuentos, double ocupacion) {
//        Map<String, Number> datosCineMap = new HashMap<>();
//        datosCineMap.put("Aforo total", aforo);
//        datosCineMap.put("Entradas vendidas", entradasVendidas);
//        datosCineMap.put("Total Recaudado", totalRecaudado);
//        datosCineMap.put("Ocupacion", ocupacion);
//        datosCineMap.put("Total descuentos", totalDescuentos);
//        return datosCineMap;
//    }
}
