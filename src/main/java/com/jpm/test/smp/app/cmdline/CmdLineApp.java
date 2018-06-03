package com.jpm.test.smp.app.cmdline;

import com.jpm.test.smp.MessageProcessor;
import com.jpm.test.smp.MessageProcessorDefault;
import com.jpm.test.smp.app.MessageDtoFactory;
import com.jpm.test.smp.app.MessageDtoFactoryException;
import com.jpm.test.smp.dao.SaleDaoInMemory;
import com.jpm.test.smp.dto.AdjustmentDto;
import com.jpm.test.smp.dto.MessageDto;
import com.jpm.test.smp.service.SaleService;
import com.jpm.test.smp.service.SaleServiceDefault;
import com.jpm.test.smp.util.Printer;
import com.jpm.test.smp.util.Report;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class CmdLineApp
{
    private MessageProcessor messageProcessor;
    private SaleService saleService;

    private int messageCounter;
    private List<AdjustmentDto> adjustmentDtoList;
    private static final int LAST_SALES_NUMBER = 10;


    public CmdLineApp(MessageProcessor messageProcessor, SaleService saleService)
    {
        this.messageProcessor = messageProcessor;
        this.saleService = saleService;
        messageCounter = 0;
        adjustmentDtoList = new ArrayList<>();
    }

    public void run(Path path) throws IOException
    {
        Files.lines(path).forEach(raw -> {
            try
            {
                MessageDto messageDto = MessageDtoFactory.create(raw);
                messageProcessor.process(messageDto);
                if (messageDto.getAdjustmentDto() != null)
                {
                    adjustmentDtoList.add(messageDto.getAdjustmentDto());
                }
            }
            catch (MessageDtoFactoryException e)
            {
                System.out.println("INVALID MESSAGE: " + e.getMessage());
                return;
            }
            messageCounter++;
            if (messageCounter % LAST_SALES_NUMBER == 0)
            {
                Printer.printSalesReport(Report.createSalesReport(saleService.findLastSales(LAST_SALES_NUMBER)));
                if (messageCounter == 50)
                {
                    Printer.printMessage("The application is pausing and stopping accepting new messages");
                    Printer.printAdjustmentsReport(Report.createAdjustmentReport(adjustmentDtoList));
                    messageCounter = 0;
                    adjustmentDtoList.clear();
                }
            }
        });
    }

    private static File parseArgs(String[] args)
    {
        if (args.length != 1)
        {
            System.out.println("You must pass one only argument, the file path of the input messages");
            System.exit(0);
        }
        File inputFile = new File(args[0]);
        if (!inputFile.exists())
        {
            System.out.println(String.format("Invalid path: %s", args[0]));
            System.exit(0);
        }
        return inputFile;
    }

    public static void main(String[] args) throws IOException
    {
        File inputFile = parseArgs(args);
        SaleService saleService = new SaleServiceDefault(new SaleDaoInMemory());
        MessageProcessor messageProcessor = new MessageProcessorDefault(saleService);
        CmdLineApp cmdLineApp = new CmdLineApp(messageProcessor, saleService);
        cmdLineApp.run(inputFile.toPath());
    }
}
