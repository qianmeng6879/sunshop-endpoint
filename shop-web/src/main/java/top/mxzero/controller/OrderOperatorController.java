package top.mxzero.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import top.mxzero.common.model.Order;
import top.mxzero.common.service.OrderService;
import top.mxzero.common.vo.OrderVO;
import top.mxzero.jwt.annotation.JWTAuthentication;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
public class OrderOperatorController {
    @Autowired
    private OrderService orderService;

    @JWTAuthentication
    @PostMapping("/orders/create")
    public Object createOrder(HttpServletRequest request, @Valid @RequestBody OrderVO orderVO){
        long userId = (Long) request.getAttribute("USERID");
        Order order = new Order();
        BeanUtils.copyProperties(orderVO, order);
        order.setUserId(userId);
        return orderService.create(order);
    }

    @JWTAuthentication
    @PostMapping("/orders/pay/{id}")
    public Object payOrder(@PathVariable("id") long orderId, HttpServletRequest request){
        long userId = (Long) request.getAttribute("USERID");
        return orderService.pay(orderId, userId);
    }

    @JWTAuthentication
    @PostMapping("/orders/cancel/{id}")
    public Object cancelOrder(@PathVariable("id") long orderId, HttpServletRequest request){
        long userId = (Long) request.getAttribute("USERID");
        return orderService.cancel(orderId, userId);
    }

    @JWTAuthentication
    @PostMapping("/orders/refund/{id}")
    public Object refundOrder(@PathVariable("id") long orderId, HttpServletRequest request){
        long userId = (Long) request.getAttribute("USERID");
        return orderService.refund(orderId, userId);
    }

    @JWTAuthentication(role = "staff")
    @PostMapping("/orders/shipped/{id}")
    public Object shippedOrder(@PathVariable("id") long orderId, HttpServletRequest request){
        long userId = (Long) request.getAttribute("USERID");
        return orderService.shipped(orderId, userId);
    }

    @JWTAuthentication
    @PostMapping("/orders/completed/{id}")
    public Object completedOrder(@PathVariable("id") long orderId, HttpServletRequest request){
        long userId = (Long) request.getAttribute("USERID");
        return orderService.completed(orderId, userId);
    }
}
