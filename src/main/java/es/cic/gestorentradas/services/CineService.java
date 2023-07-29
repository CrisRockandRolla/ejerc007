package es.cic.gestorentradas.services;

import es.cic.gestorentradas.gestion.CineDatos;
import es.cic.gestorentradas.gestion.GestorVentasCines;
import org.springframework.stereotype.Service;

@Service
public class CineService {

    public String verCine(CineDatos cine) {
        return GestorVentasCines.mostrarCine(cine);
    }
}
