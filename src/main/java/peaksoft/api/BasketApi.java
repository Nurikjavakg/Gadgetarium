package peaksoft.api;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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


    @PostMapping("/saveToBasket/{productId}")
    @Operation(summary = "Choose this product in basket", description = "Delete chosen from basket")
    public ResponseEntity<SimpleResponse> saveToBasket(@PathVariable Long productId) {
        return ResponseEntity.ok(basketService.saveProductToBasketFromUser(productId));
    }
}
