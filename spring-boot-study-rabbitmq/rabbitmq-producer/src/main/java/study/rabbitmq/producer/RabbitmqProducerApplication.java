package study.rabbitmq.producer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import study.rabbitmq.consumer.constants.RabbitmqConstant;

/**
 * @author dongyh
 * @date 2023/12/28
 */
@SpringBootApplication
public class RabbitmqProducerApplication {
    public static void main(String[] args) {
        RabbitmqConstant.DIRECT_EXCHANGE
        SpringApplication.run(RabbitmqProducerApplication.class,args);
    }
}
