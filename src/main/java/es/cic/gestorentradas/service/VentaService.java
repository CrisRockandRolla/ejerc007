package es.cic.gestorentradas.service;

import es.cic.gestorentradas.assembler.AssemblerVenta;
import es.cic.gestorentradas.excepciones.VentaException;
import es.cic.gestorentradas.gestion.GestorVentasCines;
import es.cic.gestorentradas.gestion.SesionDatos;
import es.cic.gestorentradas.gestion.VentaDatos;
import org.springframework.stereotype.Service;

@Service
public class VentaService {

    public VentaDatos crearVenta(int numEntradasCompradas, SesionDatos sesionDatos) {
        if (numEntradasCompradas > GestorVentasCines.entradasDisponibles(sesionDatos))
            throw new VentaException("No hay suficientes entradas. Quiere " + numEntradasCompradas + " pero hay " + GestorVentasCines.entradasDisponibles(sesionDatos) + "\n");

        return AssemblerVenta.assembleVenta(numEntradasCompradas, sesionDatos);
    }

    public VentaDatos verVenta(long id) {
        return GestorVentasCines.getSalasCine("CINE_1").stream()
                .flatMap(salaDatos -> salaDatos.getSesiones().stream())
                .flatMap(sesion -> sesion.getVentas().stream())
                .filter(venta -> venta.getId() == id)
                .findFirst()
                .orElseThrow(() -> new VentaException("No se ha encontrado la venta con id " + id + "\n"));
    }


    public VentaDatos modificarNumeroEntradas(long idVenta, int entradasCancelar) {
        VentaDatos ventaToModificar = GestorVentasCines.getSalasCine("CINE_1").stream()
                .flatMap(salaDatos -> salaDatos.getSesiones().stream())
                .flatMap(sesion -> sesion.getVentas().stream())
                .filter(venta -> venta.getId() == idVenta)
                .findFirst()
                .orElseThrow(() -> new VentaException("No se ha encontrado la venta con id " + idVenta + "\n"));
        if (ventaToModificar.isCancelada()) throw new VentaException("La venta esta cancelada\n");
        if (ventaToModificar.getNumEntradas() < entradasCancelar)
            throw new VentaException("No se pueden cancelar más entradas que las que se tienen compradas. Quiere cancelar " + entradasCancelar + " pero hay tiene compradas " + ventaToModificar.getNumEntradas() + "\n");
        if (entradasCancelar < 0) throw new VentaException("El numero de entradas a cancelar tiene que ser positivo\n");
        ventaToModificar.setNumEntradas(ventaToModificar.getNumEntradas() - entradasCancelar);
        ventaToModificar.setNumEntradasCanceladas(ventaToModificar.getNumEntradasCanceladas() + entradasCancelar);
        ventaToModificar.setTotalPagar(ventaToModificar.calcularTotalPagar());
        ventaToModificar.setDescuento(ventaToModificar.calcularDescuento());
        ventaToModificar.getSesionDto().setEntradasDisponibles(ventaToModificar.getSesionDto().getEntradasDisponibles() + entradasCancelar);

        if (ventaToModificar.getNumEntradas() == 0) ventaToModificar.setCancelada(true);

        return ventaToModificar;
    }

    public VentaDatos modificarSesion(long idVenta, SesionDatos session) {
        VentaDatos ventaToModificar = GestorVentasCines.getSalasCine("CINE_1").stream()
                .flatMap(salaDatos -> salaDatos.getSesiones().stream())
                .flatMap(sesion -> sesion.getVentas().stream())
                .filter(venta -> venta.getId() == idVenta)
                .findFirst()
                .orElseThrow(() -> new VentaException("No se ha encontrado la venta con id " + idVenta + "\n"));
        ventaToModificar.setSesionDto(session);

        return ventaToModificar;
    }

    public VentaDatos modificarEntradasYSesion(long idVenta, Integer entradasCancelar, SesionDatos session) {
        VentaDatos ventaToModificar = GestorVentasCines.getSalasCine("CINE_1").stream()
                .flatMap(salaDatos -> salaDatos.getSesiones().stream())
                .flatMap(sesion -> sesion.getVentas().stream())
                .filter(venta -> venta.getId() == idVenta)
                .findFirst()
                .orElseThrow(() -> new VentaException("No se ha encontrado la venta con id " + idVenta + "\n"));

        int entradasNuevaSesion = ventaToModificar.getNumEntradas() - entradasCancelar;

        if (entradasNuevaSesion > session.getEntradasDisponibles())
            throw new VentaException("No hay suficientes entradas para la nueva sesion. Quiere " + entradasNuevaSesion + " pero para la sesion " + session.getId() + " solo hay " + session.getEntradasDisponibles() + "\n");
        if (entradasCancelar < 0) throw new VentaException("El numero de entradas a cancelar tiene que ser positivo\n");

        ventaToModificar.getSesionDto().setEntradasDisponibles(ventaToModificar.getSesionDto().getEntradasDisponibles() + ventaToModificar.getNumEntradas());
        ventaToModificar.setSesionDto(session);
        ventaToModificar.setNumEntradas(entradasNuevaSesion);
        ventaToModificar.getSesionDto().setEntradasDisponibles(ventaToModificar.getSesionDto().getEntradasDisponibles() - entradasNuevaSesion);
        ventaToModificar.setDescuento(ventaToModificar.calcularDescuento());
        ventaToModificar.setTotalPagar(ventaToModificar.calcularTotalPagar());
        ventaToModificar.setNumEntradasCanceladas(0);

        return ventaToModificar;
    }
}
