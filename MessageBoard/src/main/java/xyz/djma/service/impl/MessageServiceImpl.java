package xyz.djma.service.impl;

import com.fasterxml.jackson.core.JsonToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.djma.dao.MessageDao;
import xyz.djma.dao.UserDao;
import xyz.djma.domain.Message;
import xyz.djma.service.MessageService;
import xyz.djma.util.Result;

import java.util.UUID;

@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private MessageDao messageDao;

    @Override
    public Result getAll() {
        return Result.success(messageDao.getAll());
    }

    @Override
    public Result sendMessage(Message message, String userId) {
        // 判断用户
        if (userId == null || userDao.getById(userId) == null) {
            return Result.error("用户不存在");
        }

        // 判断字段
        String title = message.getTitle();
        String content = message.getContent();
        if (title == null || title.isEmpty()) {
            return Result.error("标题不能为空");
        }
        if (title.length() > 64) {
            return Result.error("标题最多64字符");
        }
        if (content == null || content.isEmpty()) {
            return Result.error("内容不能为空");
        }
        if (content.length() > 1024) {
            return Result.error("内容最多1024字符");
        }

        // 添加
        message.setId(UUID.randomUUID().toString());
        message.setUserId(userId);
        messageDao.save(message);

        return Result.success();
    }
}
