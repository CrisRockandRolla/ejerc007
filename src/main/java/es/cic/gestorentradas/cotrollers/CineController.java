package es.cic.gestorentradas.cotrollers;

import es.cic.gestorentradas.dto.CineDto;
import es.cic.gestorentradas.service.CineServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cine")
public class CineController {
    @Autowired
    private CineServiceImp cineServiceImp;

    @GetMapping
    public CineDto crear() {
        return cineServiceImp.cargarCine();
    }

//
//    public ResponseEntity obtenerInfoCine() { //Me devuelve el Json
//        return ResponseEntity.ok(cineServiceImp.obtenerInfoCine());
//    }


}