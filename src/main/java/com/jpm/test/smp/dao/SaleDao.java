package com.jpm.test.smp.dao;

import com.jpm.test.smp.entity.SaleEntity;
import java.util.List;

public interface SaleDao
{
    SaleEntity createOrUpdate(SaleEntity saleEntity);
    List<SaleEntity> findByProductType(String productType);
}
