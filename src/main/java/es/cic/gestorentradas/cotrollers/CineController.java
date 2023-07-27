package es.cic.gestorentradas.cotrollers;

import es.cic.gestorentradas.service.CineServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RequestMapping("/cine")
public class CineController {
    @Autowired
    private CineServiceImp cineServiceImp;

    @PostMapping
    @RequestMapping("/cine")
    public ResponseEntity crear() {
        return ResponseEntity.ok(cineServiceImp.cargarCine());
    }


}