package com.jpm.test.smp.dto;

public class SaleDto
{
    private String productType;
    private Double price;
    private Integer quantity;

    public SaleDto(String productType, Double price, Integer quantity)
    {
        this.productType = productType;
        this.price = price;
        this.quantity = quantity;
    }

    public String getProductType()
    {
        return productType;
    }

    public Double getPrice()
    {
        return price;
    }

    public Integer getQuantity()
    {
        return quantity;
    }
}
