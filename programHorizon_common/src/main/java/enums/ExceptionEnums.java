package enums;

import entity.StatusCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author DillonXie
 * @version 1.0
 * @date 12/8/2019 9:16 AM
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum ExceptionEnums {
    LABEL_NOT_FOUND(StatusCode.NOT_FOUND,"没有找到相应标签");
    private int code;
    private String msg;
}
