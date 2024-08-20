package lk.icbt.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {

    private String customerId;
    private String orderDate;
    private String status;
    private List<OrderDTO.OrderDetailsDTO> orderDetails;
    private Double totalPrice;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class OrderDetailsDTO {
        private Long productId;
        private Integer quantity;
        private Double price;
        private Double total;
    }
}


