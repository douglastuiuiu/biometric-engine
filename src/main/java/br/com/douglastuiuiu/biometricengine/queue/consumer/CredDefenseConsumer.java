package br.com.douglastuiuiu.biometricengine.queue.consumer;

import br.com.douglastuiuiu.biometricengine.exception.ServiceException;
import br.com.douglastuiuiu.biometricengine.queue.dto.AnalyzeRequestMessage;
import br.com.douglastuiuiu.biometricengine.service.CredDefenseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CredDefenseConsumer {

    private static final Logger logger = LoggerFactory.getLogger(CredDefenseConsumer.class);

    @Autowired
    private CredDefenseService credDefenseService;

    @RabbitListener(queues = {"${api.queue.creddefense.name}"})
    public void receiveMessage(AnalyzeRequestMessage analyzeRequestMessage) throws ServiceException {
        credDefenseService.checkAnalyzeStatus(analyzeRequestMessage);
        logger.info("consumer message: " + analyzeRequestMessage.getId());
    }

}
