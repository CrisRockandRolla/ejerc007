package es.cic.gestorentradas.service;

import es.cic.gestorentradas.gestion.CineDatos;
import es.cic.gestorentradas.gestion.GestorVentasCines;
import org.springframework.stereotype.Service;

@Service
public class CineService {

    public String verCine(CineDatos cine) {
        return GestorVentasCines.mostrarCine(cine);
    }
}
