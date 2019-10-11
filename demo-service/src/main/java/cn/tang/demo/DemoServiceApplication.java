package cn.tang.demo;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 不要忘記@MapperScan("cn.tang.demo")註解！！！
 */
@SpringBootApplication
@EnableDubboConfiguration
@MapperScan("cn.tang.demo")
public class DemoServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoServiceApplication.class, args);
	}

}
