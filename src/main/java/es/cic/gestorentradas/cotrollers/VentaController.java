package es.cic.gestorentradas.cotrollers;

import es.cic.gestorentradas.dto.CineDto;
import es.cic.gestorentradas.dto.SalaDto;
import es.cic.gestorentradas.dto.SesionDto;
import es.cic.gestorentradas.dto.VentaDto;
import es.cic.gestorentradas.service.VentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VentaController {

    @Autowired
    private VentaService ventaService;

    @GetMapping
    @RequestMapping("/ventas/cine/{id}")
    public VentaDto getCine(@PathVariable("id") CineDto cineDto) {
        return ventaService.get(cineDto);
    }

    @GetMapping
    @RequestMapping("/ventas/sala/{id}")
    public VentaDto getSala(@PathVariable("id") SalaDto salaDto) {
        return ventaService.get(salaDto);
    }

    @GetMapping
    @RequestMapping("/sesion/{id}")
    public VentaDto getSesion(@PathVariable("id") SesionDto sesionDto) {
        return ventaService.get(sesionDto);
    }
}
