package cn.calendo.tcmdistribution.listener.rabbitmq;

import cn.calendo.tcmdistribution.dto.RcvPresInfoDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MessageListener {

    @RabbitListener(queues = "directQueue")
    public RcvPresInfoDTO receive(RcvPresInfoDTO rcvPresInfoDTO) {
        log.info("收到处方请求");
        return rcvPresInfoDTO;
    }

}
