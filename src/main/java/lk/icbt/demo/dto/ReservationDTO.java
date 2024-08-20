package lk.icbt.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservationDTO {

    private String customerName;
    private String date;
    private int headCount;
    private String buffet;
    private String branch;
    private String status;

}
