package com.jpm.test.smp.dto;

public class MessageDto
{
    private SaleDto saleDto;
    private AdjustmentDto adjustmentDto;

    public MessageDto(SaleDto saleDto, AdjustmentDto adjustmentDto)
    {
        this.saleDto = saleDto;
        this.adjustmentDto = adjustmentDto;
    }

    public SaleDto getSaleDto()
    {
        return saleDto;
    }

    public AdjustmentDto getAdjustmentDto()
    {
        return adjustmentDto;
    }

}

