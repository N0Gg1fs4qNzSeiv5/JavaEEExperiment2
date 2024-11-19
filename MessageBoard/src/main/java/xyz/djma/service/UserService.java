package xyz.djma.service;

import xyz.djma.domain.User;
import xyz.djma.util.Result;

import java.util.List;

public interface UserService {
    /**
     * 注册
     * @param user User
     * @return Result
     */
    public Result register(User user);

    /**
     * 登陆
     * @param user User
     * @return Result
     */
    public Result login(User user);

    /**
     * 根据id获取
     * @param id id
     * @return Result
     */
    public Result getById(String id);
}
