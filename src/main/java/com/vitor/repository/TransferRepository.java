package com.vitor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vitor.entitys.Transfer;

@Repository
public interface TransferRepository extends JpaRepository<Transfer, Long> {
}
