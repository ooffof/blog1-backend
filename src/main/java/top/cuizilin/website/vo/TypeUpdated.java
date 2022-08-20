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
public class TypeUpdated {
    @NotNull(message="typeId不能为空")
    @NotBlank(message="typeId不能为空")
    private String typeId;

    @NotNull(message="分类名不能为空")
    @NotBlank(message="分类名不能为空")
    @Size(max=20,message = "分类名不能超过20位")
    private String typeName;
}
