package top.mxzero.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.mxzero.common.model.Banner;
import top.mxzero.common.service.BannerService;
import top.mxzero.mapper.BannerMapper;


@Service
public class BannerServiceImpl extends ServiceImpl<BannerMapper, Banner> implements BannerService {
}
