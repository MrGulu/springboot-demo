package cn.tang.demo.base;

import cn.tang.demo.base.service.StudentService;
import com.alibaba.dubbo.config.annotation.Reference;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class StudentController {

    private static final Logger logger = LoggerFactory.getLogger(StudentController.class);

    @Reference(version = "1.0")
    private StudentService studentService;

    @RequestMapping("/out")
    public String out(String name) {
        logger.info("接收到的消息为==[{}]", name);
        return name;
    }

    @RequestMapping("/sayHello")
    public String sayHello(String name) {
        logger.info("服務器接收到的數據web receive msg:" + name);
        return studentService.sayHello(name);
    }

}
