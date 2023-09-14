package peaksoft.services;

import peaksoft.dto.product.GetAllProductRequest;
import peaksoft.dto.product.ProductRequest;
import peaksoft.dto.product.ProductResponse;
import peaksoft.dto.simple.SimpleResponse;
import peaksoft.entities.Product;
import peaksoft.enums.Category;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {
    SimpleResponse saveProduct(ProductRequest productRequest, Long brandId);
    List<ProductResponse> getProductByCategoryAndPrice(String ascOrDesc, Category category);
    SimpleResponse assign(Long productId,Long userId);
    ProductResponse getProductWithCommentAndLike(Long productId);
    SimpleResponse updateProduct(Long productId, ProductRequest productRequest);
    SimpleResponse deleteProduct(Long productId);
}
