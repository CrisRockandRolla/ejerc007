package es.cic.gestorentradas.cotrollers;

import es.cic.gestorentradas.service.SalaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SalaController {

    @Autowired
    private SalaService salaServiceImp;

}
