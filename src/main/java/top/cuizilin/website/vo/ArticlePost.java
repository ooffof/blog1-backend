package top.cuizilin.website.vo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticlePost{

    private String articleId;

    @NotNull(message = "标题不能为空")
    @NotBlank(message = "标题不能为空")
    @Size(max = 50, message = "标题不能超过50位")
    private String title;
    @NotNull(message = "内容不能为空")
    @NotBlank(message = "内容不能为空")
    private String content;

    private Boolean isPublished;

    @NotNull(message = "类型不能为空")
    @NotBlank(message = "类型不能为空")
    @Size(max = 50, message = "类型id不能超过50位")
    private String typeId;

    private String description;

    @NotNull(message = "用户id不能为空")
    @NotBlank(message = "用户id不能为空")
    @Size(max = 50, message = "用户id不能超过50位")
    private String userId;

    @NotNull(message = "标签不能为空")
    @NotBlank(message = "标签不能为空")
    private String tagIds;
}
