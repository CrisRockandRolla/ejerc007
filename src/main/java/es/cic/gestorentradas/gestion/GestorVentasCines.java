package es.cic.gestorentradas.gestion;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static es.cic.gestorentradas.gestion.CineDatos.CINE_1;

public class GestorVentasCines {
    private static final Map<String, List<SalaDatos>> mapaCine = new HashMap<>();

//    private int numEntradasVendidas;
//    private double totalRecaudado;
//    private double totalDescuentos;
//    private double numeroVentasDescuento;
//    private int numEntradasNoVendidas;
//    private String pelicula;
//    private String idSala;

    public static void addCine() {
        mapaCine.put(CINE_1.getId(), Arrays.asList(SalaDatos.SALA_1, SalaDatos.SALA_2, SalaDatos.SALA_3));
    }

    public static void addVenta(VentaDatos ventaDatos) {
        List<SalaDatos> salasCine = getSalasCine(CINE_1.getId());
        salasCine.stream()
                .flatMap(sala -> sala.getSesiones().stream())
                .filter(sesion -> sesion.getId().equalsIgnoreCase(ventaDatos.getSesionDto().getId()))
                .findFirst()
                .ifPresent(sesion -> sesion.getVentas().add(ventaDatos));
    }

    public static void actualizarEntradasDisponibles(SesionDatos sesionDatos, int entradasVendidas) {
        mapaCine.get(CINE_1.getId()).forEach(sala -> {
            sala.getSesiones().forEach(sesion -> {
                if (sesion.getId().equalsIgnoreCase(sesionDatos.getId())) {
                    sesion.setEntradasDisponibles(sesion.getEntradasDisponibles() - entradasVendidas);
                }
            });
        });
    }

//    public static void modificarVenta(VentaDatos ventaDto) {
//        List<SalaDatos> salasCine = getSalasCine(CINE_1.getId());
//        salasCine.stream()
//                .flatMap(sala -> sala.getSesiones().stream())
//                .filter(sesion -> sesion.getId().equalsIgnoreCase(ventaDto.getSesionDto().getId()))
//                .findFirst()
//                .ifPresent(sesion -> sesion.getVentas().add(ventaDto));
//    }

    public static String mostrarCine(CineDatos cine) {// etiqueta <pre> para mantener los espacios en blanco, saltos de linea y tabulaciones
        final StringBuilder sb = new StringBuilder("<pre>" + cine.getId() + "\n");

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

        sb.append("</pre>");
        return sb.toString();
    }

    public static String mostrarSala(SalaDatos sal) {
        final StringBuilder sb = new StringBuilder("<pre>");

        mapaCine.get(CINE_1.getId()).forEach(sala -> {
            if (sala.getId().equalsIgnoreCase(sal.getId())) {
                sb.append("\t").append(sala.getId()).append("\n");
                sala.getSesiones().forEach(sesion -> {
                    sb.append("\t\t").append(sesion.getId())
                            .append(": ").append(sesion.getHora())
                            .append(", ").append(sesion.getPelicula())
                            .append("\n\t\t\t Entradas disponibles: ").append(sesion.getEntradasDisponibles())
                            .append("\n");
                });
            }
        });

        sb.append("</pre>");
        return sb.toString();
    }

    public static String mostrarSesion(SesionDatos session) {
        final StringBuilder sb = new StringBuilder("<pre>");
        mapaCine.get(CINE_1.getId()).forEach(sala -> {

            sala.getSesiones().forEach(sesion -> {
                if (sesion.getId().equalsIgnoreCase(session.getId())) {
                    sb.append("\t\t").append(sesion.getId())
                            .append(": ").append(sesion.getHora())
                            .append(", ").append(sesion.getPelicula())
                            .append("\n\t\t\t Entradas disponibles: ").append(sesion.getEntradasDisponibles())
                            .append("\n");
                }
            });
        });

        sb.append("</pre>");
        return sb.toString();
    }

