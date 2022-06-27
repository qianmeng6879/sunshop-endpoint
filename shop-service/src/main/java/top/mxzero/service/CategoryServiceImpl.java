package top.mxzero.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.mxzero.common.model.Category;
import top.mxzero.common.service.CategoryService;
import top.mxzero.mapper.CategoryMapper;

@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements  CategoryService {
}
