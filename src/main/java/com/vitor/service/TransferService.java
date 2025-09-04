package com.vitor.service;

import java.math.BigDecimal;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
                dto.getTransferDate());

        Transfer transfer = new Transfer(
                dto.getSourceAccount(),
                dto.getDestinationAccount(),
                dto.getAmount(),
                fee,
                dto.getTransferDate());
        Transfer saved = repository.save(transfer);
        return new TransferResponseDTO(saved);
    }

    public Page<TransferResponseDTO> listAllPaged(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page - 1, size, Sort.by(Sort.Direction.DESC, "schedulingDate"));
        return repository.findAll(pageRequest)
                .map(TransferResponseDTO::new);
    }
}
