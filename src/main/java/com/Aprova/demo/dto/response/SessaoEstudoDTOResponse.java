package com.Aprova.demo.dto.response;

public class SessaoEstudoDTOResponse {
    private Integer sessaoEstudoId;
    private String inicio;
    private String fim;
    private String statusSessao;
    private int status;

    public Integer getSessaoEstudoId() {
        return sessaoEstudoId;
    }

    public void setSessaoEstudoId(Integer sessaoEstudoId) {
        this.sessaoEstudoId = sessaoEstudoId;
    }
}
