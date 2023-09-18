package peaksoft.api;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import peaksoft.dto.simple.SimpleResponse;
import peaksoft.services.BasketService;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class BasketApi {
    private final BasketService basketService;


    @PreAuthorize("hasAuthority('USER')")
    @PostMapping("/saveToBasket/{productId}")
    @Operation(summary = "Choose this product in basket", description = "Delete chosen from basket")
    public ResponseEntity<SimpleResponse> saveToBasket(@PathVariable Long productId) {
        return ResponseEntity.ok(basketService.saveProductToBasketFromUser(productId));
    }
    @PreAuthorize("hasAuthority('USER')")
    @PostMapping("/deleteProductFromBasket/{productId}")
    @Operation(summary = "Delete this product in basket", description = "Delete chosen from basket")
    public ResponseEntity<SimpleResponse> deleteProductFromBasket(@PathVariable Long productId) {
        return ResponseEntity.ok(basketService.deleteProductFromBasket(productId));
    }
}
