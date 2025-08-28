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

    @Column(name = "sessao_estudo_status", length = 45)
    private String status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "materia_id")
    private Materia materia;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    // Gerar Getters e Setters
}