package com.jpm.test.smp.dao;

import com.jpm.test.smp.entity.SaleEntity;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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


    @Override
    public List<SaleEntity> findByProductType(String productType)
    {
        return null;
    }
}
