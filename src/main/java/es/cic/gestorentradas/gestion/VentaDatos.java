package es.cic.gestorentradas.gestion;

public class VentaDatos {

    private static long ultimoId = 0;
    private static final double PRECIO_ENTRADA = 5;
    private static final double DESCUENTO = 0.1;

    private final String id;
    private int numEntradas, numEntradasCanceladas;
    private double totalPagar, descuento;
    private boolean cancelada;
    private SesionDatos sesionDatos;

    public VentaDatos() {
        this.id = "VENTA_" + generarId();
    }

    public double calcularTotalPagar() {
        if (numEntradas >= 5) return numEntradas * PRECIO_ENTRADA * (1 - DESCUENTO);
        else return numEntradas * PRECIO_ENTRADA;
    }

    public double calcularDescuento() {
        if (numEntradas >= 5) return numEntradas * PRECIO_ENTRADA * DESCUENTO;
        else return 0;
    }

    public boolean isCancelada() {
        return numEntradas == 0;
    }

    public void setCancelada(boolean cancelada) {
        this.cancelada = cancelada;
    }

    public String getId() {
        return id;
    }

    public int getNumEntradas() {
        return numEntradas;
    }

    public void setNumEntradas(int numEntradas) {
        this.numEntradas = numEntradas;
    }

    public double getTotalPagar() {
        return totalPagar;
    }

    public void setTotalPagar(double totalPagar) {
        this.totalPagar = totalPagar;
    }

    public double getDescuento() {
        return descuento;
    }

    public void setDescuento(double descuento) {
        this.descuento = descuento;
    }

    public int getNumEntradasCanceladas() {
        return numEntradasCanceladas;
    }

    public void setNumEntradasCanceladas(int numEntradasCanceladas) {
        this.numEntradasCanceladas = numEntradasCanceladas;
    }

    public SesionDatos getSesionDto() {
        return sesionDatos;
    }

    public void setSesionDto(SesionDatos sesionDatos) {
        this.sesionDatos = sesionDatos;
    }


    @Override
    public String toString() {
        return "Venta Nº " + id + "\t" + sesionDatos +/*.append("\t").append(sesionDatos.getSalaDto().getId())*/
                "\n\tEntradas " + numEntradas +
                "\tPrecio " + PRECIO_ENTRADA +
                "\ttotal " + numEntradas * PRECIO_ENTRADA +
                "\tDescuento " + descuento +
                "\ttotalPagar " + totalPagar +
                "\n\tVenta cancelada " + cancelada +
                "\t\tEntradas Canceladas " + numEntradasCanceladas + "\n";
    }

    private static synchronized long generarId() {
        return ++ultimoId;
    }

    public static long getUltimoId() {
        return ultimoId;
    }

    public static void setUltimoId(long ultimoId) {
        VentaDatos.ultimoId = ultimoId;
    }

    public SesionDatos getSesionDatos() {
        return sesionDatos;
    }

    public void setSesionDatos(SesionDatos sesionDatos) {
        this.sesionDatos = sesionDatos;
    }
}
