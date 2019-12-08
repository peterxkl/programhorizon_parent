package advice;

import entity.Result;
import enums.ExceptionEnums;
import exception.PhException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author DillonXie
 * @version 1.0
 * @date 12/8/2019 9:21 AM
 */
@ControllerAdvice
public class CommonExceptionHandler {

    @ExceptionHandler(PhException.class)
    public Result handleException(PhException e) {
        ExceptionEnums enums = e.getExceptionEnums();
        return Result.builder().flag(false).code(enums.getCode()).data(null).message(enums.getMsg()).build();
    }
}