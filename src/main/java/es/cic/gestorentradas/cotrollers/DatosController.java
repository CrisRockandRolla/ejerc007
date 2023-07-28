package es.cic.gestorentradas.cotrollers;

import es.cic.gestorentradas.dto.CineDto;
import es.cic.gestorentradas.dto.SalaDto;
import es.cic.gestorentradas.dto.SesionDto;
import es.cic.gestorentradas.service.DatosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/datos")
public class DatosController {

    @Autowired
    private DatosService datosService;

    @GetMapping
    @RequestMapping("/cine/{id}")
    public String get(@PathVariable("id") CineDto cineDto) {
        return datosService.get(cineDto);
//        return "Cine\n";
    }

    @GetMapping
    @RequestMapping("/sala/{id}")
    public String get(@PathVariable("id") SalaDto salaDto) {
        return datosService.get(salaDto);
//        return "Sala\n";
    }

    @GetMapping
    @RequestMapping("/sesion/{id}")
    public String get(@PathVariable("id") SesionDto sesionDto) {
        return sesionDto.getId() + "\t" + datosService.get(sesionDto);
    }
}
