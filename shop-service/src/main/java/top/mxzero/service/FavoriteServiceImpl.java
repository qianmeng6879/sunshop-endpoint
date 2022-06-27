package top.mxzero.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.mxzero.common.PageData;
import top.mxzero.common.exception.ServiceException;
import top.mxzero.common.model.Address;
import top.mxzero.common.model.Favorite;
import top.mxzero.common.model.Product;
import top.mxzero.common.service.FavoriteService;
import top.mxzero.common.service.ProductService;
import top.mxzero.mapper.FavoriteMapper;
import top.mxzero.mapper.ProductMapper;

import java.util.Date;
import java.util.List;

@Service
public class FavoriteServiceImpl implements FavoriteService {
    @Autowired
    private FavoriteMapper mapper;

    @Autowired
    private ProductMapper productMapper;

    @Override
    public List<Favorite> list(Long userId) {
        return mapper.findAllByUserId(userId);
    }

    @Override
    public PageData<Favorite> split(long current, long size, Long userId) {
        QueryWrapper<Favorite> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);

        IPage<Favorite> page = new Page<>(current, size);

        mapper.selectPage(page, queryWrapper);

        PageData<Favorite> addressPageData = new PageData<>();
        addressPageData.setCurrent(page.getCurrent());
        addressPageData.setSize(page.getRecords().size());
        addressPageData.setTotal(page.getTotal());
        addressPageData.setData(page.getRecords());

        return addressPageData;
    }

    @Override
    public boolean add(Favorite favorite) {

        QueryWrapper<Favorite> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", favorite.getUserId()).eq("product_id", favorite.getProductId());

        if (mapper.selectCount(wrapper) > 0) {
            throw new ServiceException("该商品已收藏");
        }

        if (!productMapper.exists(new QueryWrapper<Product>().eq("id", favorite.getProductId()))) {
            throw new ServiceException("商品不存在");
        }

        favorite.setCreateTime(new Date(System.currentTimeMillis()));
        return mapper.insert(favorite) > 0;
    }


    @Override
    public boolean remove(Long id, Long userId) {
        return mapper.doRemoveByIdAndUserId(id, userId) > 0;
    }

    @Override
    public long count(Long userId) {
        return mapper.getCountByUserId(userId);
    }
}
