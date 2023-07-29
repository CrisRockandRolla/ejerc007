package es.cic.gestorentradas.assembler;

import es.cic.gestorentradas.gestion.SesionDatos;
import es.cic.gestorentradas.gestion.VentaDatos;

public class AssemblerVenta {

    public static VentaDatos assembleVenta(int numEntradas, SesionDatos sesionDatos) {

        VentaDatos ventaDatos1 = new VentaDatos();
        ventaDatos1.setNumEntradas(numEntradas);
        ventaDatos1.setTotalPagar(ventaDatos1.calcularTotalPagar());
        ventaDatos1.setCancelada(false);
        ventaDatos1.setDescuento(ventaDatos1.calcularDescuento());
        ventaDatos1.setNumEntradasCanceladas(0);
        ventaDatos1.setSesionDto(sesionDatos);
        return ventaDatos1;
    }
}
