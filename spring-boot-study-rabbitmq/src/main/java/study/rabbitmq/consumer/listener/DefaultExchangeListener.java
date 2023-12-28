package study.rabbitmq.consumer.listener;

import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import study.rabbitmq.constants.RabbitmqConstant;

/**
 * @author dongyh
 * @date 2024/1/3
 */
@Component
public class DefaultExchangeListener {
    @RabbitListener(queuesToDeclare = @Queue(value = RabbitmqConstant.DefaultExchange.DEFAULT_QUEUE))
    public void message(String message){
        System.out.println("default queue received message:"+message);
    }
}
