package com.enviro.assessment.grad001.boitumelotshehla.model.enums;

import com.enviro.assessment.grad001.boitumelotshehla.exception.NoProductTypeException;
import lombok.Getter;

import java.util.Arrays;

@Getter
public enum ProductType {
    RETIREMENT("Retirement"),
    SAVINGS("Savings");

    private final String value;

    ProductType(String value) {
        this.value = value;
    }

    public static ProductType enumValueOf(String name) {
        return Arrays.stream(ProductType.values())
                .filter(e -> e.name().equalsIgnoreCase(name) || e.getValue().equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(() -> new NoProductTypeException("The product type is not available."));
    }
}
