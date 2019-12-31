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
    LABEL_NOT_FOUND(StatusCode.NOT_FOUND,"没有找到相应标签"),
    ARTICLE_NOT_FOUND(StatusCode.NOT_FOUND, "没有找到相应文章"),
    INSUFFICIENT_PERMISSION(StatusCode.ACCESSERROR, "权限不够"),
    GATHERING_NOT_FOUND(StatusCode.NOT_FOUND, "没有找到相应活动");
    private int code;
    private String msg;
}
