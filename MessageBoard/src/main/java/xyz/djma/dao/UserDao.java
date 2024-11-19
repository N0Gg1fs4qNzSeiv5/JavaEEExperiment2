package xyz.djma.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import xyz.djma.domain.User;

import java.util.List;

public interface UserDao {
    @Insert("insert into user values (#{id}, #{username}, #{password})")
    public void save(User user);

    @Update("update user set username = #{username}, password = #{password} where id = #{id}")
    public void update(User user);

    @Delete("delete from user where id = #{id};")
    public void delete(String id);

    @Select("select * from user where id = #{id}")
    public User getById(String id);

    @Select("select * from user")
    public List<User> getAll();

    @Select("select * from user where username = #{username}")
    public User getByUsername(String username);
}
