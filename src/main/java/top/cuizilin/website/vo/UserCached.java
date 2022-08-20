package top.cuizilin.website.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 用户缓存对象
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCached implements Serializable {
    private String userId;
    private String username;
    private Boolean userType;
    private String email;
    private String nickname;
    private String qq;
    private LocalDateTime loginTime;
}
