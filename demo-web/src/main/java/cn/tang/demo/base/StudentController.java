package cn.tang.demo.base;

import cn.tang.demo.base.service.StudentService;
import com.alibaba.dubbo.config.annotation.Reference;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class StudentController {

    @Reference(version = "1.0")
    private StudentService studentService;

    @RequestMapping("/sayHello")
    public String sayHello(String name) {
        return studentService.sayHello(name);
    }

}
