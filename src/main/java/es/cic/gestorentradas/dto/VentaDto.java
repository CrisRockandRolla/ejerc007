package es.cic.gestorentradas.dto;

public class VentaDto {

    private static final double PRECIO_ENTRADA = 5;
    private static final double DESCUENTO = 0.1;
    private Long id;
    private int numEntradas;
    private Double total;
    private double descuento;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getNumEntradas() {
        return numEntradas;
    }

    public void setNumEntradas(int numEntradas) {
        this.numEntradas = numEntradas;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public double getDescuento() {
        return descuento;
    }

    public void setDescuento(double descuento) {
        this.descuento = descuento;
    }

    public double comprarEntradas() {

        return numEntradas * PRECIO_ENTRADA;
    }

    public double calcularDecuento() {
        if (numEntradas >= 5) {
            return numEntradas * PRECIO_ENTRADA * DESCUENTO;
        }
        return 0;
    }
}
