package cn.tang.demo.exception;

/**
 * @description 自定义基础接口类，enum实现此接口，用于统一异常处理
 * @author tangwenlong
 */
public interface IBussinessError {

    String getCode();

    String getMessage();

    String getMessage(String... details);

}
