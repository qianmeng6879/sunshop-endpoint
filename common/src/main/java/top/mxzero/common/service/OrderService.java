package top.mxzero.common.service;

import org.springframework.transaction.annotation.Transactional;
import top.mxzero.common.enums.OrderState;
import top.mxzero.common.model.Order;

import java.util.List;

public interface OrderService extends BaseService<Order, Long>{

    List<Order> list(Long userId);

    List<Order> list(Long userId, OrderState state);

    List<Order> list(OrderState state);

    List<Order> split(long current, long size, Long userId);

    List<Order> split(long current, long size, Long userId, OrderState state);

    List<Order> split(long current, long size, OrderState state);

    long count(Long userId);

    long count(Long userId, OrderState state);

    long count(OrderState state);

    @Transactional
    boolean create(Order order);

    @Transactional
    boolean pay(Long orderId, Long userId);

    @Transactional
    boolean refund(Long orderId, Long userId);

    @Transactional
    boolean cancel(Long orderId, Long userId);

    @Transactional
    boolean shipped(Long orderId, Long userId);

    @Transactional
    boolean completed(Long orderId, Long userId);

    @Transactional
    boolean remove(Long orderId, Long userId);
}
