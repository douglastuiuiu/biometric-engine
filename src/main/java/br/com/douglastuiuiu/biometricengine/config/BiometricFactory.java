package br.com.douglastuiuiu.biometricengine.config;

import br.com.douglastuiuiu.biometricengine.exception.ServiceException;
import br.com.douglastuiuiu.biometricengine.service.AcessoService;
import br.com.douglastuiuiu.biometricengine.service.CredDefenseService;
import br.com.douglastuiuiu.biometricengine.service.IBiometricService;
import br.com.douglastuiuiu.biometricengine.util.MessageLocale;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * @author douglas
 * @since 20/03/2017
 */
@Service
@Scope(value = BeanDefinition.SCOPE_PROTOTYPE)
public class BiometricFactory implements FactoryBean<IBiometricService> {

    @Value("${api.bureau.creddefense}")
    private String creddefenseBureau;
    @Value("${api.bureau.acesso}")
    private String acessoBureau;

    @Autowired
    private HttpServletRequest request;
    @Autowired
    private CredDefenseService credDefenseService;
    @Autowired
    private AcessoService acessoService;
    @Autowired
    private MessageLocale messageLocale;

    @Override
    public IBiometricService getObject() throws ServiceException {
        try {
            String biometricBureauName = request.getHeader("biometricBureauName");

            if (creddefenseBureau.equals(biometricBureauName)) {
                return credDefenseService;
            } else if (acessoBureau.equals(biometricBureauName)) {
                return acessoService;
            } else {
                throw new ServiceException(messageLocale.getMessage("error.bureau_service_not_found"));
            }
        } catch (Exception e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public Class<IBiometricService> getObjectType() {
        return IBiometricService.class;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }

}