    public static int entradasDisponibles(SesionDatos session) {
        final int[] entradasDisponibles = {0};
        mapaCine.get(CINE_1.getId()).forEach(sala -> {
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
    public static String datosCine() {
        final StringBuilder sb = new StringBuilder("<pre>" + CINE_1.getId() + "\t");
        sb.append(datosCineMostrar().toString()).append("\n");
        mapaCine.get(CINE_1.getId()).forEach(sala -> {
            sb.append("\t").append(sala.getId()).append("\t")
                    .append(datosSalaMostrar(sala.getId()).toString());
            sb.append("\n");
            sala.getSesiones().forEach(sesion -> {
                sb.append("\t\t").append(sesion.getId())
                        .append(": ").append(sesion.getHora())
                        .append(", ").append(sesion.getPelicula())
                        .append("\t").append(datosSesionMostrar(sesion.getId()).toString())
                        .append("\n\t\t\t Entradas disponibles: ").append(sesion.getEntradasDisponibles())
                        .append("\n");
            });
        });
        sb.append("</pre>");
        return sb.toString();
    }

    public static Map<String, Number> datosCineMostrar() {
        int aforo = 0, entradasVendidas = 0;
        double totalRecaudado = 0, totalDescuentos = 0, ocupacion = 0;
        for (SalaDatos sala : getSalasCine(CINE_1.getId())) {

            for (SesionDatos sesion : sala.getSesiones()) {
                aforo += sala.getAforo();
                for (VentaDatos venta : sesion.getVentas()) {
                    entradasVendidas += venta.getNumEntradas();
                    totalRecaudado += venta.getTotalPagar();
                    totalDescuentos += venta.getDescuento();
                }
            }
        }
        ocupacion = (double) entradasVendidas * 100 / aforo;

        Map<String, Number> datosCineMap = new HashMap<>();
        datosCineMap.put("Aforo total", aforo);
        datosCineMap.put("Entradas vendidas", entradasVendidas);
        datosCineMap.put("Total Recaudado", totalRecaudado);
        datosCineMap.put("Ocupacion", ocupacion);
        datosCineMap.put("Total descuentos", totalDescuentos);

//        Map.of("Aforo total", aforo, "Entradas vendidas", entradasVendidas, "Total Recaudado", totalRecaudado,
//                "Ocupacion", ocupacion, "Total descuentos", totalDescuentos);
        return datosCineMap;
    }

    //
    public static Map<String, Number> datosSalaMostrar(String idSala) {
        int aforo = 0, entradasVendidas = 0;
        double totalRecaudado = 0, totalDescuentos = 0, ocupacion = 0;
        for (SalaDatos sala : getSalasCine(CINE_1.getId())) {
            if (!sala.getId().equalsIgnoreCase(idSala)) continue;
            for (SesionDatos sesion : sala.getSesiones()) {
                aforo += sala.getAforo();
                for (VentaDatos venta : sesion.getVentas()) {
                    entradasVendidas += venta.getNumEntradas();
                    totalRecaudado += venta.getTotalPagar();
                    totalDescuentos += venta.getDescuento();
                }
            }
        }
        ocupacion = (double) entradasVendidas * 100 / aforo;

        Map<String, Number> datosCineMap = new HashMap<>();
        datosCineMap.put("Aforo total", aforo);
        datosCineMap.put("Entradas vendidas", entradasVendidas);
        datosCineMap.put("Total Recaudado", totalRecaudado);
        datosCineMap.put("Ocupacion", ocupacion);
        datosCineMap.put("Total descuentos", totalDescuentos);

//        Map.of("Aforo total", aforo, "Entradas vendidas", entradasVendidas, "Total Recaudado", totalRecaudado,
//                "Ocupacion", ocupacion, "Total descuentos", totalDescuentos);
        return datosCineMap;
    }

    //
    public static Map<String, Number> datosSesionMostrar(String idSesion) {
        int aforo = 0, entradasVendidas = 0;
        double totalRecaudado = 0, totalDescuentos = 0, ocupacion = 0;
        for (SalaDatos sala : getSalasCine(CINE_1.getId())) {
            for (SesionDatos sesion : sala.getSesiones()) {
                if (!sesion.getId().equalsIgnoreCase(idSesion)) continue;
                aforo += sala.getAforo();
                for (VentaDatos venta : sesion.getVentas()) {
                    entradasVendidas += venta.getNumEntradas();
                    totalRecaudado += venta.getTotalPagar();
                    totalDescuentos += venta.getDescuento();
                }
            }
        }
        ocupacion = (double) entradasVendidas * 100 / aforo;

        Map<String, Number> datosCineMap = new HashMap<>();
        datosCineMap.put("Aforo total", aforo);
        datosCineMap.put("Entradas vendidas", entradasVendidas);
        datosCineMap.put("Total Recaudado", totalRecaudado);
        datosCineMap.put("Ocupacion", ocupacion);
        datosCineMap.put("Total descuentos", totalDescuentos);

//        Map.of("Aforo total", aforo, "Entradas vendidas", entradasVendidas, "Total Recaudado", totalRecaudado,
//                "Ocupacion", ocupacion, "Total descuentos", totalDescuentos);
        return datosCineMap;
    }

    public static List<SalaDatos> getSalasCine(String cineId) {
        return mapaCine.get(cineId);
    }

}
