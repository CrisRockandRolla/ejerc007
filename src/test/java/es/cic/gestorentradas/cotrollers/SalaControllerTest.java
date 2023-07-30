package es.cic.gestorentradas.cotrollers;

import es.cic.gestorentradas.services.SalaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static es.cic.gestorentradas.gestion.SalaDatos.SALA_1;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@SpringBootTest
@AutoConfigureMockMvc
class SalaControllerTest {
    @Autowired
    private MockMvc moc;
    @Autowired
    private SalaService salaService;

    @Test
    void verSala() throws Exception {

        String contenidoSala1 = "<pre>CINE_1\t\tSALA_1\t\tSESION_1: 17:00, Film1\t\t Entradas disponibles: 100\n" +
                "CINE_1\t\tSALA_1\t\tSESION_2: 20:00, Film1\t\t Entradas disponibles: 100\n" +
                "CINE_1\t\tSALA_1\t\tSESION_3: 22:30, Film2\t\t Entradas disponibles: 100\n" +
                "</pre>";

        moc.perform(MockMvcRequestBuilders.get("/sala/{id}", SALA_1.getId())
                        .contentType(MediaType.TEXT_PLAIN))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().string(contenidoSala1));
    }
}