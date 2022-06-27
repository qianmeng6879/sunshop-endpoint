package top.mxzero.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.mxzero.common.enums.OrderState;
import top.mxzero.common.service.OrderService;
import top.mxzero.jwt.annotation.JWTAuthentication;

import javax.servlet.http.HttpServletRequest;
@RestController
public class OrderQueryController {
    @Autowired
    private OrderService orderService;

    @JWTAuthentication
    @GetMapping("/orders/list")
    public Object listOrder(HttpServletRequest request, String state) {
        long userId = (Long) request.getAttribute("USERID");
        if (OrderState.transition(state) == null) {
            return orderService.list(userId);
        }
        return orderService.list(userId, OrderState.transition(state));
    }

    @JWTAuthentication
    @GetMapping("/orders/split")
    public Object splitOrder(HttpServletRequest request,
                             @RequestParam(value = "current", defaultValue = "0") long current,
                             @RequestParam(value = "size", defaultValue = "10") long size,
                             String state
    ) {
        long userId = (Long) request.getAttribute("USERID");

        if (OrderState.transition(state) == null) {
            return orderService.split(current, size, userId);
        }
        return orderService.split(current, size, userId, OrderState.transition(state));
    }
}
