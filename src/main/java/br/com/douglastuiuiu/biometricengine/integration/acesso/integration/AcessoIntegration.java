package br.com.douglastuiuiu.biometricengine.integration.acesso.integration;

import br.com.douglastuiuiu.biometricengine.integration.acesso.properties.ApiAcessoPath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author douglasg
 * @since 10/03/2017
 */
@Component
public class AcessoIntegration {

    @Autowired
    private ApiAcessoPath apiAcessoPath;

}
