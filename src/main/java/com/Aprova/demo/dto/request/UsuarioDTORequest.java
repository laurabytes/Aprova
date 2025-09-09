package com.Aprova.demo.dto.request;

public class UsuarioDTORequest {
  private String nome;
  private String email;
  private String senha;
  private int status;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
