package xiangzi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 *<p>Title: WebConfig.java</p>
 *<p>Description: 配置类，用于定义DispatcherServlet上下文的bean</p>
 *<p>CreateDate: 2017年6月12日</p>
 *@author shen
 *@version v1.0
 */

/***
 * 当DispatcherServlet启动的时候，会创建Spring应用上下文并加载配置文件或配置文件中声明的bean。
 *
 * 当它加载上下文时，使用定义在WebConfig中的bean(基于java配置)
 *
 * DispatcherServlet 加载包含web组件的bean，如控制器，视图解析器等。还有一个ContextLoaderListener加载其他bean，如中间层及数据层组件等
 */
@Configuration
@EnableWebMvc   //<mvc:annotation-driven>启用注解驱动
@ComponentScan( "xiangzi.controller" )
public class WebConfig extends WebMvcConfigurerAdapter {

    //视图解析配置
    @Bean
    public ViewResolver viewResolver(){
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/");
        resolver.setSuffix(".jsp");
        return resolver;
    }

    //文件上传，bean必须写name属性且必须为multipartResolver
    @Bean(name="multipartResolver")
    protected CommonsMultipartResolver MultipartResolver() {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        //上传中临时文件存放目录
        //multipartResolver.setUploadTempDir(new FileSystemResource("/tmp"));
        multipartResolver.setMaxUploadSize(2097152);//2M
        multipartResolver.setMaxInMemorySize(0);
        multipartResolver.setDefaultEncoding("UTF-8");
        return multipartResolver;
    }

    //静态资源的处理
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }
}