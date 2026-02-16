package com.tap.controller;


import com.tap.domain.StoreStatus;
import com.tap.exceptions.UserException;
import com.tap.mapper.StoreMapper;
import com.tap.modal.Store;
import com.tap.modal.User;
import com.tap.payload.dto.StoreDto;
import com.tap.payload.response.ApiResponse;
import com.tap.service.StoreService;
import com.tap.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stores")
@RequiredArgsConstructor
public class StoreController {

    private final StoreService storeService;
    private final UserService userService;


    @PostMapping
    public ResponseEntity<StoreDto> createStore(@RequestBody StoreDto storeDto,
                                                @RequestHeader("Authorization") String jwt) throws UserException {

        User user = userService.getUserFromJwtToken(jwt);

        return ResponseEntity.ok(storeService.createStore(storeDto,user));

    }



    @GetMapping()
    public ResponseEntity<List<StoreDto>> getAllStore(
                                                 @RequestHeader("Authorization") String jwt) throws Exception {

        User user = userService.getUserFromJwtToken(jwt);


        return ResponseEntity.ok(storeService.getAllStores());

    }


    @GetMapping("/admin")
    public ResponseEntity<StoreDto> getStoreByAdmin(
            @RequestHeader("Authorization") String jwt) throws Exception {

        return ResponseEntity.ok(StoreMapper.toDto(storeService.getStoreByAdmin()));

    }

    @GetMapping("/employee")
    public ResponseEntity<StoreDto> getStoreByEmployee(
            @RequestHeader("Authorization") String jwt) throws Exception {

        return ResponseEntity.ok(storeService.getStoreByEmployee());

    }


    @PutMapping("/{id}")
    public ResponseEntity<StoreDto> updateStore(@PathVariable Long id,
                                                @RequestBody StoreDto storeDto) throws Exception{

        return ResponseEntity.ok(storeService.updateStore(id,storeDto));

    }

    @PutMapping("/{id}/moderate")
    public ResponseEntity<StoreDto> moderateStore(@PathVariable Long id,
                                                  @RequestParam StoreStatus status
    ) throws Exception{

        return ResponseEntity.ok(storeService.moderateStore(id, status));

    }


    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> DeleteStore(@PathVariable Long id) throws Exception {

        storeService.deleteStore(id);
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Store deleted successfully");
        return ResponseEntity.ok(apiResponse);

    }

    @GetMapping("/{id}")
    public ResponseEntity<StoreDto> getStoreById(@PathVariable Long id,
                                                 @RequestHeader("Authorization") String jwt) throws Exception {

        User user = userService.getUserFromJwtToken(jwt);


        return ResponseEntity.ok(storeService.getStoreById(id));

    }






}
