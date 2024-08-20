package lk.icbt.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InquiryDTO {

    private Long customerId;
    private String date;
    private String title;
    private String description;
    private String response;
    private String status;
}
