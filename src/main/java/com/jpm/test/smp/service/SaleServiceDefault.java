package com.jpm.test.smp.service;

import com.jpm.test.smp.entity.SaleEntity;
import com.jpm.test.smp.vo.Operation;
import java.util.List;

public class SaleServiceDefault implements SaleService
{
    @Override
    public SaleEntity create(SaleEntity saleEntity)
    {
        return null;
    }


    @Override
    public List<SaleEntity> findByProductType(String productType)
    {
        return null;
    }


    @Override
    public void adjust(String productType, Operation operation, Double value)
    {

    }
}
