package es.cic.gestorentradas.cotrollers;

import es.cic.gestorentradas.gestion.GestorVentasCines;
import es.cic.gestorentradas.gestion.VentaDatos;
import es.cic.gestorentradas.services.VentaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static es.cic.gestorentradas.gestion.CineDatos.CINE_1;
import static es.cic.gestorentradas.gestion.SesionDatos.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class VentaControllerTest {

    @Autowired
    private MockMvc moc;
    @Autowired
    private VentaService ventaService;


    @BeforeEach
    void clean() {
        GestorVentasCines.removeVentaPorId("VENTA_1");
        VentaDatos.setUltimoId(0);
        SESION_2.setEntradasDisponibles(100);
        SESION_7.setEntradasDisponibles(20);
    }

    @Test
    void crear() throws Exception {
        int numEntrada = 2;
        String respuesta = "Venta Nº VENTA_1\tSESION_2\n" +
                "\tEntradas 2\tPrecio 5.0\ttotal 10.0\tDescuento 0.0\ttotalPagar 10.0\n" +
                "\tVenta cancelada false\t\tEntradas Canceladas 0" + "\n";

        moc.perform(MockMvcRequestBuilders.post("/create/{numEntradas}/{sesion}", numEntrada, SESION_2))
                .andExpect(status().isOk())
                .andExpect(content().string(respuesta));
    }

    @Test
    void crear_EntradasNegativo() throws Exception {
        int numEntrada = 0;
        moc.perform(MockMvcRequestBuilders.post("/create/{numEntradas}/{sesion}", numEntrada, SESION_2))
                .andExpect(status().is5xxServerError())
                .andExpect(content().string("El numero de entradas a comprar tiene que ser mayor de 0"));
    }

    @Test
    void crear_EntradasInsuficientes() throws Exception {
        int numEntrada = 300;
        moc.perform(MockMvcRequestBuilders.post("/create/{numEntradas}/{sesion}", numEntrada, SESION_2))
                .andExpect(status().is5xxServerError())
                .andExpect(content().string("No hay suficientes entradas. Quiere 300 pero hay 100\n"));
    }

    @Test
    void ver() throws Exception {
        VentaDatos ventaDatos = new VentaDatos();
        ventaDatos.setNumEntradas(2);
        ventaDatos.setTotalPagar(ventaDatos.calcularTotalPagar());
        ventaDatos.setDescuento(ventaDatos.calcularDescuento());
        ventaDatos.setSesionDto(SESION_3);
        GestorVentasCines.addVenta(ventaDatos, CINE_1.getId());

        moc.perform(MockMvcRequestBuilders.get("/venta/{id}", ventaDatos.getId()))
                .andExpect(status().isOk())
                .andExpect(content().string(ventaDatos.toString()));
    }

    @Test
    void verNoExisteId() throws Exception {
        moc.perform(MockMvcRequestBuilders.get("/venta/{id}", "VENTA_25"))
                .andExpect(status().is5xxServerError())
                .andExpect(content().string("No se ha encontrado la venta con id VENTA_25\n"));
    }

    @Test
    void modificarVenta_Entradas() throws Exception {
        VentaDatos ventaDatos = new VentaDatos();
        ventaDatos.setNumEntradas(4);
        ventaDatos.setTotalPagar(ventaDatos.calcularTotalPagar());
        ventaDatos.setDescuento(ventaDatos.calcularDescuento());
        ventaDatos.setSesionDto(SESION_3);
        GestorVentasCines.addVenta(ventaDatos, CINE_1.getId());

        int entradasCancelar = 2;

        String respuesta = "Venta Nº VENTA_1\tSESION_3\n" +
                "\tEntradas 2\tPrecio 5.0\ttotal 10.0\tDescuento 0.0\ttotalPagar 10.0\n" +
                "\tVenta cancelada false\t\tEntradas Canceladas 2" + "\n";

        moc.perform(MockMvcRequestBuilders.patch("/update/venta/{id}/{entradasCancelar}", ventaDatos.getId(), entradasCancelar))
                .andExpect(status().isOk())
                .andExpect(content().string(respuesta));

    }

    @Test
    void modificarVenta_EntradasNegativo() throws Exception {
        VentaDatos ventaDatos = new VentaDatos();
        ventaDatos.setNumEntradas(4);
        ventaDatos.setTotalPagar(ventaDatos.calcularTotalPagar());
        ventaDatos.setDescuento(ventaDatos.calcularDescuento());
        ventaDatos.setSesionDto(SESION_3);
        GestorVentasCines.addVenta(ventaDatos, CINE_1.getId());

        int entradasCancelar = -2;

        moc.perform(MockMvcRequestBuilders.patch("/update/venta/{id}/{entradasCancelar}", ventaDatos.getId(), entradasCancelar))
                .andExpect(status().is5xxServerError())
                .andExpect(content().string("El numero de entradas a cancelar tiene que ser positivo\n"));

    }


    @Test
    void modificarVenta_Sesion() throws Exception {
        VentaDatos ventaDatos = new VentaDatos();
        ventaDatos.setNumEntradas(4);
        ventaDatos.setTotalPagar(ventaDatos.calcularTotalPagar());
        ventaDatos.setDescuento(ventaDatos.calcularDescuento());
        ventaDatos.setSesionDto(SESION_3);
        GestorVentasCines.addVenta(ventaDatos, CINE_1.getId());


        String respuesta = "Venta Nº VENTA_1\tSESION_7\n" +
                "\tEntradas 4\tPrecio 5.0\ttotal 20.0\tDescuento 0.0\ttotalPagar 20.0\n" +
                "\tVenta cancelada false\t\tEntradas Canceladas 0" + "\n";

        moc.perform(MockMvcRequestBuilders.patch("/update/venta/{id}/_{sesion}", ventaDatos.getId(), SESION_7))
                .andExpect(status().isOk())
                .andExpect(content().string(respuesta));
    }

    @Test
    void modificarVenta_Sesion_EntradasInsuficientes() throws Exception {
        VentaDatos ventaDatos = new VentaDatos();
        ventaDatos.setNumEntradas(50);
        ventaDatos.setTotalPagar(ventaDatos.calcularTotalPagar());
        ventaDatos.setDescuento(ventaDatos.calcularDescuento());
        ventaDatos.setSesionDto(SESION_3);
        GestorVentasCines.addVenta(ventaDatos, CINE_1.getId());


        moc.perform(MockMvcRequestBuilders.patch("/update/venta/{id}/_{sesion}", ventaDatos.getId(), SESION_7))
                .andExpect(status().is5xxServerError())
                .andExpect(content().string("No hay suficientes entradas para la nueva sesion. Quiere 50 pero para la sesion SESION_7 solo hay 20\n"));
    }

    @Test
    void modificarVenta_SesionNoExiste() throws Exception {
        VentaDatos ventaDatos = new VentaDatos();
        ventaDatos.setNumEntradas(4);
        ventaDatos.setTotalPagar(ventaDatos.calcularTotalPagar());
        ventaDatos.setDescuento(ventaDatos.calcularDescuento());
        ventaDatos.setSesionDto(SESION_3);
        GestorVentasCines.addVenta(ventaDatos, CINE_1.getId());

        moc.perform(MockMvcRequestBuilders.patch("/update/venta/{id}/_{sesion}", ventaDatos.getId(), "SESION_25"))
                .andExpect(status().is4xxClientError())
                .andExpect(content().string("No se encuentra la pagina solicitada\n"));
    }

    @Test
    void modificarVenta_EntradasSesion() throws Exception {
        VentaDatos ventaDatos = new VentaDatos();
        ventaDatos.setNumEntradas(4);
        ventaDatos.setTotalPagar(ventaDatos.calcularTotalPagar());
        ventaDatos.setDescuento(ventaDatos.calcularDescuento());
        ventaDatos.setSesionDto(SESION_3);
        GestorVentasCines.addVenta(ventaDatos, CINE_1.getId());


        String respuesta = "Venta Nº VENTA_1\tSESION_7\n" +
                "\tEntradas 2\tPrecio 5.0\ttotal 10.0\tDescuento 0.0\ttotalPagar 10.0\n" +
                "\tVenta cancelada false\t\tEntradas Canceladas 0" + "\n";

        moc.perform(MockMvcRequestBuilders.patch("/update/venta/{id}/{entradasCancelar}/{sesion}", ventaDatos.getId(), 2, SESION_7))
                .andExpect(status().isOk())
                .andExpect(content().string(respuesta));
    }

    @Test
    void modificarVenta_EntradasSesion_EntradasInsuficientes() throws Exception {
        VentaDatos ventaDatos = new VentaDatos();
        ventaDatos.setNumEntradas(50);
        ventaDatos.setTotalPagar(ventaDatos.calcularTotalPagar());
        ventaDatos.setDescuento(ventaDatos.calcularDescuento());
        ventaDatos.setSesionDto(SESION_3);
        GestorVentasCines.addVenta(ventaDatos, CINE_1.getId());


        moc.perform(MockMvcRequestBuilders.patch("/update/venta/{id}/{entradasCancelar}/{sesion}", ventaDatos.getId(), 2, SESION_7))
                .andExpect(status().is5xxServerError())
                .andExpect(content().string("No hay suficientes entradas para la nueva sesion. Quiere 48 pero para la sesion SESION_7 solo hay 20\n"));
    }

    @Test
    void modificarVenta_NoExisteId() throws Exception {
        moc.perform(MockMvcRequestBuilders.patch("/update/venta/{id}/{entradasCancelar}", "VENTA_60", 2))
                .andExpect(status().is5xxServerError())
                .andExpect(content().string("No se ha encontrado la venta con id VENTA_60\n"));
    }

    @Test
    void eliminar() throws Exception {
        VentaDatos ventaDatos = new VentaDatos();
        ventaDatos.setNumEntradas(2);
        ventaDatos.setTotalPagar(ventaDatos.calcularTotalPagar());
        ventaDatos.setDescuento(ventaDatos.calcularDescuento());
        ventaDatos.setSesionDto(SESION_3);
        GestorVentasCines.addVenta(ventaDatos, CINE_1.getId());

        moc.perform(MockMvcRequestBuilders.delete("/delete/venta/{id}", "VENTA_1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Venta eliminada correctamente\n"));
    }

    @Test
    void eliminarNoExisteId() throws Exception {
        moc.perform(MockMvcRequestBuilders.delete("/delete/venta/{id}", "VENTA_60"))
                .andExpect(status().is5xxServerError())
                .andExpect(content().string("No se ha encontrado la venta con id VENTA_60\n"));
    }
}