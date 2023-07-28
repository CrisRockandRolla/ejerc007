package es.cic.gestorentradas.cotrollers;

import es.cic.gestorentradas.service.CineServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RequestMapping("/")
public class CineController {
    @Autowired
    private CineServiceImpl cineServiceImpl;

    @GetMapping
    @RequestMapping("/")
    public ResponseEntity verCine() {
        return ResponseEntity.ok(cineServiceImpl.verCine());
    }
}