package top.mxzero.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.mxzero.common.exception.ServiceException;
import top.mxzero.common.model.Recharge;
import top.mxzero.common.model.User;
import top.mxzero.common.service.RechargeService;
import top.mxzero.mapper.RechargeMapper;
import top.mxzero.mapper.UserMapper;

import java.util.Date;
import java.util.List;

@Service
public class RechargeServiceImpl implements RechargeService {
    @Autowired
    private RechargeMapper rechargeMapper;

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<Recharge> list() {
        return rechargeMapper.selectList(null);
    }

    @Override
    public List<Recharge> list(Long userId) {
        return rechargeMapper.selectList(new QueryWrapper<Recharge>().eq("user_id", userId));
    }

    @Override
    public boolean recharge(Long userId, Long amount) {
        User user = userMapper.selectById(userId);
        if(user == null){
            throw new ServiceException("用户不存在");
        }

        Recharge recharge = new Recharge();
        recharge.setUserId(userId);
        recharge.setAmount(amount);
        recharge.setOldBalance(user.getBalance());
        recharge.setNewBalance(user.getBalance() + amount);
        recharge.setCreateTime(new Date());
        userMapper.doPlusBalance(userId, amount);
        int result = rechargeMapper.insert(recharge);
        if(result == 0){
            throw new ServiceException("充值失败");
        }
        return result > 0;
    }
}
