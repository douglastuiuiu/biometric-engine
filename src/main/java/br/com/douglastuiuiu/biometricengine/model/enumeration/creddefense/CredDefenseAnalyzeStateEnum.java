package br.com.douglastuiuiu.biometricengine.model.enumeration.creddefense;

/**
 * @author douglas
 * @since 24/03/2017
 */
public enum CredDefenseAnalyzeStateEnum {

    STARTED("Started"),
    CUSTOMER_IDENTIFICATION("Customer Identification"),
    AWAITING_REGISTRATION("Awaiting Registration"),
    AWAITING_SCORE("Awaiting Score"),
    AWAITING_ANALYSIS("Awaiting Analysis"),
    ANALYZED("Analyzed"),
    CANCELED("Canceled");

    private final String name;

    CredDefenseAnalyzeStateEnum(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
