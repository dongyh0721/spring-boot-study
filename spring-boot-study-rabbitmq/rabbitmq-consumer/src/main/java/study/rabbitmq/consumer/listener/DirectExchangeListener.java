package study.rabbitmq.consumer.listener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import study.rabbitmq.consumer.config.DirectExchangeConfig;

/**
 * @author dongyh
 * @date 2024/1/3
 */
@Component
public class DirectExchangeListener {
    /**
     * 直连交换机与queue通过routingKey进行绑定
     * 消息生产者通过指定routingKey发送到对应的交换机
     * 交换机通过routingKey路由到指定队列
     */
    @RabbitListener(queues = DirectExchangeConfig.DIRECT_QUEUE)
    public void message(String message) {
        System.out.println("direct exchange received message: " + message);
    }
}
