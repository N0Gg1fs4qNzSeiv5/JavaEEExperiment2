package xyz.djma.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.djma.domain.User;
import xyz.djma.service.UserService;
import xyz.djma.util.Result;
import xyz.djma.util.ResultCode;
import xyz.djma.util.SessionKeys;

@RestController
@RequestMapping("/users")
@ResponseBody
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("register")
    public Result register(@RequestBody User user) {
        System.out.println("注册");
        return userService.register(user);
    }

    @PostMapping("/login")
    public Result login(@RequestBody User user, HttpSession session) {
        System.out.println("登陆");
        Result result = userService.login(user);
        if (result.getCode().equals(ResultCode.SUCCESS)) {
            System.out.println("登陆成功");
            session.setAttribute(SessionKeys.USER_ID, (String)result.getData());
        }
        return userService.login(user);
    }

    @GetMapping("/{id}")
    public Result getById(@PathVariable String id) {
        System.out.println("获取用户");
        return userService.getById(id);
    }
}
