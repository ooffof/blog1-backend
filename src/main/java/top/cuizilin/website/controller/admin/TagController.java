package top.cuizilin.website.controller.admin;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import top.cuizilin.website.service.ITagService;
import top.cuizilin.website.vo.TagPost;
import top.cuizilin.website.vo.TagUpdated;

import javax.validation.constraints.NotNull;
import java.lang.reflect.InvocationTargetException;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author shardemachael
 * @since 2022-03-16
 */
@RestController
@RequestMapping("/api/tag")
@Validated
public class TagController {

    @Autowired
    private ITagService tagService;

    @PostMapping("/add")
    public String addTag(@RequestBody @Validated TagPost tagPost)
            throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        return JSON.toJSONString(tagService.addTag(tagPost));
    }

    @GetMapping("/list")
    public String getAllByPage(@RequestParam(required = false, defaultValue = "1") Integer pageNo){
        return JSON.toJSONString(tagService.getAllByPage(pageNo));
    }

    @GetMapping("/name")
    public String getNameByTagId(@NotNull(message = "标签id不能为空") String tagId){
        return JSON.toJSONString(tagService.getTagNameByTagId(tagId));
    }

    @PostMapping("/set/name")
    public String setTagNameByTagId(@RequestBody @Validated TagUpdated tagUpdated){
        return JSON.toJSONString(tagService.updateTagNameByTagId(tagUpdated));
    }

    @GetMapping("/search")
    public String searchPageByTagName(@NotNull(message = "标签名不能为空") String tagName,
                                      @RequestParam(required = false, defaultValue = "1") Integer pageNo){
        return JSON.toJSONString(tagService.searchByTagName(tagName, pageNo));
    }

    @GetMapping("/delete")
    public String deleteByTagId(@NotNull(message = "标签id不能为空") String tagId){
        return JSON.toJSONString(tagService.deleteByTagId(tagId));
    }

    @GetMapping("/count")
    public String countAllTag(){
        return JSON.toJSONString(tagService.countAllTag());
    }

    @GetMapping("/all")
    public String getAllArticleTags(){
        return JSON.toJSONString(tagService.getAllArticleTags());
    }

}
