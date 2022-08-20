package top.cuizilin.website.service.impl;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import top.cuizilin.website.pojo.User;
import top.cuizilin.website.mapper.UserMapper;
import top.cuizilin.website.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.cuizilin.website.utils.Constants;
import top.cuizilin.website.utils.ObjectUtils;
import top.cuizilin.website.utils.Result;
import top.cuizilin.website.utils.UserUtils;
import top.cuizilin.website.vo.UserCached;
import top.cuizilin.website.vo.UserLogin;
import top.cuizilin.website.vo.UserRegistered;

import javax.servlet.http.HttpSession;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.Blob;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author shardemachael
 * @since 2022-03-05
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    /**
     * 注册接口
     * @param userRegistered
     * @return
     * @throws InvocationTargetException
     * @throws NoSuchMethodException
     * @throws IllegalAccessException
     */
    @Override
    public Result doRegistered(UserRegistered userRegistered)
            throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, IOException {
        Result result = new Result();

        /**
         * 填充用户信息
         */
        User user = new User();
        ObjectUtils.mergeObj(userRegistered, user);

        /**
         * 判断用户是否已经存在
         */

        User checkUser = this.getOne(new QueryWrapper<User>().eq("username", user.getUsername()));

        if(checkUser != null)
            return result.setCode(Constants.usernameExistsCode)
                    .setMessage(Constants.usernameExistsMessage);


        /**
         * 设置用户信息
         */
        user.setPassword(UserUtils.getMD5Password(user.getPassword()));
        user.setUserType(false);
        user.setLoginTime(LocalDateTime.now());
        user.setAvatar(UserUtils.getUserAvatar());
        this.save(user);

        result.setCode(Constants.successCode).setMessage(Constants.registeredSucceedMessage);
        return result;
    }

    /**
     * 登录接口
     * @param userLogin
     * @param session
     * @return
     */
    public Result doLogin(UserLogin userLogin, HttpSession session)
            throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        Result result = new Result();

        String username = userLogin.getUsername();
        String password = UserUtils.getMD5Password(userLogin.getPassword());

        /**
         * 持久层查询
         */
        User user = this.getOne(new QueryWrapper<User>().eq("username", username).
                eq("password", password)
        );

        /**
         * 存储缓存对象
         */
        if(null == user)
           return result.setCode(Constants.userLoginFailCode).setMessage(Constants.loginFailMessage);



        //存储用户缓存到redis中
        UserCached userCached = new UserCached();
        ObjectUtils.mergeObj(user, userCached);

        String userToken = user.getUserId();
        session.setAttribute(userToken, userCached);
        session.setMaxInactiveInterval(Constants.maxSessionInactive);

        result.setCode(Constants.successCode).setMessage(Constants.loginSucceedMessage).setData(userCached);

        return result;
    }

    /**
     * 获取用户头像
     * @param userToken
     * @param session
     * @return
     */
    @Override
    public byte[] getUserAvatar(String userToken, HttpSession session) {
       return this.getOne(new QueryWrapper<User>().eq("user_id", userToken)).getAvatar();
    }

    /**
     * 登录接口
     * @param userToken
     * @param session
     * @return
     */
    @Override
    public Result doLogout(String userToken, HttpSession session) {
        Result result = new Result();
        if(session.getAttribute(userToken) == null)
            return result.setCode(Constants.userNotLoginCode).setMessage(Constants.userNotLoginMessage);

        session.removeAttribute(userToken);

        result.setCode(Constants.successCode).setMessage(Constants.userLogoutSuccessMessage);
        return result;
    }
}
