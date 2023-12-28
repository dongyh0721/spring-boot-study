package study.rabbitmq.consumer.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @author dongyh
 * @date 2024/1/16
 */
@Configuration
public class DelayPluginQueueConfig {
    @Bean
    public CustomExchange delayPluginExchange() {
        Map<String,Object> args=new HashMap<>();
        args.put("x-delayed-type", ExchangeTypes.DIRECT);
        return new CustomExchange("delay.plugin.exchange", "x-delayed-message",true,false,args);
    }
    @Bean
    public Queue delayPluginQueue() {
        return QueueBuilder.durable("delay.plugin.queue").build();
    }
    @Bean
    public Binding bindingDelayPluginQueue(@Qualifier("delayPluginExchange") CustomExchange delayPluginExchange, @Qualifier("delayPluginQueue") Queue delayPluginQueue) {
        return BindingBuilder.bind(delayPluginQueue).to(delayPluginExchange).with("delay.plugin.routing.key").noargs();
    }
}
