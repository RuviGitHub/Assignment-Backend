package lk.icbt.demo.controller;

import lk.icbt.demo.dto.ItemDTO;
import lk.icbt.demo.dto.ResponseDTO;
import lk.icbt.demo.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/item")
@CrossOrigin
public class ItemController {

    @Autowired
    private ItemService itemService;

    @GetMapping("/favorite-items")
    public ResponseEntity<ResponseDTO> getFavouriteItems() {
        ResponseDTO response = itemService.getFavouriteItems();
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @GetMapping("/get-item-by-id/{id}")
    public ResponseEntity<ResponseDTO> getItemById(@PathVariable Long id) {
        ResponseDTO response = itemService.getItemById(id);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @GetMapping("/get-item-by-name")
    public ResponseEntity<ResponseDTO> getItemByName(@RequestParam String name) {
        ResponseDTO response = itemService.getItemByName(name);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @GetMapping("/all-items")
    public ResponseEntity<ResponseDTO> getItemsByFilters(
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice,
            @RequestParam(required = false) String sizeFilter,
            @RequestParam(required = false) String categoryFilter) {

        ResponseDTO response = itemService.getItemsByFilters(minPrice, maxPrice, sizeFilter, categoryFilter);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @PostMapping("/create-item")
    public ResponseEntity<ResponseDTO> createItem(@RequestBody ItemDTO itemDTO) {
        ResponseDTO response = itemService.createItem(itemDTO);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @PostMapping("/update-item/{id}")
    public ResponseEntity<ResponseDTO> updateItem(@PathVariable Long id, @RequestBody ItemDTO itemDTO) {
        ResponseDTO response = itemService.updateItem(id, itemDTO);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }
}
