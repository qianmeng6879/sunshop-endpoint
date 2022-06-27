package top.mxzero.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.mxzero.common.model.Product;
import top.mxzero.common.service.ProductService;
import top.mxzero.common.vo.ProductVO;
import top.mxzero.jwt.annotation.JWTAuthentication;

import javax.validation.Valid;

@RestController
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/products/{id}")
    public Object getProduct(@PathVariable("id") long id){
        return productService.get(id);
    }

    @GetMapping("/products/list")
    public Object listProduct() {
        return productService.list();
    }

    @GetMapping("/products/split")
    public Object splitProduct(@RequestParam(value = "current", defaultValue = "0") long current,
                               @RequestParam(value = "size", defaultValue = "10") long size) {
        return productService.split(current, size);
    }

    @GetMapping("/products/category/{id}/list")
    public Object listProductByCategoryId(@PathVariable("id") int categoryId) {
        return productService.list(categoryId);
    }

    @GetMapping("/products/category/{id}/split")
    public Object splitProductByCategoryId(
            @RequestParam(value = "current", defaultValue = "0") long current,
            @RequestParam(value = "size", defaultValue = "10") long size,
            @PathVariable("id") int categoryId
    ) {
        return productService.split(current, size, categoryId);
    }

    @JWTAuthentication(role = "staff")
    @PostMapping("/products/create")
    public Object createProduct(@Valid @RequestBody ProductVO productVO) {

        Product product = new Product();
        BeanUtils.copyProperties(productVO, product);

        return productService.add(product);
    }

    @JWTAuthentication(role = "staff")
    @PutMapping("/products/edit/{id}")
    public Object editProduct(@PathVariable("id") long id, @Valid @RequestBody ProductVO productVO) {

        Product product = new Product();
        BeanUtils.copyProperties(productVO, product);
        product.setId(id);
        return productService.edit(product);
    }

    @JWTAuthentication(role = "staff")
    @DeleteMapping("/products/remove/{id}")
    public Object removeProduct(@PathVariable("id") long id) {
        return productService.remove(id);
    }

}
