package top.cuizilin.website.mapper;

import top.cuizilin.website.pojo.Tag;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author shardemachael
 * @since 2022-03-16
 */
public interface TagMapper extends BaseMapper<Tag> {
    List<Tag> searchAllByArticleId(String articleId);
}
