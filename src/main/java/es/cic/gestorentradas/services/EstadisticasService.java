package es.cic.gestorentradas.services;

import es.cic.gestorentradas.gestion.CineDatos;
import es.cic.gestorentradas.gestion.GestorVentasCines;
import org.springframework.stereotype.Service;

@Service
public class EstadisticasService {
    public String getEstadisticas(CineDatos cineDto) {
        return GestorVentasCines.datosCine();
    }


}
