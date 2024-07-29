package com.sanelee.bigevent.controller;

import com.sanelee.bigevent.pojo.Result;
import com.sanelee.bigevent.pojo.User;
import com.sanelee.bigevent.service.UserService;
import com.sanelee.bigevent.utils.JwtUtil;
import com.sanelee.bigevent.utils.Md5Util;
import com.sanelee.bigevent.utils.ThreadLocalUtil;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

import static com.sanelee.bigevent.pojo.Result.error;

/**
 * 用户控制器，负责处理与用户相关的HTTP请求。
 */
@RestController
@RequestMapping("/user")
@Validated
public class UserController {
    /**
     * 用户服务自动注入，用于执行用户相关的业务逻辑。
     */
    @Autowired
    private UserService userService;

    /**
     * 用户注册接口。
     * 校验用户名和密码格式，并检查用户名是否已被占用。
     *
     * @param username 用户名，必须是5到16个非空字符。
     * @param password 密码，必须是5到16个非空字符。
     * @return 注册成功返回成功结果，否则返回错误信息。
     */
    @PostMapping("/register")
    public Result register(@Pattern(regexp = "^\\S{5,16}$") String username, @Pattern(regexp = "^\\S{5,16}$") String password) {
        // 查询用户是否存在
        //查询用户
        User user = userService.findByUserName(username);
        if (user == null) {
            // 注册新用户
            //注册
            userService.register(username, password);
            return Result.success();
        } else {
            return error("用户名已被占用");
        }
    }

    /**
     * 用户登录接口。
     * 校验用户名和密码，并返回登录成功后的token。
     *
     * @param username 用户名，必须是5到16个非空字符。
     * @param password 密码，必须是5到16个非空字符。
     * @return 登录成功返回包含token的成功结果，否则返回错误信息。
     */
    @PostMapping("/login")
    public Result login(@Pattern(regexp = "^\\S{5,16}$") String username, @Pattern(regexp = "^\\S{5,16}$") String password) {
        User user = userService.findByUserName(username);
        if (user == null) {
            return error("用户名错误！");
        }
        // 校验密码
        if (!Md5Util.checkPassword(password, user.getPassword())) {
            return error("密码错误！");
        }
        // 生成并返回token
        //登陆成功
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", user.getId());
        claims.put("username", user.getUsername());
        String token = JwtUtil.genToken(claims);
        return Result.success(token);
    }

    /**
     * 获取当前登录用户信息接口。
     * 从ThreadLocal中获取用户信息并返回。
     *
     * @return 当前登录用户的详细信息。
     */
    @GetMapping("/userInfo")
    public Result<User> userInfo() {
        Map<String,Object> map =ThreadLocalUtil.get();
        String username = (String) map.get("username");
        User user = userService.findByUserName(username);
        return Result.success(user);
    }

    /**
     * 更新用户信息接口。
     * 接收完整的用户对象进行更新。
     *
     * @param user 待更新的用户对象，必须通过验证。
     * @return 更新成功返回成功结果，否则返回错误信息。
     */
    @PutMapping("/update")
    public Result update(@RequestBody @Validated User user) {
        try {
            userService.update(user);
            return Result.success();
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }

    /**
     * 更新用户头像接口。
     * 接收新的头像URL进行更新。
     *
     * @param avatarUrl 新的头像URL，必须是有效的URL。
     * @return 更新成功返回成功结果，否则返回错误信息。
     */
    @PatchMapping("/updateAvatar")
    public Result updateAvatar(@RequestParam @URL String avatarUrl){
        try {
            userService.updateAvatar(avatarUrl);
            return Result.success();
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }

    /**
     * 更新用户密码接口。
     * 校验旧密码，确认新密码和重复密码一致后更新密码。
     *
     * @param params 包含旧密码、新密码和重复密码的Map。
     * @return 更新成功返回成功结果，否则返回错误信息。
     */
    @PatchMapping("/updatePassword")
    public Result updatePassword(@RequestBody Map<String,String> params){
        String oldPwd = params.get("old_pwd");
        String newPwd = params.get("new_pwd");
        String rePwd = params.get("re_pwd");

        // 校验参数完整性
        if (!StringUtils.hasLength(oldPwd) || !StringUtils.hasLength(newPwd) || !StringUtils.hasLength(rePwd)){
            return Result.error("参数错误");
        }
        // 从ThreadLocal中获取当前用户
        Map<String,Object> map =ThreadLocalUtil.get();
        String username = (String) map.get("username");
        User user = userService.findByUserName(username);
        // 校验旧密码
        //判断原密码是否正确
        if (!Md5Util.checkPassword(oldPwd,user.getPassword())){
            return Result.error("原密码错误");
        }
        // 校验新密码和重复密码是否一致
        if (!newPwd.equals(rePwd)){
            return Result.error("两次密码不一致");
        }
        // 更新密码
        //更新密码
        userService.updatePwd(newPwd);
        return Result.success();
    }
}
