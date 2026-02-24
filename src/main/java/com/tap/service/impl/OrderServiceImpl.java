package com.tap.service.impl;

import com.tap.domain.OrderStatus;
import com.tap.domain.PaymentType;
import com.tap.modal.*;
import com.tap.payload.dto.OrderDto;
import com.tap.repository.OrderRepository;
import com.tap.repository.ProductRepository;
import com.tap.repository.UserRepository;
import com.tap.service.OrderService;
import com.tap.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    private final UserService userService;
    private final ProductRepository productRepository;

    @Override
    public OrderDto createOrder(OrderDto orderDto) throws Exception {

        User cashier = userService.getCurrentUser();
        Branch branch = cashier.getBranch();
        if(branch == null){
            throw new Exception("Cashier's branch not found");
        }

        Order order = Order.builder()
                .branch(branch)
                .cashier(cashier)
                .customer(orderDto.getCustomer())
                .paymentType(orderDto.getPaymentType())
                .build();

        List<OrderItem> orderItems = orderDto.getItems().stream().map(
                itemDTO -> {

                    Product product = productRepository.findById(itemDTO.getProductId())
                            .orElseThrow(()-> new EntityNotFoundException("product not found"));

                    return OrderItem.builder()
                            .product(product)
                            .quantity(itemDTO.getQuantity())
                            .price(product.getSellingPrice() * itemDTO.getQuantity())
                            .order(order)
                            .build();
                }
        ).toList();
        double total = orderItems.stream().mapToDouble(
                OrderItem::getPrice
        ).sum();

        order.setTotalAmount(total);
        order.setItems(orderItems);

        Order savedOrder = orderRepository.save(order);

        return null;
    }

    @Override
    public OrderDto updateOrder(Long id) throws Exception {
        return null;
    }

    @Override
    public List<OrderDto> getOrdersByBranch(Long branchId, Long customerId, Long cashierId, PaymentType paymentType, OrderStatus orderStatus) throws Exception {
        return List.of();
    }

    @Override
    public List<OrderDto> getOrderByCashier(Long cashierId) {
        return List.of();
    }

    @Override
    public void deleteOrder(Long id) throws Exception {

    }

    @Override
    public List<OrderDto> getTodayOrdersByBranch(Long branchId) throws Exception {
        return List.of();
    }

    @Override
    public List<OrderDto> getOrdersByCustomerId(Long customerId) throws Exception {
        return List.of();
    }

    @Override
    public List<OrderDto> getTop5RecentOrdersByBranchID(Long branchId) throws Exception {
        return List.of();
    }
}
