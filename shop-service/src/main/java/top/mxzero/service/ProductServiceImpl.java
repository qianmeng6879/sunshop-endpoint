package top.mxzero.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.mxzero.common.PageData;
import top.mxzero.common.model.Product;
import top.mxzero.common.service.ProductService;
import top.mxzero.mapper.ProductMapper;

import java.util.Date;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductMapper productMapper;

    @Override
    public Product get(Long id) {
        return productMapper.findById(id);
    }

    @Override
    public PageData<Product> split(long current, long size) {
        List<Product> data = productMapper.findSplit(current, size);
        long total = productMapper.selectCount(null);
        return new PageData<>(current, data.size(), total, data);
    }

    @Override
    public PageData<Product> split(long current, long size, Integer categoryId) {

        List<Product> data = productMapper.findSplitByCategoryId(current, size, categoryId);
        long total = productMapper.getCountByCategoryId(categoryId);

        return new PageData<>(current, data.size(), total, data);
    }

    @Override
    public List<Product> list() {
        return productMapper.findAll();
    }

    @Override
    public List<Product> list(Integer categoryId) {
        if (categoryId == null) {
            return productMapper.findAll();
        }
        return productMapper.findAllByCategoryId(categoryId);
    }

    @Override
    public boolean add(Product product) {
        product.setCreateTime(new Date(System.currentTimeMillis()));
        product.setCode("P1");
        return productMapper.insert(product) > 0;
    }

    @Override
    public boolean remove(Long id) {
        return productMapper.deleteById(id) > 0;
    }

    @Override
    public boolean edit(Product product) {
        product.setUpdateTime(new Date(System.currentTimeMillis()));
        return productMapper.updateById(product) > 0;
    }

    @Override
    public long count() {
        return productMapper.selectCount(null);
    }
}
