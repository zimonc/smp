package com.jpm.test.smp;

import com.jpm.test.smp.dto.MessageDto;
import com.jpm.test.smp.entity.Mapper;
import com.jpm.test.smp.service.SaleService;

public class MessageProcessorDefault implements MessageProcessor
{
    private final SaleService saleService;

    public MessageProcessorDefault(final SaleService saleService)
    {
        this.saleService = saleService;
    }

    public void process(MessageDto messageDto)
    {
        saleService.createOrUpdate(Mapper.fromSaleDto(messageDto.getSaleDto()));
        if (messageDto.getAdjustmentDto() != null)
        {
            saleService.adjust(
                messageDto.getSaleDto().getProductType(),
                messageDto.getAdjustmentDto().getOperation(),
                messageDto.getAdjustmentDto().getValue());
        }
    }
}
