package peaksoft.services.servicesImpl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import peaksoft.dto.product.ProductRequest;
import peaksoft.dto.product.ProductResponse;
import peaksoft.dto.simple.SimpleResponse;
import peaksoft.entities.Brand;
import peaksoft.entities.Product;
import peaksoft.enums.Category;
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
    public SimpleResponse saveProduct(ProductRequest productRequest,Long brandId) {
        Product productName = productRepository.getProductByName(productRequest.name());
        Brand brand = brandRepository.findById(brandId).orElseThrow(()-> new NotFoundException("Brand with id:"+brandId+" not found"));
        if ( productName== null) {
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
            log.info(String.format("Company with name: %s exists", productRequest.name()));
            throw new InvalidNameException(String.format("Company with name: %s exists", productRequest.name()));

        }
    }
    @Override
    public List<ProductResponse> getProductByCategoryAndPrice(String ascOrDesc, Category category) {
        if (ascOrDesc.equalsIgnoreCase("asc")) {


            List<ProductResponse> allProducts = productRepository.getProductByCategoryAndPriceDesc(category);


            for (ProductResponse p : allProducts) {
                p.setImages(productRepository.imagess(p.getId()));
                System.err.println("product id is; " + p.getId());
                System.out.println(p.getImages().get(0));
            }


                return allProducts;
            } else if(ascOrDesc.equalsIgnoreCase("desc")) {
            List<ProductResponse> allProducts = productRepository.getProductByCategoryAndPriceAsc(category);

            for (ProductResponse p : allProducts) {
                p.setImages(productRepository.imagess(p.getId()));
                System.err.println("product id is; " + p.getId());
                System.out.println(p.getImages().get(0));
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
                .orElseThrow(()-> new NotFoundException("Product with id:"+productId+" not found"));


        List<String> comment = productRepository.comment(productId);
        List<String> images = productRepository.images(productId);

        Optional<ProductResponse> brandInfo = productRepository.getBrandInfo(productId);


        int countFavorite = productRepository.countFavorite(productId);
        productResponse.setImages(images);
        productResponse.setComment(comment);

        productResponse.setImages(images);
        productResponse.setComment(comment);

        if (brandInfo.isPresent()) {
            ProductResponse brandResponse = brandInfo.get();
            productResponse.setBrandName(brandResponse.getBrandName());
            productResponse.setBrandImage(brandResponse.getBrandImage());
        }

        productResponse.setCountFavorite(countFavorite);
        return productResponse;

    }


    @Override
    public SimpleResponse updateProduct(Long productId, ProductRequest productRequest) {
        return null;
    }

    @Override
    public SimpleResponse deleteProduct(Long productId) {
        return null;
    }
}
