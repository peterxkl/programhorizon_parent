package entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author DillonXie
 * @version 1.0
 * @date 12/5/2019 9:55 PM
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Result {
    private boolean flag;//是否成功     
    private Integer code;// 返回码     
    private String message;//返回信息     
    private Object data;// 返回数据
}
