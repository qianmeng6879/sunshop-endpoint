package top.mxzero.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.mxzero.common.model.Favorite;
import top.mxzero.common.service.FavoriteService;
import top.mxzero.jwt.annotation.JWTAuthentication;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
public class FavoriteController {
    @Autowired
    private FavoriteService service;

    @JWTAuthentication
    @GetMapping("/favorites/list")
    public Object listFavorite(HttpServletRequest request){
        long userId = (Long) request.getAttribute("USERID");
        return service.list(userId);
    }

    @JWTAuthentication
    @GetMapping("/favorites/split")
    public Object splitFavorite(HttpServletRequest request,
                                @RequestParam(value = "current", defaultValue = "0") long current,
                                @RequestParam(value = "size", defaultValue = "10") long size
                                ){
        long userId = (Long) request.getAttribute("USERID");

        return service.split(current, size, userId);
    }

    @JWTAuthentication
    @PostMapping("/favorites/create")
    public Object createFavorite(HttpServletRequest request, @Valid @RequestBody Favorite favorite){
        long userId = (Long) request.getAttribute("USERID");
        favorite.setUserId(userId);
        return service.add(favorite);
    }

    @JWTAuthentication
    @DeleteMapping("/favorites/remove/{id}")
    public Object removeFavorite(@PathVariable("id") long id, HttpServletRequest request){
        long userId = (Long) request.getAttribute("USERID");
        return service.remove(id, userId);
    }

}
