package top.mxzero.common.http;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseData<T> {
    public static ResponseData<?> SUCCESS = new ResponseData<>(200, true, "successful", null, new Date());
    public static ResponseData<?> FAIL = new ResponseData<>(500, false, "System Error", null, new Date());


    private Integer status;
    private boolean success;
    private String message;
    private Object data;
    private Date time;
}
