package com.jpm.test.smp.util;

import com.jpm.test.smp.dto.AdjustmentDto;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Printer
{
    private static final String PRINT_FORMAT = "\n############################ PRINTING %s REPORT";

    public static void printSalesReport(Map<String, Double> salesReport)
    {
        System.out.println(String.format(PRINT_FORMAT, "SALES"));
        List<String> productTypeList = new ArrayList<>(salesReport.keySet());
        productTypeList.stream().forEach(productType -> {
            System.out.println(String.format("  %s = %s", productType, salesReport.get(productType)));
        });
    }

    public static void printAdjustmentsReport(Map<String, List<AdjustmentDto>> adjustmentsReport)
    {
        System.out.println(String.format(PRINT_FORMAT, "ADJUSTMENTS"));
        List<String> productTypeList = new ArrayList<>(adjustmentsReport.keySet());
        productTypeList.stream().forEach(productType -> {
            System.out.println("  " + productType);
            List<String> operations = adjustmentsReport.get(productType).stream().map(adjustmentDto ->
                String.format("%s[%s]", adjustmentDto.getOperation().name(), adjustmentDto.getValue())
            ).collect(Collectors.toList());
            System.out.println("      " + String.join(",", operations));
        });
    }


    public static void printMessage(String s)
    {
        System.out.println("\n" + s);
    }
}
