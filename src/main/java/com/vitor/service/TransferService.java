package com.vitor.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.vitor.entitys.Transfer;
import com.vitor.entitys.dto.TransferDTO;
import com.vitor.repository.TransferRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TransferService {

    private final TransferRepository repository;

    public void scheduleTransfer(TransferDTO transferDTO) {
        Transfer transfer = new Transfer(transferDTO);
        repository.save(transfer);
    }

    public List<Transfer> listAll() {
        return repository.findAll();
    }
}
