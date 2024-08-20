package lk.icbt.demo.controller;

import lk.icbt.demo.dto.OrderDTO;
import lk.icbt.demo.dto.ResponseDTO;
import lk.icbt.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/orders")
@CrossOrigin
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/place")
    public ResponseEntity<ResponseDTO> placeOrder(@RequestBody OrderDTO orderDTO) {
        ResponseDTO response = orderService.placeOrder(orderDTO);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @GetMapping("/all")
    public ResponseEntity<ResponseDTO> getAllOrders() {
        ResponseDTO response = orderService.getAllOrders();
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }
}
