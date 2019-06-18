package com.unitri.ppi2v1.repository;

import com.unitri.ppi2v1.domain.Categoria;
import com.unitri.ppi2v1.domain.Navio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NavioRepository extends JpaRepository<Navio, Long> {

    Optional<Navio> findByModelo(String modelo);
}
