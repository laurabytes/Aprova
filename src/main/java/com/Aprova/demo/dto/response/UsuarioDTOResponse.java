package com.Aprova.demo.dto.response;

public class UsuarioDTOResponse {
    private Integer usuarioId;
    private String nome;
    private String email;
    private int status;

    public Integer getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Integer usuarioId) {
        this.usuarioId = usuarioId;
    }
}
