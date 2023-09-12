package peaksoft.services;

import peaksoft.dto.simple.SimpleResponse;

public interface FavoriteService {
   SimpleResponse like(Long userId, Long productId);
   SimpleResponse disLike(Long userId, Long productId);
   SimpleResponse getBackLike(Long userId, Long productId);

}
