package ua.logos.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ua.logos.domaim.exception.ExceptionResponse;

@ControllerAdvice
public class CustomResponseEntityExceptionHandler  extends ResponseEntityExceptionHandler {

    @ExceptionHandler({Exception.class,ServerException.class})
    public  ResponseEntity<?>handleAllException(Exception e,WebRequest req) {
        ExceptionResponse exceptionResponse =
                new ExceptionResponse(e.getMessage(), req.getDescription(false));
        return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

        @ExceptionHandler(AlreadyExistsException.class)
        public ResponseEntity<?> handleAlreadyExistsException (Exception e, WebRequest req){
            ExceptionResponse exceptionResponse =
                    new ExceptionResponse(e.getMessage(), req.getDescription(false));
            return new ResponseEntity<>(exceptionResponse, HttpStatus.CONFLICT);
        }

        @ExceptionHandler(NotFoundException.class)
        public ResponseEntity<?> handleNotFoundException (Exception e, WebRequest req){
            ExceptionResponse exceptionResponse =
                    new ExceptionResponse(e.getMessage(), req.getDescription(false));
            return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
        }

    }


