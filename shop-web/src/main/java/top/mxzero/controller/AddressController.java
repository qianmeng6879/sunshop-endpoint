package top.mxzero.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.mxzero.common.model.Address;
import top.mxzero.common.service.AddressService;
import top.mxzero.jwt.annotation.JWTAuthentication;
import top.mxzero.common.vo.AddressVO;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
public class AddressController {
    @Autowired
    private AddressService addressService;

    @JWTAuthentication
    @GetMapping("/address/list")
    private Object listAddress(HttpServletRequest request){
        long userid = (Long)request.getAttribute("USERID");
        return addressService.list(userid);
    }

    @JWTAuthentication
    @GetMapping("/address/split")
    public Object splitAddress(HttpServletRequest request,
                               @RequestParam(value = "current", defaultValue = "0") long current,
                               @RequestParam(value = "size", defaultValue = "10") long size
                               ){
        long userid = (Long)request.getAttribute("USERID");

        return addressService.split(current, size, userid);
    }

    @JWTAuthentication
    @PostMapping("/address/create")
    public Object createAddress(HttpServletRequest request, @Valid @RequestBody AddressVO addressVO){
        long userid = (Long)request.getAttribute("USERID");

        Address address = new Address();
        BeanUtils.copyProperties(addressVO, address);
        address.setUserId(userid);

        return addressService.add(address);
    }

    @JWTAuthentication
    @PutMapping("/address/edit/{id}")
    public Object editAddress(HttpServletRequest request, @PathVariable("id") long id, @Valid @RequestBody AddressVO addressVO){
        long userid = (Long)request.getAttribute("USERID");

        Address address = new Address();
        BeanUtils.copyProperties(addressVO, address);
        address.setUserId(userid);

        address.setId(id);
        return addressService.edit(address, userid);
    }

    @JWTAuthentication
    @DeleteMapping("/address/remove/{id}")
    public Object removeAddress(@PathVariable("id") long id, HttpServletRequest request){
        long userid = (Long)request.getAttribute("USERID");

        return addressService.remove(id, userid);
    }


}
