package xiangzi.config;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
/**
 *<p>Title: SpittrWebAppInitializer.java</p>
 *<p>Description: 前端控制器配置</p>
 *<p>CreateDate: 2017年6月12日</p>
 *@author shen
 *@version v1.0
 */

// Servlet3.0环境中，容器会在类路径去查找实现javax.servlet.ServletContainerInitializer接口的类，如果发现，就用它做servlet的容器
// Spring对这个接口进行了实现，为SpringServletContainerInitializer。
// SpringServletContainerInitializer会去查找实现了他的类的类并将配置任务交给他们来完成。
// Spring3.2引入了便利的实现，就是AbstractAnnotationConfigDispatcherServletInitializer。
// 部署到Servlet3.0容器中时，容器会自动发现它，并配置servlet上下文

public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    private final static Logger LOG = Logger.getLogger(WebAppInitializer.class);

    @Override
    protected Class<?>[] getRootConfigClasses() {
        LOG.info("------root配置类初始化------");
        return new Class<?>[] { RootConfig.class };
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        LOG.info("------web配置类初始化------");
        return new Class<?>[] { WebConfig.class };
    }

    @Override
    protected String[] getServletMappings() {
        LOG.info("------映射根路径初始化------");
        return new String[]{ "/" };//请求路径映射，根路径
    }
}