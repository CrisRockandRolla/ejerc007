package es.cic.gestorentradas.services;

import es.cic.gestorentradas.gestion.CineDatos;
import es.cic.gestorentradas.gestion.GestorVentasCines;
import es.cic.gestorentradas.gestion.SalaDatos;
import org.springframework.stereotype.Service;


@Service
public class SalaService {

    public String verSala(SalaDatos sala) {
        return GestorVentasCines.mostrar(sala, CineDatos.CINE_1);
    }

}
