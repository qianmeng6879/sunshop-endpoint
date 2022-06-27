package top.mxzero.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import top.mxzero.common.model.Product;

import java.util.List;

@Mapper
public interface ProductMapper extends BaseMapper<Product> {

    Product findById(Long id);

    List<Product> findAll();

    List<Product> findAllByCategoryId(Integer categoryId);

    List<Product> findSplit(@Param("current") long current, @Param("size") long size);

    List<Product> findSplitByCategoryId(@Param("current") long current, @Param("size") long size, @Param("categoryId") Integer categoryId);

    long getCountByCategoryId(@Param("categoryId") Integer categoryId);

    int toSub(@Param("productId") Long productId, @Param("quantity") long quantity);

    int toPlus(@Param("productId") Long productId, @Param("quantity") long quantity);

}
