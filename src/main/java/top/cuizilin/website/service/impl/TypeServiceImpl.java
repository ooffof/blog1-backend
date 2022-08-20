package top.cuizilin.website.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import top.cuizilin.website.pojo.Type;
import top.cuizilin.website.mapper.TypeMapper;
import top.cuizilin.website.service.ITypeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.cuizilin.website.utils.Constants;
import top.cuizilin.website.utils.MyHashMap;
import top.cuizilin.website.utils.ObjectUtils;
import top.cuizilin.website.utils.Result;
import top.cuizilin.website.vo.TypePost;
import top.cuizilin.website.vo.TypeUpdated;

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
public class TypeServiceImpl extends ServiceImpl<TypeMapper, Type> implements ITypeService {

    @Autowired
    private TypeMapper typeMapper;

    /**
     * 添加分类接口
     * @param typePost
     * @return
     */
    @Override
    public Result addType(TypePost typePost) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        Result result = new Result();
        Type type = new Type();
        ObjectUtils.mergeObj(typePost, type);


        Type searchType = this.getOne(new QueryWrapper<Type>().eq("type_name", typePost.getTypeName()));
        /**
         * 判断该分类名是否存在
         */
        if(searchType != null)
            return result.setCode(Constants.typeNameAlreadyExistsCode).
                    setMessage(Constants.typeNameAlreadyExistsMessage);


        type.setCreateTime(LocalDateTime.now());
        this.save(type);

        return result.setCode(Constants.successCode).setMessage(Constants.addTypeSucceedMessage);
    }

    /**
     * 根据页码获取所有分类
     * @param pageNo
     * @return
     */
    @Override
    public Result getAllTypesByPage(Integer pageNo) {
        Result result = new Result();
        Page<Type> page = new Page<>(pageNo, Constants.defaultTypePageSize);
        IPage<Type> typeIPage = typeMapper.selectPage(page, null);

        MyHashMap<String, Object> dataMap = new MyHashMap<>();
        dataMap.putValue("page", typeIPage.getPages()).
                putValue("current", typeIPage.getCurrent()).
                putValue("records", typeIPage.getRecords());

        result.setCode(Constants.successCode).setMessage(Constants.succeedMessage).setData(dataMap);
        return result;
    }


    /**
     * 根据typeId获取分类名
     * @param typeId
     * @return
     */
    @Override
    public Result getTypeNameByTypeId(String typeId) {
        Result result =  new Result();

        Type type = this.getOne(new QueryWrapper<Type>().eq("type_id", typeId));

        if(type == null)
           return result.setCode(Constants.typeNotExistsCode).setMessage(Constants.typeNotExistsMessage);

        result.setCode(Constants.successCode)
                .setMessage(Constants.succeedMessage)
                .setData(type.getTypeName());
        return result;
    }


    /**
     * 根据分类id修改分类名
     * @param
     * @return
     */
    @Override
    public Result updateTypeNameByTypeId(TypeUpdated typeUpdated) {
        Result result = new Result();


        Type type = this.getOne(new QueryWrapper<Type>().eq("type_id", typeUpdated.getTypeId()));
        if(type == null)
            return result.setCode(Constants.typeNotExistsCode)
                    .setMessage(Constants.typeNotExistsMessage);

        this.update(new UpdateWrapper<Type>().eq("type_id", typeUpdated.getTypeId()).set("type_name", typeUpdated.getTypeName()));

        result.setCode(Constants.successCode).setMessage(Constants.succeedMessage);

        return result;
    }

    @Override
    public Result searchByTypeName(String typeName, Integer pageNo) {
        Result result = new Result();


        Page<Type> page = new Page<>(pageNo, Constants.defaultTypePageSize);
        IPage<Type> typeIPage = typeMapper.selectPage(page, new QueryWrapper<Type>().like("type_name", typeName));


        MyHashMap<String, Object> dataMap = new MyHashMap<>();
        dataMap.putValue("page", typeIPage.getPages())
                .putValue("current", typeIPage.getCurrent())
                .putValue("records", typeIPage.getRecords());

        result.setCode(Constants.successCode).setMessage(Constants.succeedMessage).setData(dataMap);

        return result;

    }

    @Override
    public Result deleteTypeByTypeId(String typeId) {
       typeMapper.deleteById(typeId);
       return new Result().setCode(Constants.successCode).setMessage(Constants.succeedMessage);
    }

    @Override
    public Result countAllType() {
       return new Result().setMessage(Constants.succeedMessage)
               .setCode(Constants.succeedMessage)
               .setData(this.count(null));
    }

    @Override
    public Result getAllArticleTypes() {
       return new Result().setCode(Constants.successCode)
               .setMessage(Constants.succeedMessage)
               .setData(typeMapper.selectList(new QueryWrapper<Type>()
                       .in("belong", Arrays.asList(Constants.belongArticle, Constants.belongAll))
               ));
    }


}
