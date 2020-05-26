package cn.tang.demo.enumbean;

import cn.tang.demo.exception.IBussinessError;
import cn.tang.demo.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;

/**
 * @author tangwenlong
 */
@Slf4j
public enum RspCodeEnum implements IBussinessError {

    //系统公共异常
    ERR99999("系统异常", "99999"),
    ERR99998("必传参数为空,或参数不符合规范！", "99998"),
    ERR99997("必传参数%s为空,或参数不符合规范！", "99998"),
    ERR99996("操作数据库异常！", "99997"),
    ERR99995("接口发送失败！", "99996"),
    ERR99994("未查到数据！", "99995"),
    ERR99993("更新数据库失败,请重试！", "99994"),
    ERR99992("发送HTTP请求失败！", "99993"),
    ERR99991("发送HTTP [%s]请求异常！url [%s]", "99991");

    private String message;
    private String code;

    RspCodeEnum(String message, String code) {
        this.message = message;
        this.code = code;
    }

    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public String getMessage() {
        return this.message;
    }

    @Override
    public String getMessage(String... details) {
        return String.format(this.message, (Object[]) details);
    }

    /**
     * @description 根据code码匹配enum的value值并返回
     * @param code 需要匹配的code码
     * @return value 匹配到的enum的value
     */
    public static String returnMatchMsg(String code) {
        if (StringUtils.isEmpty(code)) {
            throw new IllegalArgumentException("param should not be empty!");
        }
        for (RspCodeEnum ce : RspCodeEnum.values()) {
            if (code.equals(ce.getCode())) {
                return ce.getMessage();
            }
        }
        return null;
    }
}
