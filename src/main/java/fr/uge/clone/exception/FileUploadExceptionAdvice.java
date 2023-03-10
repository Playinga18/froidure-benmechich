package fr.uge.clone.exception;

import fr.uge.clone.message.ResponseMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Gestionnaire d'exception pour les {@link MaxUploadSizeExceededException}.
 *
 * Cette classe étend {@link ResponseEntityExceptionHandler} et fournit une méthode
 * {@link #handleMaxSizeException(MaxUploadSizeExceededException)} pour gérer les
 * {@link MaxUploadSizeExceededException} levées lors de l'envoi de fichiers.
 *
 * @see MaxUploadSizeExceededException
 * @see ResponseEntityExceptionHandler
 */
@ControllerAdvice
public class FileUploadExceptionAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity<ResponseMessage> handleMaxSizeException(MaxUploadSizeExceededException exc) {
        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage("File too large!"));
    }
}