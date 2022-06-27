package top.mxzero.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import top.mxzero.common.enums.OrderState;
import top.mxzero.common.model.Order;

import java.util.List;

@Mapper
public interface OrderMapper extends BaseMapper<Order> {
    List<Order> findAllByUserId(@Param("userId") Long userId);

    List<Order> findAllByUserIdAndState(@Param("userId") Long userId, @Param("state") Integer state);

    List<Order> findAllByState(@Param("state") Integer state);

    List<Order> findSplitByUserId(@Param("current") long current, @Param("size") long size, @Param("userId") Long userId);

    List<Order> findSplitByUserIdAndState(@Param("current") long current, @Param("size") long size, @Param("userId") Long userId, @Param("state") Integer state);

    List<Order> findSplitByState(@Param("current") long current, @Param("size") long size, @Param("state") Integer state);

    long getCountByUserId(@Param("userId") Long userId);

    long getCountByUserIdAndState(@Param("userId") Long userId, @Param("state") Integer state);

    long getCountByState(@Param("state") Integer state);

    boolean doEditState(@Param("orderId") Long orderId, @Param("userId") Long userId, @Param("oldState") Integer oldState, @Param("newState") Integer newState);

}
