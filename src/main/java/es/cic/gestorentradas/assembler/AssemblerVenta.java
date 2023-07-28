package es.cic.gestorentradas.assembler;

import es.cic.gestorentradas.dto.SesionDto;
import es.cic.gestorentradas.dto.VentaDto;

public class AssemblerVenta {

    public static VentaDto assembleVenta(long id, int numEntradas, SesionDto sesionDto) {

        VentaDto ventaDto1 = new VentaDto();
        ventaDto1.setId(id);
        ventaDto1.setNumEntradas(numEntradas);
        ventaDto1.setTotalPagar(ventaDto1.calcularTotalPagar());
        ventaDto1.setCancelada(false);
        ventaDto1.setDescuento(ventaDto1.calcularDescuento());
        ventaDto1.setNumEntradasCanceladas(0);
        ventaDto1.setSesionDto(sesionDto);
        return ventaDto1;
    }
}
