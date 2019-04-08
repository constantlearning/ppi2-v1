package com.unitri.ppi2v1.repository;

import com.unitri.ppi2v1.domain.Cliente;
import com.unitri.ppi2v1.domain.Locacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LocacaoRepository extends JpaRepository<Locacao, Long> {

    Optional<Locacao> findByCliente(Cliente cliente);
}
