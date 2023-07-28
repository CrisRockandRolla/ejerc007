package es.cic.gestorentradas.dto;

public class DatosCache {

    private int numEntradasVendidas;
    private double totalRecaudado;
    private double totalDescuentos;
    private double numeroVentasDescuento;
    private int numEntradasNoVendidas;
    private String pelicula;
    private String idSala;


    public int getNumEntradasVendidas() {
        return numEntradasVendidas;
    }

    public void setNumEntradasVendidas(int numEntradasVendidas) {
        this.numEntradasVendidas = numEntradasVendidas;
    }

    public double getTotalRecaudado() {
        return totalRecaudado;
    }

    public void setTotalRecaudado(double totalRecaudado) {
        this.totalRecaudado = totalRecaudado;
    }

    public double getTotalDescuentos() {
        return totalDescuentos;
    }

    public void setTotalDescuentos(double totalDescuentos) {
        this.totalDescuentos = totalDescuentos;
    }

    public int getNumEntradasNoVendidas() {
        return numEntradasNoVendidas;
    }

    public void setNumEntradasNoVendidas(int numEntradasNoVendidas) {
        this.numEntradasNoVendidas = numEntradasNoVendidas;
    }

    public double getNumeroVentasDescuento() {
        return numeroVentasDescuento;
    }

    public void setNumeroVentasDescuento(double numeroVentasDescuento) {
        this.numeroVentasDescuento = numeroVentasDescuento;
    }

    public String getIdSala() {
        return idSala;
    }

    public void setIdSala(String idSala) {
        this.idSala = idSala;
    }

    public String getPelicula() {
        return pelicula;
    }

    public void setPelicula(String pelicula) {
        this.pelicula = pelicula;
    }

    @Override
    public String toString() {
        return /*"Sala: " + idSala + "\t" + */"Aforo total: " + (numEntradasVendidas + numEntradasNoVendidas) + "\t" +
//                "Película: " + pelicula + "\n" +
                "Numero de entradas vendidas: " + numEntradasVendidas + "\n" +
                "Recaudacion Total: " + totalRecaudado + "\n" +
                "Ocupacion: " + numEntradasVendidas * 100 / (numEntradasVendidas + numEntradasNoVendidas) + "%\n" +
                "Nº Ventas con descuentos: " + numeroVentasDescuento + "\n" +
                "Total Descuentos: " + totalDescuentos + "\n";
    }
}
