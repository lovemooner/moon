package love.moon.j2se.exception.frame;

import java.io.Serializable;


public class ApiResponse implements Serializable {
  private String status;
  private int code;
  private String message;

  public ApiResponse() {
    super();
  }



  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public int getCode() {
    return code;
  }

  public void setCode(int code) {
    this.code = code;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public void setMessageCode(MessageCode messageCode) {
    this.code = messageCode.getCode();
    this.message = messageCode.getValue();
    this.status = messageCode.getStatus();
  }



}
