package top.cuizilin.website.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TagUpdated {
    @NotNull(message = "标签id不能为空")
    @NotBlank(message = "标签id不能为空")
    private String tagId;

    @NotNull(message = "标签名不能为空")
    @NotBlank(message = "标签名不能为空")
    @Size(message = "标签名必须在0-20位之间", max = 20)
    private String tagName;
}
