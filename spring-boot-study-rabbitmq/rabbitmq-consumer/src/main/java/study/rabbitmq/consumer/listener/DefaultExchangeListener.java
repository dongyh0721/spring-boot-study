package study.rabbitmq.consumer.listener;

import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author dongyh
 * @date 2024/1/3
 */
@Component
public class DefaultExchangeListener {
    private static final String DEFAULT_QUEUE="default.queue";

    /**
     * 创建一个默认队列 不指定交换机
     * 所有队列都会绑定在rabbitmq的默认交换机上
     * 默认交换机实际是一个没有名字的交换机
     * 它有一个特殊的属性是每个新建的队列都会自动绑定到默认的交换机上
     * 绑定的路由键(routing-key)名称与队列(queue)名称相同
     * 发送消息时不指定exchange 则使用默认交换机
     */
    @RabbitListener(queuesToDeclare = @Queue(value = DEFAULT_QUEUE))
    public void message(String message){
        System.out.println("default queue received message:"+message);
    }
}
