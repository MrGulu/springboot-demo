package cn.tang.demo.bean;

import cn.tang.demo.enumbean.RspCodeEnum;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author tangwenlong
 */
public class JsonResponse implements Serializable {

    private static final String SUCCESS_CODE = "0";

    private static final String ERROR_CODE = "9999";

    private static final Map<String,Object> DEFAULT_DATA = new HashMap<>(8);

    private JsonResponse() {
    }

    private String code = null;

    private String message = null;

    private Object data = null;

    public String getCode() {
        return code;
    }

    private void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    /**
     * @description 返回前端成功
     * @param message 成功消息
     */
    @SuppressWarnings("all")
    public static JsonResponse success(String message) {
        JsonResponse jsonResponse = new JsonResponse();
        jsonResponse.setCode(SUCCESS_CODE);
        jsonResponse.setMessage(message);
        jsonResponse.setData(DEFAULT_DATA);
        return jsonResponse;
    }

    /**
     * @description 返回前端成功，携带数据
     * @param message 成功消息
     * @param data 成功返回的数据
     */
    @SuppressWarnings("all")
    public static JsonResponse success(String message, Object data) {
        JsonResponse jsonResponse = new JsonResponse();
        jsonResponse.setCode(SUCCESS_CODE);
        jsonResponse.setMessage(message);
        jsonResponse.setData(data);
        return jsonResponse;
    }

    /**
     * @description 返回前端失败
     * @param errcode 错误码
     * @param message 错误信息
     */
    @SuppressWarnings("all")
    public static JsonResponse fail(String message) {
        JsonResponse jsonResponse = new JsonResponse();
        jsonResponse.setCode(ERROR_CODE);
        jsonResponse.setMessage(message);
        jsonResponse.setData(DEFAULT_DATA);
        return jsonResponse;
    }

    /**
     * @description 返回前端失败
     * @param errcode 错误码
     * @param message 错误信息
     */
    @SuppressWarnings("all")
    public static JsonResponse fail(String errcode, String message) {
        JsonResponse jsonResponse = new JsonResponse();
        jsonResponse.setCode(errcode);
        jsonResponse.setMessage(message);
        jsonResponse.setData(DEFAULT_DATA);
        return jsonResponse;
    }

    /**
     * @description 返回前端失败-通过错误枚举类
     * @param rspCodeEnum 错误枚举类
     */
    @SuppressWarnings("all")
    public static JsonResponse fail(RspCodeEnum rspCodeEnum) {
        JsonResponse jsonResponse = new JsonResponse();
        jsonResponse.setCode(rspCodeEnum.getCode());
        jsonResponse.setMessage(rspCodeEnum.getMessage());
        jsonResponse.setData(DEFAULT_DATA);
        return jsonResponse;
    }

    /**
     * @description 返回前端失败-通过错误枚举类的可变长度参数列表方式
     * @param rspCodeEnum 错误枚举类
     * @param args 错误枚举类
     */
    @SuppressWarnings("all")
    public static JsonResponse fail(RspCodeEnum rspCodeEnum, String... args) {
        JsonResponse jsonResponse = new JsonResponse();
        jsonResponse.setCode(rspCodeEnum.getCode());
        jsonResponse.setMessage(String.format(rspCodeEnum.getMessage(), (Object[]) args));
        jsonResponse.setData(DEFAULT_DATA);
        return jsonResponse;
    }
}


