package br.com.douglastuiuiu.biometricengine;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import javax.annotation.PostConstruct;

/**
 * @author douglasg
 * @since 10/03/2017
 */
@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan("br.com.douglastuiuiu")
public class Application {

    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    public static void main(String[] agrs) {
        SpringApplication.run(Application.class);
    }

    @PostConstruct
    public void testLog() {
        logger.debug("Welcome to MicroService Archetype!");
    }
}
