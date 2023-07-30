package es.cic.gestorentradas.cotrollers;

import es.cic.gestorentradas.excepciones.VentaException;
import es.cic.gestorentradas.gestion.GestorVentasCines;
import es.cic.gestorentradas.gestion.SesionDatos;
import es.cic.gestorentradas.gestion.VentaDatos;
import es.cic.gestorentradas.services.VentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static es.cic.gestorentradas.gestion.CineDatos.CINE_1;

@RestController

public class VentaController {

    @Autowired
    private VentaService ventaService;

    @PostMapping(path = "/create/{numEntradas}/{sesion}")//curl -X POST
    public String crear(@PathVariable int numEntradas, @PathVariable SesionDatos sesion) {
        VentaDatos ventaDatos = ventaService.crearVenta(numEntradas, sesion);
        //TODO VALIDAR ventaDto
        GestorVentasCines.actualizarEntradasDisponibles(sesion, numEntradas, CINE_1.getId());
        GestorVentasCines.addVenta(ventaDatos, CINE_1.getId());
        return ventaDatos.toString();
    }

    @GetMapping(path = "/venta/{id}")
    public String ver(@PathVariable String id) {
        VentaDatos ventaDatos = ventaService.verVenta(id);
        return ventaDatos.toString();
    }

    @PatchMapping  //curl -X PATCH
    @RequestMapping(value = {"/update/venta/{id}/{entradasCancelar}", "/update/venta/{id}/_{sesion}",
            "/update/venta/{id}/{entradasCancelar}/{sesion}"})
    public String modificarVenta(@PathVariable() String id, @PathVariable(required = false) Integer entradasCancelar,
                                 @PathVariable(required = false) SesionDatos sesion) {

        if (entradasCancelar != null && sesion != null) {
            VentaDatos ventaDatos = ventaService.modificarVenta(id, entradasCancelar, sesion);
            return ventaDatos.toString();
        }
        if (sesion != null) {
            VentaDatos ventaDatos = ventaService.modificarVenta(id, sesion);
            return ventaDatos.toString();
        }
        if (entradasCancelar != null) {
            VentaDatos ventaDatos = ventaService.modificarVenta(id, entradasCancelar);
            return ventaDatos.toString();
        }
        throw new VentaException("Error del servidor al modificar la venta");
    }

    @DeleteMapping(path = "/delete/venta/{id}") //curl -X DELETE
    public String eliminar(@PathVariable String id) {
        ventaService.eliminarVenta(id);
        return "Venta eliminada correctamente\n";
    }
}


