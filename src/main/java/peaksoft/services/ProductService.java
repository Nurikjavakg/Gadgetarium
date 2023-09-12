package peaksoft.services;

import peaksoft.dto.product.ProductRequest;
import peaksoft.dto.product.ProductResponse;
import peaksoft.dto.simple.SimpleResponse;
import peaksoft.entities.Product;

import java.util.List;

public interface ProductService {
    SimpleResponse saveProduct(ProductRequest productRequest);
    List<Product> getAllProductByCategoryAndFilterByPrice(String category, String filter);
    SimpleResponse assign(Long productId,Long userId);
    ProductResponse getProductWithCommentAndLike(Long productId);
    SimpleResponse updateProduct(Long productId, ProductRequest productRequest);
    SimpleResponse deleteProduct(Long productId);
}
