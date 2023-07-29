package es.cic.gestorentradas.cotrollers;

import es.cic.gestorentradas.excepciones.VentaException;
import es.cic.gestorentradas.gestion.GestorVentasCines;
import es.cic.gestorentradas.gestion.SesionDatos;
import es.cic.gestorentradas.gestion.VentaDatos;
import es.cic.gestorentradas.services.VentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class VentaController {

    @Autowired
    private VentaService ventaService;

//    @PostMapping(path = "/create/{parametro1}/{parametro2}")
//    public ResponseEntity<VentaDatos> create(@PathVariable int parametro1, @PathVariable SesionDatos parametro2) {
//        VentaDatos ventaDatos = ventaService.crearVenta(parametro1, parametro2);
//        if (ventaDatos == null) {
//            throw new VentaException("Error del servidor al crear la venta");
//        } else {
//            GestorVentasCines.addVenta(ventaDatos);
//            return new ResponseEntity<>(ventaDatos, HttpStatus.CREATED);
//        }
//    }

    @GetMapping(path = "/create/{numEntradas}/{sesion}")
    public String crear(@PathVariable int numEntradas, @PathVariable SesionDatos sesion) {
        VentaDatos ventaDatos = ventaService.crearVenta(numEntradas, sesion);
        //TODO VALIDAR ventaDto
        GestorVentasCines.actualizarEntradasDisponibles(sesion, numEntradas);
        GestorVentasCines.addVenta(ventaDatos);
        return ventaDatos.toString();
    }

    @GetMapping(path = "/venta/{id}")
    public String ver(@PathVariable long id) {
        VentaDatos ventaDatos = ventaService.verVenta(id);
        return ventaDatos.toString();
    }

    @GetMapping
    @RequestMapping(value = {"/update/venta/{id}/{entradasCancelar}", "/update/venta/{id}//{sesion}",
            "/update/venta/{id}/{entradasCancelar}/{sesion}"})
    public String modificarEntradas(@PathVariable() long id, @PathVariable(required = false) Integer entradasCancelar,
                                    @PathVariable(required = false) SesionDatos sesion) {

        if (entradasCancelar != null && sesion != null) {
            VentaDatos ventaDatos = ventaService.modificarEntradasYSesion(id, entradasCancelar, sesion);
            return ventaDatos.toString();
        }
        if (sesion != null) {
            VentaDatos ventaDatos = ventaService.modificarSesion(id, sesion);
            return ventaDatos.toString();
        }
        if (entradasCancelar != null) {
            VentaDatos ventaDatos = ventaService.modificarNumeroEntradas(id, entradasCancelar);
            return ventaDatos.toString();
        }


        throw new VentaException("Error del servidor al modificar la venta");
    }
}

//    @GetMapping(path = "/update/venta/{idVenta}/{idSesion}")
//    public String modificarSesion(@PathVariable() long idVenta, @PathVariable() String idSesion) {
//        VentaDatos ventaDatos = ventaService.modificarSesion(idVenta, idSesion);
//        return ventaDatos.toString();
//    }

