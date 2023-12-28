package study.rabbitmq.consumer.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 延迟队列实现
 * @author dongyh
 * @date 2024/1/15
 */
@Configuration
public class DelayQueueConfig {
    /**
     * 声明一个延迟交换机
     */
    @Bean
    public DirectExchange delayExchange(){
        return ExchangeBuilder.directExchange("delay.direct.exchange").build();
    }
    /**
     * 声明一个死信队列交换机
     */
    @Bean
    public DirectExchange deadExchange(){
        return ExchangeBuilder.directExchange("dead.direct.exchange").build();
    }
    /**
     * 声明一个延迟队列
     */
    @Bean
    public Queue delayQueue(){
        return QueueBuilder
                // 队列名称
                .durable("delay.queue")
                // 死信交换机
                .deadLetterExchange("dead.direct.exchange")
                // 死信队列routing key
                .deadLetterRoutingKey("dead.routing.key").build();
    }

    /**
     * 将延迟队列绑定至交换机
     */
    @Bean
    public Binding bindingDelayQueue(@Qualifier("delayQueue")Queue delayQueue,@Qualifier("delayExchange") DirectExchange delayExchange){
        return BindingBuilder.bind(delayQueue).to(delayExchange).with("delay.routing.key");
    }
    /**
     * 声明死信队列
     */
    @Bean
    public Queue deadQueue(){
        return QueueBuilder
                // 队列名称
                .durable("dead.queue").build();
    }
    /**
     * 绑定死信队列至死信交换机
     */
    @Bean
    public Binding bindingDeadQueue(@Qualifier("deadQueue")Queue deadQueue,@Qualifier("deadExchange") DirectExchange deadExchange){
        return BindingBuilder.bind(deadQueue).to(deadExchange).with("dead.routing.key");
    }
}
