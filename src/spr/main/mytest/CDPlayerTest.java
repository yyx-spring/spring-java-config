package spr.main.mytest;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import spr.main.config.Bean1;
import spr.main.config.JavaConfig;

@ContextConfiguration(classes = JavaConfig.class)
public class CDPlayerTest {


    @Autowired
    Bean1 bean1;

    @Test
    public void main(){
        bean1.showName();
        System.out.println("Success");
    }
}
