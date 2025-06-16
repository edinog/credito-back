package br.com.credito.controller;

import br.com.credito.dto.CreditoDTO;
import br.com.credito.exception.EntidadeNaoEncontradaException;
import br.com.credito.service.CreditoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/creditos")
public class ConsultaController {

    @Autowired
    // Injetando o serviço de crédito
    private CreditoService creditoService;
    /**
     * Endpoint para consultar créditos por número da NFS-e.
     *
     * @param numeroNfse Número da NFS-e
     * @return Lista de créditos encontrados
     */
    @GetMapping("/{numeroNfse}")
    public List<CreditoDTO> getCreditosByNfse(@PathVariable String numeroNfse) {
        List<CreditoDTO> creditos = creditoService.getCreditosByNfse(numeroNfse);

        // Se não encontrar créditos, lança a exceção
        if (creditos.isEmpty()) {
            throw new EntidadeNaoEncontradaException("Nenhum crédito encontrado para o número da NFS-e: " + numeroNfse);
        }

        return creditos;
    }

    /**
     * Endpoint para consultar crédito por número do crédito.
     *
     * @param numeroCredito Número do crédito
     * @return Detalhes do crédito encontrado
     */
    @GetMapping("/credito/{numeroCredito}")
    public ResponseEntity<CreditoDTO> getCreditoByNumeroCredito(@PathVariable String numeroCredito) {
        CreditoDTO credito = creditoService.getCreditoByNumeroCredito(numeroCredito);

        // Se não encontrar o crédito, lança a exceção
        if (credito == null) {
            throw new EntidadeNaoEncontradaException("Crédito não encontrado para o número: " + numeroCredito);
        }

        return ResponseEntity.status(HttpStatus.OK).body(credito);
    }

}
