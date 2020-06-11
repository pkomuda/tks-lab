//package pl.lodz.p.it.tks.mq;
//
//import com.rabbitmq.client.Channel;
//import com.rabbitmq.client.Connection;
//import com.rabbitmq.client.ConnectionFactory;
//import com.rabbitmq.client.DeliverCallback;
//import lombok.extern.slf4j.Slf4j;
//
//import javax.annotation.PostConstruct;
//import javax.ejb.Singleton;
//import javax.ejb.Startup;
//import java.io.IOException;
//import java.nio.charset.StandardCharsets;
//import java.util.Objects;
//import java.util.concurrent.TimeoutException;
//
//@Slf4j
//@Startup
//@Singleton
//public class Old {
//
//    private static String EXCHANGE_NAME = "user_exchange";
//    private static String TOPIC_NAME = "user";
//
//    @PostConstruct
//    public void init() {
//        subscribe("create");
//    }
//
//    public void subscribe(String bindingKey) {
//        ConnectionFactory factory = new ConnectionFactory();
//        factory.setHost("localhost");
//        Connection connection;
//        Channel channel = null;
//        String queueName = null;
//        try {
//            connection = factory.newConnection();
//            channel = connection.createChannel();
//            channel.exchangeDeclare(EXCHANGE_NAME, TOPIC_NAME);
//            queueName = channel.queueDeclare().getQueue();
//            channel.queueBind(queueName, EXCHANGE_NAME, bindingKey);
//        } catch (TimeoutException | IOException e) {
//            log.error(e.getMessage());
//        }
//        log.info(" [*] Waiting for messages. To exit press CTRL+C");
//        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
//            String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
//            log.info(" [x] Received '" + delivery.getEnvelope().getRoutingKey() + "':'" + message + "'");
//        };
//        try {
//            Objects.requireNonNull(channel).basicConsume(queueName, true, deliverCallback, consumerTag -> { });
//        } catch (IOException e) {
//            log.error(e.getMessage());
//        }
//    }
//}