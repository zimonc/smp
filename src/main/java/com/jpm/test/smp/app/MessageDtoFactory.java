package com.jpm.test.smp.app;

import com.jpm.test.smp.dto.AdjustmentDto;
import com.jpm.test.smp.dto.MessageDto;
import com.jpm.test.smp.dto.SaleDto;
import com.jpm.test.smp.vo.Operation;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MessageDtoFactory
{
    private static final Pattern SALE_PATTERN = Pattern.compile(
        "^(?<productType>[a-z]+),(?<price>[0-9]+(\\.[0-9]+)?)(,(?<quantity>[0-9]+))?" +
            "(,(?<operationType>(ADD|SUBTRACT|MULTIPLY)),(?<operationValue>[0-9]+(\\.[0-9]+)?))?$");

    public static MessageDto create(String raw) throws MessageDtoFactoryException
    {
        Matcher matcher = SALE_PATTERN.matcher(raw);
        if (!matcher.find())
        {
            throw new MessageDtoFactoryException(raw);
        }
        SaleDto saleDto = new SaleDto(matcher.group("productType"), Double.valueOf(matcher.group("price")),
            matcher.group("quantity") == null ? 1 : Integer.valueOf(matcher.group("quantity")));
        AdjustmentDto adjustmentDto = null;
        if (matcher.group("operationType") != null)
        {
            adjustmentDto = new AdjustmentDto(
                Operation.valueOf(matcher.group("operationType")),
                Double.valueOf(matcher.group("operationValue")),
                saleDto.getProductType());
        }
        return new MessageDto(saleDto, adjustmentDto);
    }
}