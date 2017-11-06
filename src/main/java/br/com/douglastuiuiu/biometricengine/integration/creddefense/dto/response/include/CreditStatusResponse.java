package br.com.douglastuiuiu.biometricengine.integration.creddefense.dto.response.include;

public enum CreditStatusResponse {

    ATIVO(1, "A", "Solicitação de Crédito Aprovada."),
    INATIVO(2, "I", "Solicitação de Crédito Desconsiderada."),
    RESTRITO(3, "R", "Solicitação de Crédito Reprovada.");

    private Integer id;
    private String codigo;
    private String descricao;

    private CreditStatusResponse(Integer id, String codigo, String descricao) {
        this.id = id;
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public Integer getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getCodigo() {
        return codigo;
    }

}
