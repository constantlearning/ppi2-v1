package com.unitri.ppi2v1.repository;

import com.unitri.ppi2v1.domain.Multa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MultaRepository extends JpaRepository<Multa, Long> {

    Optional<Multa> findByDescricaoAndValor(String descricao, Double valor);
}
