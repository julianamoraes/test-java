package com.juliana.testjava.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Campanha.
 */
@Entity
@Table(name = "campanha")
public class Campanha implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(name = "nome", nullable = false)
    private String nome;

    @NotNull
    @Column(name = "inicio", nullable = false)
    private LocalDate inicio;

    @NotNull
    @Column(name = "fim", nullable = false)
    private LocalDate fim;

    @ManyToOne
    private Time time;

    @ManyToMany(mappedBy = "campanhas")
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

    public Campanha nome(String nome) {
        this.nome = nome;
        return this;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getInicio() {
        return inicio;
    }

    public Campanha inicio(LocalDate inicio) {
        this.inicio = inicio;
        return this;
    }

    public void setInicio(LocalDate inicio) {
        this.inicio = inicio;
    }

    public LocalDate getFim() {
        return fim;
    }

    public Campanha fim(LocalDate fim) {
        this.fim = fim;
        return this;
    }

    public void setFim(LocalDate fim) {
        this.fim = fim;
    }

    public Time getTime() {
        return time;
    }

    public Campanha time(Time time) {
        this.time = time;
        return this;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public Set<Usuario> getUsuarios() {
        return usuarios;
    }

    public Campanha usuarios(Set<Usuario> usuarios) {
        this.usuarios = usuarios;
        return this;
    }

    public Campanha addUsuario(Usuario usuario) {
        usuarios.add(usuario);
        usuario.getCampanhas().add(this);
        return this;
    }

    public Campanha removeUsuario(Usuario usuario) {
        usuarios.remove(usuario);
        usuario.getCampanhas().remove(this);
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
        Campanha campanha = (Campanha) o;
        if (campanha.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, campanha.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Campanha{" +
            "id=" + id +
            ", nome='" + nome + "'" +
            ", inicio='" + inicio + "'" +
            ", fim='" + fim + "'" +
            '}';
    }
}
