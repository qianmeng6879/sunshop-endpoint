package top.mxzero.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.mxzero.common.PageData;
import top.mxzero.common.exception.ServiceException;
import top.mxzero.common.model.User;
import top.mxzero.common.service.UserService;
import top.mxzero.mapper.UserMapper;

import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User get(Long id) {
        return userMapper.selectById(id);
    }

    @Override
    public List<User> list() {
        return userMapper.selectList(null);
    }

    @Override
    public PageData<User> split(long current, long size) {
        IPage<User> page = new Page<>(current, size);
        userMapper.selectPage(page,null);

        PageData<User> pageData = new PageData<>();
        pageData.setCurrent(page.getCurrent());
        pageData.setSize(page.getRecords().size());
        pageData.setTotal(page.getTotal());
        pageData.setData(page.getRecords());

        return pageData;
    }

    @Override
    public boolean add(User model) {
        model.setCreateTime(new Date());

        if(this.getByUsername(model.getUsername()) != null){
            throw new ServiceException("用户名已存在");
        }

        if(model.getNickname() == null || "".equals(model.getNickname().trim())){
            model.setNickname(model.getUsername());
        }

        return userMapper.insert(model) > 0;
    }

    @Override
    public boolean remove(Long id) {
        return userMapper.deleteById(id) > 0;
    }

    @Override
    public boolean edit(User model) {
        model.setUpdateTime(new Date());
        return userMapper.updateById(model) > 0;
    }

    @Override
    public long count() {
        return userMapper.selectCount(null);
    }

    @Override
    public User getByUsername(String username) {
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("username", username);
        return userMapper.selectOne(userQueryWrapper);
    }
}
