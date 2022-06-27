package top.mxzero.common.service;

import top.mxzero.common.PageData;
import top.mxzero.common.model.Product;

import java.util.List;

public interface ProductService extends BaseService<Product, Long>{
    PageData<Product> split(long current, long size);

    PageData<Product> split(long current, long size, Integer categoryId);

    List<Product> list(Integer categoryId);
}
