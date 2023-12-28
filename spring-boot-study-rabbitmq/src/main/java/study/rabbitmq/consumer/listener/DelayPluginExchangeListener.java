package study.rabbitmq.consumer.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author dongyh
 * @date 2024/1/16
 */
@Slf4j
@Component
public class DelayPluginExchangeListener {
    @RabbitListener(queues = "delay.plugin.queue")
    public void message(String message){
        log.info(message);
    }
}
