package es.cic.gestorentradas.excepciones;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import static es.cic.gestorentradas.excepciones.AvisosExcepciones.PAGINA_NO_ENCONTRADA;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(VentaException.class)
    public ResponseEntity<String> handleRuntimeException(VentaException ex) {
        String errorMessage = ex.getMessage();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleBadRequest(Exception ex) {
        return ResponseEntity.badRequest().body(PAGINA_NO_ENCONTRADA);
    }
}
