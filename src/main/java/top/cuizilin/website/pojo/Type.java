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
@TableName(value = "t_type", resultMap = "typeResultMap")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Type implements Serializable{

    private static final long serialVersionUID = 1L;

    /**
     * 类型id
     */
    @TableId(value="type_id", type= IdType.ASSIGN_UUID)
    private String typeId;

    /**
     * 类型name
     */
    private String typeName;

    /**
     * 属于，0表示属于文章，1表示属于视频，2表示都属于
     */
    private int belong;

    private LocalDateTime createTime;

    private List<Article> articleList;

    @Override
    public String toString() {
        return "Type{" +
                "typeId='" + typeId + '\'' +
                ", typeName='" + typeName + '\'' +
                ", belong=" + belong +
                ", createTime=" + createTime +
                ", articleList=" + articleList +
                '}';
    }
}
