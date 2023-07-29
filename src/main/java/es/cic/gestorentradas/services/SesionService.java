package es.cic.gestorentradas.services;

import es.cic.gestorentradas.gestion.GestorVentasCines;
import es.cic.gestorentradas.gestion.SesionDatos;
import org.springframework.stereotype.Service;

@Service
public class SesionService {
    public String verSesion(SesionDatos sesion) {
        return GestorVentasCines.mostrarSesion(sesion);
    }

}
