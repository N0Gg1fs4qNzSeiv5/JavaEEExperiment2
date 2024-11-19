package xyz.djma.service;

import xyz.djma.domain.Message;
import xyz.djma.util.Result;

public interface MessageService {
    /**
     * 获取所有消息
     * @return Result
     */
    public Result getAll();

    /**
     * 发送消息
     * @param message Message
     * @return Result
     */
    public Result sendMessage(Message message, String userId);
}
