package top.mxzero.common.service;


import top.mxzero.common.PageData;
import top.mxzero.common.model.Address;

import java.util.List;

public interface AddressService {
    List<Address> list(Long userId);

    PageData<Address> split(long current, long size, Long userId);

    Address get(Long id, Long userId);

    boolean add(Address address);

    boolean remove(Long addressId, Long userId);

    boolean edit(Address address, Long userId);

    long count(Long userId);
}
