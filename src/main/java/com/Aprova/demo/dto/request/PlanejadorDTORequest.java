package com.Aprova.demo.dto.request;

public class PlanejadorDTORequest {
    private String dia;
    private Integer hora;
    private Integer min;
    private Integer duracao;
    private String materia;
    private Integer usuarioId;

    public String getDia() { return dia; }
    public void setDia(String dia) { this.dia = dia; }

    public Integer getHora() { return hora; }
    public void setHora(Integer hora) { this.hora = hora; }

    public Integer getMin() { return min; }
    public void setMin(Integer min) { this.min = min; }

    public Integer getDuracao() { return duracao; }
    public void setDuracao(Integer duracao) { this.duracao = duracao; }

    public String getMateria() { return materia; }
    public void setMateria(String materia) { this.materia = materia; }

    public Integer getUsuarioId() { return usuarioId; }
    public void setUsuarioId(Integer usuarioId) { this.usuarioId = usuarioId; }
}