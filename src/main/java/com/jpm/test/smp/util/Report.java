package com.jpm.test.smp.util;

import com.jpm.test.smp.dto.AdjustmentDto;
import com.jpm.test.smp.entity.SaleEntity;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Report
{
    public static Map<String, Double> createSalesReport(List<SaleEntity> saleEntityList)
    {
        Map<String, Double> salesReport = new HashMap<>();
        saleEntityList.stream().forEach(saleEntity -> {
            if (!salesReport.containsKey(saleEntity.getProductType()))
            {
                salesReport.put(saleEntity.getProductType(), 0.0);
            }
            salesReport.put(saleEntity.getProductType(),
                salesReport.get(saleEntity.getProductType()) + saleEntity.getPrice() * saleEntity.getQuantity());
        });
        return salesReport;
    }

    public static Map<String, List<AdjustmentDto>> createAdjustmentReport(List<AdjustmentDto> adjustmentDtoList)
    {
        Map<String, List<AdjustmentDto>> adjustmentReport = new HashMap<>();
        adjustmentDtoList.stream().forEach(adjustmentDto -> {
            if (!adjustmentReport.containsKey(adjustmentDto.getProductType()))
            {
                adjustmentReport.put(adjustmentDto.getProductType(), new ArrayList<>());
            }
            adjustmentReport.get(adjustmentDto.getProductType()).add(adjustmentDto);
        });
        return  adjustmentReport;
    }

}
