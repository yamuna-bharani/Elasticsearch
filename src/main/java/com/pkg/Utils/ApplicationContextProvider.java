package com.pkg.Utils;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;


@Component
public class ApplicationContextProvider implements ApplicationContextAware {
    private static ApplicationContext context;

    public static ApplicationContext getApplicationContext() {
        return context;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }

    public static <T> T getBean(Class<T> klass, String beanName) {
        T bean = null;
        try {
            bean = context.getBean(beanName, klass);
        } catch (Exception e) {
            try {
                bean = klass.newInstance();
            } catch (InstantiationException e1) {
                e1.printStackTrace();
            } catch (IllegalAccessException e1) {
                e1.printStackTrace();
            }
            AutowireCapableBeanFactory factory = ApplicationContextProvider.getApplicationContext().getAutowireCapableBeanFactory();
            factory.autowireBean(bean);
            factory.initializeBean(bean, beanName);

        }
        return bean;
    }
}
