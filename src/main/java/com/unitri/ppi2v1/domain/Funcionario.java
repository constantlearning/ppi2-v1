package com.unitri.ppi2v1.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

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

    @JsonIgnore
    public boolean isNew() {
        return this.id == null;
    }

    @JsonIgnore
    public boolean isUpdate() {
        return this.id != null;
    }
}
