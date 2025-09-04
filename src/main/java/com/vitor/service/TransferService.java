package com.vitor.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.vitor.dto.TransferResponseDTO;
import com.vitor.entitys.Transfer;
import com.vitor.entitys.dto.TransferDTO;
import com.vitor.repository.TransferRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TransferService {

    private final TransferRepository repository;

    public TransferResponseDTO scheduleTransfer(TransferDTO dto) {
        Transfer transfer = new Transfer(
                dto.getSourceAccount(),
                dto.getDestinationAccount(),
                dto.getAmount(),
                BigDecimal.ZERO,
                dto.getTransferDate());
        Transfer save = repository.save(transfer);;
        return new TransferResponseDTO(save);
    }

    public List<TransferResponseDTO> listAll() {
        return repository.findAll()
                .stream().map(TransferResponseDTO::new)
                .collect(Collectors.toList());
    }
}
