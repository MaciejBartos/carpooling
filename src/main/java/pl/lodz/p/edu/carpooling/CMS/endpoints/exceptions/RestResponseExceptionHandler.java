package pl.lodz.p.edu.carpooling.CMS.endpoints.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import pl.lodz.p.edu.carpooling.exceptions.BaseAppException;

@ControllerAdvice
public class RestResponseExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = BaseAppException.class)
    public ResponseEntity<Object> handleException(BaseAppException ex, WebRequest request) {
        return handleExceptionInternal(ex, ex.getReason(), new HttpHeaders(), ex.getStatus(), request);
    }
}
