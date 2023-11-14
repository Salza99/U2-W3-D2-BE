package DavideSalzani.U2W3D2BE.exceptions;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestControllerAdvice
public class ExceptionsHandler {

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorsListDTO handleBadRequestException(BadRequestException ex){
        if (ex.getErrorsList() != null) {
            List<String> errorsList = ex.getErrorsList().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList();
            return new ErrorsListDTO(ex.getMessage(),LocalDate.now(), errorsList);
        }else{
            return new ErrorsListDTO(ex.getMessage(), LocalDate.now(), new ArrayList<>());
        }
    }
    @ExceptionHandler(UnauthorizedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED) // 401
    public ErrorsPayloadDTO handleUnauthorized(UnauthorizedException e){
        return new ErrorsPayloadDTO(e.getMessage(), LocalDate.now());
    }

    @ExceptionHandler(AlreadyExistException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorsPayloadDTO handleAlreadyExistException(AlreadyExistException ex){
        return new ErrorsPayloadDTO(ex.getMessage(), LocalDate.now());
    }
    @ExceptionHandler(DismissDeviceException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorsPayloadDTO handleDismissDeviceException(DismissDeviceException ex){
        return new ErrorsPayloadDTO(ex.getMessage(), LocalDate.now());
    }
    @ExceptionHandler(UnderMaintenanceException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorsPayloadDTO handleUnderMaintenanceException(UnderMaintenanceException ex){
        return new ErrorsPayloadDTO(ex.getMessage(), LocalDate.now());
    }
    @ExceptionHandler(AlreadyAvailableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorsPayloadDTO handleAlreadyAvailableException(AlreadyAvailableException ex){
        return new ErrorsPayloadDTO(ex.getMessage(), LocalDate.now());
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorsPayloadDTO handleNotFoundException(NotFoundException ex){
        return new ErrorsPayloadDTO(ex.getMessage(), LocalDate.now());
    }
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorsPayloadDTO handleGenericException(Exception ex){
        ex.printStackTrace();
        return new ErrorsPayloadDTO("errore lato server!", LocalDate.now());
    }
}
