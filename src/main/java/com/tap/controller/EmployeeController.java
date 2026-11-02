package com.tap.controller;


import com.tap.configuration.AccessRules;
import com.tap.domain.UserRole;
import com.tap.modal.User;
import com.tap.payload.dto.UserDto;
import com.tap.payload.response.ApiResponse;

import com.tap.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping("/store/{storeId}")
    @PreAuthorize(AccessRules.STORE_ADMIN)    // createStoreEmployee
    public ResponseEntity<UserDto> createStoreEmployee(@RequestBody UserDto userDto,
                                                       @PathVariable Long storeId) throws Exception {
        UserDto employee = employeeService.createStoreEmployee(userDto, storeId);

        return ResponseEntity.ok(employee);
    }

    @PostMapping("/branch/{branchId}")
    @PreAuthorize(AccessRules.STORE_LEVEL)    // createBranchEmployee
    public ResponseEntity<UserDto> createBranchEmployee(@RequestBody UserDto userDto,
                                                       @PathVariable Long branchId) throws Exception {
        UserDto employee = employeeService.createBranchEmployee(userDto, branchId);

        return ResponseEntity.ok(employee);
    }

    @PutMapping("/{id}")
    @PreAuthorize(AccessRules.STORE_ADMIN)    // updateEmployee
    public ResponseEntity<User> updateEmployee(@RequestBody UserDto userDto,
                                                        @PathVariable Long id) throws Exception {
        User employee = employeeService.updateEmployee(id, userDto);

        return ResponseEntity.ok(employee);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize(AccessRules.STORE_ADMIN)    // deleteEmployee
    public ResponseEntity<ApiResponse> deleteEmployee(@PathVariable Long id) throws Exception {
       employeeService.deleteEmployee(id);

       ApiResponse apiResponse = new ApiResponse();
       apiResponse.setMessage("Employee deleted");

       return ResponseEntity.ok(apiResponse);

    }

    @GetMapping("/store/{id}")
    @PreAuthorize(AccessRules.STORE_LEVEL)    // storeEmployee
    public ResponseEntity<List<UserDto>> storeEmployee(@PathVariable Long id,
                                              @RequestParam(required = false)UserRole userRole) throws Exception {
        List<UserDto> employee = employeeService.findStoreEmployees(id, userRole);

        return ResponseEntity.ok(employee);
    }

    @GetMapping("/branch/{id}")
    @PreAuthorize(AccessRules.BRANCH_LEVEL)   // branchEmployee
    public ResponseEntity<List<UserDto>> branchEmployee(@PathVariable Long id,
                                                    @RequestParam(required = false)UserRole userRole) throws Exception {
        List<UserDto> employee = employeeService.findBranchEmployees(id, userRole);

        return ResponseEntity.ok(employee);
    }





}
