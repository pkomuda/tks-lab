package pl.lodz.p.it.webapplication.controllers.mq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.RequestScoped;
import java.io.IOException;
import java.io.Serializable;
import java.util.concurrent.TimeoutException;

import static pl.lodz.p.it.webapplication.controllers.mq.SerializationUtils.serialize;

@Slf4j
@RequestScoped
public class RabbitTemplate {

    private final String EXCHANGE_NAME = "user_exchange";
    private Connection connection;
    private Channel channel;

    @PostConstruct
    public void init() {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try {
            connection = factory.newConnection();
            channel = connection.createChannel();
            channel.exchangeDeclare(EXCHANGE_NAME, "topic");
        } catch (TimeoutException | IOException e) {
            log.error(e.getMessage());
        }
    }

    public void send(Serializable source, String routingKey) {
        try {
            channel.basicPublish(EXCHANGE_NAME, routingKey ,null, serialize(source));
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    @PreDestroy
    public void dispose() {
        try {
            channel.close();
            connection.close();
        } catch (TimeoutException | IOException e) {
            log.error(e.getMessage());
        }
    }
}
