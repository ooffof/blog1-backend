package top.cuizilin.website.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Blob;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author shardemachael
 * @since 2022-03-05
 */
@TableName("t_user")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户id，主键，用户的唯一标识
     */
    @TableId(value = "user_id", type = IdType.ASSIGN_UUID)
    private String userId;

    /**
     * 用户名,唯一
     */
    private String username;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 1代表管理员，0代表普通用户
     */
    private Boolean userType;

    /**
     * 邮箱
     */
    private String email;

    private LocalDateTime loginTime;

    /**
     * 用户昵称
     */
    private String nickname;

    /**
     * 用户qq
     */
    private String qq;

    /**
     * 用户头像
     */
    private byte[] avatar;

    @Override
    public String toString() {
        return "User{" +
            "userId=" + userId +
            ", username=" + username +
            ", password=" + password +
            ", userType=" + userType +
            ", email=" + email +
            ", loginTime=" + loginTime +
            ", nickname=" + nickname +
            ", qq=" + qq +
            ", avatar=" + avatar +
        "}";
    }
}
