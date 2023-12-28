package study.rabbitmq.consumer.listener;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import study.rabbitmq.constants.RabbitmqConstant;

/**
 * @author dongyh
 * @date 2024/1/4
 */
@Component
public class TopicExchangeListener {
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = RabbitmqConstant.TopicExchange.FIRST_QUEUE),
            exchange = @Exchange(value = RabbitmqConstant.TopicExchange.EXCHANGE_NAME, type = ExchangeTypes.TOPIC),
            key = {"first.#"}
    ))
    public void firstMessage(String message) {
        System.out.println("topic first queue received message: " + message);
    }
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = RabbitmqConstant.TopicExchange.SECOND_QUEUE),
            exchange = @Exchange(value = RabbitmqConstant.TopicExchange.EXCHANGE_NAME, type = ExchangeTypes.TOPIC),
            key = {"#.second"}
    ))
    public void secondMessage(String message) {
        System.out.println("topic second queue received message: " + message);
    }

}
