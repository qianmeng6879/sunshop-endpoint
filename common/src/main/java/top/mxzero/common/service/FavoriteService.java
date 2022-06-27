package top.mxzero.common.service;


import top.mxzero.common.PageData;
import top.mxzero.common.model.Favorite;

import java.util.List;

public interface FavoriteService{
    List<Favorite> list(Long userId);

    PageData<Favorite> split(long current, long size, Long userId);

    boolean add(Favorite favorite);

    boolean remove(Long id, Long userId);

    long count(Long userId);
}
