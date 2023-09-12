package peaksoft.dto.brand;

public record BrandRequest(
        Long id,
        String brandName,
        String image
) {
}
