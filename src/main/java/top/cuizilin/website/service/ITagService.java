package top.cuizilin.website.service;

import top.cuizilin.website.pojo.Tag;
import com.baomidou.mybatisplus.extension.service.IService;
import top.cuizilin.website.utils.Result;
import top.cuizilin.website.vo.TagPost;
import top.cuizilin.website.vo.TagUpdated;

import java.lang.reflect.InvocationTargetException;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author shardemachael
 * @since 2022-03-16
 */
public interface ITagService extends IService<Tag> {

    /**
     * 新建标签接口
     * @param tagPost
     * @return
     * @throws InvocationTargetException
     * @throws NoSuchMethodException
     * @throws IllegalAccessException
     */
    Result addTag(TagPost tagPost) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException;

    /**
     * 根据页码分页查询
     */
    Result getAllByPage(Integer pageNo);

    /**
     * 根据标签id查询标签名
     */
    Result getTagNameByTagId(String tagId);

    /**
     * 根据id修改表亲啊名字
     */
    Result updateTagNameByTagId(TagUpdated tagUpdated);

    /**
     * 根据标签名查询所有标签
     */
    Result searchByTagName(String tagName, Integer pageNo);

    /**
     * 根据标签id删除该标签
     */
    Result deleteByTagId(String tagId);

    /**
     * 查询标签数量
     */
    Result countAllTag();

    /**
     * 获取所有标签
     */
    Result getAllArticleTags();
}


