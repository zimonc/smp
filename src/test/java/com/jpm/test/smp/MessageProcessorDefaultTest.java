package com.jpm.test.smp;

import com.jpm.test.smp.dto.AdjustmentDto;
import com.jpm.test.smp.dto.MessageDto;
import com.jpm.test.smp.dto.SaleDto;
import com.jpm.test.smp.entity.SaleEntity;
import com.jpm.test.smp.service.SaleService;
import com.jpm.test.smp.vo.Operation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import sun.jvm.hotspot.utilities.Assert;

import static org.junit.jupiter.api.Assertions.*;

class MessageProcessorDefaultTest
{
    @Test
    void process()
    {
        SaleService saleServiceMock = Mockito.mock(SaleService.class);
        MessageProcessor messageProcessor = new MessageProcessorDefault(saleServiceMock);
        SaleDto saleDto = new SaleDto("apple", 23.3, 2);
        MessageDto messageDto = new MessageDto(saleDto, null);

        messageProcessor.process(messageDto);

        ArgumentCaptor<SaleEntity> argumentCaptor = ArgumentCaptor.forClass(SaleEntity.class);

        Mockito.verify(saleServiceMock, Mockito.times(1)).createOrUpdate(argumentCaptor.capture());
        Assertions.assertEquals(saleDto.getProductType(), argumentCaptor.getValue().getProductType());
        Assertions.assertEquals(saleDto.getPrice(), argumentCaptor.getValue().getPrice());
        Assertions.assertEquals(saleDto.getQuantity(), argumentCaptor.getValue().getQuantity());
    }

    @Test
    void processWithOperation()
    {
        SaleService saleServiceMock = Mockito.mock(SaleService.class);
        MessageProcessor messageProcessor = new MessageProcessorDefault(saleServiceMock);
        SaleDto saleDto = new SaleDto("apple", 23.3, 2);
        AdjustmentDto adjustmentDto = new AdjustmentDto(Operation.ADD, 0.21, saleDto.getProductType());
        MessageDto messageDto = new MessageDto(saleDto, adjustmentDto);

        messageProcessor.process(messageDto);

        ArgumentCaptor<SaleEntity> argumentCaptor = ArgumentCaptor.forClass(SaleEntity.class);
        Mockito.verify(saleServiceMock, Mockito.times(1)).createOrUpdate(argumentCaptor.capture());
        Assertions.assertEquals(saleDto.getProductType(), argumentCaptor.getValue().getProductType());
        Assertions.assertEquals(saleDto.getPrice(), argumentCaptor.getValue().getPrice());
        Assertions.assertEquals(saleDto.getQuantity(), argumentCaptor.getValue().getQuantity());

        Mockito.verify(saleServiceMock, Mockito.times(1))
            .adjust(adjustmentDto.getProductType(), adjustmentDto.getOperation(), adjustmentDto.getValue());
    }

}