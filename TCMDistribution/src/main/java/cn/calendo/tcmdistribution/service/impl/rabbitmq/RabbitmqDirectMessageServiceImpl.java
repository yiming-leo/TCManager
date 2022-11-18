package cn.calendo.tcmdistribution.service.impl.rabbitmq;

import cn.calendo.tcmdistribution.service.message.IMessageService;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitmqDirectMessageServiceImpl implements IMessageService {

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    public Object receiveMessage() {
        Object directQueue = rabbitTemplate.receiveAndConvert("directQueue");
//        Object o = amqpTemplate.receiveAndConvert();
        return directQueue;
    }
}
