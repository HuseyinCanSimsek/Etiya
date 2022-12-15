package com.etiya.ecommercedemopair1.core.util.messages;

import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContext;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MessageManager implements MessageService{
    private MessageSource messageSource;

    @Override
    public String getMessage(String message) {
        return messageSource.getMessage(message,null, LocaleContextHolder.getLocale());
    }
}
