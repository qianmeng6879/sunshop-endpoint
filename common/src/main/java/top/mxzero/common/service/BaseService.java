package top.mxzero.common.service;


import top.mxzero.common.PageData;

import java.util.List;

public interface BaseService<T, K> {
    public T get(K id);

    public List<T> list();

    public PageData<T> split(long current, long size);

    public boolean add(T model);

    public boolean remove(K id);

    public boolean edit(T model);

    public long count();
}
