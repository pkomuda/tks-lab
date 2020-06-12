package pl.lodz.p.it.webapplication.controllers.mq;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.RequestScoped;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.UUID;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeoutException;

@Slf4j
@RequestScoped
public class RabbitRpcClient {

    private static final String QUEUE_NAME = "rpc_queue";
    private Connection connection;
    private Channel channel;

    @PostConstruct
    public void init() {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try {
            connection = factory.newConnection();
            channel = connection.createChannel();
        } catch (TimeoutException | IOException e) {
            log.error(e.getMessage());
        }
    }

    public Object call(String message) {
        final String corrId = UUID.randomUUID().toString();
        String replyQueueName = null;
        try {
            replyQueueName = channel.queueDeclare().getQueue();
        } catch (IOException e) {
            log.error(e.getMessage());
        }

        AMQP.BasicProperties props = new AMQP.BasicProperties
                .Builder()
                .correlationId(corrId)
                .replyTo(replyQueueName)
                .build();

        try {
            channel.basicPublish("", QUEUE_NAME, props, message.getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            log.error(e.getMessage());
        }

        final BlockingQueue<String> response = new ArrayBlockingQueue<>(1);
        Object result = null;
        try {
            String ctag = channel.basicConsume(replyQueueName, true, (consumerTag, delivery) -> {
                if (delivery.getProperties().getCorrelationId().equals(corrId)) {
                    response.offer(new String(delivery.getBody(), StandardCharsets.UTF_8));
                }
            }, consumerTag -> {
            });
            result = response.take();
            channel.basicCancel(ctag);
        } catch (InterruptedException | IOException e) {
            log.error(e.getMessage());
        }
        return result;
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
