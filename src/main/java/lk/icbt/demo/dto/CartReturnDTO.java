package lk.icbt.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartReturnDTO {
    private Long id;
    private Long customerId;
    private Long itemId;
    private String itemName;
    private String itemDescription;
    private String status;
    private Double itemPrice;
    private String size;
    private String category;
}
