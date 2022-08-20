package top.cuizilin.website.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import top.cuizilin.website.pojo.Tag;
import top.cuizilin.website.mapper.TagMapper;
import top.cuizilin.website.service.ITagService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.cuizilin.website.utils.Constants;
import top.cuizilin.website.utils.MyHashMap;
import top.cuizilin.website.utils.ObjectUtils;
import top.cuizilin.website.utils.Result;
import top.cuizilin.website.vo.TagPost;
import top.cuizilin.website.vo.TagUpdated;

import java.lang.reflect.InvocationTargetException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author shardemachael
 * @since 2022-03-16
 */
@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements ITagService {


    @Autowired
    private TagMapper tagMapper;
    /**
     * 添加标签接口
     * @param tagPost
     * @return
     * @throws InvocationTargetException
     * @throws NoSuchMethodException
     * @throws IllegalAccessException
     */
    @Override
    public Result addTag(TagPost tagPost)
            throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        Result result = new Result();

        Tag searchTag = this.getOne(new QueryWrapper<Tag>().eq("tag_name", tagPost.getTagName()));
        if (searchTag != null)
            return result.setCode(Constants.tagExistsCode).setMessage(Constants.tagExistsMessage);

        Tag tag = new Tag();
        ObjectUtils.mergeObj(tagPost, tag);
        tag.setCreateTime(LocalDateTime.now());
        this.save(tag);

        result.setCode(Constants.successCode).setMessage(Constants.succeedMessage);
        return result;
    }

    /**
     * 按照页码查询全部分类
     * @param pageNo
     * @return
     */
    @Override
    public Result getAllByPage(Integer pageNo) {
       Result result = new Result();

       Page<Tag> tagPage = new Page<>(pageNo, Constants.defaultTagPageSize);
       IPage<Tag> tagIPage = tagMapper.selectPage(tagPage, null);

       MyHashMap<String, Object> dataMap = new MyHashMap<>();
       dataMap.putValue("page", tagIPage.getPages())
               .putValue("current", tagIPage.getCurrent())
               .putValue("records", tagIPage.getRecords());

       result.setCode(Constants.successCode).setMessage(Constants.succeedMessage).setData(dataMap);
       return result;
    }

    /**
     * 根据标签id获取标签名
     * @param tagId
     * @return
     */
    @Override
    public Result getTagNameByTagId(String tagId) {
        Result result = new Result();

        Tag searchTag = this.getOne(new QueryWrapper<Tag>().eq("tag_id", tagId));
        if(searchTag == null)
            return result.setCode(Constants.tagNotExistsCode).setMessage(Constants.tagNotExistsMessage);

        result.setCode(Constants.successCode).setMessage(Constants.succeedMessage).setData(searchTag.getTagName());
        return result;
    }

    /**
     * 根据标签id更新标签名
     * @param tagUpdated
     * @return
     */
    @Override
    public Result updateTagNameByTagId(TagUpdated tagUpdated) {
        Result result = new Result();

        Tag searchTag = this.getOne(new QueryWrapper<Tag>().eq("tag_id", tagUpdated.getTagId()));
        if(searchTag == null)
            return result.setCode(Constants.tagNotExistsCode).setMessage(Constants.tagNotExistsMessage);

        this.update(new UpdateWrapper<Tag>()
                .eq("tag_id", tagUpdated.getTagId())
                .set("tag_name", tagUpdated.getTagName())
        );

        return result.setCode(Constants.successCode).setMessage(Constants.succeedMessage);
    }

    /**
     * 根据标签名和页码分页搜索所有标签
     * @param tagName
     * @param pageNo
     * @return
     */
    @Override
    public Result searchByTagName(String tagName, Integer pageNo) {
        Result result = new Result();

        Page<Tag> tagPage = new Page<>(pageNo, Constants.defaultTagPageSize);
        IPage<Tag> tagIPage = tagMapper.selectPage(tagPage, new QueryWrapper<Tag>().like("tag_name", tagName));

        MyHashMap<String, Object> dataMap = new MyHashMap<>();
        dataMap.putValue("page", tagIPage.getPages())
                .putValue("current", tagIPage.getCurrent())
                .putValue("records", tagIPage.getRecords());

        result.setCode(Constants.successCode).setMessage(Constants.succeedMessage).setData(dataMap);
        return result;
    }

    @Override
    public Result deleteByTagId(String tagId) {
        tagMapper.deleteById(tagId);
        return new Result().setCode(Constants.successCode).setMessage(Constants.succeedMessage);
    }

    @Override
    public Result countAllTag() {
       return new Result().setCode(Constants.successCode)
               .setMessage(Constants.succeedMessage)
               .setData(this.count(null));
    }

    @Override
    public Result getAllArticleTags() {
       return new Result().setCode(Constants.successCode)
               .setMessage(Constants.succeedMessage)
               .setData(tagMapper.selectList(new QueryWrapper<Tag>().in("belong",
                       Arrays.asList(Constants.belongArticle, Constants.belongAll))
               ));
    }
}
