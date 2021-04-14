package cn.kunli.una.utils.service;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/** 
* @author 作者 : Ponzio
* @version 创建时间：2019年6月10日 上午9:53:24 
* 类说明 :一般普通类调用service
*/
public class SpringContextUtil implements ApplicationContextAware {
	
	private static ApplicationContext applicationContext; // Spring应用上下文环境

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		SpringContextUtil.applicationContext = applicationContext;
		
	}

	public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }
 
    public static Object getBean(String name) throws BeansException {
        return applicationContext.getBean(name);
    }
 
    public static Object getBean(String name, Class requiredType)
            throws BeansException {
        return applicationContext.getBean(name, requiredType);
    }
 
    public boolean containsBean(String name) {
        return applicationContext.containsBean(name);
    }
 
    public static boolean isSingleton(String name) throws NoSuchBeanDefinitionException {
        return applicationContext.isSingleton(name);
    }
 
    public static Class getType(String name)    throws NoSuchBeanDefinitionException {
        return applicationContext.getType(name);
    }
 
    public static String[] getAliases(String name) throws NoSuchBeanDefinitionException {
        return applicationContext.getAliases(name);
    }
}
