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

    public String getInicio() {
        return inicio;
    }

    public void setInicio(String inicio) {
        this.inicio = inicio;
    }

    public String getFim() {
        return fim;
    }

    public void setFim(String fim) {
        this.fim = fim;
    }

    public String getStatusSessao() {
        return statusSessao;
    }

    public void setStatusSessao(String statusSessao) {
        this.statusSessao = statusSessao;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
