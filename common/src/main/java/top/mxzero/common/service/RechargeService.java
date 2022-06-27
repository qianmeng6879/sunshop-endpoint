package top.mxzero.common.service;


import org.springframework.transaction.annotation.Transactional;
import top.mxzero.common.model.Recharge;

import java.util.List;

public interface RechargeService {
    List<Recharge> list();

    List<Recharge> list(Long userId);

    @Transactional
    boolean recharge(Long userId, Long amount);

}
