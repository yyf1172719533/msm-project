package com.timain.result;

import lombok.Data;

/**
 * @author yyf
 * @version 1.0
 * @date 2022/7/11 19:22
 */
@Data
public class AjaxResult {

    private Integer code;

    private String msg;

    private Object data;

    public AjaxResult(String msg) {
        this.code = 500;
        this.msg = msg;
    }

    public AjaxResult(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public AjaxResult(Integer code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static AjaxResult success(Integer code, String msg) {
        return new AjaxResult(code, msg);
    }

    public static AjaxResult fail(String msg) {
        return new AjaxResult(msg);
    }
}
