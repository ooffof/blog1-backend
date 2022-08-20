package top.cuizilin.website.mapper;

import org.apache.ibatis.annotations.Select;
import top.cuizilin.website.pojo.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author shardemachael
 * @since 2022-03-05
 */
public interface UserMapper extends BaseMapper<User> {
    User searchOneByUserId(String userId);
}
