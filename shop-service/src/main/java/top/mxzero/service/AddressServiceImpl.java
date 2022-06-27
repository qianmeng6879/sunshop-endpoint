package top.mxzero.service;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import top.mxzero.common.PageData;
import top.mxzero.common.model.Address;
import top.mxzero.common.service.AddressService;
import top.mxzero.mapper.AddressMapper;

import java.util.Date;
import java.util.List;


@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressMapper addressMapper;

    @Override
    public List<Address> list(Long userId) {
        return addressMapper.findAllByUserId(userId);
    }

    @Override
    public PageData<Address> split(long current, long size, Long userId) {

        QueryWrapper<Address> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);

        IPage<Address> page = new Page<>(current, size);

        addressMapper.selectPage(page, queryWrapper);

        PageData<Address> addressPageData = new PageData<>();
        addressPageData.setCurrent(page.getCurrent());
        addressPageData.setSize(page.getRecords().size());
        addressPageData.setTotal(page.getTotal());
        addressPageData.setData(page.getRecords());
        return addressPageData;
    }


    @Override
    public Address get(Long id, Long userId) {
        return addressMapper.findByIdAndUserId(id, userId);
    }

    @Override
    public boolean add(Address address) {
        address.setCreateTime(new Date());
        return addressMapper.insert(address) > 0;
    }

    @Override
    public boolean remove(Long addressId, Long userId) {
        return addressMapper.doRemoveByIdAndUserId(addressId, userId) > 0;
    }

    @Override
    public boolean edit(Address address, Long userId) {
        Address currentAddress = addressMapper.findByIdAndUserId(address.getId(), userId);
        if(currentAddress == null){
            return false;
        }

        address.setUpdateTime(new Date());

        return addressMapper.updateById(address) > 0;
    }

    @Override
    public long count(Long userId) {
        return addressMapper.getCountByUserId(userId);
    }
}
