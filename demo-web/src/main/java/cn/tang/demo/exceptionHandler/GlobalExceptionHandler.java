package cn.tang.demo.exceptionHandler;

import cn.tang.demo.bean.JsonResponse;
import cn.tang.demo.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @description 全局异常处理类
 * @author tangwenlong
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 处理自定义的业务异常
     */
    @ExceptionHandler(value = BusinessException.class)
    @ResponseBody
    public JsonResponse bizExceptionHandler(HttpServletRequest request, BusinessException e){
        log.error("全局异常捕获自定义异常！", e);
        return JsonResponse.fail(e.getErrorCode(), e.getMessage());
    }

    /**
     * 处理其他异常
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public JsonResponse exceptionHandler(HttpServletRequest request, Exception e){
        log.error("全局异常捕获非自定义异常！", e);
        return JsonResponse.fail(e.getMessage());
    }
}
