package com.enviro.assessment.grad001.boitumelotshehla.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.experimental.Accessors;

import java.util.List;

@Accessors(chain = true)
@Data
@Entity
@Getter
public class Investor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "investor_seq")
    private Long id;
    private String firstName;
    private String middleName;
    private String lastName;
    private String idNumber;
    private String email;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address address;
    @OneToMany(mappedBy = "investor")
    private List<WithdrawalNotice> withdrawalNotices;
    @ManyToMany
    @JoinTable(
            name = "investor_product",
            joinColumns = @JoinColumn(name = "investor_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<Product> products;

}
