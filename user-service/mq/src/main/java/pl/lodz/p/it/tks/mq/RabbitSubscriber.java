package pl.lodz.p.it.tks.mq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import lombok.extern.slf4j.Slf4j;
import pl.lodz.p.it.tks.userservice.domainmodel.User;
import pl.lodz.p.it.tks.userservice.services.UserCrudService;

import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Initialized;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.TimeoutException;

import static pl.lodz.p.it.tks.mq.SerializationUtils.deserialize;

@Slf4j
@ApplicationScoped
public class RabbitSubscriber {

    private static final String EXCHANGE_NAME = "user_exchange";
    private static final String BASE_BINDING_KEY = "user.";
    private Connection connection;
    private Channel channel;
    private String queueName;

    @Inject
    private UserCrudService userCrudService;

    private void performAction(User user, String routingKey) {
        switch (routingKey) {
            case "create":
                userCrudService.addUser(user);
                break;
            case "edit":
                userCrudService.updateUser(user);
                break;
            default:
                break;
        }
    }

    private void init() {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try {
            connection = factory.newConnection();
            channel = connection.createChannel();
            channel.exchangeDeclare(EXCHANGE_NAME, "topic");
            queueName = channel.queueDeclare().getQueue();
            channel.queueBind(queueName, EXCHANGE_NAME, BASE_BINDING_KEY + "*");
        } catch (TimeoutException | IOException e) {
            log.error(e.getMessage());
        }
    }

    public void subscribe(@Observes @Initialized(ApplicationScoped.class) Object init) {
        init();
        log.info("[*] Waiting for messages");
        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            User user = deserialize(delivery.getBody());
            log.info("[x] Received " + delivery.getEnvelope().getRoutingKey() + ": " + user);
            String routingKey = delivery.getEnvelope().getRoutingKey().replace(BASE_BINDING_KEY, "");
            performAction(user, routingKey);
        };

        try {
            Objects.requireNonNull(channel).basicConsume(queueName, true, deliverCallback, consumerTag -> {});
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
