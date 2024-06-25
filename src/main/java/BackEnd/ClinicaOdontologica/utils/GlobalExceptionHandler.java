package BackEnd.ClinicaOdontologica.utils;

import BackEnd.ClinicaOdontologica.utils.exeptions.ConflictException;
import BackEnd.ClinicaOdontologica.utils.exeptions.EmailNotFoundExeption;
import BackEnd.ClinicaOdontologica.utils.exeptions.IdNotFoundException;
import BackEnd.ClinicaOdontologica.utils.exeptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    // --- ID NOT FOUND EXEPTION ---
    @ExceptionHandler(IdNotFoundException.class)
    public ResponseEntity<Object> handleIdNotFound(IdNotFoundException ex) {
        // Logica
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
    // --- EMAIL NOT FOUND EXEPTION ---
    @ExceptionHandler(EmailNotFoundExeption.class)
    public ResponseEntity<Object> handleEmailNotFound(EmailNotFoundExeption ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }


    // --- BadRequest EXEPTION ---
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<Object> handleBadRequest(IdNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    // --- Conflict EXEPTION ---
    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<Object> handleConflict(ConflictException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
    }

    // --- Service Unavaliable ---
    @ExceptionHandler(ServiceUnavailableException.class)
    public ResponseEntity<Object> handleServiceUnavailable(ServiceUnavailableException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.SERVICE_UNAVAILABLE);
    }
    // --- UNAUTHORIZED ---
    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<Object> handleUnauthorized(UnauthorizedException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.UNAUTHORIZED);
    }





}
