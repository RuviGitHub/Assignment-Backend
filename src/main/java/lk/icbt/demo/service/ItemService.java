package lk.icbt.demo.service;

import lk.icbt.demo.dto.ItemDTO;
import lk.icbt.demo.dto.ResponseDTO;
import lk.icbt.demo.entity.Item;
import lk.icbt.demo.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    public ResponseDTO getFavouriteItems() {
        List<Item> itemList = itemRepository.findTop8ByOrderByCreatedAtDesc();
        return new ResponseDTO(200, "Message: Items fetched.", itemList);
    }

    public ResponseDTO getItemById(Long id) {
        Optional<Item> item = itemRepository.findById(id);
        if (item.isPresent()) {
            return new ResponseDTO(200, "Message: Item fetched.", item.get());
        } else {
            return new ResponseDTO(404, "Message: Item not found.", null);
        }
    }

    public ResponseDTO getItemByName(String itemName) {
        Optional<Item> item = itemRepository.findByItemName(itemName);
        if (item.isPresent()) {
            return new ResponseDTO(200, "Message: Item fetched.", item.get());
        } else {
            return new ResponseDTO(404, "Message: Item not found.", null);
        }
    }

    public ResponseDTO getItemsByFilters(Double minPrice, Double maxPrice, String sizeFilter, String categoryFilter) {
        List<Item> items;

        if (minPrice != null && maxPrice != null && sizeFilter != null && categoryFilter != null) {
            items = itemRepository.findByItemPriceBetweenAndSizeAndCategory(minPrice, maxPrice, Item.Size.valueOf(sizeFilter), Item.Category.valueOf(categoryFilter));
        } else if (minPrice != null  && maxPrice != null && sizeFilter != null) {
            items = itemRepository.findByItemPriceBetweenAndSize(minPrice, maxPrice, Item.Size.valueOf(sizeFilter));
        } else if (minPrice != null && maxPrice != null && categoryFilter != null) {
            items = itemRepository.findByItemPriceBetweenAndCategory(minPrice, maxPrice, Item.Category.valueOf(categoryFilter));
        } else if (sizeFilter != null && categoryFilter != null) {
            items = itemRepository.findBySizeAndCategory(Item.Size.valueOf(sizeFilter), Item.Category.valueOf(categoryFilter));
        } else if (minPrice != null && maxPrice != null) {
            items = itemRepository.findByItemPriceBetween(minPrice, maxPrice);
        } else if (sizeFilter != null) {
            items = itemRepository.findBySize(Item.Size.valueOf(sizeFilter));
        } else if (categoryFilter != null) {
            items = itemRepository.findByCategory(Item.Category.valueOf(categoryFilter));
        } else {
            items = itemRepository.findAll();
        }

        return new ResponseDTO(200, "Message: Items fetched.", items);
    }

    public ResponseDTO createItem(ItemDTO itemDTO) {
        Item item = new Item();
        item.setItemName(itemDTO.getItemName());
        item.setItemDescription(itemDTO.getItemDescription());
        item.setStatus(Item.Status.valueOf(itemDTO.getStatus()));
        item.setItemPrice(Double.valueOf(itemDTO.getItemPrice()));
        item.setSize(Item.Size.valueOf(itemDTO.getSize()));
        item.setCategory(Item.Category.valueOf(itemDTO.getCategory()));
        item.setIsDeleted(false);

        Item createdItem = itemRepository.save(item);
        return new ResponseDTO(201, "Message: Item created.", createdItem);
    }

    public ResponseDTO updateItem(Long id, ItemDTO itemDTO) {
        Optional<Item> existingItemOpt = itemRepository.findById(id);
        if (!existingItemOpt.isPresent()) {
            return new ResponseDTO(404, "Message: Item not found.", null);
        }

        Item existingItem = existingItemOpt.get();
        if (itemDTO.getItemName() != null) {
            existingItem.setItemName(itemDTO.getItemName());
        }
        if (itemDTO.getItemDescription() != null) {
            existingItem.setItemDescription(itemDTO.getItemDescription());
        }


        Item updatedItem = itemRepository.save(existingItem);
        return new ResponseDTO(200, "Message: Item updated.", updatedItem);
    }
}
