package br.com.douglastuiuiu.biometricengine.model.enumeration;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;

/**
 * @author douglas
 * @since 24/03/2017
 */
public enum DocumentTypeEnum {

    CPF(4),
    RG_FRENTE(204),
    RG_VERSO(205),
    RG_FRENTE_TESTEMUNHA_1(208),
    RG_VERSO_TESTEMUNHA_1(209),
    RG_FRENTE_TESTEMUNHA_2(212),
    RG_VERSO_TESTEMUNHA_2(213),
    CNH_FRENTE(0),
    CNH_VERSO(0),
    RESERVISTA_FRENTE(0),
    RESERVISTA_VERSO(0),
    COMPROVANTE_RENDA(3),
    COMPROVANTE_RESIDENCIA(200),
    DECLARACAO_DEFICIENCIA_ANALFABETISMO(207),
    TAD(203);

    private final Integer id;

    DocumentTypeEnum(final int id) {
        this.id = id;
    }

    @JsonCreator
    public static DocumentTypeEnum getByCode(@JsonProperty("id") Integer code) {
        return Arrays.stream(DocumentTypeEnum.values())
                .filter(e -> e.id.equals(code))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException(String.format("Unsupported code %s.", code)));
    }

    @JsonCreator
    public static DocumentTypeEnum getByObjectName(@JsonProperty("type") String type) {
        return Arrays.stream(DocumentTypeEnum.values())
                .filter(e -> e.name().equals(type))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException(String.format("Unsupported type %s.", type)));
    }

    public int getId() {
        return id;
    }

}
