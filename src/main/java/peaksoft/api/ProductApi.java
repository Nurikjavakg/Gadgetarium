package peaksoft.api;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.product.ProductRequest;
import peaksoft.dto.product.ProductResponse;
import peaksoft.dto.simple.SimpleResponse;
import peaksoft.services.ProductService;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class ProductApi {
    private final ProductService productService;

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public SimpleResponse saveProduct(@RequestBody @Valid ProductRequest productRequest) {
        productService.saveProduct(productRequest);
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message(String.format("Product with name: %s successfully saved!", productRequest.name()))
                .build();
    }

    @GetMapping("/getInfo/{productId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ProductResponse getInfo(@PathVariable Long productId){
        return productService.getProductWithCommentAndLike(productId);
    }
}
