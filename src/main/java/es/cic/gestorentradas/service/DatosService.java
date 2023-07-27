package es.cic.gestorentradas.service;

import es.cic.gestorentradas.dto.*;
import org.springframework.stereotype.Service;

@Service
public class DatosService {


    public DatosDto get(CineDto cineDto) {

        throw new UnsupportedOperationException("sin implementar");
    }

    public DatosDto get(SalaDto salaDto) {

        throw new UnsupportedOperationException("sin implementar");
    }

    public String get(SesionDto sesionDto) {

        DatosDto datosDto = new DatosDto();

        datosDto.setIdSala(sesionDto.getSalaDto().getId());

        int entradasVendidas = datosDto.getNumEntradasVendidas();
        double totalRecaudado = datosDto.getTotalRecaudado();
        double totalDescuentos = datosDto.getTotalDescuentos();

        for (VentaDto venta : sesionDto.getVentas()) {
            entradasVendidas += venta.getNumEntradas();
            totalRecaudado += venta.getTotalPagar();
            totalDescuentos += venta.getTotalPagar();
        }

        datosDto.setNumEntradasVendidas(entradasVendidas);
        datosDto.setTotalRecaudado(totalRecaudado);
        datosDto.setTotalDescuentos(totalDescuentos);
        datosDto.setNumEntradasNoVendidas(sesionDto.getSalaDto().getAforo() - entradasVendidas);

        return datosDto.toString();
    }
}
