package com.international.demo.config;

import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

//解析locale，采用cookie,避免每次都传递参数
public class MyLocaleResolver implements LocaleResolver {

    /**
     * 通过给定的请求解析当前locale设置。
     * 在任何情况下都可以返回默认locale（这里具体指的是哪个地区的语言）设置作为回退。
     * @param httpServletRequest
     * @return
     */
    @Override
    public Locale resolveLocale(HttpServletRequest httpServletRequest) {

        //HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        Cookie[] cookies = httpServletRequest.getCookies();
        Locale locale = Locale.getDefault();
        if(cookies != null) {
            for (Cookie cookie : cookies) {
                System.out.println(cookie.getName());
                System.out.println(cookie.getValue());
                if (cookie.getName().equals("selectedLang")) {
                    String temp = cookie.getValue();
                    if (!StringUtils.isEmpty(temp)) {
                        String[] s = temp.split("_");
                        locale = new Locale(s[0],s[1]);
                    }
                }
            }
        }
        /*
        String temp = httpServletRequest.getParameter("locale");
        Locale locale = Locale.getDefault();
        if (!StringUtils.isEmpty(temp)) {
            String[] s = temp.split("_");
            locale = new Locale(s[0],s[1]);
        }
        */
        return locale;
    }

    @Override
    public void setLocale(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Locale locale) {

    }
}