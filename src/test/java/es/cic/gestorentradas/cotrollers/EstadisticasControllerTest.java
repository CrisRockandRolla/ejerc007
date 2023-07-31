package es.cic.gestorentradas.cotrollers;

import es.cic.gestorentradas.gestion.GestorVentasCines;
import es.cic.gestorentradas.gestion.VentaDatos;
import es.cic.gestorentradas.services.EstadisticasService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static es.cic.gestorentradas.gestion.CineDatos.CINE_1;
import static es.cic.gestorentradas.gestion.SesionDatos.SESION_3;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@SpringBootTest
@AutoConfigureMockMvc
class EstadisticasControllerTest {

    @Autowired
    private MockMvc moc;
    @Autowired
    private EstadisticasService estadisticasService;

    @BeforeEach
    void clean() {
        GestorVentasCines.removeVentaPorId("VENTA_1");
        VentaDatos.setUltimoId(0);
        SESION_3.setEntradasDisponibles(100);
    }

    @Test
    void get() throws Exception {
        String respuesta = "CINE_1\t{Total descuentos=0.0, Total Recaudado=0.0, Aforo total=510, Ocupacion=0.0, Entradas vendidas=0}\n" +
                "\tSALA_1\t{Total descuentos=0.0, Total Recaudado=0.0, Aforo total=300, Ocupacion=0.0, Entradas vendidas=0}\n" +
                "\t\tSESION_1: 17:00, Film1\t{Total descuentos=0.0, Total Recaudado=0.0, Aforo total=100, Ocupacion=0.0, Entradas vendidas=0}\t\t Entradas disponibles: 100\n" +
                "\t\tSESION_2: 20:00, Film1\t{Total descuentos=0.0, Total Recaudado=0.0, Aforo total=100, Ocupacion=0.0, Entradas vendidas=0}\t\t Entradas disponibles: 100\n" +
                "\t\tSESION_3: 22:30, Film2\t{Total descuentos=0.0, Total Recaudado=0.0, Aforo total=100, Ocupacion=0.0, Entradas vendidas=0}\t\t Entradas disponibles: 100\n" +
                "\tSALA_2\t{Total descuentos=0.0, Total Recaudado=0.0, Aforo total=150, Ocupacion=0.0, Entradas vendidas=0}\n" +
                "\t\tSESION_4: 17:00, Film3\t{Total descuentos=0.0, Total Recaudado=0.0, Aforo total=50, Ocupacion=0.0, Entradas vendidas=0}\t\t Entradas disponibles: 50\n" +
                "\t\tSESION_5: 20:00, Film2\t{Total descuentos=0.0, Total Recaudado=0.0, Aforo total=50, Ocupacion=0.0, Entradas vendidas=0}\t\t Entradas disponibles: 50\n" +
                "\t\tSESION_6: 22:30, Film4\t{Total descuentos=0.0, Total Recaudado=0.0, Aforo total=50, Ocupacion=0.0, Entradas vendidas=0}\t\t Entradas disponibles: 50\n" +
                "\tSALA_3\t{Total descuentos=0.0, Total Recaudado=0.0, Aforo total=60, Ocupacion=0.0, Entradas vendidas=0}\n" +
                "\t\tSESION_7: 17:00, Film5\t{Total descuentos=0.0, Total Recaudado=0.0, Aforo total=20, Ocupacion=0.0, Entradas vendidas=0}\t\t Entradas disponibles: 20\n" +
                "\t\tSESION_8: 20:00, Film3\t{Total descuentos=0.0, Total Recaudado=0.0, Aforo total=20, Ocupacion=0.0, Entradas vendidas=0}\t\t Entradas disponibles: 20\n" +
                "\t\tSESION_9: 22:30, Film5\t{Total descuentos=0.0, Total Recaudado=0.0, Aforo total=20, Ocupacion=0.0, Entradas vendidas=0}\t\t Entradas disponibles: 20\n";
        moc.perform(MockMvcRequestBuilders.get("/estadisticas/{id}", CINE_1))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(content().string(respuesta));
    }
}