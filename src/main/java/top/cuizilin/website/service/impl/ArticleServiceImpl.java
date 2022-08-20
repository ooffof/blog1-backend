package top.cuizilin.website.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import top.cuizilin.website.pojo.Article;
import top.cuizilin.website.mapper.ArticleMapper;
import top.cuizilin.website.service.IArticleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.cuizilin.website.utils.*;
import top.cuizilin.website.vo.ArticlePost;
import top.cuizilin.website.vo.ArticleQuery;

import java.lang.reflect.InvocationTargetException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author shardemachael
 * @since 2022-03-16
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements IArticleService {

    @Autowired
    private ArticleMapper articleMapper;


    /**
     * 新增文章接口
     *
     * @param articlePost
     * @return
     */
    @Override
    public Result addArticle(ArticlePost articlePost) throws Exception {
        Article article = new Article();
        ObjectUtils.mergeObj(articlePost, article);
        article.setCreateTime(LocalDateTime.now());
        article.setUpdateTime(LocalDateTime.now());
        article.setGoodNo(0);
        article.setViewNo(0);

        if(articlePost.getDescription().length() == 0){
            if(articlePost.getContent().length() >= 150)
               article.setDescription(article.getContent().substring(0, Constants.defaultArticleDescriptionLength));
            else
                article.setDescription(article.getContent());
        }

        this.save(article);

        for (String tagId : articlePost.getTagIds().split(",")) {
            articleMapper.saveTagAndArticle(tagId, article.getArticleId());
        }
        return new Result().setCode(Constants.successCode).setMessage(Constants.succeedMessage);
    }


    @Override
    public Result searchByCondition(ArticleQuery articleQuery) {
       Result result = new Result();

       Page<Article> page = new Page<>(articleQuery.getPageNo(), articleQuery.getPageSize());
       QueryWrapper<Article> queryWrapper = new QueryWrapper<>();
       MyHashMap<String, Object> dataMap = new MyHashMap<>();
       IPage<Article> resultPage = null;

       switch (articleQuery.getOrderContent()){
           case 0:
               //true为降序，false为升序
               queryWrapper.orderBy(true, articleQuery.getOrderType(), "create_time");
               break;
           case 1:
               queryWrapper.orderBy(true, articleQuery.getOrderType(), "view_no");
               break;
           case 2:
               queryWrapper.orderBy(true, articleQuery.getOrderType(), "good_no");
               break;
       }

       if(articleQuery.getSearchType() == 0){
           //根据标题搜索
           queryWrapper.like("title", articleQuery.getSearchContent());
           resultPage = articleMapper.selectPage(page, queryWrapper);
       }else if(articleQuery.getSearchType() == 1){
           //根据分类搜索
           queryWrapper.eq("type_id", articleQuery.getSearchContent());
           resultPage = articleMapper.selectPage(page, queryWrapper);
       }else if(articleQuery.getSearchType() == 2){
           List<String> tagIds = Arrays.asList(articleQuery.getSearchContent().split(","));
           resultPage = articleMapper.searchAllByTagList(tagIds, page,
                   articleQuery.getOrderContent(), articleQuery.getOrderType());
       }
       dataMap.putValue("totalPage", resultPage.getPages())
               .putValue("records", resultPage.getRecords())
               .putValue("current", resultPage.getCurrent());

       return result.setCode(Constants.successCode)
               .setMessage(Constants.succeedMessage)
               .setData(dataMap);
    }

    @Override
    public Result searchById(String articleId, Boolean flag) {
       Result result = new Result();
       Article article = this.getOne(new QueryWrapper<Article>().eq("article_id", articleId));
       if(article == null){
           return new Result().setCode(Constants.articleNotExistsCode)
                   .setMessage(Constants.articleNotExistsMessage);
       }
       if(flag) {
           article.setContent(MarkdownUtils.markdownToHTML(article.getContent()));
           this.update(new UpdateWrapper<Article>().eq("article_id", articleId).set("view_no", article.getViewNo() + 1));
       }

       return result.setCode(Constants.successCode)
               .setMessage(Constants.succeedMessage)
               .setData(article);
    }

    @Override
    public Result deleteById(String articleId) {
        articleMapper.deleteById(articleId);
        return new Result().setCode(Constants.successCode)
                .setMessage(Constants.succeedMessage);
    }

    @Override
    public Result updateArticle(ArticlePost articlePost) {
        Result result = new Result();

        UpdateWrapper<Article> updateWrapper = new UpdateWrapper<>();

        updateWrapper.eq("article_id", articlePost.getArticleId())
                .set("title", articlePost.getTitle())
                .set("content", articlePost.getContent())
                .set("update_time", LocalDateTime.now())
                .set("description", articlePost.getDescription())
                .set("type_id", articlePost.getTypeId());

        this.update(updateWrapper);
        articleMapper.deleteByArticleId(articlePost.getArticleId());
        List<String> tagIds = Arrays.asList(articlePost.getTagIds().split(","));
        articleMapper.insertNewArticleTags(articlePost.getArticleId(), tagIds);

        return result.setCode(Constants.successCode)
                .setMessage(Constants.succeedMessage);
    }


}
