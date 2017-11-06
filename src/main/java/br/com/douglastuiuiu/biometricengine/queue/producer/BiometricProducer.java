package br.com.douglastuiuiu.biometricengine.queue.producer;

import br.com.douglastuiuiu.biometricengine.queue.dto.AnalyzeRequestMessage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.core.MessagePropertiesBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class BiometricProducer {

    private final Logger logger = LoggerFactory.getLogger(BiometricProducer.class);

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Value("${api.exchange.name}")
    private String exchangeName;

    @Value("${api.routing.creddefense.key}")
    private String routingKeyCredDefense;

    @Value("${api.routing.acesso.key}")
    private String routingKeyCredAcesso;

    @Value("${api.exchange.requeue.time}")
    private Integer defaultDelay;

    @Value("${api.exchange.requeueByError.time}")
    private Integer errorDelay;

    public void enqueueMessage(String id, String routingKey) throws JsonProcessingException, AmqpException {
        sendMessage(id, routingKey, defaultDelay);
    }

    public void requeueMessageByError(String id, String routingKey) {
        try {
            logger.error("REQUEUE MESSAGE WITH ID " + id + ", EXCHANGE " + exchangeName + ", ROUTERKEY " + routingKey + " and DELAY " + errorDelay);
            this.sendMessage(id, routingKey, errorDelay);
        } catch (JsonProcessingException e) {
            logger.error("[FATAL] ERROR TO RENQUEUE MESSAGE WITH ID " + id + ", EXCHANGE " + exchangeName + ", ROUTERKEY " + routingKey + " and DELAY " + errorDelay, e);
        }
    }

    public void requeueMessage(String id, String routingKey) {
        try {
            logger.error("REQUEUE MESSAGE WITH ID " + id + ", EXCHANGE " + exchangeName + ", ROUTERKEY " + routingKey + " and DELAY " + defaultDelay);
            this.sendMessage(id, routingKey, defaultDelay);
        } catch (JsonProcessingException e) {
            logger.error("[FATAL] ERROR TO RENQUEUE MESSAGE WITH ID " + id + ", EXCHANGE " + exchangeName + ", ROUTERKEY " + routingKey + " and DELAY " + defaultDelay, e);
        }
    }

    private void sendMessage(String id, String routingKey, Integer delay) throws JsonProcessingException, AmqpException {
        if (delay == null || delay == 0) delay = errorDelay;

        AnalyzeRequestMessage analyzeRequestMessage = new AnalyzeRequestMessage(id);

        MessageProperties props = MessagePropertiesBuilder.newInstance().setContentType(MessageProperties.CONTENT_TYPE_JSON).build();
        props.setHeader("x-delay", delay);
        ObjectMapper mapper = new ObjectMapper();
        Message message = MessageBuilder.withBody(mapper.writeValueAsBytes(analyzeRequestMessage)).andProperties(props).build();

        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
        rabbitTemplate.setExchange(exchangeName);
        rabbitTemplate.setRoutingKey(routingKey);
        rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {
            if (!ack) throw new AmqpException("ERROR TO ENQUEUE MESSAGE [CAUSE]: " + cause);
        });
        rabbitTemplate.convertAndSend(message);
    }

    public String getRoutingKeyCredDefense() {
        return routingKeyCredDefense;
    }

    public String getRoutingKeyCredAcesso() {
        return routingKeyCredAcesso;
    }

}
