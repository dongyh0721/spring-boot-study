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
public class FanoutExchangeListener {
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(RabbitmqConstant.FanoutExchange.FIRST_QUEUE),
            exchange = @Exchange(value = RabbitmqConstant.FanoutExchange.EXCHANGE_NAME,type = ExchangeTypes.FANOUT)
    ))
    public void firstMessage(String message) {
        System.out.println("fanout first queue received message: " + message);
    }
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(RabbitmqConstant.FanoutExchange.SECOND_QUEUE),
            exchange = @Exchange(value = RabbitmqConstant.FanoutExchange.EXCHANGE_NAME,type = ExchangeTypes.FANOUT)
    ))
    public void secondMessage(String message) {
        System.out.println("fanout second queue received message: " + message);
    }
}
