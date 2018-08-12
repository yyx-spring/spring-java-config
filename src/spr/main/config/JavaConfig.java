package spr.main.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JavaConfig {

    @Bean(name="bean1")
    public Bean1 createBean1() {
        return new Bean1();
    }

    @Bean(name="bean2")
    public Bean2 createBean2() {
        return new Bean2();
    }


}
