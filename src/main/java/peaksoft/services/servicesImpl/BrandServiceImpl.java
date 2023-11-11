package peaksoft.services.servicesImpl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import peaksoft.dto.brand.BrandRequest;
import peaksoft.dto.brand.BrandResponse;
import peaksoft.dto.simple.SimpleResponse;
import peaksoft.entities.Brand;
import peaksoft.exception.AccessDenied;
import peaksoft.exception.InvalidNameException;
import peaksoft.repository.BrandRepository;
import peaksoft.services.BrandService;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class BrandServiceImpl implements BrandService{
    private final BrandRepository brandRepository;
    @Override
    public List<Brand> getAllBrands() {
        return null;
    }

    @Override
    public SimpleResponse saveBrand(BrandRequest brandRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new AccessDenied("Authentication required to delete a comment !!!");
        }
        Brand brand1 = brandRepository.getBrandByBrandName(brandRequest.brandName());
        if (brand1 == null) {
            Brand brand2 = new Brand();
            brand2.setBrandName(brandRequest.brandName());
            brand2.setImage(brandRequest.image());
            brandRepository.save(brand2);
            String message = String.format("Brand with id: %s successfully", brandRequest.brandName() + "saved...");
            log.info(message);
            return SimpleResponse.builder()
                    .httpStatus(HttpStatus.OK)
                    .message(message)
                    .build();
        } else {
            log.info(String.format("Brand with name: %s exists" ,brandRequest.brandName()));
            throw new InvalidNameException(String.format("Brand with name: %s exists", brandRequest.brandName()));

        }

    }

    @Override
    public BrandResponse getBrandByName(String name) {
        return null;
    }

    @Override
    public SimpleResponse updateBrand(Long BrandId, BrandRequest brandRequest) {
        return null;
    }

    @Override
    public SimpleResponse deleteBrand(Long brandId) {
        return null;
    }
}
