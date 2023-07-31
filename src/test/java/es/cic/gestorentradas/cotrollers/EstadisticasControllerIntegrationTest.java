package es.cic.gestorentradas.cotrollers;

import es.cic.gestorentradas.constantes.DatosTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static es.cic.gestorentradas.gestion.CineDatos.CINE_1;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@SpringBootTest
@AutoConfigureMockMvc
class EstadisticasControllerIntegrationTest {

    @Autowired
    private MockMvc moc;

    @Test
    void get() throws Exception {
        moc.perform(MockMvcRequestBuilders.get("/estadisticas/{id}", CINE_1))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().string(DatosTest.CONTENIDO_ESTADISTICAS));
    }
}