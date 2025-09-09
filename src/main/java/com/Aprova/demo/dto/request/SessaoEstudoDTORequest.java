package com.Aprova.demo.dto.request;

public class SessaoEstudoDTORequest {
    private String inicio;
    private String fim;
    private String statusSessao;
    private Integer materiaId;
    private Integer usuarioId;
    private int status;

    public String getInicio() {
        return inicio;
    }

    public void setInicio(String inicio) {
        this.inicio = inicio;
    }
}
