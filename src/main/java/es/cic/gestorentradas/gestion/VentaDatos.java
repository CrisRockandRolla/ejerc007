package es.cic.gestorentradas.gestion;

public class VentaDatos {

    private static long ultimoId = 0;
    private static final double PRECIO_ENTRADA = 5;
    private static final double DESCUENTO = 0.1;

    private long id;
    private int numEntradas;
    private double totalPagar;
    private double descuento;
    private boolean cancelada;
    private int numEntradasCanceladas;
    private SesionDatos sesionDatos;

    public VentaDatos() {
        this.id = generarId();
    }

    public double calcularTotalPagar() {
        if (numEntradas >= 5) return numEntradas * PRECIO_ENTRADA * (1 - DESCUENTO);
        else return numEntradas * PRECIO_ENTRADA;
    }

    public double calcularDescuento() {
        if (numEntradas >= 5) return numEntradas * PRECIO_ENTRADA * DESCUENTO;
        else return 0;
    }

    public boolean hayDisponibles() {
        return numEntradas <= sesionDatos.getEntradasDisponibles();
    }

    public boolean isCancelada() {
        return numEntradas == 0;
    }

    public void setCancelada(boolean cancelada) {
        this.cancelada = cancelada;
    }

    public long getId() {
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
        final StringBuilder sb = new StringBuilder("Venta Nº ");
        sb.append(id).append("\t").append(sesionDatos)/*.append("\t").append(sesionDatos.getSalaDto().getId())*/;
        sb.append("\n\tEntradas ").append(numEntradas);
        sb.append("\tPrecio ").append(PRECIO_ENTRADA);
        sb.append("\ttotal ").append(numEntradas * PRECIO_ENTRADA);
        sb.append("\tDescuento ").append(descuento);
        sb.append("\ttotalPagar ").append(totalPagar);
        sb.append("\n\tVenta cancelada ").append(cancelada);
        sb.append("\t\tEntradas Canceladas ").append(numEntradasCanceladas).append("\n");

        return sb.toString();
    }

    private static synchronized long generarId() {
        return ++ultimoId;
    }
}
