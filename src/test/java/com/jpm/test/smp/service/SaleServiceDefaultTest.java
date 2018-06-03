package com.jpm.test.smp.service;

import com.jpm.test.smp.dao.SaleDao;
import com.jpm.test.smp.entity.SaleEntity;
import com.jpm.test.smp.vo.Operation;
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
    void adjustWithADD()
    {
        SaleDao saleDaoMock = Mockito.mock(SaleDao.class);
        SaleService saleService = new SaleServiceDefault(saleDaoMock);
        List<SaleEntity> saleEntityList = Arrays.asList(
            new SaleEntity("apple", 12.5, 1),
            new SaleEntity("nut", 12.3, 2));
        String productType = "nut";
        Mockito.when(saleDaoMock.findByProductType(productType)).thenReturn(Arrays.asList(saleEntityList.get(1)));

        saleService.adjust(productType, Operation.ADD, 0.7);

        Assertions.assertEquals(12.5, saleEntityList.get(0).getPrice().doubleValue());
        Assertions.assertEquals(13, saleEntityList.get(1).getPrice().doubleValue());
        Mockito.verify(saleDaoMock, Mockito.times(1)).findByProductType(productType);
        Mockito.verify(saleDaoMock, Mockito.times(1)).createOrUpdate(saleEntityList.get(1));
    }

    @Test
    void adjustWithSUBCRACT()
    {
        SaleDao saleDaoMock = Mockito.mock(SaleDao.class);
        SaleService saleService = new SaleServiceDefault(saleDaoMock);
        List<SaleEntity> saleEntityList = Arrays.asList(
            new SaleEntity("apple", 12.5, 1),
            new SaleEntity("nut", 12.3, 2));
        String productType = "nut";
        Mockito.when(saleDaoMock.findByProductType(productType)).thenReturn(Arrays.asList(saleEntityList.get(1)));

        saleService.adjust(productType, Operation.SUBTRACT, 0.3);

        Assertions.assertEquals(12.5, saleEntityList.get(0).getPrice().doubleValue());
        Assertions.assertEquals(12, saleEntityList.get(1).getPrice().doubleValue());
        Mockito.verify(saleDaoMock, Mockito.times(1)).findByProductType(productType);
        Mockito.verify(saleDaoMock, Mockito.times(1)).createOrUpdate(saleEntityList.get(1));
    }

    @Test
    void adjustWithMULTIPLY()
    {
        SaleDao saleDaoMock = Mockito.mock(SaleDao.class);
        SaleService saleService = new SaleServiceDefault(saleDaoMock);
        List<SaleEntity> saleEntityList = Arrays.asList(
            new SaleEntity("apple", 12.5, 1),
            new SaleEntity("nut", 12.3, 2));
        String productType = "nut";
        Mockito.when(saleDaoMock.findByProductType(productType)).thenReturn(Arrays.asList(saleEntityList.get(1)));

        saleService.adjust(productType, Operation.MULTIPLY, 2.0);

        Assertions.assertEquals(12.5, saleEntityList.get(0).getPrice().doubleValue());
        Assertions.assertEquals(24.6, saleEntityList.get(1).getPrice().doubleValue());
        Mockito.verify(saleDaoMock, Mockito.times(1)).findByProductType(productType);
        Mockito.verify(saleDaoMock, Mockito.times(1)).createOrUpdate(saleEntityList.get(1));
    }

    @Test
    void findLastSales()
    {
        SaleDao saleDaoMock = Mockito.mock(SaleDao.class);
        SaleService saleService = new SaleServiceDefault(saleDaoMock);
        List<SaleEntity> saleEntityList = Arrays.asList(
            new SaleEntity("apple", 12.5, 1),
            new SaleEntity("nut", 12.3, 2));
        String productType = "nut";
        int number = 10;
        Mockito.when(saleDaoMock.findLastSales(number)).thenReturn(saleEntityList);

        List<SaleEntity> lastSales = saleService.findLastSales(number);

        Assertions.assertEquals(saleEntityList, lastSales);
        Mockito.verify(saleDaoMock, Mockito.times(1)).findLastSales(number);

    }

}