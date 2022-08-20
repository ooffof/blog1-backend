package top.cuizilin.website.controller.admin;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import top.cuizilin.website.service.ITypeService;
import top.cuizilin.website.service.impl.TypeServiceImpl;
import top.cuizilin.website.vo.TypePost;
import top.cuizilin.website.vo.TypeUpdated;

import javax.validation.constraints.Digits;
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
@RequestMapping("/api/type")
@Validated
public class TypeController {

    @Autowired
    private ITypeService typeService;

    @PostMapping("/add")
    public String addType(@RequestBody @Validated TypePost typePost)
            throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        return JSON.toJSONString(typeService.addType(typePost));
    }

    @GetMapping("/list")
    public String getAllTypesByPage(
            @RequestParam(required = false, defaultValue = "1") Integer pageNo){
        return JSON.toJSONString(typeService.getAllTypesByPage(pageNo));
    }

    @GetMapping("/name")
    public String updateTypeName(@NotNull(message = "分类id不能为空") String typeId){
        return JSON.toJSONString(typeService.getTypeNameByTypeId(typeId));
    }

    @PostMapping("/set/name")
    public String setTypeName(@RequestBody @Validated TypeUpdated typeUpdated){
        return JSON.toJSONString(typeService.updateTypeNameByTypeId(typeUpdated));
    }

    @GetMapping("/search")
    public String searchByTypeName(@NotNull(message = "分类名不能为空") String typeName,
                                   @RequestParam(required = false, defaultValue = "1") Integer pageNo){
        return JSON.toJSONString(typeService.searchByTypeName(typeName, pageNo));
    }

    @GetMapping("/delete")
    public String deleteByTypeId(@NotNull(message = "分类id不能为空") String typeId){
        return JSON.toJSONString(typeService.deleteTypeByTypeId(typeId));
    }

    @GetMapping("/count")
    public String countAllType(){
        return JSON.toJSONString(typeService.countAllType());
    }

    @GetMapping("/all")
    public String getAllArticleTypes(){
        return JSON.toJSONString(typeService.getAllArticleTypes());
    }

}
