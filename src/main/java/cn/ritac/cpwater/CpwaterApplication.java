package cn.ritac.cpwater;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import tk.mybatis.spring.annotation.MapperScan;

@MapperScan(basePackages = "cn.ritac.cpwater.mybatis.mapper*")
@SpringBootApplication
@ComponentScan
@EnableAsync(proxyTargetClass = true)
public class CpwaterApplication extends SpringBootServletInitializer {

	// extends SpringBootServletInitializer
	public static void main(String[] args) {
		SpringApplication.run(CpwaterApplication.class, args);

	}
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(CpwaterApplication.class);
	}

}
