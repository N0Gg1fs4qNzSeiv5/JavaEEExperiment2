package xyz.djma.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.djma.domain.Message;
import xyz.djma.service.MessageService;
import xyz.djma.util.Result;
import xyz.djma.util.SessionKeys;

@RestController
@RequestMapping("/messages")
@ResponseBody
public class MessageController {
    @Autowired
    private MessageService messageService;

    @GetMapping
    public Result getAll() {
        System.out.println("获取所有消息");
        return messageService.getAll();
    }

    @PostMapping
    public Result sendMessage(@RequestBody Message message, HttpSession session) {
        System.out.println("发送消息");
        return messageService.sendMessage(message, (String) session.getAttribute(SessionKeys.USER_ID));
    }
}
