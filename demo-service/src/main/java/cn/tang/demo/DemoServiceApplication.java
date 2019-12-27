package cn.tang.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.CountDownLatch;

/**
 * 不要忘記@MapperScan("cn.tang.demo")註解！！！否则service中引用mapper接口启动报错！
 */
//如果启动时不连接数据库需要添加exclude= {DataSourceAutoConfiguration.class}
//@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
@SpringBootApplication
@MapperScan("cn.tang.demo")
public class DemoServiceApplication {

	public static void main(String[] args) throws InterruptedException {
		SpringApplication.run(DemoServiceApplication.class, args);
		//prevent main thread from exit
		CountDownLatch countDownLatch = new CountDownLatch(1);
		countDownLatch.await();
	}

}
