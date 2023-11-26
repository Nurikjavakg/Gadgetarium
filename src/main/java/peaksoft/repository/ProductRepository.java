package peaksoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import peaksoft.dto.product.ProductResponse;
import peaksoft.entities.Brand;
import peaksoft.entities.Product;
import peaksoft.enums.Category;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("select new peaksoft.dto.product.ProductResponse(p.id,p.name,p.price, p.characteristic,p.madeIn,p.category) from Product p where p.id= :productId")
    Optional<ProductResponse> getProductWithComment(Long productId);
    @Query("select new peaksoft.dto.product.ProductResponse(b.brandName,b.image) from Product p join p.brand b where p.id = :productId")
    Optional<ProductResponse> getBrandInfo(Long productId);
    @Query("select b.brandName from Product p join p.brand b where p.id = :productId")
    String brandName(Long productId);
    @Query("select b.image from Product p join p.brand b where p.id = :productId")
    String brandImage(Long productId);

    @Query("select (p.images)from Product p where p.id = :productId")
    List<String> images(Long productId);

    @Query("select concat(c.user.firstName, ':', c.comment) from Product p join p.comments c where p.id = :productId")
    List<String> comment(Long productId);
    @Query("select cast(count (c) as int) from Product p join p.comments c where p.id = :productId")
    int countFromComment(Long productId);

    @Query("select cast(count (f) as int) from Product p join p.favorites f where p.id = :productId")
    int countFavorite(Long productId);
    @Query("select (f.user.firstName) from Product p join p.favorites f where p.id = :productId")
    List<String>favoriteFromUser(Long productId);

    Product getProductByName(String name);
    @Query
    Product getProductById(Long productId);


    @Query("select new peaksoft.dto.product.ProductResponse(p.id,p.name,p.price, p.characteristic,p.madeIn,p.category)from Product p where p.category=:category order by p.price asc")
    List<ProductResponse> getProductByCategoryAndPriceAsc(Category category);

    @Query("select new peaksoft.dto.product.ProductResponse(p.id,p.name,p.price, p.characteristic,p.madeIn,p.category)from Product p  where p.category=:category order by p.price desc")
    List<ProductResponse> getProductByCategoryAndPriceDesc(Category category);
    @Query("select new peaksoft.dto.product.ProductResponse(p.id,p.name,p.price, p.characteristic,p.madeIn,p.category)from Product p")
    List<ProductResponse> getProductGetAll();
    @Query(value = "select images from product_images where product_id = ?1", nativeQuery = true)
    List<String> imagess(Long productId);

}