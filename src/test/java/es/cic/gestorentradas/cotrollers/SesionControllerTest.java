package es.cic.gestorentradas.cotrollers;

import es.cic.gestorentradas.gestion.GestorVentasCines;
import es.cic.gestorentradas.gestion.VentaDatos;
import es.cic.gestorentradas.services.SesionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static es.cic.gestorentradas.gestion.SesionDatos.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@SpringBootTest
@AutoConfigureMockMvc
class SesionControllerTest {

    @Autowired
    private MockMvc moc;
    @Autowired
    private SesionService sesionService;

    @BeforeEach
    void clean() {
        GestorVentasCines.removeVentaPorId("VENTA_1");
        VentaDatos.setUltimoId(0);
        SESION_2.setEntradasDisponibles(100);
        SESION_7.setEntradasDisponibles(20);
        SESION_3.setEntradasDisponibles(100);
    }

    @Test
    void verSesion() throws Exception {

        String contenidoSesion1 = "<pre>CINE_1\t\tSALA_1\t\tSESION_1: 17:00, Film1\t\t Entradas disponibles: 100\n" +
                "\n" +
                "{Total descuentos=0.0, Total Recaudado=0.0, Aforo total=100, Ocupacion=0.0, Entradas vendidas=0}</pre>\n";

        moc.perform(MockMvcRequestBuilders.get("/sesion/{id}", SESION_1.getId()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().string(contenidoSesion1));
    }
}