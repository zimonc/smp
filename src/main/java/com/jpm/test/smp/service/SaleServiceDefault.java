package com.jpm.test.smp.service;

import com.jpm.test.smp.dao.SaleDao;
import com.jpm.test.smp.entity.SaleEntity;
import com.jpm.test.smp.vo.Operation;
import java.util.List;

public class SaleServiceDefault implements SaleService
{
    private final SaleDao saleDao;

    public SaleServiceDefault(final SaleDao saleDao)
    {
        this.saleDao = saleDao;
    }

    @Override
    public SaleEntity createOrUpdate(SaleEntity saleEntity)
    {
        return saleDao.createOrUpdate(saleEntity);
    }

    @Override
    public List<SaleEntity> findByProductType(String productType)
    {
        return saleDao.findByProductType(productType);
    }

    @Override
    public void adjust(String productType, Operation operation, Double value)
    {
        List<SaleEntity> sales = saleDao.findByProductType(productType);
        if (operation.equals(Operation.ADD))
        {
            sales.forEach((s) -> { s.setPrice(s.getPrice() + value); saleDao.createOrUpdate(s); });
        } else if (operation.equals(Operation.SUBTRACT))
        {
            sales.forEach((s) -> { s.setPrice(s.getPrice() - value); saleDao.createOrUpdate(s); });
        } else
        {
            sales.forEach((s) -> { s.setPrice(s.getPrice() * value); saleDao.createOrUpdate(s); });
        }

    }


    @Override
    public List<SaleEntity> findLastSales(int number)
    {
        return saleDao.findLastSales(number);
    }
}
