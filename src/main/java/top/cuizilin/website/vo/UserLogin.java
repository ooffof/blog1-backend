package top.cuizilin.website.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserLogin {
    @NotNull(message="用户名不能为空")
    @NotBlank(message = "用户名不能为空")
    @Size(max=40, message = "用户名不能超过40位")
    private String username;

    @NotBlank(message = "密码不能为空")
    @NotNull(message = "密码不能为空")
    @Size(max=32, message = "密码不能超过32位")
    private String password;

    private String userToken;
}
