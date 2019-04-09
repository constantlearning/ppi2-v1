package com.unitri.ppi2v1.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@NoArgsConstructor

@Entity
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "cliente.nome.mandatory")
    private String nome;

    @NotBlank(message = "cliente.cpf.mandatory")
    @CPF(message = "cliente.cpf.invalid")
    private String cpf;

    @OneToMany(mappedBy = "cliente")
    private List<Locacao> locacaos;

    @JsonIgnore
    public boolean isNew() {
        return this.id == null;
    }

    public boolean isUpdate() {
        return this.id != null;
    }
}
