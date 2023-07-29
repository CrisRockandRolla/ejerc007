package es.cic.gestorentradas.excepciones;

public class VentaException extends RuntimeException {

    public VentaException(String message) {
        super(message);
    }

    public VentaException(String message, Throwable cause) {
        super(message, cause);
    }
}
