package com.unitri.ppi2v1.repository;

import com.unitri.ppi2v1.domain.Avaria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AvariaRepository extends JpaRepository<Avaria, Long> {

    Optional<Avaria> findByDescricaoAndValor(String descricao, Double valor);
}
