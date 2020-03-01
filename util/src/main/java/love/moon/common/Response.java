package love.moon.common;

import java.io.Serializable;

/**
 * Author: lovemooner
 * Date: 2017/10/13 17:32
 */
public class Response implements Serializable {
    private String status;
    private long code;
    private String message;

    public Response() {
    }

    public Response(long code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
