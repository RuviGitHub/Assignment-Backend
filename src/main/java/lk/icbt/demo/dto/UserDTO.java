package lk.icbt.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private String fullName;
    private String mobile;
    private String email;
    private String password;
    private String addressLine01;
    private String addressLine02;
    private String state;
    private String country;
    private String cardName;
    private String cardHolder;
    private String cardNo;
    private String exp;
    private String cvv;
    private String status;
}
