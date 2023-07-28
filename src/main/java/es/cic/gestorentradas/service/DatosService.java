package es.cic.gestorentradas.service;

import es.cic.gestorentradas.cache.CineCache;
import es.cic.gestorentradas.dto.*;
import org.springframework.stereotype.Service;

import java.util.List;

import static es.cic.gestorentradas.dto.CineDto.CINE_1;

@Service
public class DatosService {


    public String get(CineDto cineDto) {

        DatosCache datosDto = new DatosCache();

        List<SalaDto> salas = CineCache.getSalasCine(CINE_1.getId());

        int entradasVendidas = datosDto.getNumEntradasVendidas();
        int aforo = 0;
        double totalRecaudado = datosDto.getTotalRecaudado();
        double totalDescuentos = datosDto.getTotalDescuentos();


        for (SalaDto sala : salas) {
            for (SesionDto sesion : sala.getSesiones()) {
                aforo += sala.getAforo();
                datosDto.setIdSala(sala.getId());
                for (VentaDto venta : sesion.getVentas()) {
                    entradasVendidas += venta.getNumEntradas();
                    totalRecaudado += venta.getTotalPagar();
                    totalDescuentos += venta.getDescuento();
                }
            }
        }

        datosDto.setNumEntradasVendidas(entradasVendidas);
        datosDto.setTotalRecaudado(totalRecaudado);
        datosDto.setTotalDescuentos(totalDescuentos);
        datosDto.setNumEntradasNoVendidas(aforo - entradasVendidas);

        return datosDto.toString();
    }

    public String get(SalaDto salaDto) {

        DatosCache datosDto = new DatosCache();
        List<SalaDto> salas = CineCache.getMapaCine().get(CINE_1.getId());

        int entradasVendidas = datosDto.getNumEntradasVendidas();
        int aforo = 0;
        double totalRecaudado = datosDto.getTotalRecaudado();
        double totalDescuentos = datosDto.getTotalDescuentos();

        for (SalaDto sala : salas) {
            if (sala.getId().equalsIgnoreCase(salaDto.getId())) {
                aforo = sala.getAforo();
                for (SesionDto sesion : sala.getSesiones()) {
                    datosDto.setIdSala(sala.getId());
                    for (VentaDto venta : sesion.getVentas()) {
                        entradasVendidas += venta.getNumEntradas();
                        totalRecaudado += venta.getTotalPagar();
                        totalDescuentos += venta.getDescuento();
                    }
                }
            }
        }

        datosDto.setNumEntradasVendidas(entradasVendidas);
        datosDto.setTotalRecaudado(totalRecaudado);
        datosDto.setTotalDescuentos(totalDescuentos);
        datosDto.setNumEntradasNoVendidas(aforo - entradasVendidas);

        return datosDto.toString();
    }

    public String get(SesionDto sesionDto) {

        DatosCache datosDto = new DatosCache();
        List<SalaDto> salas = CineCache.getMapaCine().get(CINE_1.getId());
        int entradasVendidas = datosDto.getNumEntradasVendidas();
        int aforo = 0;
        double totalRecaudado = datosDto.getTotalRecaudado();
        double totalDescuentos = datosDto.getTotalDescuentos();

        fuera:
        for (SalaDto sala : salas) {
            for (SesionDto sesion : sala.getSesiones()) {
                if (sesion.getId().equalsIgnoreCase(sesionDto.getId())) {
                    datosDto.setIdSala(sala.getId());
                    for (VentaDto venta : sesion.getVentas()) {
                        entradasVendidas += venta.getNumEntradas();
                        totalRecaudado += venta.getTotalPagar();
                        totalDescuentos += venta.getDescuento();
                    }
                    aforo = sala.getAforo();
                    break fuera;
                }
            }
        }

        datosDto.setNumEntradasVendidas(entradasVendidas);
        datosDto.setTotalRecaudado(totalRecaudado);
        datosDto.setTotalDescuentos(totalDescuentos);
        datosDto.setNumEntradasNoVendidas(aforo - entradasVendidas);

        return datosDto.toString();
    }
}
