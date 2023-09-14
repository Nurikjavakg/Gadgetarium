package peaksoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import peaksoft.entities.Brand;
@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {
    Brand getBrandByBrandName(String brandName);
}