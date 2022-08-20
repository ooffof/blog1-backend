package top.cuizilin.website.vo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TagPost {

    @NotNull(message = "标签名不能为空")
    @NotBlank(message = "标签名不能为空")
    @Size(max = 20, message = "标签名不能超过20位")
    private String tagName;

    @NotNull(message = "归属不能为空")
    @Min(value = 0, message = "归属必须在0-2之间")
    @Max(value = 2, message = "归属必须在0-2之间")
    private int belong;
}

