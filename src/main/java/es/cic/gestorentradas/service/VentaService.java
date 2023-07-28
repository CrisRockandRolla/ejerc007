package es.cic.gestorentradas.service;

import es.cic.gestorentradas.assembler.AssemblerVenta;
import es.cic.gestorentradas.dto.SesionDto;
import es.cic.gestorentradas.dto.VentaDto;
import org.springframework.stereotype.Service;

@Service
public class VentaService {

    public VentaDto crearVenta() {
        return AssemblerVenta.assembleVenta(1, 3, SesionDto.SESION_1);
    }

}
