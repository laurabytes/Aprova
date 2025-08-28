package com.Aprova.demo.Entity;

import jakarta.persistence.*;

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
    private String data;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    // Gerar Getters e Setters
}