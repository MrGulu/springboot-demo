package cn.tang.demo.base;

import cn.tang.demo.bean.JsonResponse;
import cn.tang.demo.enumbean.RspCodeEnum;
import cn.tang.demo.exception.BusinessException;
import cn.tang.demo.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Controller
@RequestMapping("/test")
public class TestController {

    @RequestMapping("/testException")
    @ResponseBody
    public JsonResponse testException(HttpServletRequest request) {
        String flag = request.getParameter("flag");
        if (StringUtils.isEmpty(flag)) {
            throw new BusinessException(RspCodeEnum.ERR99993, "test Exception");
        } else {
            throw new NullPointerException("test NPE");
        }
    }
}
