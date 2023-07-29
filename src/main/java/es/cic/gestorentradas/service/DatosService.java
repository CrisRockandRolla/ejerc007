//package es.cic.gestorentradas.service;
//
//import es.cic.gestorentradas.cache.GestorVentasCines;
//import es.cic.gestorentradas.dto.DatosCache;
//import es.cic.gestorentradas.gestion.VentaDatos;
//import es.cic.gestorentradas.gestion.CineDatos;
//import es.cic.gestorentradas.gestion.SalaDatos;
//import es.cic.gestorentradas.gestion.SesionDatos;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//import static es.cic.gestorentradas.gestion.CineDatos.CINE_1;
//
//@Service
//public class DatosService {
//
//
//    public String get(CineDatos cineDto) {
//
//        DatosCache datosDto = new DatosCache();
//
//        List<SalaDatos> salas = GestorVentasCines.getSalasCine(CINE_1.getId());
//
//        int entradasVendidas = datosDto.getNumEntradasVendidas();
//        int aforo = 0;
//        double totalRecaudado = datosDto.getTotalRecaudado();
//        double totalDescuentos = datosDto.getTotalDescuentos();
//
//
//        for (SalaDatos sala : salas) {
//            for (SesionDatos sesion : sala.getSesiones()) {
//                aforo += sala.getAforo();
//                datosDto.setIdSala(sala.getId());
//                for (VentaDatos venta : sesion.getVentas()) {
//                    entradasVendidas += venta.getNumEntradas();
//                    totalRecaudado += venta.getTotalPagar();
//                    totalDescuentos += venta.getDescuento();
//                }
//            }
//        }
//
//        datosDto.setNumEntradasVendidas(entradasVendidas);
//        datosDto.setTotalRecaudado(totalRecaudado);
//        datosDto.setTotalDescuentos(totalDescuentos);
//        datosDto.setNumEntradasNoVendidas(aforo - entradasVendidas);
//
//        return datosDto.toString();
//    }
//
//    public String get(SalaDatos salaDto) {
//
//        DatosCache datosDto = new DatosCache();
//        List<SalaDatos> salas = GestorVentasCines.getMapaCine().get(CINE_1.getId());
//
//        int entradasVendidas = datosDto.getNumEntradasVendidas();
//        int aforo = 0;
//        double totalRecaudado = datosDto.getTotalRecaudado();
//        double totalDescuentos = datosDto.getTotalDescuentos();
//
//        for (SalaDatos sala : salas) {
//            if (sala.getId().equalsIgnoreCase(salaDto.getId())) {
//                aforo = sala.getAforo();
//                for (SesionDatos sesion : sala.getSesiones()) {
//                    datosDto.setIdSala(sala.getId());
//                    for (VentaDatos venta : sesion.getVentas()) {
//                        entradasVendidas += venta.getNumEntradas();
//                        totalRecaudado += venta.getTotalPagar();
//                        totalDescuentos += venta.getDescuento();
//                    }
//                }
//            }
//        }
//
//        datosDto.setNumEntradasVendidas(entradasVendidas);
//        datosDto.setTotalRecaudado(totalRecaudado);
//        datosDto.setTotalDescuentos(totalDescuentos);
//        datosDto.setNumEntradasNoVendidas(aforo - entradasVendidas);
//
//        return datosDto.toString();
//    }
//
//    public String get(SesionDatos sesionDto) {
//
//        DatosCache datosDto = new DatosCache();
//        List<SalaDatos> salas = GestorVentasCines.getMapaCine().get(CINE_1.getId());
//        int entradasVendidas = datosDto.getNumEntradasVendidas();
//        int aforo = 0;
//        double totalRecaudado = datosDto.getTotalRecaudado();
//        double totalDescuentos = datosDto.getTotalDescuentos();
//
//        fuera:
//        for (SalaDatos sala : salas) {
//            for (SesionDatos sesion : sala.getSesiones()) {
//                if (sesion.getId().equalsIgnoreCase(sesionDto.getId())) {
//                    datosDto.setIdSala(sala.getId());
//                    for (VentaDatos venta : sesion.getVentas()) {
//                        entradasVendidas += venta.getNumEntradas();
//                        totalRecaudado += venta.getTotalPagar();
//                        totalDescuentos += venta.getDescuento();
//                    }
//                    aforo = sala.getAforo();
//                    break fuera;
//                }
//            }
//        }
//
//        datosDto.setNumEntradasVendidas(entradasVendidas);
//        datosDto.setTotalRecaudado(totalRecaudado);
//        datosDto.setTotalDescuentos(totalDescuentos);
//        datosDto.setNumEntradasNoVendidas(aforo - entradasVendidas);
//
//        return datosDto.toString();
//    }
//}
