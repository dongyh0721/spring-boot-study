package study.rabbitmq.producer;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.test.context.SpringBootTest;
import study.rabbitmq.constants.RabbitmqConstant;

import java.time.LocalDateTime;

/**
 * @author dongyh
 * @date 2023/12/29
 */
@SpringBootTest
public class RabbitmqTest {
    @Resource
    private RabbitTemplate rabbitTemplate;
    /**
     * 所有队列都会绑定在rabbitmq的默认交换机上
     * 默认交换机实际是一个没有名字的交换机
     * 它有一个特殊的属性是每个新建的队列都会自动绑定到默认的交换机上
     * 绑定的路由键(routing-key)名称与队列(queue)名称相同
     * 发送消息时不指定exchange 则使用默认交换机
     * {@link study.rabbitmq.consumer.listener.DefaultExchangeListener}
     */
    @Test
    public void defaultExchangeTest(){
        rabbitTemplate.convertAndSend(RabbitmqConstant.DefaultExchange.DEFAULT_QUEUE,"hello default exchange");
    }

    /**
     * 直连交换机与queue通过routingKey进行绑定
     * 消息生产者通过指定routingKey发送到对应的交换机
     * 交换机通过routingKey路由到指定队列
     * routingKey必须完全匹配
     * {@link study.rabbitmq.consumer.listener.DirectExchangeListener}
     */
    @Test
    public void directExchangeTest(){
        rabbitTemplate.convertAndSend(RabbitmqConstant.DirectExchange.EXCHANGE_NAME,RabbitmqConstant.DirectExchange.DEFAULT_QUEUE+RabbitmqConstant.DirectExchange.ROUTING_KEY,"hello direct exchange");
    }

    /**
     * 扇形交换机将消息路由到绑定在它身上的所有队
     * 不会理会具体的路由键
     * 如果多个队列绑定到某个扇型交换机上
     * 当有消息发送给此扇型交换机时
     * 交换机会将消息的拷贝分别发送给这些队列
     * 扇型用来交换机处理消息的广播路由
     * {@link study.rabbitmq.consumer.listener.FanoutExchangeListener}
     */
    @Test
    public void fanoutExchangeTest(){
        rabbitTemplate.convertAndSend(RabbitmqConstant.FanoutExchange.EXCHANGE_NAME,"","hello fanout exchange");
    }

    /**
     * 主题交换机的routingKey与绑定到该交换器的队列的pattern进行匹配
     * 路由到一个或多个队列
     * direct是完全匹配 topic可以模糊匹配
     * binding key 中可以存在两种特殊字符 “*” 与“#”用于做模糊匹配
     * 其中 “*” 用于匹配一个单词，“#”用于匹配多个单词（可以是零个）
     * routing key 为一个句点号 “.” 分隔的字符串
     * （我们将被句点号 “. ” 分隔开的每一段独立的字符串称为一个单词）
     * 如“stock.usd.nyse”、“nyse.vmw”、“quick.orange.rabbit”
     * binding key 与 routing key 一样也是句点号 “.” 分隔的字符串
     */
    @Test
    public void topicExchangeTest(){
        rabbitTemplate.convertAndSend(RabbitmqConstant.TopicExchange.EXCHANGE_NAME,"first.second","hello topic exchange");
    }
    /**
     * 延迟队列实现
     * {@link study.rabbitmq.consumer.config.DelayQueueConfig}
     * {@link study.rabbitmq.consumer.listener.DelayExchangeListener}
     * rabbitmq自身实现延迟队列只会按照第一个消息结束时间拉取消息
     * 如果第一条消息时间过长 后面的消息时间过短
     * 则需要等待第一条消息过期 后面的消息才会处理
     * 不是动态的
     */
    @Test
    public void delayQueueTest(){
        JSONObject json1 = new JSONObject();
        json1.putOnce("sendTime", LocalDateTime.now());
        json1.putOnce("message","hello delay exchange ");
        rabbitTemplate.convertAndSend("delay.direct.exchange","delay.routing.key", JSONUtil.toJsonPrettyStr(json1), msg->{
            msg.getMessageProperties().setExpiration("5000");
            return msg;
        });
    }
    /**
     * 插件实现延迟队列
     * {@link study.rabbitmq.consumer.config.DelayPluginQueueConfig}
     * {@link study.rabbitmq.consumer.listener.DelayPluginExchangeListener}
     */
    @Test
    public void delayPluginQueueTest(){
        LocalDateTime now = LocalDateTime.now();
        String msg=now.getYear()+"-" + now.getMonthValue() + "-" + now.getDayOfMonth() + " " + now.getHour() + ":" + now.getMinute() + ":" + now.getSecond()+" hello delay 1";
        rabbitTemplate.convertAndSend("delay.plugin.exchange","delay.plugin.routing.key",msg,message->{
            message.getMessageProperties().setDelay(10000);
            return message;
        });
        String msg2=now.getYear()+"-" + now.getMonthValue() + "-" + now.getDayOfMonth() + " " + now.getHour() + ":" + now.getMinute() + ":" + now.getSecond()+" hello delay 2";
        rabbitTemplate.convertAndSend("delay.plugin.exchange","delay.plugin.routing.key",msg2,message->{
            message.getMessageProperties().setDelay(5000);
            return message;
        });
    }
}
