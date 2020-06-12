package pl.lodz.p.it.tks.mq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import lombok.extern.slf4j.Slf4j;
import pl.lodz.p.it.tks.userservice.domainmodel.User;
import pl.lodz.p.it.tks.userservice.services.UserCrudService;

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
public class RabbitTemplate {

    private final String EXCHANGE_NAME = "user_exchange";
    private final String CREATE_KEY = "user.create";
    private final String EDIT_KEY = "user.edit";

    @Inject
    private UserCrudService userCrudService;

    public void init(@Observes @Initialized(ApplicationScoped.class) Object init) {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");

        Connection connection;
        Channel channel = null;
        String queueName = null;

        try {
            connection = factory.newConnection();
            channel = connection.createChannel();
            channel.exchangeDeclare(EXCHANGE_NAME, "topic");
            queueName = channel.queueDeclare().getQueue();
            channel.queueBind(queueName, EXCHANGE_NAME, CREATE_KEY);
            channel.queueBind(queueName, EXCHANGE_NAME, EDIT_KEY);
        } catch (TimeoutException | IOException e) {
            log.error(e.getMessage());
        }

        log.info(" [*] Waiting for messages. To exit press CTRL+C");

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            User user = deserialize(delivery.getBody());
            String routingKey = delivery.getEnvelope().getRoutingKey();
            log.info(" [x] Received '" + routingKey + "':'" + user + "'");
            performAction(user, routingKey);
        };
        try {
            Objects.requireNonNull(channel).basicConsume(queueName, true, deliverCallback, consumerTag -> { });
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    private void performAction(User user, String routingKey) {
        switch (routingKey) {
            case "user.create":
                userCrudService.addUser(user);
                break;
            case "user.edit":
                userCrudService.updateUser(user);
                break;
            default:
                break;
        }
    }
}
