package xyz.djma.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.djma.dao.MessageDao;
import xyz.djma.dao.ReplyDao;
import xyz.djma.dao.UserDao;
import xyz.djma.domain.Reply;
import xyz.djma.service.ReplyService;
import xyz.djma.util.Result;

import java.util.UUID;

@Service
public class ReplyServiceImpl implements ReplyService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private MessageDao messageDao;
    @Autowired
    private ReplyDao replyDao;

    @Override
    public Result getByMessageId(String messageId) {
        return Result.success(replyDao.getByMessageId(messageId));
    }

    @Override
    public Result sendReply(Reply reply, String userId, String messageId) {
        // 判断用户
        if (userId == null || userDao.getById(userId) == null) {
            return Result.error("用户不存在");
        }

        // 判断留言
        if (messageId == null || messageDao.getById(messageId) == null) {
            return Result.error("留言不存在");
        }

        // 判断字段
        String content = reply.getContent();
        if (content == null || content.isEmpty()) {
            return Result.error("内容不能为空");
        }
        if (content.length() > 256) {
            return Result.error("内容最多256字符");
        }

        // 添加
        reply.setId(UUID.randomUUID().toString());
        reply.setUserId(userId);
        reply.setMessageId(messageId);

        replyDao.save(reply);
        return Result.success();
    }
}
