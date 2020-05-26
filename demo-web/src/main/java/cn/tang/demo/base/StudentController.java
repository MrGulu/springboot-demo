package cn.tang.demo.base;

import cn.tang.demo.base.entity.Student;
import cn.tang.demo.base.service.StudentService;
import com.alibaba.dubbo.config.annotation.Reference;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description 测试demo controller
 * @author tangwenlong
 */
@Slf4j
@RestController
public class StudentController {

    /**service使用dubbo的service注解，web引用service时使用dubbo的@Reference注解才行！！！*/
    @Reference
    private StudentService studentService;

    @RequestMapping("/out")
    public String out(String name) {
        log.info("接收到的消息为==[{}]", name);
        return name;
    }

    @RequestMapping("/sayHello")
    public String sayHello(String name) {
        log.info("服务器接收到的参数web receive msg:" + name);
        return studentService.sayHello(name);
    }

    @RequestMapping("/queryDb")
    public Student queryDb(Integer id) {
        log.info("服务器接收到的参数web receive msg:" + id);
        return studentService.getStudentById(id);
    }

}
