package com.Aprova.demo.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "sessao_estudo")
public class SessaoEstudo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sessao_estudo_id")
    private Integer sessaoEstudoId;

    @Column(name = "sessao_estudo_inicio", length = 45)
    private String inicio;

    @Column(name = "sessao_estudo_fim", length = 45)
    private String fim;

    @Column(name = "sessao_estudo_status")
    private int status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "materia_id")
    private Materia materia;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;


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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Materia getMateria() {
        return materia;
    }

    public void setMateria(Materia materia) {
        this.materia = materia;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}