package peaksoft.services;

import peaksoft.dto.brand.BrandRequest;
import peaksoft.dto.brand.BrandResponse;
import peaksoft.dto.simple.SimpleResponse;
import peaksoft.entities.Brand;

import java.util.List;

public interface BrandService {
    List<Brand> getAllBrands();
    SimpleResponse saveBrand(BrandRequest brandRequest);
    BrandResponse getBrandByName(String name);
    SimpleResponse updateBrand(Long BrandId, BrandRequest brandRequest);
    SimpleResponse deleteBrand(Long brandId);

}
