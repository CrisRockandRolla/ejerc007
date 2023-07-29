package es.cic.gestorentradas.cotrollers;

import es.cic.gestorentradas.gestion.SalaDatos;
import es.cic.gestorentradas.service.SalaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SalaController {

    @Autowired
    private SalaService salaService;

    @GetMapping
    @RequestMapping("/sala/{id}")
    public String verSala(@PathVariable("id") SalaDatos salaDto) {
        return salaService.verSala(salaDto);
    }
}
