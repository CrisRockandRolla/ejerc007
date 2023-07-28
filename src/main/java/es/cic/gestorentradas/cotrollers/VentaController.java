package es.cic.gestorentradas.cotrollers;

import es.cic.gestorentradas.cache.CineCache;
import es.cic.gestorentradas.dto.VentaDto;
import es.cic.gestorentradas.service.VentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VentaController {

    @Autowired
    private VentaService ventaService;

    @PostMapping
    @RequestMapping("/create")
    public String create() {
        VentaDto ventaDto = ventaService.crearVenta();
        if (ventaDto != null) {
            CineCache.addVenta(ventaDto);
            return ventaDto.toString();
        } else {
            return "No se ha podido crear la venta";
        }
    }

}
