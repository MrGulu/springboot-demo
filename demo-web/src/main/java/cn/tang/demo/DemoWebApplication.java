package cn.tang.demo;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * @description web启动类，使用外置容器tomcat，需继承SpringBootServletInitializer
 * 				重写configure方法。
 * 				不要忘記	@EnableDubboConfiguration 註解！！！
 * @author tangwenlong
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
@EnableDubboConfiguration
public class DemoWebApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(DemoWebApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		// 注意这里要指向原先用main方法执行的Application启动类
		return builder.sources(DemoWebApplication.class);
	}

//	//web端口设置
//	@Bean
//	public WebServerFactoryCustomizer<ConfigurableWebServerFactory> webServerFactoryCustomizer() {
//		return (ConfigurableWebServerFactory factory) -> {
//			factory.setPort(Integer.parseInt(ApolloUtil.getConfig("web.server")));
//		};
//	}
}
