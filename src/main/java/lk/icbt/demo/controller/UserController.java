package lk.icbt.demo.controller;

import lk.icbt.demo.dto.LoginDTO;
import lk.icbt.demo.dto.ResponseDTO;
import lk.icbt.demo.dto.UserDTO;
import lk.icbt.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<ResponseDTO> registerUser(@RequestBody UserDTO userDTO) {
        ResponseDTO response = userService.registerUser(userDTO);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseDTO> loginUser(@RequestBody LoginDTO loginDTO) {
        ResponseDTO response = userService.loginUser(loginDTO);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }
}
