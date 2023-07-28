package es.cic.gestorentradas.service;

import es.cic.gestorentradas.assembler.AssemblerVenta;
import es.cic.gestorentradas.cache.CineCache;
import es.cic.gestorentradas.dto.SalaDto;
import es.cic.gestorentradas.dto.SesionDto;
import es.cic.gestorentradas.dto.VentaDto;
import es.cic.gestorentradas.excepciones.EntradasInsuficientesException;
import org.springframework.stereotype.Service;

@Service
public class VentaService {

    public VentaDto crearVenta(int numEntradas, SesionDto sesionDto) {
        int entradasDisponibles = 0;
        fuera:
        for (SalaDto salaDto : CineCache.getSalasCine("CINE_1")) {
            for (SesionDto sesion : salaDto.getSesiones()) {
                if (sesion.getId().equalsIgnoreCase(sesionDto.getId())) {
                    entradasDisponibles = sesion.getEntradasDisponibles();
                    break fuera;
                }
            }
        }

        if (numEntradas > entradasDisponibles)
            throw new EntradasInsuficientesException("No hay suficientes entradas. Quiere " + numEntradas + " pero hay " + entradasDisponibles + "\n");

        for (SalaDto salaDto : CineCache.getSalasCine("CINE_1")) {
            for (SesionDto sesion : salaDto.getSesiones()) {
                if (sesion.getId().equalsIgnoreCase(sesionDto.getId())) {
                    sesion.setEntradasDisponibles(sesion.getEntradasDisponibles() - numEntradas);
                }
            }
        }

        return AssemblerVenta.assembleVenta(1, numEntradas, sesionDto);
    }

    public VentaDto verVenta(long id) {
        fuera:
        for (SalaDto salaDto : CineCache.getSalasCine("CINE_1")) {
            for (SesionDto sesion : salaDto.getSesiones()) {
                for (VentaDto venta : sesion.getVentas()) {
                    if (venta.getId() == id) {
                        return venta;
                    }
                }
            }
        }
        throw new EntradasInsuficientesException("No se ha encontrado la venta con id " + id);
    }
}
