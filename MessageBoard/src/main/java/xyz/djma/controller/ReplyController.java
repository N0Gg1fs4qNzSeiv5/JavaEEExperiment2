package xyz.djma.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.djma.domain.Reply;
import xyz.djma.service.ReplyService;
import xyz.djma.util.Result;
import xyz.djma.util.SessionKeys;

@RestController
@RequestMapping("/replies")
@ResponseBody
public class ReplyController {
    @Autowired
    private ReplyService replyService;

    @GetMapping("/{messageId}")
    public Result getByMessageId(@PathVariable String messageId) {
        System.out.println("根据留言获取回复");
        return replyService.getByMessageId(messageId);
    }

    @PostMapping("/{messageId}")
    public Result sendReply(@RequestBody Reply reply, @PathVariable String messageId, HttpSession session) {
        System.out.println("发送回复");
        return replyService.sendReply(reply, (String) session.getAttribute(SessionKeys.USER_ID), messageId);
    }
}
