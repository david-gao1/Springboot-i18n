package com.i18n.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

/**
 * 解析locale:采用cookie,避免每次都传递参数
 *
 * @author v_liangggao
 */
@Component
public class MyLocaleResolver implements LocaleResolver {

    private static Logger LOG = LoggerFactory.getLogger(MyLocaleResolver.class);

    /**
     * 通过给定的请求解析当前locale设置。
     * 在任何情况下都可以返回默认locale（这里具体指的是当地地区的语言）设置作为回退。
     *
     * @param httpServletRequest
     * @return
     */
    @Override
    public Locale resolveLocale(HttpServletRequest httpServletRequest) {
        Cookie[] cookies = httpServletRequest.getCookies();
        Locale locale = Locale.getDefault();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("locale".equals(cookie.getName())) {
                    //zh_CN;en_US
                    //cn;en
                    LOG.info("[resolveLocale] cookie.getName():{},cookie.getValue():{}", cookie.getName(), cookie.getValue());
                    String temp = cookie.getValue();
                    if ("en".equals(temp)) {
                        locale = new Locale("en", "US");
                    } else if ("cn".equals(temp)) {
                        locale = new Locale("zh", "CN");
                    }
                }
            }
        }

        return locale;
    }

    @Override
    public void setLocale(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Locale locale) {

    }
}