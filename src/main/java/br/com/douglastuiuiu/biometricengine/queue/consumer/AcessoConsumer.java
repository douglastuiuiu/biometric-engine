package br.com.douglastuiuiu.biometricengine.queue.consumer;

import br.com.douglastuiuiu.biometricengine.exception.ServiceException;
import br.com.douglastuiuiu.biometricengine.queue.dto.AnalyzeRequestMessage;
import br.com.douglastuiuiu.biometricengine.service.AcessoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AcessoConsumer {

    private static final Logger logger = LoggerFactory.getLogger(AcessoConsumer.class);

    @Autowired
    private AcessoService acessoService;

    @RabbitListener(queues = {"${api.queue.acesso.name}"})
    public void receiveMessage(AnalyzeRequestMessage analyzeRequestMessage) throws ServiceException {
        acessoService.checkAnalyzeStatus(analyzeRequestMessage);
        logger.info("consumer message: " + analyzeRequestMessage.getId());
    }

}
