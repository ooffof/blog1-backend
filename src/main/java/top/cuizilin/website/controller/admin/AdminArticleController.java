package top.cuizilin.website.controller.admin;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import top.cuizilin.website.service.IArticleService;
import top.cuizilin.website.vo.ArticlePost;
import top.cuizilin.website.vo.ArticleQuery;

import javax.validation.constraints.NotNull;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author shardemachael
 * @since 2022-03-13
 */
@RestController
@RequestMapping("/api/admin/article")
public class AdminArticleController {

    @Autowired
    private IArticleService articleService;

    @PostMapping("/post")
    public String postArticle(@RequestBody @Validated ArticlePost articlePost) throws Exception{
        return JSON.toJSONString(articleService.addArticle(articlePost));
    }

    @PostMapping("/search/condition")
    public String searchArticle(@RequestBody ArticleQuery articleQuery){
       return JSON.toJSONString(articleService.searchByCondition(articleQuery), SerializerFeature.DisableCircularReferenceDetect);
    }

    @GetMapping("/delete")
    public String deleteArticleById(String articleId){
        return JSON.toJSONString(articleService.deleteById(articleId));
    }

    @PostMapping("/update")
    public String updateArticle(@RequestBody @Validated ArticlePost articlePost){
        return JSON.toJSONString(articleService.updateArticle(articlePost));
    }
}
