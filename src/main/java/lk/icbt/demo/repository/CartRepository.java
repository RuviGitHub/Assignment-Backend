package lk.icbt.demo.repository;

import lk.icbt.demo.entity.Cart;
import lk.icbt.demo.entity.Item;
import lk.icbt.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    List<Cart> findAllByCustomerId(Long id);

}
