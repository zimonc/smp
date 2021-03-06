package com.jpm.test.smp.service;

import com.jpm.test.smp.entity.SaleEntity;
import com.jpm.test.smp.vo.Operation;
import java.util.List;

public interface SaleService
{
    SaleEntity createOrUpdate(SaleEntity saleEntity);
    List<SaleEntity> findByProductType(String productType);
    void adjust(String productType, Operation operation, Double value);
    List<SaleEntity> findLastSales(int number);
}
