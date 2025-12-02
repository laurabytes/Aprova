package com.Aprova.demo.dto.response;

public class PlanejadorDTOResponse {
    private Integer id;
    private String dia;
    private Integer hora;
    private Integer min;
    private Integer duracao;
    private String materia;


    public PlanejadorDTOResponse() {}


    public PlanejadorDTOResponse(Integer id, String dia, Integer hora, Integer min, Integer duracao, String materia) {
        this.id = id;
        this.dia = dia;
        this.hora = hora;
        this.min = min;
        this.duracao = duracao;
        this.materia = materia;
    }


    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

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
}