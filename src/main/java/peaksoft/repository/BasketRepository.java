package peaksoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import peaksoft.entities.Basket;
@Repository
public interface BasketRepository extends JpaRepository<Basket, Long> {
}