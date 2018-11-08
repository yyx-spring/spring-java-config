package com.main.config;

import com.main.domain.Bean1;
import com.main.domain.Bean2;
import com.main.listener.JmsHandler;
import com.main.listener.QueueMessageListener;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.apache.activemq.spring.ActiveMQConnectionFactory;
import org.springframework.aop.framework.autoproxy.BeanNameAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.listener.DefaultMessageListenerContainer;
import org.springframework.jms.listener.adapter.MessageListenerAdapter;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageType;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>Title: RootConfig.java</p>
 * <p>Description: 配置类，用于管理ContextLoadListener创建的上下文的bean</p>
 * <p>CreateDate: 2017年6月12日</p>
 *
 * @author shen
 * @version v1.0
 */

@Configuration
//@Import({DataSourceConfig.class})
@EnableAspectJAutoProxy
@EnableWebSecurity
@ComponentScan("com.main.*")
public class RootConfig {

    @Bean
    public BeanNameAutoProxyCreator proxycreate() {
        BeanNameAutoProxyCreator proxycreate = new BeanNameAutoProxyCreator();
        proxycreate.setProxyTargetClass(true);
        proxycreate.setBeanNames("*Service");
        proxycreate.setInterceptorNames("transactionInterceptor");
        return proxycreate;
    }

    /***
     * With Spring Data Redis 2.0, those methods have been deprecated. You now need to configure using RedisStandaloneConfiguration
     */
    @Bean
    public JedisConnectionFactory jedisConnectionFactory() {
        JedisConnectionFactory cf = new JedisConnectionFactory();
        cf.setHostName("127.0.0.1");
        cf.setPort(7379);
        cf.setPassword("password");
        return cf;
    }

    @Bean
    public ActiveMQConnectionFactory activeMQConnectionFactory() {
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory();
        activeMQConnectionFactory.setBrokerURL("tcp://localhost:61616");
//        activeMQConnectionFactory.setUserName("admin");
//        activeMQConnectionFactory.setPassword("admin");
        return activeMQConnectionFactory;
    }

    @Bean
    public ActiveMQQueue activeMQQueue() {
        ActiveMQQueue activeMQQueue = new ActiveMQQueue("xiangzi.queue");
        return activeMQQueue;
    }

    @Bean
    public ActiveMQTopic activeMQTopic() {
        ActiveMQTopic activeMQTopic = new ActiveMQTopic("xiangzi.topic");
        return activeMQTopic;
    }

    @Bean
    public JmsTemplate jmsTemplate(ActiveMQConnectionFactory activeMQConnectionFactory, ActiveMQQueue activeMQQueue, MappingJackson2MessageConverter mappingJackson2MessageConverter) {
        JmsTemplate jmsTemplate = new JmsTemplate(activeMQConnectionFactory);
        //通过队列、主题bean设置默认目的地
        jmsTemplate.setDefaultDestination(activeMQQueue);
        //通过名称设置目的地
//        jmsTemplate.setDefaultDestinationName("xiangzi.queue");
        jmsTemplate.setMessageConverter(mappingJackson2MessageConverter);
        return jmsTemplate;
    }

    @Bean
    public MappingJackson2MessageConverter mappingJackson2MessageConverter() {
        //org.springframework.jms.support.converter.MappingJackson2MessageConverter
        MappingJackson2MessageConverter mappingJackson2MessageConverter = new MappingJackson2MessageConverter();
        //mappingJackson2MessageConverter.setTargetType(MessageType.TEXT);
        // 定义了typeId到Class的Map
//        Map<String, Class<?>> typeIdMap = new HashMap<String, Class<?>>();
//        typeIdMap.put("Bean1", Bean1.class);
//        typeIdMap.put("Bean2", Bean2.class);
//        mappingJackson2MessageConverter.setTypeIdMappings(typeIdMap);
        // 设置发送到队列中的typeId的名称
        mappingJackson2MessageConverter.setTypeIdPropertyName("Bean2");
        mappingJackson2MessageConverter.setEncoding("UTF-8");
        return mappingJackson2MessageConverter;
    }

    @Bean
    public JmsHandler jmsHandler(){
        return new JmsHandler();
    }

    @Bean
    public MessageListenerAdapter queueMessageListenerAdapter(JmsHandler jmsHandler) {
        MessageListenerAdapter messageListenerAdapter = new MessageListenerAdapter(jmsHandler);
//        messageListenerAdapter.setDelegate(jmsHandler);
        messageListenerAdapter.setDefaultListenerMethod("handleSpittleAlert");
        return messageListenerAdapter;
    }

    @Bean
    public DefaultMessageListenerContainer defaultMessageListenerContainer(ActiveMQConnectionFactory activeMQConnectionFactory, MessageListenerAdapter messageListenerAdapter, QueueMessageListener queueMessageListener, ActiveMQQueue activeMQQueue) {
        DefaultMessageListenerContainer defaultMessageListenerContainer = new DefaultMessageListenerContainer();
        defaultMessageListenerContainer.setConnectionFactory(activeMQConnectionFactory);
        defaultMessageListenerContainer.setMessageListener(queueMessageListener);
        defaultMessageListenerContainer.setDestination(activeMQQueue);
        return defaultMessageListenerContainer;
    }
}