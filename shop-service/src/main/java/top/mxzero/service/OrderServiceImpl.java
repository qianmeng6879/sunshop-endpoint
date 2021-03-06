package top.mxzero.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.mxzero.common.PageData;
import top.mxzero.common.enums.OrderState;
import top.mxzero.common.exception.ServiceException;
import top.mxzero.common.model.Address;
import top.mxzero.common.model.Order;
import top.mxzero.common.model.Product;
import top.mxzero.common.model.User;
import top.mxzero.common.service.OrderService;
import top.mxzero.mapper.AddressMapper;
import top.mxzero.mapper.OrderMapper;
import top.mxzero.mapper.ProductMapper;
import top.mxzero.mapper.UserMapper;

import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private AddressMapper addressMapper;

    @Autowired
    private UserMapper userMapper;

    @Override
    public Order get(Long id) {
        return orderMapper.selectById(id);
    }

    @Override
    public List<Order> list() {
        return orderMapper.selectList(null);
    }

    @Override
    public PageData<Order> split(long current, long size) {
        IPage<Order> page = new Page<>(current, size);
        orderMapper.selectPage(page, null);

        return new PageData<>(current, page.getRecords().size(), page.getTotal(), page.getRecords());
    }

    @Override
    public boolean add(Order model) {
        return false;
    }

    @Override
    public boolean remove(Long id) {
        return false;
    }

    @Override
    public boolean edit(Order model) {
        return false;
    }

    @Override
    public long count() {
        return orderMapper.selectCount(null);
    }

    @Override
    public List<Order> list(Long userId) {
        return orderMapper.findAllByUserId(userId);
    }

    @Override
    public List<Order> list(Long userId, OrderState status) {
        return orderMapper.findAllByUserIdAndState(userId, status.getValue());
    }

    @Override
    public List<Order> list(OrderState status) {
        return orderMapper.findAllByState(status.getValue());
    }

    @Override
    public List<Order> split(long current, long size, Long userId) {
        return null;
    }

    @Override
    public List<Order> split(long current, long size, Long userId, OrderState status) {
        return null;
    }

    @Override
    public List<Order> split(long current, long size, OrderState status) {
        return null;
    }

    @Override
    public long count(Long userId) {
        return orderMapper.getCountByUserId(userId);
    }

    @Override
    public long count(Long userId, OrderState status) {
        return orderMapper.getCountByUserIdAndState(userId, status.getValue());
    }

    @Override
    public long count(OrderState status) {
        return orderMapper.getCountByState(status.getValue());
    }

    @Override
    public boolean create(Order model) {
        Product product = productMapper.selectById(model.getProductId());

        if (product == null) {
            throw new ServiceException("???????????????");
        }

        if (product.getStock() < model.getQuantity()) {
            throw new ServiceException("??????????????????");
        }

        if (!addressMapper.exists(new QueryWrapper<Address>().eq("id", model.getAddressId()))) {
            throw new ServiceException("?????????????????????");
        }

        model.setCreateTime(new Date());
        model.setCode("M" + System.currentTimeMillis());
        model.setState(OrderState.unpaid);
        model.setPrice(product.getToPrice() * model.getQuantity());

        int createOrderResult = orderMapper.insert(model);

        if (createOrderResult == 0) {
            throw new ServiceException("??????????????????");
        }

        // ????????????
        int subStockResult = productMapper.toSub(model.getProductId(), model.getQuantity());

        if (subStockResult == 0) {
            throw new ServiceException("??????????????????");
        }

        return createOrderResult > 0 && subStockResult > 0;
    }

    @Override
    public boolean pay(Long orderId, Long userId) {
        Order order = orderMapper.selectById(orderId);
        if (order == null) {
            throw new ServiceException("???????????????");
        }
        if (order.getState() != OrderState.unpaid) {
            throw new ServiceException("????????????????????????");
        }

        User user = userMapper.selectById(userId);
        if (user == null) {
            throw new ServiceException("???????????????");
        }

        if (user.getBalance() < order.getPrice()) {
            throw new ServiceException("??????????????????");
        }

        userMapper.doSubBalance(userId, order.getPrice());

        return orderMapper.doEditState(orderId, userId, order.getState().getValue(), OrderState.paid.getValue());
    }

    @Override
    public boolean refund(Long orderId, Long userId) {
        throw new ServiceException("??????????????????");
    }

    @Override
    public boolean cancel(Long orderId, Long userId) {
        Order order = orderMapper.selectById(orderId);
        if (order == null) {
            throw new ServiceException("???????????????");
        }

        if(order.getState() == OrderState.canceled){
            throw new ServiceException("????????????????????????");
        }

        if(order.getState() == OrderState.shipped){
            throw new ServiceException("?????????????????????????????????????????????????????????");
        }

        if(order.getState() == OrderState.completed){
            throw new ServiceException("????????????????????????????????????");
        }

        // ?????????????????????????????????
        if(order.getState() == OrderState.paid){
            if (userMapper.doPlusBalance(userId, order.getPrice()) == 0) {
                throw new ServiceException("??????????????????");
            }
        }

        if (productMapper.toPlus(order.getProductId(), order.getQuantity()) == 0) {
            throw new ServiceException("??????????????????");
        }

        return orderMapper.doEditState(orderId, userId, order.getState().getValue(), OrderState.canceled.getValue());
    }

    @Override
    public boolean shipped(Long orderId, Long userId) {
        Order order = orderMapper.selectById(orderId);
        if (order == null) {
            throw new ServiceException("???????????????");
        }

        if(order.getState() == OrderState.shipped){
            throw new ServiceException("?????????????????????????????????");
        }

        log.info("???????????? {} ??????,?????? {}", userId, orderId);
        return orderMapper.doEditState(orderId, order.getUserId(), order.getState().getValue(), OrderState.shipped.getValue());
    }

    @Override
    public boolean completed(Long orderId, Long userId) {
        Order order = orderMapper.selectById(orderId);
        if (order == null) {
            throw new ServiceException("???????????????");
        }

        if(order.getState() != OrderState.shipped){
            throw new ServiceException("????????????");
        }

        return orderMapper.doEditState(orderId, userId, order.getState().getValue(), OrderState.completed.getValue());
    }

    @Override
    public boolean remove(Long orderId, Long userId) {
        Order order = orderMapper.selectById(orderId);
        if (order == null) {
            throw new ServiceException("???????????????");
        }
        return orderMapper.delete(new QueryWrapper<Order>().eq("id", order).eq("user_id", userId)) > 0;
    }
}
