package peaksoft.services;

import peaksoft.dto.favorite.FavoriteResponse;
import peaksoft.dto.simple.SimpleResponse;
import peaksoft.entities.Favorite;

import java.util.List;

public interface FavoriteService {
   SimpleResponse clickFavorite(Long productId);
   List<FavoriteResponse> getAllFavoritesFromUser(Long productId);

}
