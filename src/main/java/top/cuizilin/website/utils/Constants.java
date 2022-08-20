package top.cuizilin.website.utils;

import org.springframework.beans.factory.annotation.Value;

public class Constants {
    /**
     * SuccessCode
     */
    public static final String successCode = "200";

    /**
     * generalSucceedMessage
      */
    public static final String succeedMessage = "成功";

    /**
     * generalErrorCode
     */
    public static final String generalExceptionCode = "500001";

    /**
     * generalErrorMessage
     */
    public static final String generalExceptionMessage = "未知错误";


    /**
     * userErrorCode
     */

    public static final String usernameExistsCode = "500101";
    public static final String userLoginFailCode = "500102";
    public static final String userNotLoginCode = "500103";

    /**
     * UserMessage
     */
    public static final String usernameExistsMessage = "用户名已经存在";
    public static final String registeredSucceedMessage = "恭喜你，注册成功";
    public static final String loginSucceedMessage = "登录成功";
    public static final String loginFailMessage = "登录失败，用户名或密码错误";
    public static final String userNotLoginMessage = "用户没有登录";
    public static final String userLogoutSuccessMessage = "成功退出";

    /**
     * typeCode
     */
    public static final String typeNameAlreadyExistsCode = "500200";
    public static final String typeNotExistsCode = "500201";


    /**
     * type Message
     */
    public static final String typeNameAlreadyExistsMessage = "该分类名已经存在";
    public static final String addTypeSucceedMessage = "添加分类成功";
    public static final String typeNotExistsMessage = "该分类不存在";


    /**
     * tag Code
     */
    public static final String tagExistsCode = "500301";
    public static final String tagNotExistsCode = "500302";

    /**
     * tag Message
     */

    public static final String tagExistsMessage = "该标签名已经存在";
    public static final String tagNotExistsMessage = "该标签不存在";

    /**
     * articleCode
     */
    public static final String articleNotExistsCode = "500401";

    /**
     * articleMessage
     */
    public static final String articleNotExistsMessage = "文章不存在";

    /**
     * Other Constants
     */
    public static final String avatarUrl = "https://picsum.photos/200/200";
    public static final int defaultTypePageSize = 4;
    public static final int maxSessionInactive = 30 * 60;
    public static final int defaultTagPageSize = 4;
    public static final int belongArticle = 0;
    public static final int belongVideo = 1;
    public static final int belongAll = 2;
    public static final int defaultArticleDescriptionLength = 150;

}
