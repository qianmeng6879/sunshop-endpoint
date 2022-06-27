package top.mxzero.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import top.mxzero.common.model.Recharge;
import top.mxzero.common.service.RechargeService;
import top.mxzero.common.vo.RechargeVO;
import top.mxzero.jwt.annotation.JWTAuthentication;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
public class RechargeController {
    @Autowired
    private RechargeService service;

    @JWTAuthentication
    @PostMapping("/recharge/create")
    public Object createRecharge(HttpServletRequest request, @Valid @RequestBody RechargeVO rechargeVO) {
        long userId = (Long) request.getAttribute("USERID");

        Recharge recharge = new Recharge();
        recharge.setUserId(userId);
        recharge.setAmount(rechargeVO.getAmount());

        return service.recharge(userId, rechargeVO.getAmount());
    }

    @JWTAuthentication
    @GetMapping("/recharge/list")
    public Object listRecharge(HttpServletRequest request) {
        long userId = (Long) request.getAttribute("USERID");

        return service.list(userId);
    }
}
