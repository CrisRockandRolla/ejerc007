package es.cic.gestorentradas.service;

import es.cic.gestorentradas.cache.CineCache;
import es.cic.gestorentradas.dto.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class DatosService {


    public String get(CineDto cineDto) {

        DatosCache datosDto = new DatosCache();
        Map<String, List<SalaDto>> cine = CineCache.getMapaCine();
        List<SalaDto> salas = cine.get(cineDto.getId());

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
                    totalDescuentos += venta.getTotalPagar();
                }
            }
        }

        datosDto.setNumEntradasVendidas(entradasVendidas);
        datosDto.setTotalRecaudado(totalRecaudado);
        datosDto.setTotalDescuentos(totalDescuentos);
        System.out.println(aforo);
        System.out.println(entradasVendidas);
        System.out.println(aforo - entradasVendidas);
        datosDto.setNumEntradasNoVendidas(aforo - entradasVendidas);

        return datosDto.toString();
    }

    public String get(SalaDto salaDto) {

        DatosCache datosDto = new DatosCache();
        List<SalaDto> salas = CineCache.getSalas();
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
                        totalDescuentos += venta.getTotalPagar();
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
        List<SalaDto> salas = CineCache.getSalas();
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
                        totalDescuentos += venta.getTotalPagar();
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
