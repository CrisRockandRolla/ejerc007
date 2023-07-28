package es.cic.gestorentradas.service;

import es.cic.gestorentradas.cache.CineCache;
import es.cic.gestorentradas.dto.CineDto;
import es.cic.gestorentradas.dto.SalaDto;
import es.cic.gestorentradas.dto.SesionDto;
import es.cic.gestorentradas.dto.VentaDto;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CineServiceImpl implements ICineService {

//    @Override
//    //FIXME arreglar esto. El enum es inmutable y no puedo cambiar los datos
//    public void crearCine() {
//        CineCache.addCine();
//    }

    @Override
    //FIXME arreglar esto. El enum es inmutable y no puedo cambiar los datos
    public String verCine() {
        return CineCache.miToString();
    }

    @Override
    public int recaudacion() {
        int recaudacion = 0;
        List<SalaDto> salas = CineDto.CINE_1.getSalas();
        for (SalaDto salaDto : salas) {
            List<SesionDto> sesiones = salaDto.getSesiones();
            for (SesionDto sesionDto : sesiones) {
                List<VentaDto> ventas = sesionDto.getVentas();
                for (VentaDto ventaDto : ventas) {
                    recaudacion += ventaDto.getTotalPagar();
                }
            }
        }
        return recaudacion;
    }


    public int recaudacion(SalaDto sala) {
        int recaudacion = 0;
        for (SesionDto sesionDto : sala.getSesiones()) {
            for (VentaDto ventaDto : sesionDto.getVentas()) {
                recaudacion += ventaDto.getTotalPagar();
            }
        }
        return recaudacion;
    }

    public int recaudacion(List<VentaDto> ventas) {
        int recaudacion = 0;
        for (VentaDto ventaDto : ventas) {
            recaudacion += ventaDto.getTotalPagar() - ventaDto.getDescuento();
        }
        return recaudacion;
    }

    public int calcularRecaudacionTotal(CineDto cineDto) {
        int recaudacion = 0;
        for (SalaDto salaDto : cineDto.getSalas()) {
            for (SesionDto sesionDto : salaDto.getSesiones()) {
                recaudacion += recaudacion(sesionDto.getVentas());
            }
        }
        return recaudacion;
    }
}
