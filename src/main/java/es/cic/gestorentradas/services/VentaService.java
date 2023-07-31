package es.cic.gestorentradas.services;

import es.cic.gestorentradas.assembler.AssemblerVenta;
import es.cic.gestorentradas.excepciones.VentaException;
import es.cic.gestorentradas.gestion.CineDatos;
import es.cic.gestorentradas.gestion.GestorVentasCines;
import es.cic.gestorentradas.gestion.SesionDatos;
import es.cic.gestorentradas.gestion.VentaDatos;
import org.springframework.stereotype.Service;

@Service
public class VentaService {

    public VentaDatos crearVenta(int numEntradasCompradas, SesionDatos sesionDatos) {
        if (numEntradasCompradas > GestorVentasCines.entradasDisponibles(sesionDatos, CineDatos.CINE_1))
            throw new VentaException("No hay suficientes entradas. Quiere " + numEntradasCompradas + " pero hay " + GestorVentasCines.entradasDisponibles(sesionDatos, CineDatos.CINE_1) + "\n");
        if (numEntradasCompradas <= 0)
            throw new VentaException("El numero de entradas tiene que ser mayor de 0\n");

        return AssemblerVenta.assembleVenta(numEntradasCompradas, sesionDatos);
    }

    public VentaDatos verVenta(String id) {
        return GestorVentasCines.getSalasCine("CINE_1").stream()
                .flatMap(salaDatos -> salaDatos.getSesiones().stream())
                .flatMap(sesion -> sesion.getVentas().stream())
                .filter(venta -> venta.getId().equalsIgnoreCase(id))
                .findFirst()
                .orElseThrow(() -> new VentaException("No se ha encontrado la venta con id " + id + "\n"));
    }


    public VentaDatos modificarVenta(String idVenta, int entradasCancelar) {
        VentaDatos ventaToModificar = GestorVentasCines.buscarVenta(idVenta);
        if (ventaToModificar.isCancelada()) throw new VentaException("La venta esta cancelada\n");
        if (ventaToModificar.getNumEntradas() < entradasCancelar)
            throw new VentaException("No se pueden cancelar más entradas que las que se tienen compradas. Quiere cancelar " + entradasCancelar + " pero hay tiene compradas " + ventaToModificar.getNumEntradas() + "\n");
        if (entradasCancelar < 0) throw new VentaException("El numero de entradas tiene que ser mayor de 0\n");

        validarCambio(entradasCancelar, ventaToModificar);

        return ventaToModificar;
    }


    public VentaDatos modificarVenta(String idVenta, SesionDatos session) {
        VentaDatos ventaToModificar = GestorVentasCines.buscarVenta(idVenta);
        int entradasNuevaSesion = ventaToModificar.getNumEntradas();
        if (entradasNuevaSesion > session.getEntradasDisponibles())
            throw new VentaException("No hay suficientes entradas para la nueva sesion. Quiere " + entradasNuevaSesion + " pero para la sesion " + session.getId() + " solo hay " + session.getEntradasDisponibles() + "\n");

        ventaToModificar.getSesionDto().setEntradasDisponibles(ventaToModificar.getSesionDto().getEntradasDisponibles() + ventaToModificar.getNumEntradas());
        ventaToModificar.setSesionDto(session);
        ventaToModificar.getSesionDto().setEntradasDisponibles(ventaToModificar.getSesionDto().getEntradasDisponibles() - ventaToModificar.getNumEntradas());


        return ventaToModificar;
    }

    public VentaDatos modificarVenta(String idVenta, Integer entradasCancelar, SesionDatos session) {
        VentaDatos ventaModificada = GestorVentasCines.buscarVenta(idVenta);
        int entradasNuevaSesion = ventaModificada.getNumEntradas() - entradasCancelar;
        if (entradasNuevaSesion > session.getEntradasDisponibles())
            throw new VentaException("No hay suficientes entradas para la nueva sesion. Quiere " + entradasNuevaSesion + " pero para la sesion " + session.getId() + " solo hay " + session.getEntradasDisponibles() + "\n");
        if (entradasCancelar < 0) throw new VentaException("El numero de entradas tiene que ser mayor de 0\n");

        validarCambio(session, ventaModificada, entradasNuevaSesion);

        return ventaModificada;
    }

    public void eliminarVenta(String idVenta) {
        GestorVentasCines.eliminarVenta(idVenta);
    }

    private static void validarCambio(SesionDatos session, VentaDatos ventaModificada, int entradasNuevaSesion) {
        ventaModificada.getSesionDto().setEntradasDisponibles(ventaModificada.getSesionDto().getEntradasDisponibles() + ventaModificada.getNumEntradas());
        ventaModificada.setSesionDto(session);
        ventaModificada.setNumEntradas(entradasNuevaSesion);
        ventaModificada.getSesionDto().setEntradasDisponibles(ventaModificada.getSesionDto().getEntradasDisponibles() - entradasNuevaSesion);
        ventaModificada.setDescuento(ventaModificada.calcularDescuento());
        ventaModificada.setTotalPagar(ventaModificada.calcularTotalPagar());
        ventaModificada.setNumEntradasCanceladas(0);
    }

    private static void validarCambio(int entradasCancelar, VentaDatos ventaToModificar) {
        ventaToModificar.setNumEntradas(ventaToModificar.getNumEntradas() - entradasCancelar);
        ventaToModificar.setNumEntradasCanceladas(ventaToModificar.getNumEntradasCanceladas() + entradasCancelar);
        ventaToModificar.setTotalPagar(ventaToModificar.calcularTotalPagar());
        ventaToModificar.setDescuento(ventaToModificar.calcularDescuento());
        ventaToModificar.getSesionDto().setEntradasDisponibles(ventaToModificar.getSesionDto().getEntradasDisponibles() + entradasCancelar);

        if (ventaToModificar.getNumEntradas() == 0) ventaToModificar.setCancelada(true);
    }
}
