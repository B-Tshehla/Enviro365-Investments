package com.enviro.assessment.grad001.boitumelotshehla.repository;

import com.enviro.assessment.grad001.boitumelotshehla.model.WithdrawalNotice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface WithdrawalNoticeRepository extends JpaRepository<WithdrawalNotice, Long>, JpaSpecificationExecutor<WithdrawalNotice> {
}

