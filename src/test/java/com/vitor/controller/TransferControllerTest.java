package com.vitor.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import com.vitor.dto.TransferResponseDTO;
import com.vitor.entitys.dto.TransferDTO;
import com.vitor.service.TransferService;

class TransferControllerTest {
    @Mock
    private TransferService transferService;

    @InjectMocks
    private TransferController transferController;

    public TransferControllerTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testScheduleTransferSuccess() {
        TransferDTO transferDTO = new TransferDTO();
        transferDTO.setAmount(BigDecimal.valueOf(100));
        transferDTO.setTransferDate(LocalDate.now().plusDays(5));

        TransferResponseDTO responseDTO = new TransferResponseDTO();
        responseDTO.setFee(BigDecimal.valueOf(10));

        when(transferService.scheduleTransfer(any())).thenReturn(responseDTO);

        ResponseEntity<TransferResponseDTO> response = transferController.scheduleTransfer(transferDTO);
        assertEquals(201, response.getStatusCodeValue());
        assertEquals(BigDecimal.valueOf(10), response.getBody().getFee());
    }

    @Test
    void testScheduleTransferThrowsException() {
        TransferDTO transferDTO = new TransferDTO();
        transferDTO.setAmount(BigDecimal.valueOf(100));
        transferDTO.setTransferDate(LocalDate.now().plusDays(60));

        when(transferService.scheduleTransfer(any())).thenThrow(new IllegalArgumentException("Data de transferência fora do prazo permitido."));

        assertThrows(IllegalArgumentException.class, () -> {
            transferController.scheduleTransfer(transferDTO);
        });
    }
}
