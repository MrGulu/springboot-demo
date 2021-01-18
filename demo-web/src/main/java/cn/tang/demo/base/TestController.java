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
import javax.servlet.http.HttpSession;

/**
 * @description 统一测试controller
 * @author tangwenlong
 */
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

    /**
     * @description
     * 关于session的一点理解:
     * 前端跨域请求时，每一次请求都生成一个新的sessionId
     * 同一个浏览器，在不关闭的情况下无论请求多少次或者打开新的tab页请求，sessionId都是同一个
     * 同一个浏览器，比如说这次访问sessionId是A，如果把浏览器关闭重新打开然后访问时，sessionId是不同的
     * 不同的浏览器相当于不同的客户端，访问时产生的sessionId是不同的
     */
    @RequestMapping("/testSessionCreateCode")
    @ResponseBody
    public JsonResponse testSessionCreateCode(HttpServletRequest request) {
        HttpSession session = request.getSession();
        log.info("session id:[{}],origin:[{}]", session.getId(), request.getHeader("Origin"));
        session.setAttribute("randCode", "123");
        return JsonResponse.success("ok");
    }

    @RequestMapping("/testSessionCreateCode2")
    @ResponseBody
    public JsonResponse testSessionCreateCode2(HttpServletRequest request) {
        HttpSession session = request.getSession();
        log.info("session id:[{}],origin:[{}]", session.getId(), request.getHeader("Origin"));
        session.setAttribute("randCode", "456");
        return JsonResponse.success("ok");
    }

    @RequestMapping("/testSessionValidCode")
    @ResponseBody
    public JsonResponse testSessionValidCode(HttpServletRequest request) {
        HttpSession session = request.getSession();
        log.info("session id:[{}],origin:[{}]", session.getId(), request.getHeader("Origin"));
        String randCodeSession = (String) session.getAttribute("randCode");
        System.out.println(randCodeSession);
        return JsonResponse.success("ok");
    }
}
