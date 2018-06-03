package com.jpm.test.smp.entity;

import java.time.LocalDateTime;

public class SaleEntity
{
    private String productType;
    private Double price;
    private Integer quantity;
    private LocalDateTime timestamp;

    public SaleEntity(String productType, Double price, Integer quantity)
    {
        this.timestamp = null;
        this.productType = productType;
        this.price = price;
        this.quantity = quantity;
    }

    public void setTimestamp(LocalDateTime timestamp)
    {
        this.timestamp = timestamp;
    }
    public LocalDateTime getTimestamp()
    {
        return timestamp;
    }

    public String getProductType()
    {
        return productType;
    }

    public Double getPrice()
    {
        return price;
    }

    public void setPrice(double price)
    {
        this.price = price;
    }

    public Integer getQuantity()
    {
        return quantity;
    }

    public String toString(){
        String toString = String.format("productType=%s, price=%s, quantity=%s, timestamp=%s",
            productType, price, quantity, timestamp);
        return String.format("%s - %s", super.toString(), toString);
    }
}
