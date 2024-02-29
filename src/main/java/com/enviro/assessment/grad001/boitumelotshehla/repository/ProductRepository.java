package com.enviro.assessment.grad001.boitumelotshehla.repository;

import com.enviro.assessment.grad001.boitumelotshehla.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    List<Product> findAllByInvestorId(Long investorId);
}
