package com.vitor.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.vitor.dto.TransferResponseDTO;
import com.vitor.entitys.Transfer;
import com.vitor.entitys.dto.TransferDTO;
import com.vitor.repository.TransferRepository;
import com.vitor.service.fee.FeeCalculationService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TransferService {

    private final TransferRepository repository;
    private final FeeCalculationService feeCalculationService;

    public TransferResponseDTO scheduleTransfer(TransferDTO dto) {
        BigDecimal fee = feeCalculationService.calculateFee(
            dto.getAmount(), 
            dto.getTransferDate()
        );

        Transfer transfer = new Transfer(
                dto.getSourceAccount(),
                dto.getDestinationAccount(),
                dto.getAmount(),
                fee,
                dto.getTransferDate());
        Transfer saved = repository.save(transfer);;
        return new TransferResponseDTO(saved);
    }

    public List<TransferResponseDTO> listAll() {
        return repository.findAll()
                .stream().map(TransferResponseDTO::new)
                .collect(Collectors.toList());
    }
}
