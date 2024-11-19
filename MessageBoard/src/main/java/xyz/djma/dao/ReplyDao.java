package xyz.djma.dao;

import org.apache.ibatis.annotations.*;
import xyz.djma.domain.Reply;
import xyz.djma.domain.User;

import java.util.List;

public interface ReplyDao {
    @Insert("insert into reply values (#{id}, #{content}, NOW(), #{userId}, #{messageId})")
    @Results({
            @Result(property = "userId", column = "user_id"),
            @Result(property = "messageId", column = "messaeg_id")
    })
    public void save(Reply reply);

    @Update("update reply set content = #{content} where id = #{id}")
    public void update(Reply reply);

    @Delete("delete from reply where id = #{id};")
    public void delete(String id);

    @Select("select * from reply where id = #{id}")
    @Results({
            @Result(property = "createAt", column = "create_at"),
            @Result(property = "userId", column = "user_id"),
            @Result(property = "messageId", column = "messaeg_id")
    })
    public Reply getById(String id);

    @Select("select * from reply where message_id = #{messageId}")
    @Results({
            @Result(property = "createAt", column = "create_at"),
            @Result(property = "userId", column = "user_id"),
            @Result(property = "messageId", column = "messaeg_id")
    })
    public List<Reply> getByMessageId(String messageId);

    @Select("select * from reply")
    @Results({
            @Result(property = "createAt", column = "create_at"),
            @Result(property = "userId", column = "user_id"),
            @Result(property = "messageId", column = "messaeg_id")
    })
    public List<Reply> getAll();
}
