package top.mxzero.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import top.mxzero.common.model.Address;

import java.util.List;

@Mapper
public interface AddressMapper extends BaseMapper<Address> {
    Address findById(@Param("id") Long id);

    Address findByIdAndUserId(@Param("id") Long id, @Param("userId") Long userId);

    List<Address> findAllByUserId(@Param("userId") Long userId);

    List<Address> findSplitByUserId(@Param("current") long current, @Param("size") long size, @Param("userId") Long userId);

    int doRemoveByIdAndUserId(@Param("id") Long id, @Param("userId") Long userId);

    long getCountByUserId(@Param("userId") Long userId);
}
