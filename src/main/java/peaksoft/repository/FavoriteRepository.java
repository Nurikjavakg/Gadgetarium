package peaksoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import peaksoft.dto.favorite.FavoriteResponse;
import peaksoft.entities.Favorite;
import peaksoft.entities.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, Long> {
    @Query("select new peaksoft.dto.favorite.FavoriteResponse(f.id,p.name,u.lastName) from Favorite f join f.product p join f.user u where p.id =:productId")
    List<FavoriteResponse> getAllFavoritesFromUser(@Param("productId") Long productId);

}