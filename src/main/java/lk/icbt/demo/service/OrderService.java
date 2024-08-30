package lk.icbt.demo.service;

import lk.icbt.demo.dto.OrderDTO;
import lk.icbt.demo.dto.ResponseDTO;
import lk.icbt.demo.entity.Inquiry;
import lk.icbt.demo.entity.Order;
import lk.icbt.demo.entity.OrderDetails;
import lk.icbt.demo.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public ResponseDTO placeOrder(OrderDTO orderDTO) {
        Order order = new Order();
        order.setCustomerId(orderDTO.getCustomerId());
        order.setOrderDate(orderDTO.getOrderDate());
        order.setStatus(Order.Status.valueOf(orderDTO.getStatus()));
        order.setTotalPrice(orderDTO.getTotalPrice());

        List<OrderDetails> orderDetailsList = orderDTO.getOrderDetails().stream().map(detailDTO -> {
            OrderDetails orderDetails = new OrderDetails();
            orderDetails.setProductId(detailDTO.getProductId());
            orderDetails.setQuantity(detailDTO.getQuantity());
            orderDetails.setPrice(detailDTO.getPrice());
            orderDetails.setTotal(detailDTO.getTotal());
            orderDetails.setOrder(order);
            return orderDetails;
        }).collect(Collectors.toList());

        order.setOrderDetails(orderDetailsList);

        Order createdOrder = orderRepository.save(order);
        return new ResponseDTO(201, "Message: Order placed successfully.", createdOrder);
    }

    public ResponseDTO getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        return new ResponseDTO(200, "Message: Orders fetched successfully.", orders);
    }

    public ResponseDTO updateOrder(Long id) {
        // Check if  exists

        Optional<Order> existingOrderOpt = orderRepository.findById(id);

        if (!existingOrderOpt.isPresent()) {
            return new ResponseDTO(404, "Error: Order not found.", null);
        }

        Order existingOrder = existingOrderOpt.get();

        // Update  details
        existingOrder.setStatus(Order.Status.COMPLETED);

        // Save the updated user information
        Order updatedOrder = orderRepository.save(existingOrder);

        // Return a success response
        return new ResponseDTO(200, "Message: Order confirmed.", updatedOrder);
    }
}
