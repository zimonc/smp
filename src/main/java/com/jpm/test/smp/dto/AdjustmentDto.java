package com.jpm.test.smp.dto;

import com.jpm.test.smp.vo.Operation;

public class AdjustmentDto
{
    private Operation operation;
    private Double value;
    private String productType;

    public AdjustmentDto(Operation operation, Double value, String productType)
    {
        this.operation = operation;
        this.value = value;
        this.productType = productType;
    }


    public Operation getOperation()
    {
        return operation;
    }
    public Double getValue()
    {
        return value;
    }
    public String getProductType()
    {
        return productType;
    }
}
