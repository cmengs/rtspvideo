package cn.com.zj;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * @SpringBootApplication 开启扫描，固定写法
 * @MapperScan("cn.com.zj.springboot.springboot_mybatis.mapper") 扫描数据库操作mapper所在的包
 * @EnableTransactionManagement 开启事务支持
 * @author Administrator
 *
 */
@SpringBootApplication
//正常配置，jar启动
/*public class App {
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}
}*/
//war包启动，tomcat启动
public class App extends SpringBootServletInitializer{
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(App.class);
	}
}
