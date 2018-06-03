package com.jpm.test.smp.app;

public class MessageDtoFactoryException extends Throwable
{
    public MessageDtoFactoryException(String raw)
    {
        super(String.format("Invalid raw: %s", raw));
    }
}
