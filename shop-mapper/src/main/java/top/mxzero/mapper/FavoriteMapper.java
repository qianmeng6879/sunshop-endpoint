package top.mxzero.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import top.mxzero.common.model.Favorite;

import java.util.List;

@Mapper
public interface FavoriteMapper extends BaseMapper<Favorite> {
    List<Favorite> findAllByUserId(@Param("userId") Long userId);

    List<Favorite> findSplitByUserId(@Param("current") long current, @Param("size") long size, @Param("userId") Long userId);

    int doRemoveByIdAndUserId(@Param("id") Long id, @Param("userId") Long userId);

    long getCountByUserId(@Param("userId") Long userId);
}
