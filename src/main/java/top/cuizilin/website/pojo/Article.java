package top.cuizilin.website.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 
 * </p>
 *
 * @author shardemachael
 * @since 2022-03-16
 */
@TableName(value = "t_article", resultMap = "articleResultMap")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Article implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 文章id，主键唯一
     */
    @TableId(value = "article_id", type = IdType.ASSIGN_UUID)
    private String articleId;

    /**
     * 文章标题
     */
    private String title;

    /**
     * 文章内容
     */
    private String content;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 是否发布，0为暂时存储，1为发布
     */
    private Boolean isPublished;

    /**
     * 类型id
     */
    private String typeId;

    private String userId;

    /**
     * 简介
     */
    private String description;

    /**
     * 观看数
     */
    private Integer viewNo;

    /**
     * 点赞
     */
    private Integer goodNo;

    /**
     * 文章类型
     */
    private Type type;
    /**
     * 用户
     */
    private User user;

    /**
     * 标签
     */
    private List<Tag> tagList;

    @Override
    public String toString() {
        return "Article{" +
                "articleId='" + articleId + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", isPublished=" + isPublished +
                ", typeId='" + typeId + '\'' +
                ", userId='" + userId + '\'' +
                ", description='" + description + '\'' +
                ", viewNo=" + viewNo +
                ", goodNo=" + goodNo +
                ", type=" + type +
                ", user=" + user +
                ", tagList=" + tagList +
                '}';
    }
}
