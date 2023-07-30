package es.cic.gestorentradas.services;

import es.cic.gestorentradas.gestion.CineDatos;
import es.cic.gestorentradas.gestion.EstadisticasDatos;
import org.springframework.stereotype.Service;

import static es.cic.gestorentradas.gestion.CineDatos.CINE_1;

@Service
public class EstadisticasService {

    public String getEstadisticas(CineDatos cineDto) {
        return EstadisticasDatos.mostrarEstadisticasCine(CINE_1);
    }
}
