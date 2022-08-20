package top.cuizilin.website.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import top.cuizilin.website.pojo.Article;
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
public interface ArticleMapper extends BaseMapper<Article> {
    Article searchOneByTypeId(String articleId);

    List<Article> searchAllByTagId(String tagId);

    int saveTagAndArticle(String tagId, String articleId);

    IPage<Article> searchAllByTagList(List<String> tagIds, IPage<Article> iPage,
                                      Integer orderContent, Boolean orderType);

    int deleteByArticleId(String articleId);

    int insertNewArticleTags(String articleId, List<String> tagIds);
}

