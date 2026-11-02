package com.tap.controller;


import com.tap.configuration.AccessRules;
import com.tap.modal.Customer;
import com.tap.payload.response.ApiResponse;
import com.tap.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerService customerService;


    @PostMapping()
    @PreAuthorize(AccessRules.ALL_STAFF)    // create
    public ResponseEntity<Customer> create(@RequestBody Customer customer){
        return ResponseEntity.ok(customerService.createCustomer(customer));
    }


    @PutMapping("/{id}")
    @PreAuthorize(AccessRules.ALL_STAFF)    // update
    public ResponseEntity<Customer> update(@RequestBody Customer customer,
                                           @PathVariable Long id) throws Exception {
        return ResponseEntity.ok(customerService.updateCustomer(id, customer));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize(AccessRules.STORE_LEVEL)  // delete
    public ResponseEntity<ApiResponse> delete(@PathVariable Long id) throws Exception {
         customerService.deleteCustomer(id);

         ApiResponse apiResponse = new ApiResponse();
         apiResponse.setMessage("Customer deleted");

         return ResponseEntity.ok(apiResponse);
    }


    @GetMapping()
    @PreAuthorize(AccessRules.BRANCH_LEVEL) // getAll
    public ResponseEntity<List<Customer>> getAll() throws Exception {
        return ResponseEntity.ok(customerService.getAllCustomers());
    }

    @GetMapping("/search")
    @PreAuthorize(AccessRules.ALL_STAFF)    // search
    public ResponseEntity<List<Customer>> search(@RequestParam String q) throws Exception {
        return ResponseEntity.ok(customerService.searchCustomers(q));
    }




}
