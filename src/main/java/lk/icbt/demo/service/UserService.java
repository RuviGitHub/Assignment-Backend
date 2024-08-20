package lk.icbt.demo.service;

import lk.icbt.demo.dto.LoginDTO;
import lk.icbt.demo.dto.ResponseDTO;
import lk.icbt.demo.dto.UserDTO;
import lk.icbt.demo.entity.User;
import lk.icbt.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public ResponseDTO registerUser(UserDTO userDTO) {
        // Check if the user already exists by email
        Optional<User> existingUser = userRepository.findByEmail(userDTO.getEmail());
        if (existingUser.isPresent()) {
            return new ResponseDTO(409, "Error: User already exists with this email.", null);
        }

        User user = new User();
        user.setFullName(userDTO.getFullName());
        user.setMobile(userDTO.getMobile());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setAddressLine01(userDTO.getAddressLine01());
        user.setAddressLine02(userDTO.getAddressLine02());
        user.setState(userDTO.getState());
        user.setCountry(userDTO.getCountry());
        user.setCardName(userDTO.getCardName());
        user.setCardHolder(userDTO.getCardHolder());
        user.setCardNo(userDTO.getCardNo());
        user.setExp(userDTO.getExp());
        user.setCvv(userDTO.getCvv());
        user.setStatus(User.Status.valueOf(userDTO.getStatus()));
        user.setIsDeleted(false);

        User createdUser = userRepository.save(user);
        return new ResponseDTO(201, "Message: User registered successfully.", createdUser);
    }

    public ResponseDTO loginUser(LoginDTO loginDTO) {
        Optional<User> user = userRepository.findByEmail(loginDTO.getEmail());

        if (!user.isPresent() || user.get().getIsDeleted()) {
            return new ResponseDTO(401, "Error: User not found or account is deleted.", null);
        }

        if (user.get().getPassword().equals(loginDTO.getPassword())) {
            return new ResponseDTO(200, "Message: Login successful.", user.get());
        } else {
            return new ResponseDTO(401, "Error: Invalid credentials.", null);
        }
    }

}
