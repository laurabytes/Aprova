package com.Aprova.demo.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "planejador")
public class Planejador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "planejador_id")
    private Integer id;

    @Column(name = "planejador_dia", nullable = false, length = 20)
    private String dia;

    @Column(name = "planejador_hora", nullable = false)
    private Integer hora;

    @Column(name = "planejador_min", nullable = false)
    private Integer min;

    @Column(name = "planejador_duracao", nullable = false)
    private Integer duracao;

    @Column(name = "planejador_materia", nullable = false)
    private String materia;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    public Planejador() {}

    // Getters e Setters
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
    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }
}