package es.cic.gestorentradas.service;

import es.cic.gestorentradas.dto.CineDto;
import es.cic.gestorentradas.dto.SalaDto;
import es.cic.gestorentradas.dto.SesionDto;
import es.cic.gestorentradas.dto.VentaDto;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class CineServiceImp implements ICineServices {
    private final CineDto cineDto = new CineDto();

    @Override
    public CineDto cargarCine() {
        SalaDto salaDto1 = new SalaDto();
        salaDto1.setId(1L);
        salaDto1.setAforo(100);

        SalaDto salaDto2 = new SalaDto();
        salaDto2.setId(2L);
        salaDto2.setAforo(50);

        SalaDto salaDto3 = new SalaDto();
        salaDto3.setId(3L);
        salaDto3.setAforo(20);

        SesionDto sesionDto1 = new SesionDto();
        sesionDto1.setId(1L);
        sesionDto1.setHora("17:00");
        sesionDto1.setPelicula("Film1");

        SesionDto sesionDto2 = new SesionDto();
        sesionDto2.setId(2L);
        sesionDto2.setHora("20:00");
        sesionDto2.setPelicula("Film1");

        SesionDto sesionDto3 = new SesionDto();
        sesionDto3.setId(3L);
        sesionDto3.setHora("22:30");
        sesionDto3.setPelicula("Film2");


        salaDto1.setSesiones(Arrays.asList(sesionDto1, sesionDto2, sesionDto3));
        salaDto2.setSesiones(Arrays.asList(sesionDto1, sesionDto2, sesionDto3));
        salaDto3.setSesiones(Arrays.asList(sesionDto1, sesionDto2, sesionDto3));

        cineDto.setSalas(Arrays.asList(salaDto1, salaDto2, salaDto3));
        return cineDto;
    }

    @Override
    public int recaudacion() {
        int recaudacion = 0;
        List<SalaDto> salas = cineDto.getSalas();
        for (SalaDto salaDto : salas) {
            List<SesionDto> sesiones = salaDto.getSesiones();
            for (SesionDto sesionDto : sesiones) {
                List<VentaDto> ventas = sesionDto.getVentas();
                for (VentaDto ventaDto : ventas) {
                    recaudacion += ventaDto.getTotal();
                }
            }
        }
        return recaudacion;
    }

    public int recaudacion(SalaDto sala) {
        int recaudacion = 0;
        for (SesionDto sesionDto : sala.getSesiones()) {
            for (VentaDto ventaDto : sesionDto.getVentas()) {
                recaudacion += ventaDto.getTotal();
            }
        }
        return recaudacion;
    }

    public int recaudacion(List<VentaDto> ventas) {
        int recaudacion = 0;
        for (VentaDto ventaDto : ventas) {
            recaudacion += ventaDto.getTotal() - ventaDto.getDescuento();
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
