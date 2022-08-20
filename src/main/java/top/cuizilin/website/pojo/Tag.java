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
@TableName(value = "t_tag", resultMap = "tagResultMap")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tag implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 标签id
     */
    @TableId(value = "tag_id", type = IdType.ASSIGN_UUID)
    private String tagId;

    /**
     * 标签名
     */
    private String tagName;

    /**
     * 属于，0表示属于文章，1表示属于视频，2表示都属于
     */
    private int belong;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 文章列表
     */
    private List<Article> articleList;

    @Override
    public String toString() {
        return "Tag{" +
                "tagId='" + tagId + '\'' +
                ", tagName='" + tagName + '\'' +
                ", belong=" + belong +
                ", createTime=" + createTime +
                ", articleList=" + articleList +
                '}';
    }
}
