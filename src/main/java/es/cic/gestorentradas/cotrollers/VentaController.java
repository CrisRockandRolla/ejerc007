package es.cic.gestorentradas.cotrollers;

import es.cic.gestorentradas.cache.CineCache;
import es.cic.gestorentradas.dto.SesionDto;
import es.cic.gestorentradas.dto.VentaDto;
import es.cic.gestorentradas.service.VentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class VentaController {

    @Autowired
    private VentaService ventaService;

    @GetMapping(path = "/create/{parametro1}/{parametro2}")
    public String ver(@PathVariable int parametro1, @PathVariable SesionDto parametro2) {
        VentaDto ventaDto = ventaService.crearVenta(parametro1, parametro2);
        CineCache.addVenta(ventaDto);
        return ventaDto.toString();
    }
//    @GetMapping(path = "/venta/{parametro1}")
//    public String ver(@PathVariable long parametro1) {
//        VentaDto ventaDto = ventaService.verVenta(parametro1);
//        return ventaDto.toString();
//    }
//
//    @PostMapping(path = "/create/{parametro1}/{parametro2}")
//    public ResponseEntity<VentaDto> create(@PathVariable int parametro1, @PathVariable SesionDto parametro2) {
//        VentaDto ventaDto = ventaService.crearVenta(parametro1, parametro2);
//        if (ventaDto == null) {
//            throw new EntradasInsuficientesException("Error del servidor al crear la venta");
//        } else {
//            CineCache.addVenta(ventaDto);
//            return new ResponseEntity<>(ventaDto, HttpStatus.CREATED);
//        }
//    }

}
