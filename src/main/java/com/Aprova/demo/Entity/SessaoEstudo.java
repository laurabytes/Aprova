package com.Aprova.demo.Entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "sessao_estudo")
public class SessaoEstudo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sessao_estudo_id")
    private Integer id;

    @Column(name = "sessao_estudo_inicio", length = 45)
    private LocalDateTime inicio;

    @Column(name = "sessao_estudo_fim", length = 45)
    private LocalDateTime fim;

    @Column(name = "sessao_estudo_status")
    private int status;


//    @Transient
//    @JsonProperty("idMateria")
//    public int getIdMateria(){
//        return usuario !=null ? materia.getId() : null;
//    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "materia_id")
    private Materia materia;

//    @Transient
//    @JsonProperty("idUsuario")
//    public int getIdUsuario(){
//        return usuario !=null ? usuario.getId() : null;
//    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getInicio() {
        return inicio;
    }

    public void setInicio(LocalDateTime inicio) {
        this.inicio = inicio;
    }

    public LocalDateTime getFim() {
        return fim;
    }

    public void setFim(LocalDateTime fim) {
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