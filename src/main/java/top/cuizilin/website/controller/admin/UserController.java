package top.cuizilin.website.controller.admin;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import top.cuizilin.website.config.URLConfig;
import top.cuizilin.website.service.IUserService;
import top.cuizilin.website.vo.UserLogin;
import top.cuizilin.website.vo.UserRegistered;

import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author shardemachael
 * @since 2022-03-05
 */
@RestController
@RequestMapping("/api/user")
@Validated
public class UserController {

    @Autowired
    private IUserService userService;

    @PostMapping("/registered")
    public String registered(@RequestBody @Validated UserRegistered userRegistered)
            throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, IOException {
        return JSON.toJSONString(userService.doRegistered(userRegistered));
    }

    @PostMapping("/login")
    public String login(@RequestBody @Validated UserLogin userLogin, HttpSession session)
            throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
       return JSON.toJSONString(userService.doLogin(userLogin, session));
    }

    @GetMapping(value = "/avatar", produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] getUserAvatar(@NotNull(message = "用户id不能为空") String userToken, HttpSession session){
         return userService.getUserAvatar(userToken, session);
    }
    @GetMapping(value = "/logout")
    public String logout(@NotNull(message = "用户id不能为空") String userToken, HttpSession session){
        return JSON.toJSONString(userService.doLogout(userToken, session));
    }

}
