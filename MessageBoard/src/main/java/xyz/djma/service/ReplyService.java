package xyz.djma.service;

import xyz.djma.domain.Reply;
import xyz.djma.util.Result;

public interface ReplyService {
    public Result getByMessageId(String messageId);

    public Result sendReply(Reply reply, String userId, String messageId);
}
