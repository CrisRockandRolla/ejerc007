package es.cic.gestorentradas.cotrollers;

import es.cic.gestorentradas.service.VentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VentaController {

    @Autowired
    private VentaService ventaService;

    @PostMapping
    @RequestMapping("/create")
    public String create() {
        return ventaService.crearVenta();
    }

}
