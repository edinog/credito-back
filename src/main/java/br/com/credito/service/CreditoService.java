package br.com.credito.service;

import br.com.credito.domain.Credito;
import br.com.credito.dto.CreditoDTO;
import br.com.credito.repository.CreditoRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CreditoService {

    private final CreditoRepository creditoRepository;
    private final ModelMapper modelMapper;

    public List<CreditoDTO> getCreditosByNfse(String numeroNfse) {
        return creditoRepository.findByNumeroNfse(numeroNfse)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    private CreditoDTO toDTO(Credito credito) {
            return credito != null ? modelMapper.map(credito, CreditoDTO.class) : null;
        }

    public CreditoDTO getCreditoByNumeroCredito(String numeroCredito) {
        Credito credito = creditoRepository.findCreditoByNumeroCredito(numeroCredito);
        return toDTO(credito);
    }
}