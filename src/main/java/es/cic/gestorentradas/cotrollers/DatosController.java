//package es.cic.gestorentradas.cotrollers;
//
//import es.cic.gestorentradas.gestion.CineDatos;
//import es.cic.gestorentradas.gestion.SalaDatos;
//import es.cic.gestorentradas.gestion.SesionDatos;
//import es.cic.gestorentradas.service.DatosService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/datos")
//public class DatosController {
//
//    @Autowired
//    private DatosService datosService;
//
//    @GetMapping
//    @RequestMapping("/cine/{id}")
//    public String get(@PathVariable("id") CineDatos cineDto) {
//        return datosService.get(cineDto);
//    }
//
//    @GetMapping
//    @RequestMapping("/sala/{id}")
//    public String get(@PathVariable("id") SalaDatos salaDto) {
//        return datosService.get(salaDto);
//    }
//
//    @GetMapping
//    @RequestMapping("/sesion/{id}")
//    public String get(@PathVariable("id") SesionDatos sesionDto) {
//        return sesionDto.getId() + "\t" + datosService.get(sesionDto);
//    }
//}
