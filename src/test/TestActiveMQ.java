import org.apache.activemq.spring.ActiveMQConnectionFactory;
import org.junit.Test;

import javax.jms.*;

/**
 * @Author: yunxiang.yang
 * @Date: 2018/11/7 14:20
 */

public class TestActiveMQ {

    @Test
    public void testProduceMsg() throws Exception{
        //连接工厂
        //使用默认的用户名,密码,路径
        //路径为 tcp://host:61616
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
        //获取一个连接
        Connection connection = connectionFactory.createConnection();
        //创建一个会话
        Session session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
        //创建队列或者话题
        Queue queue = session.createQueue("HelloActiveMQ1");
        //创建生产者或者消费者
        MessageProducer producer = session.createProducer(queue);
        //发送消息
        for (int i = 0; i < 10; i++) {
            producer.send(session.createTextMessage("activeMQ,你好!"+i));
        }
        //提交操作
        session.commit();
    }
}
