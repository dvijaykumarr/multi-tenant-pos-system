package com.tap.service;

import com.tap.domain.StoreStatus;
import com.tap.exceptions.UserException;
import com.tap.modal.Store;
import com.tap.modal.User;
import com.tap.payload.dto.StoreDto;

import java.util.List;

public interface StoreService {

    StoreDto createStore(StoreDto storeDto, User user);
    StoreDto getStoreById(Long id) throws Exception;
    List<StoreDto> getAllStores();
    Store getStoreByAdmin() throws UserException;
    StoreDto updateStore(Long id, StoreDto storeDto) throws Exception;
    void deleteStore(Long id) throws UserException;
    StoreDto getStoreByEmployee() throws UserException;

    //platform owner can change the status of store either he can approve, or block, or pending etc
    StoreDto moderateStore(Long id, StoreStatus status) throws Exception;

}
