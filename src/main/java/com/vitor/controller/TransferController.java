package com.vitor.controller;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    public ResponseEntity<Page<TransferResponseDTO>> listTransfers(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<TransferResponseDTO> transfers = transferService.listAllPaged(page, size);
        return ResponseEntity.ok(transfers);
    }
}
