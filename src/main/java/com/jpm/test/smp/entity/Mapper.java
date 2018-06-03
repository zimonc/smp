package com.jpm.test.smp.entity;

import com.jpm.test.smp.dto.SaleDto;

public class Mapper
{
    public static SaleEntity fromSaleDto(SaleDto saleDto)
    {
        return new SaleEntity(saleDto.getProductType(), saleDto.getPrice(), saleDto.getQuantity());
    }
}
