package es.cic.gestorentradas.service;

import es.cic.gestorentradas.gestion.GestorVentasCines;
import es.cic.gestorentradas.gestion.SalaDatos;
import org.springframework.stereotype.Service;


@Service
public class SalaService {

    public String verSala(SalaDatos sala) {
        return GestorVentasCines.mostrarSala(sala);
    }

}
