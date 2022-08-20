package top.cuizilin.website.service;

import top.cuizilin.website.pojo.User;
import com.baomidou.mybatisplus.extension.service.IService;
import top.cuizilin.website.utils.Result;
import top.cuizilin.website.vo.UserLogin;
import top.cuizilin.website.vo.UserRegistered;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author shardemachael
 * @since 2022-03-05
 */
public interface IUserService extends IService<User> {

    /**
     * 注册接口
     * @return
     */
    Result doRegistered(UserRegistered userRegistered) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, IOException;

    /**
     * 登录接口
     * @return
     */
    Result doLogin(UserLogin userLogin, HttpSession session) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException;

    /**
     * 获取用户头像
     */
    byte[] getUserAvatar(String userToken, HttpSession session);

    /**
     * 用户退出
     */
    Result doLogout(String userToken, HttpSession session);
}
