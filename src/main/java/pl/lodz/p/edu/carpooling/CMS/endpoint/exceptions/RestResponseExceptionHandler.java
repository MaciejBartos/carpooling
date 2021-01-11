package pl.lodz.p.edu.carpooling.CMS.endpoint.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import pl.lodz.p.edu.carpooling.exception.BaseAppException;

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
}
