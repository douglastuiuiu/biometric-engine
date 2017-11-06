package br.com.douglastuiuiu.biometricengine.integration.creddefense.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author douglasg
 * @since 10/03/2017
 */
@Configuration
@PropertySource("classpath:creddefenseengine/path.properties")
public class ApiCreddefensePath {

    @Value("${api.path.creddefense.host}")
    private String host;

    @Value("${api.path.creddefense.basePath}")
    private String basePath;

    @Value("${api.path.creddefense.user}")
    private String user;

    @Value("${api.path.creddefense.passwd}")
    private String passwd;

    @Value("${api.path.creddefense.cpf}")
    private String cpf;

    @Value("${api.path.creddefense.idLoja}")
    private String idLoja;

    @Value("${api.path.creddefense.nome}")
    private String nome;

    @Value("${api.path.creddefense.base64test1}")
    private String base64test1;

    @Value("${api.path.creddefense.base64test2}")
    private String base64test2;

    @Value("${api.path.creddefense.base64test3}")
    private String base64test3;

    @Value("${api.path.creddefense.base64test4}")
    private String base64test4;

    @Value("${api.path.creddefense.base64test5}")
    private String base64test5;

    @Value("${api.path.creddefense.base64test6}")
    private String base64test6;

    @Value("${api.path.creddefense.base64test7}")
    private String base64test7;

    @Value("${api.path.creddefense.base64test8}")
    private String base64test8;

    @Value("${api.path.creddefense.base64test9}")
    private String base64test9;

    @Value("${api.path.creddefense.base64test10}")
    private String base64test10;

    public String getHost() {
        return host;
    }

    public String getBasePath() {
        return basePath;
    }

    public String getUser() {
        return user;
    }

    public String getPasswd() {
        return passwd;
    }

    public String getEndpoint() {
        return getHost() + getBasePath();
    }

    public String getCpf() {
        return cpf;
    }

    public String getIdLoja() {
        return idLoja;
    }

    public String getNome() {
        return nome;
    }

    public String getBase64test1() {
        return base64test1;
    }

    public String getBase64test2() {
        return base64test2;
    }

    public String getBase64test3() {
        return base64test3;
    }

    public String getBase64test4() {
        return base64test4;
    }

    public String getBase64test5() {
        return base64test5;
    }

    public String getBase64test6() {
        return base64test6;
    }

    public String getBase64test7() {
        return base64test7;
    }

    public String getBase64test8() {
        return base64test8;
    }

    public String getBase64test9() {
        return base64test9;
    }

    public String getBase64test10() {
        return base64test10;
    }
}
