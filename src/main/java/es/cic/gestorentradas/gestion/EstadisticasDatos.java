package es.cic.gestorentradas.gestion;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EstadisticasDatos {

    public EstadisticasDatos() {
    }

    public static Map<String, Number> calcularEstadisticas(List<SalaDatos> salasCine, String idSala, String idSesion) {
        int aforo = 0, entradasVendidas = 0;
        double totalRecaudado = 0, totalDescuentos = 0;


        for (SalaDatos sala : salasCine) {
            if (idSala != null && !sala.getId().equalsIgnoreCase(idSala)) continue;
            for (SesionDatos sesion : sala.getSesiones()) {
                if (idSesion != null && !sesion.getId().equalsIgnoreCase(idSesion)) continue;

                aforo += sala.getAforo();
                for (VentaDatos venta : sesion.getVentas()) {
                    entradasVendidas += venta.getNumEntradas();
                    totalRecaudado += venta.getTotalPagar();
                    totalDescuentos += venta.getDescuento();
                }
            }
        }

        double ocupacion = (double) entradasVendidas * 100 / aforo;

        return getEstadisticasCineMap(aforo, entradasVendidas, totalRecaudado, totalDescuentos, ocupacion);
    }

    private static Map<String, Number> getEstadisticasCineMap(int aforo, int entradasVendidas, double totalRecaudado, double totalDescuentos, double ocupacion) {
        Map<String, Number> datosCineMap = new HashMap<>();
        datosCineMap.put("Aforo total", aforo);
        datosCineMap.put("Entradas vendidas", entradasVendidas);
        datosCineMap.put("Total Recaudado", totalRecaudado);
        datosCineMap.put("Ocupacion", ocupacion);
        datosCineMap.put("Total descuentos", totalDescuentos);
        return datosCineMap;
    }

    public static String mostrarEstadisticasCine(CineDatos cine) {
        final StringBuilder sb = new StringBuilder(cine.getId() + "\t");
        sb.append(datosCine(cine.getSalas())).append("\n");
        GestorVentasCines.getSalasCine(cine.getId()).forEach(sala -> {
            sb.append("\t").append(sala.getId()).append("\t")
                    .append(datosSala(GestorVentasCines.getSalasCine(cine.getId()), sala.getId()));
            sb.append("\n");
            sala.getSesiones().forEach(sesion ->
                    sb.append("\t\t").append(sesion.getId())
                            .append(": ").append(sesion.getHora())
                            .append(", ").append(sesion.getPelicula())
                            .append("\t").append(datosSesion(GestorVentasCines.getSalasCine(cine.getId()), sesion.getId()))
                            .append("\t\t Entradas disponibles: ").append(sesion.getEntradasDisponibles())
                            .append("\n"));
        });
        return sb.toString();
    }


    public static Map<String, Number> datosCine(List<SalaDatos> salasCine) {
        return calcularEstadisticas(salasCine, null, null);
    }

    public static Map<String, Number> datosSala(List<SalaDatos> salasCine, String idSala) {
        return calcularEstadisticas(salasCine, idSala, null);
    }

    public static Map<String, Number> datosSesion(List<SalaDatos> salasCine, String idSesion) {
        return calcularEstadisticas(salasCine, null, idSesion);
    }
}
