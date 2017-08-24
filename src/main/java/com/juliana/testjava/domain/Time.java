package com.juliana.testjava.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Time.
 */
@Entity
@Table(name = "time")
public class Time implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(name = "nome", nullable = false)
    private String nome;

    @OneToMany(mappedBy = "time")
    @JsonIgnore
    private Set<Campanha> campanhas = new HashSet<>();

    @OneToMany(mappedBy = "time")
    @JsonIgnore
    private Set<Usuario> usuarios = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public Time nome(String nome) {
        this.nome = nome;
        return this;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Set<Campanha> getCampanhas() {
        return campanhas;
    }

    public Time campanhas(Set<Campanha> campanhas) {
        this.campanhas = campanhas;
        return this;
    }

    public Time addCampanha(Campanha campanha) {
        campanhas.add(campanha);
        campanha.setTime(this);
        return this;
    }

    public Time removeCampanha(Campanha campanha) {
        campanhas.remove(campanha);
        campanha.setTime(null);
        return this;
    }

    public void setCampanhas(Set<Campanha> campanhas) {
        this.campanhas = campanhas;
    }

    public Set<Usuario> getUsuarios() {
        return usuarios;
    }

    public Time usuarios(Set<Usuario> usuarios) {
        this.usuarios = usuarios;
        return this;
    }

    public Time addUsuario(Usuario usuario) {
        usuarios.add(usuario);
        usuario.setTime(this);
        return this;
    }

    public Time removeUsuario(Usuario usuario) {
        usuarios.remove(usuario);
        usuario.setTime(null);
        return this;
    }

    public void setUsuarios(Set<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Time time = (Time) o;
        if (time.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, time.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Time{" +
            "id=" + id +
            ", nome='" + nome + "'" +
            '}';
    }
}
