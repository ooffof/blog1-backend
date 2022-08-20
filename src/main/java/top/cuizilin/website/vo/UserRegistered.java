package top.cuizilin.website.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRegistered {
    @NotNull(message = "用户名不能为空")
    @NotBlank(message = "用户名不能为空")
    @Size(min=6, max=40, message = "用户名必须在6到40位之间")
    private String username;

    @NotNull(message = "密码不能为空")
    @NotBlank(message = "密码不能为空")
    @Size(min=8, message = "密码必须在超过8在32位之间")
    private String password;

    @NotNull(message = "邮箱不能为空")
    @NotBlank(message = "邮箱不能为空")
    @Email(message = "邮箱不合法")
    private String email;

    @NotNull(message = "昵称不能为空")
    @NotBlank(message = "昵称不能为空")
    @Size(max=50, message = "昵称不能超过50")
    private String nickname;
}
