package study.rabbitmq.consumer.listener;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import study.rabbitmq.constants.RabbitmqConstant;

/**
 * @author dongyh
 * @date 2024/1/3
 */
@Component
public class DirectExchangeListener {
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = RabbitmqConstant.DirectExchange.DEFAULT_QUEUE),
            exchange = @Exchange(value = RabbitmqConstant.DirectExchange.EXCHANGE_NAME),
            key = RabbitmqConstant.DirectExchange.DEFAULT_QUEUE+RabbitmqConstant.DirectExchange.ROUTING_KEY
    ))
    public void message(String message) {
        System.out.println("direct queue received message: " + message);
    }
}
