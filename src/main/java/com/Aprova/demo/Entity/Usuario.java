package com.Aprova.demo.Entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usuario_id")
    private Integer usuarioId;

    @Column(name = "usuario_nome", length = 30)
    private String nome;

    @Column(name = "usuario_email", length = 45)
    private String email;

    @Column(name = "usuario_senha", length = 45)
    private String senha;

    @Column(name = "usuario_status")
    private int status;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Materia> materias;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Metas> metas;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SessaoEstudo> sessoesEstudo;



    public Integer getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Integer usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<Materia> getMaterias() {
        return materias;
    }

    public void setMaterias(List<Materia> materias) {
        this.materias = materias;
    }

    public List<Metas> getMetas() {
        return metas;
    }

    public void setMetas(List<Metas> metas) {
        this.metas = metas;
    }

    public List<SessaoEstudo> getSessoesEstudo() {
        return sessoesEstudo;
    }

    public void setSessoesEstudo(List<SessaoEstudo> sessoesEstudo) {
        this.sessoesEstudo = sessoesEstudo;
    }
}