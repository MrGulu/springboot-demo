package cn.tang.demo.exception;

import java.util.HashMap;
import java.util.Map;

/**
 * @description 自定义异常
 * @author tangwenlong
 */
public class BusinessException extends RuntimeException {

  private static final long serialVersionUID = -5433056591268198786L;

  private static final String DEFAULT_ERROR_CODE = "99999";

  private static final Map<String, Object> DEFAULT_ERROR_MESSAGES = new HashMap<>(8);

  private String errorCode;

  private Map<String, Object> errorMessages;

  public String getErrorCode() {
    return errorCode;
  }

  public Map<String, Object> getErrorMessages() {
    return this.errorMessages;
  }

  public void setErrorCode(String errorCode) {
    this.errorCode = errorCode;
  }

  public void setErrorMessages(Map<String, Object> errorMessages) {
    this.errorMessages = errorMessages;
  }

  public BusinessException(String message) {
    super(message);
    this.errorCode = DEFAULT_ERROR_CODE;
    this.errorMessages = DEFAULT_ERROR_MESSAGES;
  }

  public BusinessException(String errorCode, String message) {
    super(message);
    this.errorCode = errorCode;
    this.errorMessages = DEFAULT_ERROR_MESSAGES;
  }

  public BusinessException(IBussinessError bussinessError) {
    super(bussinessError.getMessage());
    this.errorCode = bussinessError.getCode();
    this.errorMessages = DEFAULT_ERROR_MESSAGES;
  }

  public BusinessException(IBussinessError bussinessError, String... details) {
    super(bussinessError.getMessage(details));
    this.errorCode = bussinessError.getCode();
    this.errorMessages = DEFAULT_ERROR_MESSAGES;
  }

  public BusinessException(Throwable cause) {
    super(cause);
    this.errorCode = DEFAULT_ERROR_CODE;
    this.errorMessages = DEFAULT_ERROR_MESSAGES;
  }

  public BusinessException(String message, Throwable cause) {
    super(message, cause);
    this.errorCode = DEFAULT_ERROR_CODE;
    this.errorMessages = DEFAULT_ERROR_MESSAGES;
  }
}
