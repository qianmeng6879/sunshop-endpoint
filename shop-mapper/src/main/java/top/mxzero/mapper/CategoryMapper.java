package top.mxzero.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import top.mxzero.common.model.Category;

@Mapper
public interface CategoryMapper extends BaseMapper<Category> {
}
