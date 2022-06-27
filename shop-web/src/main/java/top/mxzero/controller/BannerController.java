package top.mxzero.controller;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;
import top.mxzero.common.model.Banner;
import top.mxzero.common.model.Image;
import top.mxzero.common.service.BannerService;
import top.mxzero.common.service.ImageService;
import top.mxzero.jwt.annotation.JWTAuthentication;

import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
public class BannerController {
    @Autowired
    private BannerService service;

    @Autowired
    private ImageService imageService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @GetMapping("/banners/list")
    public Object listBanner() {
        String result = redisTemplate.opsForValue().get("banners");

        if(result != null){
            return JSONObject.parseObject(result, List.class);
        }

        List<Banner> banners = service.list();
        banners.forEach(banner -> {
            Image image = imageService.getById(banner.getImageId());
            banner.setImage(image);
        });

        redisTemplate.opsForValue().set("banners", JSONObject.toJSONString(banners, SerializerFeature.WriteDateUseDateFormat), 30, TimeUnit.MINUTES);

        return banners;
    }

    @JWTAuthentication(role = "staff")
    @PostMapping("/banners/create")
    public Object createBanner(@RequestBody Banner banner) {
        return service.save(banner);
    }

    @JWTAuthentication(role = "staff")
    @DeleteMapping("/banners/remove/{id}")
    public Object removeBanner(@PathVariable("id") int id) {
        return service.removeById(id);
    }
}
