package love.moon.common.exception;

import love.moon.common.Response;

/**
 * Created by lovemooner on 2017/4/25.
 */
public enum MessageCode {

    INTERNAL_SERVER_ERROR(10002L, "Internal Server Error."),
    UserNotFound(10003L, "User not found."),
    REQUEST_NOT_FOUND(10004L, "REQUEST Not FOUND.");

    private long code;
    private String message;

    MessageCode(long code, String message) {
        this.code = code;
        this.message = message;
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

    public static Response toResponse(MessageCode code) {
        Response response = new Response();
        response.setCode(code.getCode());
        response.setMessage(code.getMessage());
        return response;
    }
}
