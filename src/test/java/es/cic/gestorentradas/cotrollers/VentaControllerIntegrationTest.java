package es.cic.gestorentradas.cotrollers;

import es.cic.gestorentradas.constantes.DatosTest;
import es.cic.gestorentradas.excepciones.AvisosExcepciones;
import es.cic.gestorentradas.gestion.GestorVentasCines;
import es.cic.gestorentradas.gestion.VentaDatos;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static es.cic.gestorentradas.gestion.GestorVentasCines.preCargarVenta;
import static es.cic.gestorentradas.gestion.SesionDatos.SESION_2;
import static es.cic.gestorentradas.gestion.SesionDatos.SESION_7;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class VentaControllerIntegrationTest {

    @Autowired
    private MockMvc moc;

    @AfterEach
    void clean() {
        GestorVentasCines.removeVentaPorId("VENTA_1");
        VentaDatos.setUltimoId(0);
        SESION_2.setEntradasDisponibles(100);
        SESION_2.setEntradasVendidas(0);
        SESION_7.setEntradasDisponibles(20);
        SESION_7.setEntradasVendidas(0);
    }

    @Test
    void crear() throws Exception {
        moc.perform(MockMvcRequestBuilders.post("/create/{numEntradas}/{sesion}", 50, SESION_2))
                .andExpect(status().isOk())
                .andExpect(content().string(DatosTest.respuestaVerCrear));
    }

    @Test
    void crear_EntradasNegativo() throws Exception {
        moc.perform(MockMvcRequestBuilders.post("/create/{numEntradas}/{sesion}", 0, SESION_2))
                .andExpect(status().is5xxServerError())
                .andExpect(content().string(AvisosExcepciones.entradasNegativo));
    }

    @Test
    void crear_EntradasInsuficientes() throws Exception {
        moc.perform(MockMvcRequestBuilders.post("/create/{numEntradas}/{sesion}", 300, SESION_2))
                .andExpect(status().is5xxServerError())
                .andExpect(content().string("No hay suficientes entradas. Quiere " + 300 + " pero hay " + SESION_2.getEntradasDisponibles() + "\n"));
    }

    @Test
    void ver() throws Exception {
        VentaDatos ventaDatos = preCargarVenta(50);

        moc.perform(MockMvcRequestBuilders.get("/venta/{id}", ventaDatos.getId()))
                .andExpect(status().isOk())
                .andExpect(content().string(DatosTest.respuestaVerCrear));
    }

    @Test
    void verNoExisteId() throws Exception {
        moc.perform(MockMvcRequestBuilders.get("/venta/{id}", "VENTA_25"))
                .andExpect(status().is5xxServerError())
                .andExpect(content().string(AvisosExcepciones.idVentaNoExiste));
    }

    @Test
    void modificarVenta_Entradas() throws Exception {
        VentaDatos ventaDatos = preCargarVenta(50);

        moc.perform(MockMvcRequestBuilders.patch("/update/venta/{id}/{entradasCancelar}", ventaDatos.getId(), 2))
                .andExpect(status().isOk())
                .andExpect(content().string(DatosTest.respuestaModificarEntradas));
    }

    @Test
    void modificarVenta_EntradasNegativo() throws Exception {
        VentaDatos ventaDatos = preCargarVenta(50);

        moc.perform(MockMvcRequestBuilders.patch("/update/venta/{id}/{entradasCancelar}", ventaDatos.getId(), -2))
                .andExpect(status().is5xxServerError())
                .andExpect(content().string(AvisosExcepciones.entradasNegativo));
    }

    @Test
    void modificarVenta_Sesion() throws Exception {
        VentaDatos ventaDatos = preCargarVenta(4);

        moc.perform(MockMvcRequestBuilders.patch("/update/venta/{id}/_{sesion}", ventaDatos.getId(), SESION_7))
                .andExpect(status().isOk())
                .andExpect(content().string(DatosTest.respuestaModificarSesion));
    }

    @Test
    void modificarVenta_Sesion_EntradasInsuficientes() throws Exception {
        VentaDatos ventaDatos = preCargarVenta(50);

        moc.perform(MockMvcRequestBuilders.patch("/update/venta/{id}/_{sesion}", ventaDatos.getId(), SESION_7))
                .andExpect(status().is5xxServerError())
                .andExpect(content().string("No hay suficientes entradas para la nueva sesion. Quiere 50 pero para la sesion SESION_7 solo hay 20\n"));
    }

    @Test
    void modificarVenta_SesionNoExiste() throws Exception {
        VentaDatos ventaDatos = preCargarVenta(50);

        moc.perform(MockMvcRequestBuilders.patch("/update/venta/{id}/_{sesion}", ventaDatos.getId(), "SESION_25"))
                .andExpect(status().is4xxClientError())
                .andExpect(content().string(AvisosExcepciones.paginaNoEncontrada));
    }

    @Test
    void modificarVenta_EntradasSesion() throws Exception {
        VentaDatos ventaDatos = preCargarVenta(4);

        moc.perform(MockMvcRequestBuilders.patch("/update/venta/{id}/{entradasCancelar}/{sesion}", ventaDatos.getId(), 2, SESION_7))
                .andExpect(status().isOk())
                .andExpect(content().string(DatosTest.respuestaModificarEntradasSesion));
    }

    @Test
    void modificarVenta_EntradasSesion_EntradasInsuficientes() throws Exception {
        VentaDatos ventaDatos = preCargarVenta(50);

        moc.perform(MockMvcRequestBuilders.patch("/update/venta/{id}/{entradasCancelar}/{sesion}", ventaDatos.getId(), 2, SESION_7))
                .andExpect(status().is5xxServerError())
                .andExpect(content().string("No hay suficientes entradas para la nueva sesion. Quiere 48 pero para la sesion SESION_7 solo hay 20\n"));
    }

    @Test
    void modificarVenta_NoExisteId() throws Exception {
        moc.perform(MockMvcRequestBuilders.patch("/update/venta/{id}/{entradasCancelar}", "VENTA_25", 2))
                .andExpect(status().is5xxServerError())
                .andExpect(content().string(AvisosExcepciones.idVentaNoExiste));
    }

    @Test
    void eliminar() throws Exception {
        moc.perform(MockMvcRequestBuilders.delete("/delete/venta/{id}", preCargarVenta(4).getId()))
                .andExpect(status().isOk())
                .andExpect(content().string(DatosTest.respuestaEliminadaOK));
    }

    @Test
    void eliminarNoExisteId() throws Exception {
        moc.perform(MockMvcRequestBuilders.delete("/delete/venta/{id}", "VENTA_25"))
                .andExpect(status().is5xxServerError())
                .andExpect(content().string(AvisosExcepciones.idVentaNoExiste));
    }
}
