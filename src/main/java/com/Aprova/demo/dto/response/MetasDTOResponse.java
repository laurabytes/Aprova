package com.Aprova.demo.dto.response;

import java.time.LocalDate;

public class MetasDTOResponse {
    private Integer metasId;
    private String nome;
    private LocalDate data;
    private int status;

    public Integer getMetasId() {
        return metasId;
    }

    public void setMetasId(Integer metasId) {
        this.metasId = metasId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
