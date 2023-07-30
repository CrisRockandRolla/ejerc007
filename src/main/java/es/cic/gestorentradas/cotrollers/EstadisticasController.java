package es.cic.gestorentradas.cotrollers;

import es.cic.gestorentradas.gestion.CineDatos;
import es.cic.gestorentradas.services.EstadisticasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/estadisticas")
public class EstadisticasController {

    @Autowired
    private EstadisticasService estadisticasService;

    @GetMapping
    @RequestMapping("/{id}")
    public String get(@PathVariable("id") CineDatos cineDto) {
        return estadisticasService.getEstadisticas(cineDto);
    }
}
