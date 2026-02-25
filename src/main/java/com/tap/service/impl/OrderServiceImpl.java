package com.tap.service.impl;

import com.tap.domain.OrderStatus;
import com.tap.domain.PaymentType;
import com.tap.mapper.OrderMapper;
import com.tap.modal.*;
import com.tap.payload.dto.OrderDto;
import com.tap.repository.CustomerRepository;
import com.tap.repository.OrderRepository;
import com.tap.repository.ProductRepository;
import com.tap.repository.UserRepository;
import com.tap.service.OrderService;
import com.tap.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    private final CustomerRepository customerRepository;
    private final UserService userService;
    private final ProductRepository productRepository;

    @Override
    public OrderDto createOrder(OrderDto orderDto) throws Exception {

        User cashier = userService.getCurrentUser();
        Branch branch = cashier.getBranch();
        if(branch == null){
            throw new Exception("Cashier's branch not found");
        }

        Customer customer = null;
        if(orderDto.getCustomerId() != null){
            customer = customerRepository.findById(orderDto.getCustomerId())
                    .orElseThrow(()-> new Exception("Customer not found"));
        }

        Order order = Order.builder()
                .branch(branch)
                .cashier(cashier)
//                .customer(orderDto.getCustomer())
                .customer(customer)
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

        return OrderMapper.toDTO(savedOrder);
    }

    @Override
    public OrderDto getOrderById(Long id) throws Exception {
        return orderRepository.findById(id)
                .map(OrderMapper::toDTO)
                .orElseThrow(
                ()->new Exception("Order not found with id " + id)
        );
    }

    @Override
    public List<OrderDto> getOrdersByBranch(Long branchId,
                                            Long customerId,
                                            Long cashierId,
                                            PaymentType paymentType,
                                            OrderStatus orderStatus) throws Exception {

        return orderRepository.findByBranch_Id(branchId).stream()
                .filter(order -> customerId == null ||
                        (order.getCustomer() != null &&
                            order.getCustomer().getId().equals(customerId)))
                .filter(order -> cashierId == null ||
                        order.getCashier() != null &&
                        order.getCashier().getId().equals(cashierId))
                .filter(order -> paymentType == null ||
                        order.getPaymentType() == paymentType)
                .map(OrderMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<OrderDto> getOrderByCashier(Long cashierId) {

        return orderRepository.findByCashier_Id(cashierId).stream()
                .map(OrderMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public void deleteOrder(Long id) throws Exception {

        Order order = orderRepository.findById(id).orElseThrow(
                ()->new Exception("Order not found with id")
        );

        orderRepository.delete(order);

    }

    @Override
    public List<OrderDto> getTodayOrdersByBranch(Long branchId) throws Exception {

        LocalDate today = LocalDate.now();
        LocalDateTime start = today.atStartOfDay();
        LocalDateTime end = today.plusDays(1).atStartOfDay();


        return orderRepository.findByBranch_IdAndCreatedAtBetween(
                branchId, start, end
        ).stream().map(
                OrderMapper::toDTO
        ).collect(Collectors.toList());
    }

    @Override
    public List<OrderDto> getOrdersByCustomerId(Long customerId) throws Exception {
        return orderRepository.findByCustomer_Id(customerId)
                .stream().map(
                        OrderMapper::toDTO
                ).collect(Collectors.toList());
    }

    @Override
    public List<OrderDto> getTop5RecentOrdersByBranchID(Long branchId) throws Exception {
        return orderRepository.findTop5ByBranch_IdOrderByCreatedAtDesc(branchId)
                .stream().map(
                OrderMapper::toDTO
        ).collect(Collectors.toList());
    }
}
