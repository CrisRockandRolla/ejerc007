package es.cic.gestorentradas.service;

import es.cic.gestorentradas.assembler.AssemblerVenta;
import es.cic.gestorentradas.cache.CineCache;
import es.cic.gestorentradas.dto.SalaDto;
import es.cic.gestorentradas.dto.SesionDto;
import es.cic.gestorentradas.dto.VentaDto;
import org.springframework.stereotype.Service;

@Service
public class VentaService {

    public VentaDto crearVenta() {
        int entradasDisponibles = 0;
        fuera:
        for (SalaDto salaDto : CineCache.getSalasCine("CINE_1")) {
            for (SesionDto sesionDto : salaDto.getSesiones()) {
                if (sesionDto.getId().equalsIgnoreCase(SesionDto.SESION_8.getId())) {
                    entradasDisponibles = sesionDto.getEntradasDisponibles();
                    break fuera;
                }
            }
        }

        if (6 >= entradasDisponibles) throw new RuntimeException("No hay suficientes entradas");

        for (SalaDto salaDto : CineCache.getSalasCine("CINE_1")) {
            for (SesionDto sesionDto : salaDto.getSesiones()) {
                if (sesionDto.getId().equalsIgnoreCase(SesionDto.SESION_8.getId())) {
                    sesionDto.setEntradasDisponibles(sesionDto.getEntradasDisponibles() - 6);
                }
            }
        }

        return AssemblerVenta.assembleVenta(1, 6, SesionDto.SESION_8);
    }
}
