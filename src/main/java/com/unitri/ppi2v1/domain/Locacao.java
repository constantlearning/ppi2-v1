package com.unitri.ppi2v1.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor

@Entity
public class Locacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "locacao.data.mandatory")
    private LocalDate data;

    @NotNull(message = "locacao.veiculo.mandatory")
    @ManyToOne
    private Veiculo veiculo;

    @NotNull(message = "locacao.cliente.mandatory")
    @ManyToOne
    private Cliente cliente;

    @NotNull(message = "locacao.funcionario.retirada.mandatory")
    @ManyToOne
    private Funcionario funcionarioRetirada;

    @ManyToOne
    private Funcionario funcionarioEntrega;

    @ManyToMany
    private List<Avaria> avarias = new ArrayList<>();

    @ManyToMany
    private List<Multa> multas = new ArrayList<>();

    @JsonIgnore
    public boolean isNew() {
        return this.id == null;
    }

    @JsonIgnore
    public boolean isUpdate() {
        return this.id != null;
    }
}
