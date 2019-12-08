package exception;

import enums.ExceptionEnums;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author DillonXie
 * @version 1.0
 * @date 12/8/2019 9:20 AM
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PhException extends RuntimeException{
    private ExceptionEnums exceptionEnums;
}
