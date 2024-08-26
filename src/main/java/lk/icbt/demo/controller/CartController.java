package lk.icbt.demo.controller;

import lk.icbt.demo.dto.CartDTO;
import lk.icbt.demo.dto.ResponseDTO;
import lk.icbt.demo.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/carts")
@CrossOrigin
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("/create")
    public ResponseEntity<ResponseDTO> createCart(@RequestBody CartDTO cartDTO) {
        ResponseDTO response = cartService.createCart(cartDTO);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @GetMapping("/all")
    public ResponseEntity<ResponseDTO> getAllCarts(@RequestParam Long id) {  // Changed @PathVariable to @RequestParam
        ResponseDTO response = cartService.getAllCartByCustomerId(id);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @DeleteMapping("/clear/{customerId}")
    public ResponseEntity<ResponseDTO> clearCart(@PathVariable Long customerId) {
        cartService.clearCartByCustomerId(customerId);
        return ResponseEntity.ok(new ResponseDTO(200, "Cart cleared successfully.", null));
    }
}
