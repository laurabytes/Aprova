package com.Aprova.demo.Entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "metas")
public class Metas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "metas_id")
    private Integer metasId;

    @Column(name = "metas_nome", length = 45)
    private String nome;

    @Column(name = "metas_data", length = 45)
    private LocalDate data;

    @Column(name = "metas_status")
    private int status;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;


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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}