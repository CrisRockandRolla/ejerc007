package es.cic.gestorentradas.cotrollers;

import es.cic.gestorentradas.excepciones.EntradasInsuficientesException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(EntradasInsuficientesException.class)
    public ResponseEntity<String> handleRuntimeException(EntradasInsuficientesException ex) {
        String errorMessage = ex.getMessage();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
    }

}
