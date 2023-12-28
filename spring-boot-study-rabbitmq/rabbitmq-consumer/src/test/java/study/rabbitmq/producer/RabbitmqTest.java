package study.rabbitmq.producer;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author dongyh
 * @date 2023/12/29
 */
@SpringBootTest
public class RabbitmqTest {
    @Resource
    private RabbitTemplate rabbitTemplate;
    /**
     * 默认交换机实现
     * {@link study.rabbitmq.consumer.listener.DefaultExchangeListener}
     */
    @Test
    public void defaultExchangeTest(){
        rabbitTemplate.convertAndSend("default.queue","hello default exchange");
    }

    @Test
    public void directExchangeTest(){
        rabbitTemplate.convertAndSend("hello.direct.queue","hello direct exchange");
    }

}
