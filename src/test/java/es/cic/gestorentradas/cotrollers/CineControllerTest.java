//package es.cic.gestorentradas.cotrollers;
//
//import es.cic.gestorentradas.gestion.GestorVentasCines;
//import es.cic.gestorentradas.gestion.VentaDatos;
//import es.cic.gestorentradas.services.CineService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//
//import static es.cic.gestorentradas.gestion.CineDatos.CINE_1;
//import static es.cic.gestorentradas.gestion.SesionDatos.SESION_2;
//import static es.cic.gestorentradas.gestion.SesionDatos.SESION_7;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
//
//
//@SpringBootTest
//@AutoConfigureMockMvc
//class CineControllerTest {
//
//    @Autowired
//    private MockMvc moc;
//    @Autowired
//    private CineService cineService;
//
//    @BeforeEach
//    void clean() {
//        GestorVentasCines.removeVentaPorId("VENTA_1");
//        VentaDatos.setUltimoId(0);
//        SESION_2.setEntradasDisponibles(100);
//        SESION_7.setEntradasDisponibles(20);
//    }
//
//    @Test
//    void index() throws Exception {
//        moc.perform(MockMvcRequestBuilders.get("/"))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(content().string("Bienvenido a cines-CIC"));
//    }
//
//    @Test
//    void verCine() throws Exception {
//
//        String contenidoCine = "<pre>CINE_1\t\tSALA_1\t\tSESION_1: 17:00, Film1\t\t Entradas disponibles: 100\n" +
//                "CINE_1\t\tSALA_1\t\tSESION_2: 20:00, Film1\t\t Entradas disponibles: 100\n" +
//                "CINE_1\t\tSALA_1\t\tSESION_3: 22:30, Film2\t\t Entradas disponibles: 100\n" +
//                "CINE_1\t\tSALA_2\t\tSESION_4: 17:00, Film3\t\t Entradas disponibles: 50\n" +
//                "CINE_1\t\tSALA_2\t\tSESION_5: 20:00, Film2\t\t Entradas disponibles: 50\n" +
//                "CINE_1\t\tSALA_2\t\tSESION_6: 22:30, Film4\t\t Entradas disponibles: 50\n" +
//                "CINE_1\t\tSALA_3\t\tSESION_7: 17:00, Film5\t\t Entradas disponibles: 20\n" +
//                "CINE_1\t\tSALA_3\t\tSESION_8: 20:00, Film3\t\t Entradas disponibles: 20\n" +
//                "CINE_1\t\tSALA_3\t\tSESION_9: 22:30, Film5\t\t Entradas disponibles: 20\n" +
//                "\n" +
//                "{Total descuentos=0.0, Total Recaudado=0.0, Aforo total=510, Ocupacion=0.0, Entradas vendidas=0}\n" +
//                "</pre>\n";
//
//
//        moc.perform(MockMvcRequestBuilders.get("/cine/{id}", CINE_1.getId())
//                        .contentType(MediaType.TEXT_PLAIN))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(content().string(contenidoCine));
//    }
//}
package es.cic.gestorentradas.cotrollers;


import es.cic.gestorentradas.gestion.GestorVentasCines;
import es.cic.gestorentradas.gestion.VentaDatos;
import es.cic.gestorentradas.services.CineService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static es.cic.gestorentradas.gestion.CineDatos.CINE_1;
import static es.cic.gestorentradas.gestion.SesionDatos.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;


@SpringBootTest

@AutoConfigureMockMvc
class CineControllerTest {


    @Autowired

    private MockMvc moc;

    @Autowired

    private CineService cineService;


    @BeforeEach
    void clean() {
        GestorVentasCines.removeVentaPorId("VENTA_1");
        VentaDatos.setUltimoId(0);
        SESION_2.setEntradasDisponibles(100);
        SESION_7.setEntradasDisponibles(20);
        SESION_3.setEntradasDisponibles(100);
    }


    @Test
    void index() throws Exception {

        moc.perform(MockMvcRequestBuilders.get("/"))

                .andExpect(MockMvcResultMatchers.status().isOk())

                .andExpect(content().string("Bienvenido a cines-CIC"));

    }


    @Test
    void verCine() throws Exception {


        String contenidoCine = "<pre>CINE_1\t\tSALA_1\t\tSESION_1: 17:00, Film1\t\t Entradas disponibles: 100\n" +
                "CINE_1\t\tSALA_1\t\tSESION_2: 20:00, Film1\t\t Entradas disponibles: 100\n" +
                "CINE_1\t\tSALA_1\t\tSESION_3: 22:30, Film2\t\t Entradas disponibles: 100\n" +
                "CINE_1\t\tSALA_2\t\tSESION_4: 17:00, Film3\t\t Entradas disponibles: 50\n" +
                "CINE_1\t\tSALA_2\t\tSESION_5: 20:00, Film2\t\t Entradas disponibles: 50\n" +
                "CINE_1\t\tSALA_2\t\tSESION_6: 22:30, Film4\t\t Entradas disponibles: 50\n" +
                "CINE_1\t\tSALA_3\t\tSESION_7: 17:00, Film5\t\t Entradas disponibles: 20\n" +
                "CINE_1\t\tSALA_3\t\tSESION_8: 20:00, Film3\t\t Entradas disponibles: 20\n" +
                "CINE_1\t\tSALA_3\t\tSESION_9: 22:30, Film5\t\t Entradas disponibles: 20\n" +
                "\n" +
                "{Total descuentos=0.0, Total Recaudado=0.0, Aforo total=510, Ocupacion=0.0, Entradas vendidas=0}</pre>\n";


        moc.perform(MockMvcRequestBuilders.get("/cine/{id}", CINE_1.getId())

                        .contentType(MediaType.TEXT_PLAIN))

                .andExpect(MockMvcResultMatchers.status().isOk())

                .andExpect(content().string(contenidoCine));

    }

}
