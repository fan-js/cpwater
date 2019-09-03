package cn.ritac.cpwater.comm;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringContextHelper implements ApplicationContextAware {

	
	private static ApplicationContext context = null;
	 
	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		context = applicationContext;
	}
	
	public static Object getBean(String name){
		return context.getBean(name);
	}


}
