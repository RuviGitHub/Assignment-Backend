package lk.icbt.demo.repository;

import lk.icbt.demo.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<Item,Long>{
    Optional<Item> findByItemName(String itemName);

    @Query(value = "SELECT i FROM Item i WHERE i.isDeleted = false")
    List<Item> findTop8ByOrderByCreatedAtDesc();

    List<Item> findBySize(Item.Size size);

    List<Item> findByCategory(Item.Category category);


    List<Item> findByItemPriceBetween(Double minPrice, Double maxPrice);

    List<Item> findByItemPriceBetweenAndSizeAndCategory(Double minPrice, Double maxPrice, Item.Size size, Item.Category category);

    List<Item> findByItemPriceBetweenAndSize(Double minPrice, Double maxPrice, Item.Size size);

    List<Item> findByItemPriceBetweenAndCategory(Double minPrice, Double maxPrice, Item.Category category);

    List<Item> findBySizeAndCategory(Item.Size size, Item.Category category);
}
