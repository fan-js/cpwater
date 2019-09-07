package cn.ritac.cpwater.web.dto;

import java.util.Date;

/**
 * @Author:FanJS
 * @Date:2019-9-6 8:21
 */
public class CheckDto {
    private Boolean result;

    private String msg;

    public Boolean getResult() {
        return result;
    }

    public void setResult(Boolean result) {
        this.result = result;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
