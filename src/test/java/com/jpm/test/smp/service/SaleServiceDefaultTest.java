package com.jpm.test.smp.service;

import com.jpm.test.smp.dao.SaleDao;
import com.jpm.test.smp.entity.SaleEntity;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.Assertions;
import org.mockito.Mockito;

class SaleServiceDefaultTest
{
    @Test
    void create()
    {
        SaleDao saleDaoMock = Mockito.mock(SaleDao.class);
        SaleService saleService = new SaleServiceDefault(saleDaoMock);
        SaleEntity saleEntity = new SaleEntity("apple", 12.5, 1);
        Mockito.when(saleDaoMock.createOrUpdate(saleEntity)).thenReturn(saleEntity);

        Assertions.assertEquals(saleEntity, saleService.createOrUpdate(saleEntity));

        Mockito.verify(saleDaoMock, Mockito.times(1)).createOrUpdate(saleEntity);
    }


    @Test
    void findByProductType()
    {
    }


    @Test
    void adjust()
    {
    }

}