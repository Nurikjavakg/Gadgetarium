package peaksoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import peaksoft.dto.product.ProductResponse;
import peaksoft.entities.Product;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("select new peaksoft.dto.product.ProductResponse(p.id,p.name,p.price, p.characteristic,p.madeIn,p.category) from Product p where p.id= :productId")
    Optional<ProductResponse> getProductWithComment(Long productId);
   @Query("select (p.images)from Product p where p.id = :productId")
    List<String> images(Long productId);

    @Query("select (c.comment)from Product p join p.comments c where p.id = :productId")
    List<String> comment(Long productId);
    @Query("select cast(count (f) as int) from Product p join p.favorites f where p.id = :productId")
    int countFavorite(Long productId);
    Product getProductByName(String name);


    Product getProductById(Long productId);
}