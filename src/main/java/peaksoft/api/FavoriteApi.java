package peaksoft.api;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import peaksoft.dto.favorite.FavoriteResponse;
import peaksoft.dto.simple.SimpleResponse;
import peaksoft.services.FavoriteService;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class FavoriteApi {
    private final FavoriteService favoriteService;


    @PostMapping("/{productId}")
    @Secured("USER")
    @Operation(summary = "Like or dislike",description = "Like or dislike by user")
    public ResponseEntity<SimpleResponse> saveComment(@PathVariable Long productId){
        return ResponseEntity.ok(favoriteService.clickFavorite(productId));
    }


    @PostMapping("/getAllLikes/{productId}")
    @Secured("ADMIN")
    @Operation(summary = "Get all likes from Product",description = "Like or dislike by user")
    public List<FavoriteResponse> getFavorite(@PathVariable Long productId){
        return favoriteService.getAllFavoritesFromUser(productId);
    }


}
