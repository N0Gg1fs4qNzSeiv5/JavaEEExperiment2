package xyz.djma.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.djma.dao.UserDao;
import xyz.djma.domain.User;
import xyz.djma.service.UserService;
import xyz.djma.util.Result;

import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public Result register(User user) {
        // 检查用户信息
        String errorMsg = checkUserInfo(user);
        if (errorMsg != null) {
            return Result.error(errorMsg);
        }

        // 判断用户是否已经存在
        if (userDao.getByUsername(user.getUsername()) != null) {
            System.out.println("用户已存在");
            return Result.error("用户已存在");
        }

        // 设置UUID，添加
        user.setId(UUID.randomUUID().toString());
        userDao.save(user);
        System.out.println("注册成功");
        return Result.success();
    }

    @Override
    public Result login(User user) {
        // 检查用户信息
        String errorMsg = checkUserInfo(user);
        if (errorMsg != null) {
            return Result.error(errorMsg);
        }

        // 判断登陆
        User dbUser = userDao.getByUsername(user.getUsername());
        if (dbUser == null) return Result.error("用户不存在");
        if (!dbUser.getPassword().equals(user.getPassword())) {
            return Result.error("密码不正确");
        }

        // 登陆成功，携带id返回，用于身份验证
        return Result.success(dbUser.getId());
    }

    @Override
    public Result getById(String id) {
        User dbUser = userDao.getById(id);
        if (dbUser == null) {
            return Result.error("用户不存在");
        }
        return Result.success(dbUser);
    }

    private boolean isEmptyString(String str) {
        return str == null || str.isEmpty();
    }

    private boolean isAlphanumericString(String str) {
        return str.matches("^[a-zA-Z0-9]+$");
    }

    private String checkUserInfo(User user) {
        String username = user.getUsername();
        String password = user.getPassword();

        // 判断用户名
        if (isEmptyString(username)) {
            System.out.println("用户名不能为空");
            return "用户名不能为空";
        }
        if (!isAlphanumericString(username)) {
            System.out.println("用户名只能由字母和数字组成");
            return "用户名只能由字母和数字组成";
        }
        if (username.length() > 16) {
            System.out.println("用户名过长，最长16位");
            return "用户名过长，最长16位";
        }

        // 判断密码
        if (isEmptyString(password)) {
            System.out.println("密码不能为空");
            return "密码不能为空";
        }
        if (!isAlphanumericString(password)) {
            System.out.println("密码只能由字母和数字组成");
            return "密码只能由字母和数字组成";
        }
        if (password.length() > 16) {
            System.out.println("密码过长，最长16位");
            return "密码过长，最长16位";
        }

        return null;
    }
}
