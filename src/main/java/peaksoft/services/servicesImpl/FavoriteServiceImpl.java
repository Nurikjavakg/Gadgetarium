package peaksoft.services.servicesImpl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import peaksoft.config.JwtService;

import peaksoft.dto.favorite.FavoriteResponse;
import peaksoft.dto.simple.SimpleResponse;
import peaksoft.entities.Favorite;
import peaksoft.entities.Product;
import peaksoft.entities.User;
import peaksoft.exception.AccessDenied;
import peaksoft.exception.NotFoundException;
import peaksoft.repository.FavoriteRepository;
import peaksoft.repository.ProductRepository;
import peaksoft.repository.UserRepository;
import peaksoft.services.FavoriteService;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class FavoriteServiceImpl implements FavoriteService {
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final FavoriteRepository favoriteRepository;
    private final JwtService jwtService;

    @Override
    public SimpleResponse clickFavorite(Long productId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new AccessDenied("Authentication required to delete a comment !!!");
        }
        String email = authentication.getName();
        User user = userRepository.getUserByEmail(email)
                .orElseThrow(()-> new NotFoundException("User with id:"+email+" not found"));

        Product product = productRepository.findById(productId)
                .orElseThrow(()-> new NotFoundException("Product with id:"+productId+" not found"));

        List<Favorite> favorites = favoriteRepository.findAll();
        for (Favorite f :favorites) {
            if (f.getUser().equals(user) && f.getProduct().equals(product)){
                favoriteRepository.deleteById(f.getId());
                return SimpleResponse.builder()
                        .httpStatus(HttpStatus.OK)
                        .message("disliked")
                        .build();
            }
        }
        Favorite favorite = new Favorite();
        favorite.setUser(user);
        favorite.setProduct(product);
        product.getFavorites().size();
        favoriteRepository.save(favorite);
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("Liked")
                .build();

    }

    @Override
    public List<FavoriteResponse> getAllFavoritesFromUser(Long productId) {
         List<FavoriteResponse> favoriteResponse = favoriteRepository.getAllFavoritesFromUser(productId);
         return favoriteResponse;


    }



}
