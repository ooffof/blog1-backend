package top.cuizilin.website.service;

import top.cuizilin.website.pojo.Type;
import com.baomidou.mybatisplus.extension.service.IService;
import top.cuizilin.website.utils.Result;
import top.cuizilin.website.vo.TypePost;
import top.cuizilin.website.vo.TypeUpdated;

import java.lang.reflect.InvocationTargetException;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author shardemachael
 * @since 2022-03-16
 */
public interface ITypeService extends IService<Type> {
    /**
     * 新建分类接口
     */
    Result addType(TypePost typePost) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException;

    /**
     * 根据页码查询所有分类
     */
    Result getAllTypesByPage(Integer pageNo);

    /**
     * 根据分类id获取分类名
     */
    Result getTypeNameByTypeId(String typeId);

    /**
     * 根据分类id修改分类名
     */
    Result updateTypeNameByTypeId(TypeUpdated typeUpdated);

    /**
     * 根据分类名查询所有分类
     */
    Result searchByTypeName(String typeName, Integer pageNo);

    /**
     * 根据分类id 删除分类
     */
    Result deleteTypeByTypeId(String typeId);

    /**
     * 查询分类个数
     */
    Result countAllType();

    /**
     * 获取所有的分裂 不分页
     */
    Result getAllArticleTypes();
}
