package top.cuizilin.website.controller;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import top.cuizilin.website.service.IArticleService;
import top.cuizilin.website.vo.ArticlePost;

@RestController
@RequestMapping("/api/article")
public class ArticleController {

    @Autowired
    private IArticleService articleService;

    /**
     * flag为true表示转换成html
     * @param articleId
     * @param flag
     * @return
     */
    @GetMapping("/id")
    public String getArticleById(String articleId, @RequestParam(required = false, defaultValue = "true") Boolean flag){
        return JSON.toJSONString(articleService.searchById(articleId, flag));
    }

}
