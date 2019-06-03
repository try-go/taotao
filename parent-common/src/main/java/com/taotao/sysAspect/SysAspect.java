package com.taotao.sysAspect;

import org.apache.activemq.ActiveMQConnectionFactory;


import javax.jms.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SysAspect {
    public void afterItemChange() {
        try {
            ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://39.107.47.227:61616");
            Connection connection = connectionFactory.createConnection();
            connection.start();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Queue queue = session.createQueue("queue-item");
            MessageProducer producer = session.createProducer(queue);
            Date date = new Date();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String string = simpleDateFormat.format(date);
            TextMessage textMessage = session.createTextMessage(string);
            producer.send(textMessage);
            producer.close();
            session.close();
            connection.close();
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

}
