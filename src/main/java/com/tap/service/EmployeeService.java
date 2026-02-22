package com.tap.service;

import com.tap.domain.UserRole;
import com.tap.modal.User;
import com.tap.payload.dto.UserDto;

import java.util.List;

public interface EmployeeService {
    // we don't need to create a model or repository because we already have user, branch and store
    // so we will use that

    // if we want we can put this Employee endpoints in Branch and Store Service  as well, because this is not any extra modal or repository for employee
    //but i have created a seprate service so that code can become more structured and readable

    UserDto createStoreEmployee(UserDto employee, Long storeId) throws Exception;
    UserDto createBranchEmployee(UserDto employee, Long branchId) throws Exception;
    User updateEmployee(Long employeeId, UserDto employeeDetails) throws Exception;
    void deleteEmployee(Long employeeId) throws Exception;
    List<User> findStoreEmployees(Long storeId, UserRole role) throws Exception;
    List<User> findBranchEmployees(Long branchId, UserRole role) throws Exception;

}
