package pl.lodz.p.edu.carpooling.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class RestResponseExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = BaseAppException.class)
    public ResponseEntity<Object> handleException(BaseAppException ex, WebRequest request) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", ex.getStatus());
        response.put("error", ex.getReason());
        return new ResponseEntity(response, new HttpHeaders(), ex.getStatus());
    }

    @ExceptionHandler(value = AuthenticationException.class)
    public ResponseEntity<Object> handlerException(Exception e, WebRequest request) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", HttpStatus.UNAUTHORIZED);
        response.put("error", e.getMessage());
        return new ResponseEntity(response, new HttpHeaders(), HttpStatus.UNAUTHORIZED);
    }
}
