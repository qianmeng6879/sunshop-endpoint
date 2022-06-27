package top.mxzero.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.mxzero.common.exception.ServiceException;
import top.mxzero.common.model.User;
import top.mxzero.common.service.UserService;
import top.mxzero.jwt.annotation.JWTAuthentication;
import top.mxzero.jwt.config.JWTConfigProperties;
import top.mxzero.jwt.service.ITokenService;
import top.mxzero.common.vo.UserEditVO;
import top.mxzero.common.vo.UserVO;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private ITokenService tokenService;

    @Autowired
    private JWTConfigProperties jwtConfigProperties;


    @GetMapping("/users/{id}")
    public Object getUser(@PathVariable("id") long id) {
        return userService.get(id);
    }

    @JWTAuthentication(role = "staff")
    @GetMapping("/users/list")
    public Object listUser() {
        return userService.list();
    }

    @JWTAuthentication
    @GetMapping("/users/split")
    public Object splitUser(
            @RequestParam(value = "current", defaultValue = "0") long current,
            @RequestParam(value = "size", defaultValue = "10") long size) {

        return userService.split(current, size);
    }

    @PostMapping("/users/create")
    public Object createUser(@RequestBody UserVO userVO) {
        User user = new User();
        BeanUtils.copyProperties(userVO, user);
        return userService.add(user);
    }

    @PutMapping("/users/edit/{id}")
    public Object editUser(@PathVariable("id") long id, @RequestBody UserEditVO userEditVO) {
        User user = new User();
        BeanUtils.copyProperties(userEditVO, user);
        user.setId(id);
        return userService.edit(user);
    }

    @JWTAuthentication(role = "staff")
    @DeleteMapping("/users/remove/{id}")
    public Object removeUser(@PathVariable("id") long id) {
        return userService.remove(id);
    }

    @PostMapping("/users/token/create")
    public Object createToken(@RequestBody UserVO userVO) {
        User user = userService.getByUsername(userVO.getUsername());
        if (user == null) {
            throw new ServiceException("用户名或密码错误");
        }

        if (!user.getPassword().equals(userVO.getPassword())) {
            throw new ServiceException("用户名与密码不匹配");
        }

        Map<String, Object> map = new HashMap<>();
        map.put("id", user.getId());
        map.put("username", user.getUsername());
        map.put("nickname", user.getNickname());
        map.put("type", user.getType());
        String token = tokenService.createToken(UUID.randomUUID().toString(), map);

        Map<String, Object> data = new HashMap<>();
        data.put("token", token);
        data.put("expires_time", new Date(System.currentTimeMillis() + jwtConfigProperties.getExpire() * 1000));
        return data;
    }

    @JWTAuthentication
    @RequestMapping("/users/current")
    public Object currentUser(HttpServletRequest request){
        long userId = (Long) request.getAttribute("USERID");
        return userService.get(userId);
    }
}
