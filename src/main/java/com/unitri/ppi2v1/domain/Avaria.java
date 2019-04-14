package com.unitri.ppi2v1.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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
    public boolean isNew() {
        return this.id == null;
    }

    @JsonIgnore
    public boolean isUpdate() {
        return id != null;
    }
}
