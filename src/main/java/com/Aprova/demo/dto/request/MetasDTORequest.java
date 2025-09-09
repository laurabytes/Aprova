package com.Aprova.demo.dto.request;

public class MetasDTORequest {
    private String nome;
    private String data;
    private Integer usuarioId;
    private int status;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
