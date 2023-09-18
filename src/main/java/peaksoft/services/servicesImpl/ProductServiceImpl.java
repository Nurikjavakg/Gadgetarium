package peaksoft.services.servicesImpl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import peaksoft.dto.product.ProductRequest;
import peaksoft.dto.product.ProductResponse;
import peaksoft.dto.simple.SimpleResponse;
import peaksoft.entities.Brand;
import peaksoft.entities.Product;
import peaksoft.enums.Category;
import peaksoft.enums.Role;
import peaksoft.exception.AccessDenied;
import peaksoft.exception.InvalidNameException;
import peaksoft.exception.NotFoundException;
import peaksoft.repository.BrandRepository;
import peaksoft.repository.ProductRepository;
import peaksoft.services.ProductService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final BrandRepository brandRepository;

    @Override
    public SimpleResponse saveProduct(ProductRequest productRequest, Long brandId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new AccessDenied("Authentication required to delete a comment !!!");
        }
        Product productName = productRepository.getProductByName(productRequest.name());
        Brand brand = brandRepository.findById(brandId).orElseThrow(() -> new NotFoundException("Brand with id:" + brandId + " not found"));
        if (productName == null) {
            Product product = new Product();
            product.setName(productRequest.name());
            product.setPrice(productRequest.price());
            product.setImages(productRequest.images());
            product.setCharacteristic(productRequest.characteristic());
            product.setMadeIn(productRequest.madeIn());
            product.setCategory(productRequest.category());
            product.setBrand(brand);
            productRepository.save(product);
            String message = String.format("Product with id: %s successfully", product.getId() + "saved...");
            log.info(message);
            return SimpleResponse.builder()
                    .httpStatus(HttpStatus.OK)
                    .message(message)
                    .build();
        } else {
            log.info(String.format("Product with name: %s exists", productRequest.name()));
            throw new InvalidNameException(String.format("Company with name: %s exists", productRequest.name()));

        }
    }

    @Override
    public List<ProductResponse> getProductByCategoryAndPrice(String ascOrDesc, Category category) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new AccessDenied("Authentication required to delete a comment !!!");
        }


        if (ascOrDesc.equalsIgnoreCase("desc")) {


            List<ProductResponse> allProducts = productRepository.getProductByCategoryAndPriceDesc(category);

            for (ProductResponse p : allProducts) {
                String brandName = productRepository.brandName(p.getId());
                String brandImage = productRepository.brandImage(p.getId());
                p.setImages(productRepository.imagess(p.getId()));
                p.setCountComment(productRepository.countFavorite(p.getId()));
                p.setCountFavorite(productRepository.countFavorite(p.getId()));
                p.setComment(productRepository.comment(p.getId()));
                p.setFavoriteFromUser(productRepository.favoriteFromUser(p.getId()));
                p.setBrandName(brandName);
                p.setBrandImage(brandImage);

            }


            return allProducts;
        } else if (ascOrDesc.equalsIgnoreCase("asc")) {
            List<ProductResponse> allProducts = productRepository.getProductByCategoryAndPriceAsc(category);

            for (ProductResponse p : allProducts) {
                String brandName = productRepository.brandName(p.getId());
                String brandImage = productRepository.brandImage(p.getId());
                p.setImages(productRepository.imagess(p.getId()));
                p.setCountComment(productRepository.countFavorite(p.getId()));
                p.setCountFavorite(productRepository.countFavorite(p.getId()));
                p.setComment(productRepository.comment(p.getId()));
                p.setFavoriteFromUser(productRepository.favoriteFromUser(p.getId()));
                p.setBrandName(brandName);
                p.setBrandImage(brandImage);

            }

            return allProducts;

        }return null;

    }


    @Override
    public SimpleResponse assign(Long productId, Long userId) {
        return null;
    }

    @Override
    public ProductResponse getProductWithCommentAndLike(Long productId) {
        ProductResponse productResponse = productRepository.getProductWithComment(productId)
                .orElseThrow(() -> new NotFoundException("Product with id:" + productId + " not found"));


        List<String> comment = productRepository.comment(productId);
        List<String> images = productRepository.images(productId);
        List<String> favorite = productRepository.favoriteFromUser(productId);

        Optional<ProductResponse> brandInfo = productRepository.getBrandInfo(productId);


        int countFavorite = productRepository.countFavorite(productId);
        int countComment = productRepository.countFromComment(productId);
        productResponse.setImages(images);
        productResponse.setComment(comment);
        productResponse.setFavoriteFromUser(favorite);

        productResponse.setImages(images);
        productResponse.setComment(comment);

        if (brandInfo.isPresent()) {
            ProductResponse brandResponse = brandInfo.get();
            productResponse.setBrandName(brandResponse.getBrandName());
            productResponse.setBrandImage(brandResponse.getBrandImage());
        }
        productResponse.setCountComment(countComment);
        productResponse.setCountFavorite(countFavorite);

        return productResponse;

    }


    @Override
    @Transactional
    public SimpleResponse updateProduct(Long productId, ProductRequest productRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new AccessDenied("Authentication required to update product !!!");
        }
        if (!authentication.getAuthorities().stream()
                .anyMatch(role -> role.getAuthority().equals(Role.ADMIN))) {
            throw new AccessDenied("You do not have permission to update a product.");
        }

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new NotFoundException("Product with id:" + productId + " not found"));

        product.setName(productRequest.name());
        product.setPrice(productRequest.price());
        product.setImages(productRequest.images());
        product.setCharacteristic(productRequest.characteristic());
        product.setMadeIn(productRequest.madeIn());
        product.setCategory(productRequest.category());
        log.info("Product with id:" + productId + " updated...");
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message(String.format("User with id: %s successfully updated", productId))
                .build();
    }

    @Override
    public SimpleResponse deleteProduct(Long productId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new AccessDenied("Authentication required to delete a comment !!!");
        }

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> {
                    log.info("Product with id:" + productId + " not found...");
                    return new NotFoundException("Product with id:" + productId + " not found...");
                });

        productRepository.delete(product);

        log.info("Product is deleted with id:" + productId + "...");
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("Product with id:" + productId + " has been deleted.")
                .build();
    }
}

