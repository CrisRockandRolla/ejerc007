package es.cic.gestorentradas.service;

import es.cic.gestorentradas.dto.SesionDto;
import es.cic.gestorentradas.dto.VentaDto;
import org.springframework.stereotype.Service;

@Service
public class VentaService {

    public String crearVenta() {
        VentaDto ventaDto1 = new VentaDto();
        ventaDto1.setId(1);
        ventaDto1.setNumEntradas(3);
        ventaDto1.setTotalPagar(ventaDto1.calcularTotalPagar());
        ventaDto1.setCancelada(false);
        ventaDto1.setDescuento(ventaDto1.calcularDescuento());
        ventaDto1.setNumEntradasCanceladas(0);
        ventaDto1.setSesionDto(SesionDto.SESION_1);


        return ventaDto1.toString();
    }

}
