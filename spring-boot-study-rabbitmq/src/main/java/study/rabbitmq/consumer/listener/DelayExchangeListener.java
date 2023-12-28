package study.rabbitmq.consumer.listener;

import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author dongyh
 * @date 2024/1/15
 */
@Slf4j
@Component
public class DelayExchangeListener {
    @RabbitListener(queues = "dead.queue")
    public void message(String message){
        log.info("延迟队列收到消息:{}", JSONUtil.parseObj(message));
    }
}
