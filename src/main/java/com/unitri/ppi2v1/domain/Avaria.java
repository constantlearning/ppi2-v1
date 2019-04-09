package com.unitri.ppi2v1.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor

@Entity
public class Avaria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "avaria.descricao.mandatory")
    private String descricao;

    @DecimalMin(message = "avaria.valor.minValue", value = "0")
    @NotNull(message = "avaria.valor.mandatory")
    private Double valor;

    @JsonIgnore
    @ManyToMany(mappedBy = "avarias")
    private List<Locacao> locacaos = new ArrayList<>();

    @JsonIgnore
    public boolean isNew() {
        return this.id == null;
    }

    @JsonIgnore
    public boolean isUpdate() {
        return id != null;
    }
}
