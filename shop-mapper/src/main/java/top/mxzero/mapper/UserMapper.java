package top.mxzero.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import top.mxzero.common.model.User;

@Mapper
public interface UserMapper extends BaseMapper<User> {

    @Update("update user set balance = balance - #{value} where id = #{userId} and deleted = 0")
    int doSubBalance(@Param("userId") Long userId, @Param("value") double value);

    @Update("update user set balance = balance + #{value} where id = #{userId} and deleted = 0")
    int doPlusBalance(@Param("userId") Long userId, @Param("value") double value);
}

