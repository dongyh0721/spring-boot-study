package study.rabbitmq.consumer.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author dongyh
 * @date 2024/1/3
 */
@Configuration
public class DirectExchangeConfig {
    private static final String DIRECT_QUEUE_ROUTING_KEY = "direct.queue.routing.key";
    public static final String DIRECT_QUEUE="direct.queue";
    public static final String DIRECT_EXCHANGE="direct.exchange";
    /**
     * 创建队列
     */
    @Bean
    public Queue directQueue() {
        return new Queue(DIRECT_QUEUE, true);
    }
    /**
     * 创建交换机
     */
    @Bean
    public Exchange directExchange() {
        return new DirectExchange(DIRECT_EXCHANGE);
    }
    /**
     * 绑定路由键与交换机
     */
    @Bean
    public Binding directBinding(@Qualifier("directQueue") Queue helloDirectQueue,@Qualifier("directExchange") Exchange helloDirectExchange) {
        return BindingBuilder.bind(helloDirectQueue).to(helloDirectExchange).with(DIRECT_QUEUE_ROUTING_KEY).noargs();
    }
}
