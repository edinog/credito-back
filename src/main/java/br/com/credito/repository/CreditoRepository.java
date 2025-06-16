package br.com.credito.repository;

import br.com.credito.domain.Credito;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CreditoRepository extends JpaRepository<Credito, Long> {
    List<Credito> findByNumeroNfse(String numeroNfse);

    Credito findCreditoByNumeroCredito(String numeroCredito);
}