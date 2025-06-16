package br.com.credito.service;

import br.com.credito.domain.Credito;
import br.com.credito.dto.CreditoDTO;
import br.com.credito.repository.CreditoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CreditoServiceTest {

    @Mock
    private CreditoRepository creditoRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private CreditoService creditoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Nested
    class GetCreditosByNfse {

        @Test
        void returnsListCreditoDTO() {
            String numeroNfse = "12345";
            Credito credito = new Credito(1L, "123", "12345", LocalDate.now(), BigDecimal.TEN);
            CreditoDTO creditoDTO = new CreditoDTO();
            when(creditoRepository.findByNumeroNfse(numeroNfse)).thenReturn(List.of(credito));
            when(modelMapper.map(credito, CreditoDTO.class)).thenReturn(creditoDTO);

            List<CreditoDTO> result = creditoService.getCreditosByNfse(numeroNfse);

            assertNotNull(result);
            assertEquals(1, result.size());
            assertEquals(creditoDTO, result.get(0));
            verify(creditoRepository, times(1)).findByNumeroNfse(numeroNfse);
            verify(modelMapper, times(1)).map(credito, CreditoDTO.class);
        }

        @Test
        void returnsEmptyList() {
            String numeroNfse = "12345";
            when(creditoRepository.findByNumeroNfse(numeroNfse)).thenReturn(Collections.emptyList());

            List<CreditoDTO> result = creditoService.getCreditosByNfse(numeroNfse);

            assertNotNull(result);
            assertTrue(result.isEmpty());
            verify(creditoRepository, times(1)).findByNumeroNfse(numeroNfse);
            verifyNoInteractions(modelMapper);
        }
    }

    @Nested
    class getCreditoByNumeroCredito {

        @Test
        void returnCreditoDTO() {
            String numeroCredito = "12345";
            Credito credito = new Credito(1L, "123", "12345", LocalDate.now(), BigDecimal.TEN);
            CreditoDTO creditoDTO = new CreditoDTO();
            when(creditoRepository.findCreditoByNumeroCredito(numeroCredito)).thenReturn(credito);
            when(modelMapper.map(credito, CreditoDTO.class)).thenReturn(creditoDTO);

            CreditoDTO result = creditoService.getCreditoByNumeroCredito(numeroCredito);

            assertNotNull(result);
            verify(creditoRepository, times(1)).findCreditoByNumeroCredito(numeroCredito);
            verify(modelMapper, times(1)).map(credito, CreditoDTO.class);
        }

        @Test
        void returnEmptyDTO() {
            String numeroCredito = "12345";
            when(creditoRepository.findCreditoByNumeroCredito(numeroCredito)).thenReturn(null);

            CreditoDTO result = creditoService.getCreditoByNumeroCredito(numeroCredito);

            assertNull(result);

            verify(creditoRepository, times(1)).findCreditoByNumeroCredito(numeroCredito);
            verifyNoInteractions(modelMapper);
        }
    }

}