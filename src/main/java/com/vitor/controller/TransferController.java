package com.vitor.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vitor.entitys.Transfer;
import com.vitor.entitys.dto.TransferDTO;
import com.vitor.service.TransferService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/transfers")
public class TransferController {

    private final TransferService transferService;

    @PostMapping()
    public ResponseEntity<Void> scheduleTransfer(@RequestBody TransferDTO transferDTO) {
        transferService.scheduleTransfer(transferDTO);
        return ResponseEntity.ok().build();
    }

    @GetMapping()
    public ResponseEntity<List<Transfer>> listTransfers() {
        List<Transfer> allTransfers = transferService.listAll();;
        return ResponseEntity.ok(allTransfers);
    }
}
