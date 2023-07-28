package es.cic.gestorentradas.configuracion;

import es.cic.gestorentradas.cache.CineCache;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
public class DataInitializer {


    @PostConstruct
    public void initializeData() {
        // Crea y guarda los cines, salas y sesiones en memoria
        CineCache.addCine();

//        ResponseEntity.ok(cineServiceImpl.crearCine());

    }
}
