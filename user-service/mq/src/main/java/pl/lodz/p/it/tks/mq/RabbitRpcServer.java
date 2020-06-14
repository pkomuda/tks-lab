package pl.lodz.p.it.tks.mq;

import com.rabbitmq.client.*;
import lombok.extern.slf4j.Slf4j;
import pl.lodz.p.it.tks.userservice.services.UserFilterService;
import pl.lodz.p.it.tks.userservice.services.UserGetService;

import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Initialized;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

import static pl.lodz.p.it.tks.mq.SerializationUtils.serialize;
import static pl.lodz.p.it.tks.mq.SerializationUtils.serializeList;

@Slf4j
@ApplicationScoped
public class RabbitRpcServer {

    private static final String QUEUE_NAME = "rpc_queue";
    private Connection connection;
    private Channel channel;

    @Inject
    private UserGetService userGetService;
    @Inject
    private UserFilterService userFilterService;

    public void init(@Observes @Initialized(ApplicationScoped.class) Object init) {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try {
            connection = factory.newConnection();
            channel = connection.createChannel();
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            channel.queuePurge(QUEUE_NAME);
            channel.basicQos(1);
        } catch (TimeoutException | IOException e) {
            log.error(e.getMessage());
        }

        log.info("[x] Awaiting RPC requests");
        Object monitor = new Object();
        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            AMQP.BasicProperties replyProps = new AMQP.BasicProperties
                    .Builder()
                    .correlationId(delivery.getProperties().getCorrelationId())
                    .build();

            String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
            log.info("[.] Received RPC: " + message);
            switch (message.substring(0, message.indexOf(' '))) {
                case "GET":
                    channel.basicPublish("", delivery.getProperties().getReplyTo(), replyProps, serialize(userGetService.getUser(message.substring(message.indexOf(' ') + 1))));
                    break;
                case "GET_ALL":
                    channel.basicPublish("", delivery.getProperties().getReplyTo(), replyProps, serializeList(userGetService.getUsers()));
                    break;
                case "FILTER":
                    channel.basicPublish("", delivery.getProperties().getReplyTo(), replyProps, serializeList(userFilterService.filterUsers(message.substring(message.indexOf(' ') + 1))));
                    break;
            }
            channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
            synchronized (monitor) {
                monitor.notify();
            }
        };

        try {
            channel.basicConsume(QUEUE_NAME, false, deliverCallback, (consumerTag -> {}));
        } catch (IOException e) {
            log.error(e.getMessage());
        }

        synchronized (monitor) {
            try {
                monitor.wait();
            } catch (InterruptedException e) {
                log.error(e.getMessage());
            }
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
