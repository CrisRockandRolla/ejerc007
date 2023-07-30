package es.cic.gestorentradas.cotrollers;

import es.cic.gestorentradas.gestion.SesionDatos;
import es.cic.gestorentradas.services.SesionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SesionController {

    @Autowired
    private SesionService sesionService;

    @GetMapping
    @RequestMapping("/sesion/{id}")
    public ResponseEntity<String> verSesion(@PathVariable("id") SesionDatos sesion) {
        return ResponseEntity.ok(sesionService.verSesion(sesion));
    }
}
