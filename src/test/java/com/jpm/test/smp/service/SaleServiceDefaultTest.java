package com.jpm.test.smp.service;

import com.jpm.test.smp.dao.SaleDao;
import com.jpm.test.smp.entity.SaleEntity;
import java.util.Arrays;
import java.util.List;
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
        SaleDao saleDaoMock = Mockito.mock(SaleDao.class);
        SaleService saleService = new SaleServiceDefault(saleDaoMock);
        String productType = "any";
        List<SaleEntity> saleEntityList = Arrays.asList(
            new SaleEntity("apple", 12.5, 1),
            new SaleEntity("nut", 12.3, 2));
        Mockito.when(saleDaoMock.findByProductType(productType)).thenReturn(saleEntityList);

        Assertions.assertEquals(saleEntityList, saleService.findByProductType(productType));

        Mockito.verify(saleDaoMock, Mockito.times(1)).findByProductType(productType);
    }

    @Test
    void adjust()
    {
    }

}