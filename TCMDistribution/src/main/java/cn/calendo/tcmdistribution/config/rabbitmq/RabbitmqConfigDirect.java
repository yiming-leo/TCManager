package cn.calendo.tcmdistribution.config.rabbitmq;


import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * rabbitmq直接消息模型配置
 */
@Configuration
public class RabbitmqConfigDirect {

    /**
     * 消息队列
     * @return 新建消息队列
     */
    @Bean
    public Queue directQueue() {
        return new Queue("directQueue", true, false, false);
    }

    /**
     * 交换机
     * @return 新建交换机
     */
    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange("directExchange");
    }

    /**
     * 绑定消息队列和交换机
     * @return 绑定单元
     */
    @Bean
    public Binding bindingDirect() {
        return BindingBuilder.bind(directQueue()).to(directExchange()).with("MessageReceive");
    }

}
