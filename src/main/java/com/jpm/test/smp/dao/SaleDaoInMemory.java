package com.jpm.test.smp.dao;

import com.jpm.test.smp.entity.SaleEntity;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SaleDaoInMemory implements SaleDao
{
    private List<SaleEntity> memory;

    public SaleDaoInMemory()
    {
        this.memory = new ArrayList<>();
    }

    public SaleDaoInMemory(List<SaleEntity> memory)
    {
        this.memory = memory;
    }

    public SaleEntity createOrUpdate(SaleEntity saleEntity)
    {
        if (!memory.contains(saleEntity))
        {
            saleEntity.setTimestamp(LocalDateTime.now());
            memory.add(saleEntity);
        }
        else
        {
            // We have do do nothing since the object was, eventually, updated "in place".
        }
        return saleEntity;
    }


    public List<SaleEntity> findByProductType(final String productType)
    {
        return memory.stream().filter(s -> s.getProductType().equals(productType)).collect(Collectors.toList());
    }


    @Override
    public List<SaleEntity> findLastSales(int number)
    {
        return memory.subList(memory.size() - number, memory.size());
    }
}
