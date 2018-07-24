package love.moon.exception.frame;

import java.text.MessageFormat;

/**
 * Author: lovemooner
 * Date: 2018/7/19 16:43
 */
public enum MessageCode {

    FAILED(-3, "操作失败！", Status.FAIL),
    SUCCESS(0, "操作成功！", Status.SUCCESS),

    //login
    LOGIN_SUCCESS(200, "登录成功！", Status.SUCCESS),
    LOGIN_FAIL(-200, "{0}，登录失败！", Status.FAIL),
    LOGIN_EXCEPTION(-201, "登录异常！", Status.FAIL),
    USER_NAME_EMPITY(-501, "登录异常！", Status.FAIL),
    ;

    private enum Status {
        SUCCESS, FAIL
    }

    private int code;
    private String value;
    private Status status;

    private MessageCode(int code, String value, Status status) {
        this.code = code;
        this.value = value;
        this.status = status;
    }

    public static ApiResponse toApiResponse(MessageCode code) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setStatus(code.getStatus());
        apiResponse.setErrorCode(code.getCode());
        apiResponse.setMessage(code.getValue());
        return apiResponse;
    }

    public static ApiResponse toApiResponse(MessageCode code, String reason) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setStatus(code.getStatus());
        apiResponse.setErrorCode(code.getCode());
        apiResponse.setMessage(MessageFormat.format(code.getValue(), reason));
        return apiResponse;
    }


    public String getStatus() {
        return status.name();
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
