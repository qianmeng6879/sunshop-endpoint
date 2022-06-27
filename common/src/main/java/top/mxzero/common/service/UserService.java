package top.mxzero.common.service;

import top.mxzero.common.model.User;

public interface UserService extends BaseService<User, Long> {
    public User getByUsername(String username);
}
