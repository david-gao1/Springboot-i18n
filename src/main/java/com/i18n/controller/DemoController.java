package com.i18n.controller;

import com.i18n.util.MessageResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/api")
public class DemoController {
    Logger log = LoggerFactory.getLogger(DemoController.class);
    @Autowired
    private MessageResolver messageResolver;


    /**
     * 用于拼接字串，动态输出message
     *
     * @return
     */
    @GetMapping("/hello-coder")
    public ResponseEntity greeting() {
        final String message = messageResolver.getMessage("你好！" + "JavaCoder" + "~", "message.key.hello1",
                new Object[]{"JavaCoder", "hahaha"});
        return ResponseEntity.ok(message);
    }

    /**
     * 静态代码
     *
     * @return
     */
    @GetMapping("/test")
    public ResponseEntity test() {
        String message = messageResolver.getMessage("执行接口变更审批失败111",
                "TopoResourceApproveService.doOperate1", null);
        return ResponseEntity.ok(message);
    }

}
