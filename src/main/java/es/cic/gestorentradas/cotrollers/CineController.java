package es.cic.gestorentradas.cotrollers;

import es.cic.gestorentradas.gestion.CineDatos;
import es.cic.gestorentradas.services.CineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class CineController {
    @Autowired
    private CineService cineService;

    @GetMapping
    @RequestMapping("/")
    public String index() {
        return "Bienvenido a cines-CIC";
    }

    @GetMapping
    @RequestMapping("/cine/{id}")
    public ResponseEntity verCine(@PathVariable("id") CineDatos cineDto) {
        return ResponseEntity.ok(cineService.verCine(cineDto));
    }
}