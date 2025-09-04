package com.vitor.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vitor.dto.TransferResponseDTO;
import com.vitor.entitys.dto.TransferDTO;
import com.vitor.service.TransferService;

import lombok.RequiredArgsConstructor;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/transfers")
public class TransferController {

    private final TransferService transferService;

    @PostMapping
    public ResponseEntity<TransferResponseDTO> scheduleTransfer(@RequestBody @Valid TransferDTO transferDTO) {
        TransferResponseDTO response = transferService.scheduleTransfer(transferDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<TransferResponseDTO>> listTransfers() {
        List<TransferResponseDTO> allTransfers = transferService.listAll();
        return ResponseEntity.ok(allTransfers);
    }
}
