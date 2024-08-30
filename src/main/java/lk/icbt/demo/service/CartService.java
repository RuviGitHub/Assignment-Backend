package lk.icbt.demo.service;

import lk.icbt.demo.dto.CartDTO;
import lk.icbt.demo.dto.CartReturnDTO;
import lk.icbt.demo.dto.ResponseDTO;
import lk.icbt.demo.entity.Cart;
import lk.icbt.demo.entity.Item;
import lk.icbt.demo.repository.CartRepository;
import lk.icbt.demo.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ItemRepository itemRepository;

    public ResponseDTO createCart(CartDTO cartDTO) {
        Cart cart = new Cart();
        cart.setCustomerId(cartDTO.getCustomerId());
        cart.setItemId(cartDTO.getItemId());
        cart.setIsDeleted(false);

        Cart createdCart = cartRepository.save(cart);
        return new ResponseDTO(201, "Message: Cart created.", createdCart);
    }

    public ResponseDTO getAllCartByCustomerId(Long id) {
        List<Cart> carts = cartRepository.findAllByCustomerId(id)
                .stream()
                .filter(cart -> !cart.getIsDeleted())
                .collect(Collectors.toList());

        // Fetch the items corresponding to each itemId in the cart
        List<CartReturnDTO> cartReturnDTOs = carts.stream()
                .map(cart -> {
                    Optional<Item> itemOptional = itemRepository.findById(cart.getItemId());
                    if (itemOptional.isPresent() && !itemOptional.get().getIsDeleted()) {
                        Item item = itemOptional.get();

                        CartReturnDTO cartReturnDTO = new CartReturnDTO();
                        cartReturnDTO.setId(cart.getId());
                        cartReturnDTO.setCustomerId(id);
                        cartReturnDTO.setItemId(item.getId());
                        cartReturnDTO.setItemName(item.getItemName());
                        cartReturnDTO.setItemDescription(item.getItemDescription());
                        cartReturnDTO.setItemPrice(item.getItemPrice());
                        cartReturnDTO.setSize(item.getSize().toString());
                        cartReturnDTO.setCategory(item.getCategory().toString());
                        cartReturnDTO.setStatus(item.getStatus().toString());

                        return cartReturnDTO;
                    } else {
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

//        return new ResponseDTO(200, "Message: Carts fetched.", cartReturnDTOs);
        return new ResponseDTO(401, "Message: Session expired.", cartReturnDTOs);
    }

    public void clearCartByCustomerId(Long customerId) {
        List<Cart> carts = cartRepository.findAllByCustomerId(customerId);
        for (Cart cart : carts) {
            cart.setIsDeleted(true); // Mark the cart item as deleted
            cartRepository.save(cart);
        }
    }
}
