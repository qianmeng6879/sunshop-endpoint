package top.mxzero.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.mxzero.common.model.Image;
import top.mxzero.common.service.ImageService;
import top.mxzero.mapper.ImageMapper;

@Service
public class ImageServiceImpl extends ServiceImpl<ImageMapper, Image> implements ImageService {
}
