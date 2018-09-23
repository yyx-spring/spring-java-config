package com.main.config;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.ServletRegistration;

/**
 * <p>Title: SpittrWebAppInitializer.java</p>
 * <p>Description: 前端控制器配置</p>
 * <p>CreateDate: 2017年6月12日</p>
 *
 * @author shen
 * @version v1.0
 */

public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    private final static Logger LOG = Logger.getLogger(WebAppInitializer.class);

    @Override
    protected Class<?>[] getRootConfigClasses() {
        LOG.info("------root配置类初始化------");
        return new Class<?>[]{RootConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        LOG.info("------web配置类初始化------");
        return new Class<?>[]{WebConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        LOG.info("------映射根路径初始化------");
        return new String[]{"/"};//请求路径映射，根路径
    }

    @Override
    public void customizeRegistration(ServletRegistration.Dynamic registration) {
        registration.setInitParameter("throwExceptionIfNoHandlerFound", "true");
    }

    /**
     * 重载AbstractAnnotationConfigDispatcherServletInitializer方法，设置filter
     * @return
     */
    /*@Override
    protected Filter[] getServletFilters(){
        return new Filter[] { null };
    }*/
}