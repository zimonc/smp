package com.jpm.test.smp;

import com.jpm.test.smp.dto.MessageDto;

public interface MessageProcessor
{
    void process(MessageDto messageDto);
}
