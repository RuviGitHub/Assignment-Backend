package lk.icbt.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemDTO {
    private String itemName;
    private String itemDescription;
    private String status;
    private Double itemPrice;
    private String size;
    private String category;
}
