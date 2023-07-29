package es.cic.gestorentradas.configuracion;

import es.cic.gestorentradas.gestion.GestorVentasCines;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
public class DataInitializer {

    @PostConstruct
    public void initializeData() {
        //Guardo los cines, salas y sesiones en memoria
        GestorVentasCines.addCine();
    }
}
