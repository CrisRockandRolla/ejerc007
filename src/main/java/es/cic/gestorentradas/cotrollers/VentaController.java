package es.cic.gestorentradas.cotrollers;

import es.cic.gestorentradas.cache.CineCache;
import es.cic.gestorentradas.dto.SalaDto;
import es.cic.gestorentradas.dto.SesionDto;
import es.cic.gestorentradas.dto.VentaDto;
import es.cic.gestorentradas.service.VentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static es.cic.gestorentradas.dto.CineDto.CINE_1;

@RestController
public class VentaController {

    @Autowired
    private VentaService ventaService;

    @PostMapping
    @RequestMapping("/create")
    public String create() {
        VentaDto ventaDto = ventaService.crearVenta();
        List<SalaDto> salasCine = CineCache.getSalasCine(CINE_1.getId());
        for (SalaDto sala : salasCine) {
            for (SesionDto sesion : sala.getSesiones()) {
                if (sesion.getId().equalsIgnoreCase(ventaDto.getSesionDto().getId())) {
                    sesion.getVentas().add(ventaDto);
                }
            }
        }
        return ventaDto.toString();
    }

}
