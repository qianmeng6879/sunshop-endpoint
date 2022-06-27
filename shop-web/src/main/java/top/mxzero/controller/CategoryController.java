package top.mxzero.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;
import top.mxzero.common.model.Category;
import top.mxzero.common.service.CategoryService;
import top.mxzero.jwt.annotation.JWTAuthentication;

import javax.validation.Valid;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
public class CategoryController {

    @Autowired
    private CategoryService service;

    @Autowired
    private StringRedisTemplate redisTemplate;


    @GetMapping("/categories/list")
    public Object listCategory() {
        QueryWrapper<Category> categoryQueryWrapper = new QueryWrapper<>();
        categoryQueryWrapper.isNull("parent_id");

        String categories = redisTemplate.opsForValue().get("categories");
        if(categories != null){
            return  JSONObject.parseObject(categories, List.class);
        }

        List<Category> list = service.list(categoryQueryWrapper);
        for (Category category : list) {
            QueryWrapper<Category> wrapper = new QueryWrapper<>();
            wrapper.eq("parent_id", category.getId());
            category.setSubs(service.list(wrapper));
        }

        redisTemplate.opsForValue().set("categories", JSONObject.toJSONString(list), 30, TimeUnit.MINUTES);

        return list;
    }

    @JWTAuthentication(role = "staff")
    @PostMapping("/categories/create")
    public Object createCategory(@RequestBody Category category) {
        return service.save(category);
    }

    @JWTAuthentication(role = "staff")
    @PutMapping("/categories/edit/{id}")
    public Object editCategory(@PathVariable("id") Integer id, @Valid @RequestBody Category category) {
        category.setId(id);
        return service.updateById(category);
    }

    @JWTAuthentication(role = "staff")
    @DeleteMapping("/categories/remove/{id}")
    public Object removeCategory(@PathVariable("id") Integer id) {
        return service.removeById(id);
    }

}
