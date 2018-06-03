package com.jpm.test.smp.dao;

import com.jpm.test.smp.entity.SaleEntity;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.Assertions;
import org.mockito.Mockito;

class SaleDaoInMemoryTest
{

    @Test
    void createOrUpdate()
    {
        List<SaleEntity> memoryMock = Mockito.mock(List.class);
        SaleDao saleDao = new SaleDaoInMemory(memoryMock);

        SaleEntity saleEntity = new SaleEntity("apple", 10.2, 3);
        SaleEntity saleEntityStored = saleDao.createOrUpdate(saleEntity);

        Assertions.assertEquals(saleEntity, saleEntityStored);
        Assertions.assertNotNull(saleEntityStored.getTimestamp());
        System.out.println(saleEntityStored.getTimestamp());
        System.out.println(LocalDateTime.now());
        Assertions.assertTrue(LocalDateTime.now().isAfter(saleEntityStored.getTimestamp()));

        Mockito.verify(memoryMock, Mockito.times(1)).add(saleEntity);
    }


    @Test
    void findByProductType()
    {
        List<SaleEntity> memory = Arrays.asList(new SaleEntity("apple", 10.2, 2),
            new SaleEntity("nut", 13.2, 3));
        SaleDao saleDao = new SaleDaoInMemory(memory);

        List<SaleEntity> filteredSales = saleDao.findByProductType("apple");
        Assertions.assertEquals(1, filteredSales.size());
        Assertions.assertEquals(memory.get(0), filteredSales.get(0));

        filteredSales = saleDao.findByProductType("shoe");
        Assertions.assertEquals(0, filteredSales.size());
    }

    @Test
    void findByProductTypeEmpty()
    {
        List<SaleEntity> memory = Arrays.asList(new SaleEntity("apple", 10.2, 2),
            new SaleEntity("nut", 13.2, 12));
        SaleDao saleDao = new SaleDaoInMemory(memory);

        List<SaleEntity> filteredSales = saleDao.findByProductType("shoe");
        Assertions.assertEquals(0, filteredSales.size());
    }

}