package com.i18n.util;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Locale;

@Component
public class MessageResolver {
    private static Logger LOG = LoggerFactory.getLogger(MessageResolver.class);

    @Autowired
    private MessageSource messageSource;
    @Autowired
    private MyLocaleResolver myLocaleResolver;


    /**
     * 尝试从数据包中获取message，如果获取的message为空，则返回defaultMessage
     *
     * @param defaultMessage 需要被替换的message
     * @param msgKey
     * @param args
     * @return
     */
    public String getMessage(String defaultMessage, String msgKey, Object[] args) {
        //通过RequestContextHolder获取请求
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        RequestContextHolder.setRequestAttributes(servletRequestAttributes);
        LOG.info("[MessageResolver getMessage servletRequestAttributes]{}", servletRequestAttributes);
        String message;
        try {
            if (servletRequestAttributes == null) {
                //获取不到cookie（例如rpc调用）时，设置为默认locale（当地的）
                message = messageSource.getMessage(msgKey, args, Locale.getDefault());
            } else {
                message = messageSource.getMessage(msgKey, args, myLocaleResolver.resolveLocale(servletRequestAttributes.getRequest()));
            }
            if (message.equals(msgKey) || StringUtils.isEmpty(message)) {
                message = defaultMessage;
            }
            return message;
        } catch (NoSuchMessageException e) {
            LOG.error("[getMessage] NoSuchMessageException", e);
            message = defaultMessage;
        }
        return message;
    }

}
