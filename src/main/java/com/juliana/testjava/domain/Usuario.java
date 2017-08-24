package com.juliana.testjava.domain;


import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Usuario.
 */
@Entity
@Table(name = "usuario")
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(name = "nome_completo", nullable = false)
    private String nomeCompleto;

    @NotNull
    @Column(name = "email", nullable = false)
    private String email;

    @NotNull
    @Column(name = "nascimento", nullable = false)
    private LocalDate nascimento;

    @ManyToOne
    private Time time;

    @ManyToMany
    @JoinTable(name = "usuario_campanha",
               joinColumns = @JoinColumn(name="usuarios_id", referencedColumnName="ID"),
               inverseJoinColumns = @JoinColumn(name="campanhas_id", referencedColumnName="ID"))
    private Set<Campanha> campanhas = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public Usuario nomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
        return this;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public String getEmail() {
        return email;
    }

    public Usuario email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getNascimento() {
        return nascimento;
    }

    public Usuario nascimento(LocalDate nascimento) {
        this.nascimento = nascimento;
        return this;
    }

    public void setNascimento(LocalDate nascimento) {
        this.nascimento = nascimento;
    }

    public Time getTime() {
        return time;
    }

    public Usuario time(Time time) {
        this.time = time;
        return this;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public Set<Campanha> getCampanhas() {
        return campanhas;
    }

    public Usuario campanhas(Set<Campanha> campanhas) {
        this.campanhas = campanhas;
        return this;
    }

    public Usuario addCampanha(Campanha campanha) {
        campanhas.add(campanha);
        campanha.getUsuarios().add(this);
        return this;
    }

    public Usuario removeCampanha(Campanha campanha) {
        campanhas.remove(campanha);
        campanha.getUsuarios().remove(this);
        return this;
    }

    public void setCampanhas(Set<Campanha> campanhas) {
        this.campanhas = campanhas;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Usuario usuario = (Usuario) o;
        if (usuario.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, usuario.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Usuario{" +
            "id=" + id +
            ", nomeCompleto='" + nomeCompleto + "'" +
            ", email='" + email + "'" +
            ", nascimento='" + nascimento + "'" +
            '}';
    }
}
