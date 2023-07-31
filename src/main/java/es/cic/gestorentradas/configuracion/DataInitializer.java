package es.cic.gestorentradas.configuracion;

import es.cic.gestorentradas.gestion.GestorVentasCines;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

import static es.cic.gestorentradas.gestion.CineDatos.CINE_1;

@Configuration
public class DataInitializer {

    @PostConstruct
    public void initializeData() {
        //Guardo los cines, salas y sesiones en caché
        GestorVentasCines.addCine(CINE_1.getId());
    }
}
