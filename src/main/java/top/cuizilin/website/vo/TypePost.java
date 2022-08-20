package top.cuizilin.website.vo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TypePost {
    @NotNull
    @NotBlank
    @Size(min=0, max=20, message = "分类不能超过30位")
    private String typeName;

    @NotNull
    @Min(value = 0, message = "归属必须在0-2之间")
    @Max(value = 2, message = "归属必须在0-2之间")
    private int belong;
}
