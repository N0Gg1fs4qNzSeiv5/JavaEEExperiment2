package xyz.djma.dao;

import org.apache.ibatis.annotations.*;
import xyz.djma.domain.Message;

import java.util.List;

public interface MessageDao {
    @Insert("insert into message values (#{id}, #{title}, #{content}, NOW(), #{userId})")
    @Results(
            @Result(property = "userId", column = "user_id")
    )
    public void save(Message message);

    @Update("update message set title = #{title}, content = #{content} where id = #{id}")
    public void update(Message message);

    @Delete("delete from message where id = #{id};")
    public void delete(String id);

    @Select("select * from message where id = #{id}")
    @Results({
            @Result(property = "createAt", column = "create_at"),
            @Result(property = "userId", column = "user_id")
    })
    public Message getById(String id);

    @Select("select * from message")
    @Results({
            @Result(property = "createAt", column = "create_at"),
            @Result(property = "userId", column = "user_id")
    })
    public List<Message> getAll();
}
