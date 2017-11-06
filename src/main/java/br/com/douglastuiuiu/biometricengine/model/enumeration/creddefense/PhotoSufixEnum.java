package br.com.douglastuiuiu.biometricengine.model.enumeration.creddefense;

/**
 * @author douglas
 * @since 24/03/2017
 */
public enum PhotoSufixEnum {

    PHOTO("_photo"),
    CREDITREQUEST("_creditrequest"),
    CUSTOMER("_customer"),
    FRAUD("_fraud_"),
    CREDIREQUEST_SIMILAR("_credirequest_similar_"),
    CUSTOMER_SIMILAR("_customer_similar_");

    private final String name;

    PhotoSufixEnum(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
