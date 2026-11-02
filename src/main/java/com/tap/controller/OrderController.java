package com.tap.controller;


import com.tap.configuration.AccessRules;
import com.tap.domain.OrderStatus;
import com.tap.domain.PaymentType;
import com.tap.payload.dto.OrderDto;
import com.tap.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    @PreAuthorize(AccessRules.POS_OPERATOR) // createOrder
    public ResponseEntity<OrderDto> createOrder(@RequestBody OrderDto orderDto) throws Exception {
        return ResponseEntity.ok(orderService.createOrder(orderDto));
    }


    @GetMapping("/{id}")
    @PreAuthorize(AccessRules.ALL_STAFF)    // getOrderById
    public ResponseEntity<OrderDto> getOrderById(@PathVariable Long id) throws Exception {

        return ResponseEntity.ok(orderService.getOrderById(id));
    }

    @GetMapping("/branch/{branchId}")
    @PreAuthorize(AccessRules.BRANCH_LEVEL) // getOrderByBranch
    public ResponseEntity<List<OrderDto>> getOrderByBranch(@PathVariable Long branchId,
                                                           @RequestParam(required = false) Long customerId,
                                                           @RequestParam(required = false) Long cashierId,
                                                           @RequestParam(required = false)PaymentType paymentType,
                                                           @RequestParam(required = false)OrderStatus orderStatus) throws Exception {

        return ResponseEntity.ok(orderService.getOrdersByBranch(branchId, customerId, cashierId, paymentType, orderStatus));
    }


    @GetMapping("/cashier/{cashierId}")
    @PreAuthorize(AccessRules.ALL_STAFF)  // getOrderByCashier
    public ResponseEntity<List<OrderDto>> getOrderByCashier(@PathVariable Long cashierId) throws Exception {

        return ResponseEntity.ok(orderService.getOrderByCashier(cashierId));
    }

    @GetMapping("/today/branch/{id}")
    @PreAuthorize(AccessRules.ALL_STAFF)    // getTodayOrder
    public ResponseEntity<List<OrderDto>> getTodayOrder(@PathVariable Long id) throws Exception {

        return ResponseEntity.ok(orderService.getTodayOrdersByBranch(id));
    }


    @GetMapping("/customer/{id}")
    @PreAuthorize(AccessRules.ALL_STAFF)    // getCustomersOrder
    public ResponseEntity<List<OrderDto>> getCustomersOrder(@PathVariable Long id) throws Exception {

        return ResponseEntity.ok(orderService.getOrdersByCustomerId(id));
    }

    @GetMapping("/recent/{branchId}")
    @PreAuthorize(AccessRules.ALL_STAFF)    // getRecentOrder
    public ResponseEntity<List<OrderDto>> getRecentOrder(@PathVariable Long branchId) throws Exception {

        return ResponseEntity.ok(orderService.getTop5RecentOrdersByBranchID(branchId));
    }

}
