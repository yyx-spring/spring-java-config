package com.main.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * <p>Title: WebConfig.java</p>
 * <p>Description: 配置类，用于定义DispatcherServlet上下文的bean</p>
 * <p>CreateDate: 2017年6月12日</p>
 *
 * @author shen
 * @version v1.0
 */

@Configuration
@EnableWebMvc
@ComponentScan("com.main.*")
public class WebConfig extends WebMvcConfigurerAdapter {

    //视图解析配置
    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/");
        resolver.setSuffix(".html");
        return resolver;
    }

    //文件上传，bean必须写name属性且必须为multipartResolver
    @Bean(name = "multipartResolver")
    protected CommonsMultipartResolver MultipartResolver() {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        //上传中临时文件存放目录
        //multipartResolver.setUploadTempDir(new FileSystemResource("/tmp"));
        multipartResolver.setMaxUploadSize(2097152);//2M
        multipartResolver.setMaxInMemorySize(0);
        multipartResolver.setDefaultEncoding("UTF-8");
        return multipartResolver;
    }

    //静态资源的处理：默认匹配/**请求，在找不到资源时默认处理
    /*@Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }*/

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(licenseCheckerInterceptor());
    }

    //静态资源处理，匹配如下规则按照资源处理，不符合交个下一个handler解析
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/skin/**").addResourceLocations("/skin/");
        registry.addResourceHandler("/images/**").addResourceLocations("/images/");
        registry.addResourceHandler("/js/**").addResourceLocations("/js/");
        registry.addResourceHandler("/download/**").addResourceLocations("/download/");
        registry.addResourceHandler("/*.html", "/*.js", "/*.css").addResourceLocations("/");
    }

}