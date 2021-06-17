package test.lowes.urlShortner.lowesurlShortner.errorhandling;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityNotFoundException;

import java.net.MalformedURLException;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.OK;

@ControllerAdvice
    public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

        /**
         * Handle the {@link EntityNotFoundException}
         *
         * @param ex
         * @param request
         * @return {@link ResponseEntity} with status code 400
         */
        @ExceptionHandler(EntityNotFoundException.class)
        public ResponseEntity<Object> appExceptionHandler(EntityNotFoundException ex, WebRequest request) {
            return handleExceptionInternal(ex, ex.getMessage(), null,OK , request);
        }

    /**
     *  Handle the {@link MalformedURLException}
     * @param ex
     * @param request
     * @return
     */
    @ExceptionHandler(MalformedURLException.class)
    public ResponseEntity<Object> appExceptionHandler(MalformedURLException ex, WebRequest request) {
        return handleExceptionInternal(ex, ex.getMessage(), null,BAD_REQUEST , request);
    }
}
