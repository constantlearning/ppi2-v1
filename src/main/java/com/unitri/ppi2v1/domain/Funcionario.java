package com.unitri.ppi2v1.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@NoArgsConstructor

@Entity
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "funcionario.matricula.mandatory")
    private String matricula;

    @NotBlank(message = "funcionario.nome.mandatory")
    private String nome;

    @OneToMany(mappedBy = "funcionarioRetirada")
    private List<Locacao> locacaoRetirada;

    @OneToMany(mappedBy = "funcionarioEntrega")
    private List<Locacao> locacaosEntrega;

    @JsonIgnore
    public boolean isNew() {
        return this.id == null;
    }

    @JsonIgnore
    public boolean isUpdate() {
        return this.id != null;
    }
}
