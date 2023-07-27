package es.cic.gestorentradas.cotrollers;

import es.cic.gestorentradas.service.CineServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RequestMapping("/")
public class CineController {
    @Autowired
    private CineServiceImpl cineServiceImpl;

    @PostMapping
    @RequestMapping("/")
    public ResponseEntity crearCine() {
        return ResponseEntity.ok(cineServiceImpl.crearCine());
    }
}