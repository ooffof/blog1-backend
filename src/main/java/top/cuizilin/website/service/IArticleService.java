package top.cuizilin.website.service;

import top.cuizilin.website.pojo.Article;
import com.baomidou.mybatisplus.extension.service.IService;
import top.cuizilin.website.utils.Result;
import top.cuizilin.website.vo.ArticlePost;
import top.cuizilin.website.vo.ArticleQuery;

import java.lang.reflect.InvocationTargetException;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author shardemachael
 * @since 2022-03-16
 */
public interface IArticleService extends IService<Article> {

    /**
     * 新增文章接口
     */
    Result addArticle(ArticlePost articlePost) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, Exception;



    /**
     * 根据给定搜索条件搜索文章
     */
    Result searchByCondition(ArticleQuery articleQuery);

    /**
     * 根据id获取文章并且自增
     */
    Result searchById(String articleId, Boolean flag);

    /**
     * 根据id删除该文章
     */
    Result deleteById(String articleId);

    /**
     * 更新文章
     */
    Result updateArticle(ArticlePost articlePost);
}
