package study.rabbitmq.constants;

/**
 * @author dongyh
 * @date 2024/1/4
 */
public abstract class RabbitmqConstant {
    public static abstract class DefaultExchange{
        public static final String DEFAULT_QUEUE="default.queue";
    }
    public static abstract class DirectExchange{
        public static final String EXCHANGE_NAME="direct.exchange";
        public static final String ROUTING_KEY=".routing.key";
        public static final String DEFAULT_QUEUE="direct.queue";
    }

    public static abstract class FanoutExchange{
        public static final String EXCHANGE_NAME="fanout.exchange";
        public static final String FIRST_QUEUE="fanout.first.queue";
        public static final String SECOND_QUEUE="fanout.second.queue";
    }
    public static abstract class TopicExchange{
        public static final String EXCHANGE_NAME="topic.exchange";
        public static final String FIRST_QUEUE="topic.first.queue";
        public static final String SECOND_QUEUE="topic.second.queue";
    }
}
