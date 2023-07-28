package es.cic.gestorentradas.excepciones;

public class EntradasInsuficientesException extends RuntimeException {

    public EntradasInsuficientesException(String message) {
        super(message);
    }

    public EntradasInsuficientesException(String message, Throwable cause) {
        super(message, cause);
    }
}
