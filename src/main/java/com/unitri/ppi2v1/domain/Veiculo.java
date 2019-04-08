package com.unitri.ppi2v1.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor

@Entity
public class Veiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "veiculo.marca.mandatory")
    private String marca;

    @NotBlank(message = "veiculo.modelo.mandatory")
    private String modelo;

    @NotNull(message = "veiculo.categoria.mandatory")
    @ManyToOne
    private Categoria categoria;

    @OneToMany(mappedBy = "veiculo")
    private List<Locacao> locacoes;

    @JsonIgnore
    public boolean isNew() {
        return this.id == null;
    }

    @JsonIgnore
    public boolean isUpdate() {
        return this.id != null;
    }
}
